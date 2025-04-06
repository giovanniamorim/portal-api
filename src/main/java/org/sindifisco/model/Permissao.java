package org.sindifisco.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "permissao")
public class Permissao {

	@Id
	private Long codigo;

	private String descricao;

	// Construtor padrão
	public Permissao() {}

	// Construtor completo
	public Permissao(Long codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Permissao)) return false;
		Permissao that = (Permissao) o;
		return Objects.equals(codigo, that.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public String toString() {
		return "Permissao{" +
				"codigo=" + codigo +
				", descricao='" + descricao + '\'' +
				'}';
	}
}
