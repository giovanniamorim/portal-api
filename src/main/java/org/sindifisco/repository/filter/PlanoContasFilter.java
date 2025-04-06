package org.sindifisco.repository.filter;

import java.util.Date;

public class PlanoContasFilter {

	private String codigo;
	private String nome;
	private String profundidade;
	private String tipoLancamento;

	public PlanoContasFilter() {}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	@Override
	public String toString() {
		return "PlanoContasFilter{" +
				"codigo='" + codigo + '\'' +
				", nome='" + nome + '\'' +
				", profundidade='" + profundidade + '\'' +
				", tipoLancamento='" + tipoLancamento + '\'' +
				'}';
	}
}

