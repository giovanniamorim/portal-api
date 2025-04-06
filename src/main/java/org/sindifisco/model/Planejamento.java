package org.sindifisco.model;

import javax.persistence.*;

@Entity
@Table(name = "orc_planejamento")
public class Planejamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long ano;

    private String descricao;

    private String fileUrl;

    // Construtor padrão
    public Planejamento() {}

    // Construtor completo
    public Planejamento(Long id, Long ano, String descricao, String fileUrl) {
        this.id = id;
        this.ano = ano;
        this.descricao = descricao;
        this.fileUrl = fileUrl;
    }

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

    @Override
    public String toString() {
        return "Planejamento{" +
                "id=" + id +
                ", ano=" + ano +
                ", descricao='" + descricao + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Planejamento)) return false;

        Planejamento that = (Planejamento) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
