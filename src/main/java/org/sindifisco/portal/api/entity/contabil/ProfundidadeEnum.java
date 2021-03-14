package org.sindifisco.portal.api.entity.contabil;

public enum ProfundidadeEnum {
	
	A("Analítica"),
	S("Sintética");

	private String descricao;
	
	private ProfundidadeEnum(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}

}
