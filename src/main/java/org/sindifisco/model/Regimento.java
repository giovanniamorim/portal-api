package org.sindifisco.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doc_regimento")
public class Regimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dataAprovacao;
    private String descricao;
    private String fileUrl;

    public Regimento() {
    }

    public Regimento(Long id, Date dataAprovacao, String descricao, String fileUrl) {
        this.id = id;
        this.dataAprovacao = dataAprovacao;
        this.descricao = descricao;
        this.fileUrl = fileUrl;
    }

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

}