package org.sindifisco.portal.api.repository.security;


import org.sindifisco.portal.api.entity.security.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GrupoRepository extends JpaRepository<Grupo, Integer>, GrupoRepositoryQuery {

}
