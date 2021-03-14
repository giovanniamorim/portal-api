package org.sindifisco.portal.api.repository.contabil;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.sindifisco.portal.api.entity.contabil.PlanoConta;
import org.sindifisco.portal.api.entity.contabil.PlanoConta_;
import org.sindifisco.portal.api.repository.filter.PlanoContaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class PlanoContaRepositoryImpl implements PlanoContaRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<PlanoConta> filtrar(PlanoContaFilter planoContaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PlanoConta> criteria = builder.createQuery(PlanoConta.class);
		Root<PlanoConta> root = criteria.from(PlanoConta.class);
		
		Predicate[] predicates = criarRestricoes(planoContaFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<PlanoConta> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(planoContaFilter));
	}
	
	
	private Predicate[] criarRestricoes(PlanoContaFilter planoContaFilter, CriteriaBuilder builder,
			Root<PlanoConta> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (planoContaFilter.getDescricao()  != null) {
			predicates.add(
					builder.equal(root.get(PlanoConta_.descricao), planoContaFilter.getDescricao()));
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
	
	private Long total(PlanoContaFilter planoContaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<PlanoConta> root = criteria.from(PlanoConta.class);
		
		Predicate[] predicates = criarRestricoes(planoContaFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
