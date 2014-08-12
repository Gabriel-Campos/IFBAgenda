package br.com.ifbasamaro.cadastro.modelo;

import java.io.Serializable;

public class Aluno implements Serializable{
	
	private Long id;
	private String nome;
	private String endereco;
	private String telefone;
	private String foto;
	
	
	@Override
	public String toString() {
		return nome;
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

	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	
}