package org.sindifisco.portal.api.entity.security;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sec_usuario_grupo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioGrupo {
	
	@EmbeddedId
	private UsuarioGrupoId id;
	
	public UsuarioGrupoId getId() {
		return id;
	}
	
	

}

