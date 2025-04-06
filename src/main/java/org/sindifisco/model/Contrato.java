package org.sindifisco.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ctb_contrato")
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prestador;
    private String descServico;
    private Date dataInicial;
    private Date dataFinal;
    private String obs;
    private Double valor;
    private String file;

    // Construtor padrão
    public Contrato() {
    }

    // Construtor completo
    public Contrato(Long id, String prestador, String descServico, Date dataInicial, Date dataFinal, String obs, Double valor, String file) {
        this.id = id;
        this.prestador = prestador;
        this.descServico = descServico;
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        this.obs = obs;
        this.valor = valor;
        this.file = file;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrestador() {
        return prestador;
    }

    public void setPrestador(String prestador) {
        this.prestador = prestador;
    }

    public String getDescServico() {
        return descServico;
    }

    public void setDescServico(String descServico) {
        this.descServico = descServico;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    // equals e hashCode com base no ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contrato)) return false;
        Contrato contrato = (Contrato) o;
        return Objects.equals(id, contrato.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "Contrato{" +
                "id=" + id +
                ", prestador='" + prestador + '\'' +
                ", descServico='" + descServico + '\'' +
                ", dataInicial=" + dataInicial +
                ", dataFinal=" + dataFinal +
                ", obs='" + obs + '\'' +
                ", valor=" + valor +
                ", file='" + file + '\'' +
                '}';
    }
}
