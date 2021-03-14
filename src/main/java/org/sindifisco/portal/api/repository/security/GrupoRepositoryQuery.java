package org.sindifisco.portal.api.repository.security;

import org.sindifisco.portal.api.entity.security.Grupo;
import org.sindifisco.portal.api.repository.filter.GrupoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GrupoRepositoryQuery {
	
	public Page<Grupo> filtrar(GrupoFilter grupoFilter, Pageable pageable);

}
 