package org.sindifisco.portal.api.entity.security;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sec_usuario")
@Data
@NoArgsConstructor
public class Usuario {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="O nome é obrigatório.")
	private String nome;
	
	@NotEmpty(message="O email é obrigatório.")
	@Email(message = "Email inválido")
	@Column(unique = true)
	private String email;
	
	@Column(name="senha")
	@NotEmpty(message="A senha é obrigatória")
	private String senha;
	
	@Transient
	private String confirmacaoSenha;
	
	@Column
	@Size(min=1, message="Selecione pelo menos um grupo.")
	@ManyToMany
	@JoinTable(name="sec_usuario_grupo", joinColumns = @JoinColumn(name = "id_usuario"), 
					inverseJoinColumns= @JoinColumn(name="id_grupo"))
	private List<Grupo> grupos;
	
	private Boolean ativo;
	
	@Column
	@Lob
	private byte[] avatar;

}
