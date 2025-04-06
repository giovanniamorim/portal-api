package org.sindifisco.repository.filter;

import java.util.Date;

public class AcordoColetivoFilter {

	private Date dataAprovacao;
	private String descricao;

	public AcordoColetivoFilter() {
	}

	public Date getDataAprovacao() {
		return dataAprovacao;
	}

	public void setDataAprovacao(Date dataAprovacao) {
		this.dataAprovacao = dataAprovacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
