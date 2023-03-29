package org.sindifisco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
    private String planoConta;
    private Double valor;
    private String modoPagamento;
    private String tipoComprovante;
    private String numDoc;
    private String numCheque;
    private String obs;
    private String supCaixa;
    private Integer anoExercicio;
    private String fileUrl;

    private LocalDateTime created;
    private LocalDateTime updated;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updated = LocalDateTime.now();
    }



}