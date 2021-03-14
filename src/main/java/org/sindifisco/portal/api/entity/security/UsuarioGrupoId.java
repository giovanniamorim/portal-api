package org.sindifisco.portal.api.entity.security;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class UsuarioGrupoId implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "id_grupo")
	private Grupo grupo;
}

