package org.sindifisco.portal.api.repository.security;

import java.util.List;
import java.util.Optional;

import org.sindifisco.portal.api.entity.security.Usuario;
import org.sindifisco.portal.api.repository.filter.UsuarioFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioRepositoryQuery {
	
	public Page<Usuario> filtrar(UsuarioFilter usuarioFilter, Pageable pageable);

	public Optional<Usuario> porEmailEAtivo(String email);
	
	public List<String> permissoes(Usuario usuario);
	
	public Usuario findByGrupo(Integer id);

}
