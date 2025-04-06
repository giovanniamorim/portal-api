package org.sindifisco.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;

	private String nome;
	private String email;
	private String senha;
	private String celular;

	@NotNull(message = "O campo CPF é obrigatório")
	@CPF(message = "CPF invalido")
	private String cpf;

	private String rg;
	private String rgOrgaoExp;
	private String matricula;
	private String situacao;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_permissao",
			joinColumns = @JoinColumn(name = "codigo_usuario"),
			inverseJoinColumns = @JoinColumn(name = "codigo_permissao"))
	private List<Permissao> permissoes;

	public Usuario() {}

	public Usuario(Long codigo, String nome, String email, String senha, String celular, String cpf,
				   String rg, String rgOrgaoExp, String matricula, String situacao, List<Permissao> permissoes) {
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.celular = celular;
		this.cpf = cpf;
		this.rg = rg;
		this.rgOrgaoExp = rgOrgaoExp;
		this.matricula = matricula;
		this.situacao = situacao;
		this.permissoes = permissoes;
	}

	// Getters e Setters

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRgOrgaoExp() {
		return rgOrgaoExp;
	}

	public void setRgOrgaoExp(String rgOrgaoExp) {
		this.rgOrgaoExp = rgOrgaoExp;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public List<Permissao> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<Permissao> permissoes) {
		this.permissoes = permissoes;
	}

	// equals e hashCode baseados no campo "codigo"

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Usuario)) return false;
		Usuario usuario = (Usuario) o;
		return Objects.equals(codigo, usuario.codigo);
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	// toString()

	@Override
	public String toString() {
		return "Usuario{" +
				"codigo=" + codigo +
				", nome='" + nome + '\'' +
				", email='" + email + '\'' +
				", cpf='" + cpf + '\'' +
				", situacao='" + situacao + '\'' +
				'}';
	}
}
