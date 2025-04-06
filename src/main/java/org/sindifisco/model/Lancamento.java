package org.sindifisco.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "ctb_lancamento")
public class Lancamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoLancamento;

    @Temporal(TemporalType.DATE)
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

    // Construtor padrão
    public Lancamento() {
    }

    // Construtor completo
    public Lancamento(Long id, String tipoLancamento, Date dataLancamento, String planoConta, Double valor,
                      String modoPagamento, String tipoComprovante, String numDoc, String numCheque,
                      String obs, String supCaixa, Integer anoExercicio, String fileUrl,
                      LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.tipoLancamento = tipoLancamento;
        this.dataLancamento = dataLancamento;
        this.planoConta = planoConta;
        this.valor = valor;
        this.modoPagamento = modoPagamento;
        this.tipoComprovante = tipoComprovante;
        this.numDoc = numDoc;
        this.numCheque = numCheque;
        this.obs = obs;
        this.supCaixa = supCaixa;
        this.anoExercicio = anoExercicio;
        this.fileUrl = fileUrl;
        this.created = created;
        this.updated = updated;
    }

    // Getters e Setters

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

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public String getPlanoConta() {
        return planoConta;
    }

    public void setPlanoConta(String planoConta) {
        this.planoConta = planoConta;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
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

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }
}
