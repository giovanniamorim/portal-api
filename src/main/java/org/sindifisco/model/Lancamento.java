package org.sindifisco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ctb_lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoLancamento;
    private Date dataLancamento;

    @NotNull(message = "O campo Conta é obrigatório")
    @OneToOne
    private PlanoContas planoConta;
    private Double valor;
    private String modoPagamento;
    private String tipoComprovante;
    private String obs;
    private String supCaixa;
    private String fileUrl;
}