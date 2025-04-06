package org.sindifisco.model;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Endereco {

	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cep;
	private String cidade;
	private String estado;

	// Construtor padrão
	public Endereco() {
	}

	// Construtor completo
	public Endereco(String logradouro, Integer numero, String complemento, String bairro, String cep, String cidade, String estado) {
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
	}

	// Getters e Setters
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	// equals e hashCode — importante para Embeddable
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Endereco)) return false;
		Endereco endereco = (Endereco) o;
		return Objects.equals(logradouro, endereco.logradouro) &&
				Objects.equals(numero, endereco.numero) &&
				Objects.equals(complemento, endereco.complemento) &&
				Objects.equals(bairro, endereco.bairro) &&
				Objects.equals(cep, endereco.cep) &&
				Objects.equals(cidade, endereco.cidade) &&
				Objects.equals(estado, endereco.estado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(logradouro, numero, complemento, bairro, cep, cidade, estado);
	}

	@Override
	public String toString() {
		return "Endereco{" +
				"logradouro='" + logradouro + '\'' +
				", numero=" + numero +
				", complemento='" + complemento + '\'' +
				", bairro='" + bairro + '\'' +
				", cep='" + cep + '\'' +
				", cidade='" + cidade + '\'' +
				", estado='" + estado + '\'' +
				'}';
	}
}
