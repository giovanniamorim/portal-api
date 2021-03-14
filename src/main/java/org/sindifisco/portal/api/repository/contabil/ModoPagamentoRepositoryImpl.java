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

import org.sindifisco.portal.api.entity.contabil.ModoPagamento;
import org.sindifisco.portal.api.entity.contabil.ModoPagamento_;
import org.sindifisco.portal.api.repository.filter.ModoPagamentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ModoPagamentoRepositoryImpl implements ModoPagamentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<ModoPagamento> filtrar(ModoPagamentoFilter modoPagamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ModoPagamento> criteria = builder.createQuery(ModoPagamento.class);
		Root<ModoPagamento> root = criteria.from(ModoPagamento.class);
		
		Predicate[] predicates = criarRestricoes(modoPagamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ModoPagamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(modoPagamentoFilter));
	}
	
	
	private Predicate[] criarRestricoes(ModoPagamentoFilter modoPagamentoFilter, CriteriaBuilder builder,
			Root<ModoPagamento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (modoPagamentoFilter.getDescricao()  != null) {
			predicates.add(
					builder.equal(root.get(ModoPagamento_.descricao), modoPagamentoFilter.getDescricao()));
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
	
	private Long total(ModoPagamentoFilter modoPagamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ModoPagamento> root = criteria.from(ModoPagamento.class);
		
		Predicate[] predicates = criarRestricoes(modoPagamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
