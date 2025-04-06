package org.sindifisco.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ctb_inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataAquisicao;
    private String departamento;
    private String numero;
    private Integer quant;
    private String descricao;
    private String estadoConservacao;
    private String fileUrl;

    // Construtor padrão
    public Inventario() {
    }

    // Construtor completo
    public Inventario(Long id, Date dataAquisicao, String departamento, String numero,
                      Integer quant, String descricao, String estadoConservacao, String fileUrl) {
        this.id = id;
        this.dataAquisicao = dataAquisicao;
        this.departamento = departamento;
        this.numero = numero;
        this.quant = quant;
        this.descricao = descricao;
        this.estadoConservacao = estadoConservacao;
        this.fileUrl = fileUrl;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(Date dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getQuant() {
        return quant;
    }

    public void setQuant(Integer quant) {
        this.quant = quant;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(String estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    // equals e hashCode baseados no ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventario)) return false;
        Inventario that = (Inventario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + id +
                ", dataAquisicao=" + dataAquisicao +
                ", departamento='" + departamento + '\'' +
                ", numero='" + numero + '\'' +
                ", quant=" + quant +
                ", descricao='" + descricao + '\'' +
                ", estadoConservacao='" + estadoConservacao + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
