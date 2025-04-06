package org.sindifisco.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "doc_acordo")
public class AcordoColetivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dataAprovacao;
    private String descricao;
    private String fileUrl;

    // Construtor sem argumentos
    public AcordoColetivo() {
    }

    // Construtor com todos os argumentos
    public AcordoColetivo(Long id, Date dataAprovacao, String descricao, String fileUrl) {
        this.id = id;
        this.dataAprovacao = dataAprovacao;
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

    public Date getDataAprovacao() {
        return dataAprovacao;
    }

    public void setDataAprovacao(Date dataAprovacao) {
        this.dataAprovacao = dataAprovacao;
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

    // equals e hashCode (baseado no id, que é comum em entidades JPA)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AcordoColetivo)) return false;
        AcordoColetivo that = (AcordoColetivo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString
    @Override
    public String toString() {
        return "AcordoColetivo{" +
                "id=" + id +
                ", dataAprovacao=" + dataAprovacao +
                ", descricao='" + descricao + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
