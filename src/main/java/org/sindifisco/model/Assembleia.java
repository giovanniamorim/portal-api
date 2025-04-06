package org.sindifisco.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "doc_assembleia")
public class Assembleia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date data;
    private String tipo;
    private String assunto;
    private String comentario;
    private String fileUrl;

    public Assembleia(Long id, Date data, String tipo, String assunto, String comentario, String fileUrl) {
        this.id = id;
        this.data = data;
        this.tipo = tipo;
        this.assunto = assunto;
        this.comentario = comentario;
        this.fileUrl = fileUrl;
    }

    public Assembleia() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}

