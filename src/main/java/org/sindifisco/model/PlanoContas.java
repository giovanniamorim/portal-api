package org.sindifisco.model;

import javax.persistence.*;

@Entity
@Table(name = "ctb_plano_conta")
public class PlanoContas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;

    private String nome;

    private String profundidade;

    private String tipoLancamento;

    @ManyToOne
    @JoinColumn(name = "conta_pai_id")
    private PlanoContas contaPai;

    public PlanoContas() {
    }

    public PlanoContas(Long id, String codigo, String nome, String profundidade, String tipoLancamento, PlanoContas contaPai) {
        this.id = id;
        this.codigo = codigo;
        this.nome = nome;
        this.profundidade = profundidade;
        this.tipoLancamento = tipoLancamento;
        this.contaPai = contaPai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(String profundidade) {
        this.profundidade = profundidade;
    }

    public String getTipoLancamento() {
        return tipoLancamento;
    }

    public void setTipoLancamento(String tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public PlanoContas getContaPai() {
        return contaPai;
    }

    public void setContaPai(PlanoContas contaPai) {
        this.contaPai = contaPai;
    }

}