package org.conefisco.repository.contabil.contrato;

import org.conefisco.model.Contrato;
import org.conefisco.repository.filter.ContratoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class ContratoRepositoryImpl implements ContratoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;


	@Override
	public Page<Contrato> filtrar(ContratoFilter contratoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Contrato> criteria = builder.createQuery(Contrato.class);
		
		Root<Contrato> root = criteria.from(Contrato.class);
		
		//cria as restrições
		Predicate[] predicates = criarRestricoes(contratoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Contrato> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(contratoFilter));
	}


	@SuppressWarnings("deprecation")
	private Predicate[] criarRestricoes(ContratoFilter contratoFilter, CriteriaBuilder builder, Root<Contrato> root) {
		
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(contratoFilter.getPrestador())) {
			predicates.add(builder.equal(builder.lower(root.get("data")), contratoFilter.getPrestador()));
		}


		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(ContratoFilter ContratoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Contrato> roor = criteria.from(Contrato.class);
		
		Predicate[] predicates = criarRestricoes(ContratoFilter, builder, roor);
		criteria.where(predicates);
		
		criteria.select(builder.count(roor));
		
		return manager.createQuery(criteria).getSingleResult();
	}


}
