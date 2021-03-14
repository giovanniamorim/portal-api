package org.sindifisco.portal.api.repository.security;

import java.util.Optional;

import org.sindifisco.portal.api.entity.security.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, UsuarioRepositoryQuery {
	
	public Optional<Usuario> findByEmail(String email);
	public Optional<Usuario> findById(Integer id);
	public Optional<Usuario> findByNomeContaining(String nome);

}
