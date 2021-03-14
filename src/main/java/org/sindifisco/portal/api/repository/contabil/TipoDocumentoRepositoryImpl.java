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

import org.sindifisco.portal.api.entity.contabil.TipoDocumento;
import org.sindifisco.portal.api.entity.contabil.TipoDocumento_;
import org.sindifisco.portal.api.repository.filter.TipoDocumentoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class TipoDocumentoRepositoryImpl implements TipoDocumentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<TipoDocumento> filtrar(TipoDocumentoFilter tipoDocumentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<TipoDocumento> criteria = builder.createQuery(TipoDocumento.class);
		Root<TipoDocumento> root = criteria.from(TipoDocumento.class);
		
		Predicate[] predicates = criarRestricoes(tipoDocumentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<TipoDocumento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(tipoDocumentoFilter));
	}
	
	
	private Predicate[] criarRestricoes(TipoDocumentoFilter tipoDocumentoFilter, CriteriaBuilder builder,
			Root<TipoDocumento> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (tipoDocumentoFilter.getDescricao()  != null) {
			predicates.add(
					builder.equal(root.get(TipoDocumento_.descricao), tipoDocumentoFilter.getDescricao()));
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
	
	private Long total(TipoDocumentoFilter tipoDocumentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<TipoDocumento> root = criteria.from(TipoDocumento.class);
		
		Predicate[] predicates = criarRestricoes(tipoDocumentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
