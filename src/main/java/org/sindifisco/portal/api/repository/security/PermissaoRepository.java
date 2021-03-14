package org.sindifisco.portal.api.repository.security;


import org.sindifisco.portal.api.entity.security.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissaoRepository extends JpaRepository<Permissao, Integer>, PermissaoRepositoryQuery {

}

