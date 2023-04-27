package com.clubedolivro.modelo;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DadosPessoais {

	@Column(name = "NOME_VENDEDOR")
	private String nome;
	
	@Column(name = "CPF_VENDEDOR")
	private String cpf;
	
	public DadosPessoais() {
		
	}
	
	public DadosPessoais(String nome, String cpf) {
		this.nome = nome;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@Override
	public String toString() {
		return "DadosPessoais [nome=" + nome + ", cpf=" + cpf + "]";
	}
	
}