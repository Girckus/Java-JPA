package com.clubedolivro.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ESTOQUE")
public class Estoque {
	
	@Id
	@Column(name = "ID_LIVRO")
	private Integer id;
	
	@OneToOne(mappedBy = "estoque", fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_LIVRO")
	@MapsId
	private Livro livro;
	
	@Column(name = "QTD_ESTOQUE")
	private Integer estoque;

	public Estoque() {}
	
	public Estoque(Integer id, Integer estoque) {
		this.id = id;
		this.estoque = estoque;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getEstoque() {
		return estoque;
	}

	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}

	@Override
	public String toString() {
		return "Estoque [estoque=" + estoque + "]";
	}
	
}