package org.sindifisco.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "jur_evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Date data;
    private String descricao;
    private String fileUrl;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "processo_id", nullable = false)
    private Processo processo;

    // Construtor padrão
    public Evento() {
    }

    // Construtor completo
    public Evento(Long id, String nome, Date data, String descricao, String fileUrl, Processo processo) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
        this.fileUrl = fileUrl;
        this.processo = processo;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
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

    public Processo getProcesso() {
        return processo;
    }

    public void setProcesso(Processo processo) {
        this.processo = processo;
    }

    // equals e hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento)) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // toString sem o processo (para evitar recursão infinita)
    @Override
    public String toString() {
        return "Evento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", data=" + data +
                ", descricao='" + descricao + '\'' +
                ", fileUrl='" + fileUrl + '\'' +
                '}';
    }
}
