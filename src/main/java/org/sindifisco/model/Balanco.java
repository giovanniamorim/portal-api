package org.sindifisco.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ctb_balanco")
public class Balanco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long ano;
    private String mes;
    private String descricao;
    private String fileUrl;

    // Construtor padrão
    public Balanco() {
    }

    // Construtor completo
    public Balanco(Long id, Long ano, String mes, String descricao, String fileUrl) {
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

    // equals e hashCode (baseado no id)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Balanco)) return false;
        Balanco balanco = (Balanco) o;
        return Objects.equals(id, balanco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "Balanco{" +
                "id=" + id +
                ", ano=" + ano +
                ", mes='" + mes + '\'' +
                ", descricao='" + descricao + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
