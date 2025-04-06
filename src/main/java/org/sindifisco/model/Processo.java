package org.sindifisco.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "jur_processo")
public class Processo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String exequente;
    private String executado;
    private String juizo;
    private String juiz;
    private String assunto;
    private Double valor;

    @JsonManagedReference
    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Evento> eventos;

    public Processo() {
    }

    public Processo(Long id, String numero, String exequente, String executado, String juizo, String juiz,
                    String assunto, Double valor, List<Evento> eventos) {
        this.id = id;
        this.numero = numero;
        this.exequente = exequente;
        this.executado = executado;
        this.juizo = juizo;
        this.juiz = juiz;
        this.assunto = assunto;
        this.valor = valor;
        this.eventos = eventos;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getExequente() {
        return exequente;
    }

    public void setExequente(String exequente) {
        this.exequente = exequente;
    }

    public String getExecutado() {
        return executado;
    }

    public void setExecutado(String executado) {
        this.executado = executado;
    }

    public String getJuizo() {
        return juizo;
    }

    public void setJuizo(String juizo) {
        this.juizo = juizo;
    }

    public String getJuiz() {
        return juiz;
    }

    public void setJuiz(String juiz) {
        this.juiz = juiz;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(List<Evento> eventos) {
        this.eventos = eventos;
    }
}
