package org.sindifisco.repository.filter;

import java.time.LocalDate;


import org.springframework.format.annotation.DateTimeFormat;

public class LancamentoFilter {

	private Long id;
	private String tipoLancamento;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataLancamentoDe;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataLancamentoAte;
	private String planoConta;
	private String modoPagamento;
	private String tipoComprovante;
	private String numDoc;
	private String numCheque;
	private String supCaixa;
	private Integer anoExercicio;
	private Double valorMin;
	private Double valorMax;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	public LocalDate getDataLancamentoDe() {
		return dataLancamentoDe;
	}

	public void setDataLancamentoDe(LocalDate dataLancamentoDe) {
		this.dataLancamentoDe = dataLancamentoDe;
	}

	public LocalDate getDataLancamentoAte() {
		return dataLancamentoAte;
	}

	public void setDataLancamentoAte(LocalDate dataLancamentoAte) {
		this.dataLancamentoAte = dataLancamentoAte;
	}

	public String getPlanoConta() {
		return planoConta;
	}

	public void setPlanoConta(String planoConta) {
		this.planoConta = planoConta;
	}

	public String getModoPagamento() {
		return modoPagamento;
	}

	public void setModoPagamento(String modoPagamento) {
		this.modoPagamento = modoPagamento;
	}

	public String getTipoComprovante() {
		return tipoComprovante;
	}

	public void setTipoComprovante(String tipoComprovante) {
		this.tipoComprovante = tipoComprovante;
	}

	public String getNumDoc() {
		return numDoc;
	}

	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}

	public String getNumCheque() {
		return numCheque;
	}

	public void setNumCheque(String numCheque) {
		this.numCheque = numCheque;
	}

	public String getSupCaixa() {
		return supCaixa;
	}

	public void setSupCaixa(String supCaixa) {
		this.supCaixa = supCaixa;
	}

	public Integer getAnoExercicio() {
		return anoExercicio;
	}

	public void setAnoExercicio(Integer anoExercicio) {
		this.anoExercicio = anoExercicio;
	}

	public Double getValorMin() {
		return valorMin;
	}

	public void setValorMin(Double valorMin) {
		this.valorMin = valorMin;
	}

	public Double getValorMax() {
		return valorMax;
	}

	public void setValorMax(Double valorMax) {
		this.valorMax = valorMax;
	}
}
