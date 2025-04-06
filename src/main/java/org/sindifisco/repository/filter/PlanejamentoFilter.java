package org.sindifisco.repository.filter;

public class PlanejamentoFilter {

	private Long ano;
	private String descricao;

	public PlanejamentoFilter() {
	}

	public Long getAno() {
		return ano;
	}

	public void setAno(Long ano) {
		this.ano = ano;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
