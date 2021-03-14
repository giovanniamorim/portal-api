package org.sindifisco.portal.api.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.sindifisco.portal.api.entity.contabil.ModoPagamento;
import org.sindifisco.portal.api.entity.contabil.PlanoConta;
import org.sindifisco.portal.api.entity.contabil.TipoDocumento;
import org.sindifisco.portal.api.entity.contabil.TipoLancamentoEnum;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LancamentoFilter {
	
	private TipoLancamentoEnum tipo;
	private PlanoConta conta;
	private BigDecimal valor;
	private ModoPagamento modoPagamento;
	private TipoDocumento tipoDocumento;
	private String numCheque;
	private String observacoes;
	private String anoExercicio;
	private String descricao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamentoDe;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataPagamentoAte;


}
