package org.sindifisco.portal.api.repository.security;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.sindifisco.portal.api.entity.security.Grupo;
import org.sindifisco.portal.api.entity.security.Grupo_;
import org.sindifisco.portal.api.repository.filter.GrupoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class GrupoRepositoryImpl implements GrupoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Grupo> filtrar(GrupoFilter grupoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Grupo> criteria = builder.createQuery(Grupo.class);
		Root<Grupo> root = criteria.from(Grupo.class);
		
		Predicate[] predicates = criarRestricoes(grupoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Grupo> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(grupoFilter));
	}
	
	
	private Predicate[] criarRestricoes(GrupoFilter grupoFilter, CriteriaBuilder builder,
			Root<Grupo> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (grupoFilter.getDescricao()  != null) {
			predicates.add(
					builder.equal(root.get(Grupo_.descricao), grupoFilter.getDescricao()));
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
	
	private Long total(GrupoFilter grupoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Grupo> root = criteria.from(Grupo.class);
		
		Predicate[] predicates = criarRestricoes(grupoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
