package org.sindifisco.portal.api.entity.contabil;

public enum TipoLancamentoEnum {

	R("Receita"),
	D("Depesa");

	private String descricao;
	
	private TipoLancamentoEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
