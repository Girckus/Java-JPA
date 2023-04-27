package com.clubedolivro.modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "VENDAS")
@NamedQuery(name = "Venda.relatorioLivrosVendidos", 
		    query = """
		    		SELECT new com.clubedolivro.dto.RelatorioLivrosVendidosDTO(livro.nome, SUM(venda.quantidade) AS quantidadeVendas, MAX(venda.date))
					FROM Venda venda
					RIGHT JOIN venda.livro livro
					GROUP BY livro.nome
					ORDER BY quantidadeVendas DESC
		    """)
public class Venda {

	@EmbeddedId
	private VendaId id;
	
	@ManyToOne
	@JoinColumn(name="ID_VENDEDOR", nullable=false, insertable = false, updatable = false)
	private Vendedor vendedor;
	
	@ManyToOne
	@JoinColumn(name="ID_LIVRO", nullable=false)
	private Livro livro;
	
	@Column(name = "QTD_VENDIDA")
	private Integer quantidade;
	
	@Column(name = "DATA_VENDA")
	private LocalDate date;
	
	public Venda() {
		
	}

	public Venda(VendaId id, Vendedor vendedor, Livro livro, Integer quantidade) {
		this.id = id;
		this.quantidade = quantidade;
		this.date = LocalDate.now();
		
		vendedor.addVenda(this);
		livro.addVenda(this);
	}

	public VendaId getId() {
		return id;
	}

	public void setId(VendaId id) {
		this.id = id;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Venda [id=" + id + ", vendedor=" + vendedor + ", quantidade=" + quantidade + ", date=" + date + "]";
	}
	
}