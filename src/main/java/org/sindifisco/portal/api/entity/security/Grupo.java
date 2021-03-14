package org.sindifisco.portal.api.entity.security;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="sec_grupo")
@Data
@NoArgsConstructor
public class Grupo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	@ManyToMany
	@JoinTable(name="sec_grupo_permissao", joinColumns = @JoinColumn(name = "id_grupo"), 
	inverseJoinColumns= @JoinColumn(name="id_permissao"))
	public List<Permissao> permissoes;


}
