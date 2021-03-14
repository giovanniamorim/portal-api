package org.sindifisco.portal.api.repository.filter;

import org.sindifisco.portal.api.entity.security.Grupo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioFilter {
	
	private String nome;
	private String email;
	private Grupo grupos;
	private Boolean ativo;

}
