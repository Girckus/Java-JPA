package com.clubedolivro.modelo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "LIVROS")
public class Livro {

	@Id
	@Column(name = "ID_LIVRO")
	private Integer id;

	@Column(name = "NOME_LIVRO")
	private String nome;

	@Column(name = "AUTORIA")
	private String autoria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_EDITORA", nullable=false)
	private Editora editora;
	
	@Column(name = "CATEGORIA")
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@Column(name = "PRECO")
	private BigDecimal preco;
	
	@OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
	private Estoque estoque;
	
	@OneToMany(mappedBy = "livro")
	private List<Venda> vendas = new ArrayList<>();
	
	public Livro() {}
	
	public Livro(Integer id, String nome, String autoria, Editora editora, Categoria categoria, BigDecimal preco, Estoque estoque) {
		this.id = id;
		this.nome = nome;
		this.autoria = autoria;
		this.editora = editora;
		this.categoria = categoria;
		this.preco = preco;
		this.estoque = estoque;
	}
	
	public void addVenda(Venda venda) {
		venda.setLivro(this);
		this.vendas.add(venda);
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

	public String getAutoria() {
		return autoria;
	}

	public void setAutoria(String autoria) {
		this.autoria = autoria;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}
	
	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	@Override
	public String toString() {
		return "Livro [id=" + id + ", nome=" + nome + ", autoria=" + autoria + ", editora=" + editora + ", categoria="
				+ categoria + ", preco=" + preco + ", estoque=" + estoque + ", vendas=" + vendas + "]";
	}
	
}