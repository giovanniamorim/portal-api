package org.sindifisco.model;

import javax.persistence.*;

@Entity
@Table(name = "orc_execucao")
public class Execucao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ano;
    private String mes;
    private String descricao;

    @Column(name = "file_url")
    private String fileUrl;

    // Construtor padrão
    public Execucao() {
    }

    // Construtor completo
    public Execucao(Long id, Long ano, String mes, String descricao, String fileUrl) {
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
}
