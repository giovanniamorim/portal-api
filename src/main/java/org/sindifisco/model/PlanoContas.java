package org.sindifisco.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Entity(name = "ctb_plano_conta")
public class PlanoContas {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private String profundidade;
    private String tipoLancamento;

    @ManyToOne
    public  PlanoContas contaPai;

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