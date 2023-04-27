package com.clubedolivro.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EDITORAS")
public class Editora {

	@Id
	@Column(name = "ID_EDITORA")
	private Integer id;
	
	@Column(name = "NOME_EDITORA")
	private String nome;

	public Editora() {}
	
	public Editora(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Editora [id=" + id + ", nome=" + nome + "]";
	}
	
}