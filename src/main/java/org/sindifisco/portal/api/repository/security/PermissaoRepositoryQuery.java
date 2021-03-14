package org.sindifisco.portal.api.repository.security;

import org.sindifisco.portal.api.entity.security.Permissao;
import org.sindifisco.portal.api.repository.filter.PermissaoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PermissaoRepositoryQuery {
	
	public Page<Permissao> filtrar(PermissaoFilter permissaoFilter, Pageable pageable);

}
 