package org.sindifisco.portal.api.repository.filter;

import org.sindifisco.portal.api.entity.contabil.PlanoConta;
import org.sindifisco.portal.api.entity.contabil.ProfundidadeEnum;
import org.sindifisco.portal.api.entity.contabil.TipoLancamentoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoContaFilter {
	
	private String conta;
	private String descricao;
	private TipoLancamentoEnum tipoLancamento;
	private PlanoConta contaPai;
	private ProfundidadeEnum profundidade;
	private String anoExercicio;

}
