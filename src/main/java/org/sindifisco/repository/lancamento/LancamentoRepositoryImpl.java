package org.conefisco.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
//
//import com.algaworks.model.LancamentoOld;
//import com.algaworks.repository.filter.LancamentoFilter;
//import com.algaworks.repository.projection.ResumoLancamento;
//
//public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
//
//	@PersistenceContext
//	private EntityManager manager;
//
//	@Override
//	public Page<LancamentoOld> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		CriteriaQuery<LancamentoOld> criteria = builder.createQuery(LancamentoOld.class);
//
//		Root<LancamentoOld> root = criteria.from(LancamentoOld.class);
//
//		//cria as restrições
//		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
//		criteria.where(predicates);
//
//		TypedQuery<LancamentoOld> query = manager.createQuery(criteria);
//		adicionarRestricoesDePaginacao(query, pageable);
//
//		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
//	}
//
//	@Override
//	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
//
//		Root<LancamentoOld> root = criteria.from(LancamentoOld.class);
//
//		criteria.select(builder.construct(ResumoLancamento.class
//				, root.get("codigo")
//				, root.get("descricao")
//				, root.get("dataVencimento")
//				, root.get("dataPagamento")
//				, root.get("valor")
//				, root.get("tipo")
//				, root.get("categoria").get("nome")
//				, root.get("pessoa").get("nome")));
//
//		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
//		criteria.where(predicates);
//
//		TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
//		adicionarRestricoesDePaginacao(query, pageable);
//
//		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
//	}
//
//	@SuppressWarnings("deprecation")
//	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<LancamentoOld> root) {
//
//		List<Predicate> predicates = new ArrayList<>();
//
//		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
//			predicates.add(builder.like(builder.lower(root.get("descricao")), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
//		}
//
//		if (lancamentoFilter.getDataVencimentoDe() != null) {
//			predicates.add(builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe()));
//		}
//
//		if (lancamentoFilter.getDataVencimentoAte() != null) {
//			predicates.add(builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoAte()));
//		}
//
//		return predicates.toArray(new Predicate[predicates.size()]);
//	}
//
//	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
//		int paginaAtual = pageable.getPageNumber();
//		int totalRegistrosPorPagina = pageable.getPageSize();
//		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;
//
//		query.setFirstResult(primeiroRegistroDaPagina);
//		query.setMaxResults(totalRegistrosPorPagina);
//	}
//
//	private Long total(LancamentoFilter lancamentoFilter) {
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
//		Root<LancamentoOld> roor = criteria.from(LancamentoOld.class);
//
//		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, roor);
//		criteria.where(predicates);
//
//		criteria.select(builder.count(roor));
//
//		return manager.createQuery(criteria).getSingleResult();
//	}
//}
