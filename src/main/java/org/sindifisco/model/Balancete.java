package org.sindifisco.model;

import javax.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ctb_balancete")
public class Balancete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ano;
    private String mes;
    private String descricao;
    private String fileUrl;

    // Construtor padrão (sem argumentos)
    public Balancete() {
    }

    // Construtor completo
    public Balancete(Long id, Long ano, String mes, String descricao, String fileUrl) {
        this.id = id;
        this.ano = ano;
        this.mes = mes;
        this.descricao = descricao;
        this.fileUrl = fileUrl;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAno() {
        return ano;
    }

    public void setAno(Long ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    // equals e hashCode usando o id (padrão para entidades)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balancete)) return false;
        Balancete that = (Balancete) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "Balancete{" +
                "id=" + id +
                ", ano=" + ano +
                ", mes='" + mes + '\'' +
                ", descricao='" + descricao + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}

