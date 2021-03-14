package org.sindifisco.portal.api.repository.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.sindifisco.portal.api.entity.security.Usuario;
import org.sindifisco.portal.api.entity.security.Usuario_;
import org.sindifisco.portal.api.repository.filter.UsuarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

public class UsuarioRepositoryImpl implements UsuarioRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Optional<Usuario> porEmailEAtivo(String email) {
		return manager
				.createQuery("from Usuario where lower(email) = lower(:email) and ativo = true", Usuario.class)
				.setParameter("email", email).getResultList().stream().findFirst();
	}
	
	@Override
	public List<String> permissoes(Usuario usuario) {
		return manager.createQuery(
				"select distinct p.nome from Usuario u inner join u.grupos g inner join g.permissoes p where u = :usuario", String.class)
				.setParameter("usuario", usuario)
				.getResultList();
	}
	
	@Override
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteria = builder.createQuery(Usuario.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Usuario> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(usuarioFilter));
	}
	
	@Transactional(readOnly = true)
	@Override
	public Usuario findByGrupo(Integer id) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Usuario.class);
		criteria.createAlias("grupos", "g", JoinType.LEFT_OUTER_JOIN);
		criteria.add(Restrictions.eq("id", id));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (Usuario) criteria.uniqueResult();
	}
	
	private Predicate[] criarRestricoes(UsuarioFilter usuarioFilter, CriteriaBuilder builder,
			Root<Usuario> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(usuarioFilter.getNome())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(Usuario_.nome)), "%" + usuarioFilter.getNome().toLowerCase() + "%"));
		}
		
		if(!StringUtils.isEmpty(usuarioFilter.getEmail())) {
			predicates.add(
					builder.like(root.get(Usuario_.email), usuarioFilter.getEmail().toLowerCase()));
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
	
	private Long total(UsuarioFilter usuarioFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Usuario> root = criteria.from(Usuario.class);
		
		Predicate[] predicates = criarRestricoes(usuarioFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
