package org.sindifisco.portal.api.entity.contabil;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="cont_planoconta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoConta implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	
	@NotEmpty(message="O conta é obrigatória.")
	private String conta;
	
	@NotEmpty(message="A descrição da conta é obrigatória.")
	private String descricao;	
	
	@Column(name = "tipo_lanc")
	@NotEmpty(message="O Tipo de Lançamento é obrigatório.")
	@Enumerated(EnumType.STRING)
	private TipoLancamentoEnum tipoLancamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conta_pai", referencedColumnName = "conta", updatable = true, insertable = true)
	private PlanoConta contaPai;
	
	@NotEmpty(message="A profundidades é obrigatória.")
	@Enumerated(EnumType.STRING)
	private ProfundidadeEnum profundidade;
	
	private String anoExercicio;

}
