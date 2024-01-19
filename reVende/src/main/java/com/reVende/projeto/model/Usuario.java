package com.reVende.projeto.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "O atributo nome é de preenchimento obrigatório!")
	@Size(max = 255, message = "Atributo nome recebe no máximo 255 caracteres")
	private String nome;
	
	@NotBlank(message = "O atributo email é de preenchimento obrigatório!")
	@Size(max = 255, message = "Atributo email recebe no máximo 255 caracteres")
	private String email;
	
	@NotBlank(message = "O atributo senha é de preenchimento obrigatório!")
	@Size(max = 255, message = "Atributo senha recebe no máximo 255 caracteres")
	private String senha;
	
	@Size(max =255, message = "O atributo foto deve conter no maximo 255 caracteres")
	private String foto;
	
	@Size(max = 14, message = "Atributo cpf recebe no máximo 14 caracteres")
	private String cpf;
	
	@Size(max = 18, message = "Atributo cnpj recebe no máximo 18 caracteres")
	private String cnpj;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Produto> produtos;
	
	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

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

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
