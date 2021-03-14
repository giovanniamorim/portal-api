package org.sindifisco.portal.api.entity.contabil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cont_lancamento")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lancamento implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@Column(name="tipo", nullable = false)
	@NotNull(message = "O Tipo de Lançamento é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoLancamentoEnum tipo;
	
	@Column(name = "data_pagamento")
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "O Data do documento é obrigatórioa")
	private LocalDate data;
	
	@NotNull(message = "O Plano de Contas é obrigatório")
	@OneToOne
	@JoinColumn(name = "planoconta_id")
	private PlanoConta planoConta;
	
	@Column
	@NotNull(message = "O Valor é obrigatório")
	private BigDecimal valor;
	
	@NotNull(message = "O modo de pagamento é obrigatório")
	@JoinColumn(name = "modopag_id")
	@OneToOne
	private ModoPagamento modoPagamento;
	
	@NotNull(message = "O tipo de documento é obrigatório")
	@OneToOne
	@JoinColumn(name = "tipodoc_id")
	private TipoDocumento tipoDocumento;
	
	@Column(name = "num_doc")
	private String numeroDocumento;
	
	@Column(name = "num_cheque")
	private String numeroCheque;
	
	private String observacoes;
	
	@Column(name = "ano_exercicio")
	@NotNull(message = "O Ano Exercício é obrigatório")
	private String anoExercicio;
	
	@Column
	@Lob
	private byte[] imgComprovante;

	@Column(name = "data_cadastro", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime  dataCadastro;
	
	@PrePersist
	public void dataCadastro() {
		setDataCadastro(LocalDateTime.now());
	}

}
