package com.clubedolivro.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VENDEDORES")
public class Vendedor {

	@Id
	@Column(name = "ID_VENDEDOR")
	private Integer id;
	
	@Embedded
	private DadosPessoais dadosPessoais;

	@OneToMany(mappedBy = "vendedor")
	private List<Venda> vendas = new ArrayList<>();
	
	public Vendedor() {
		
	}
	
	public Vendedor(Integer id, String nome, String cpf) {
		this.id = id;
		this.dadosPessoais = new DadosPessoais(nome, cpf);
	}

	public void addVenda(Venda venda) {
		venda.setVendedor(this);
		this.vendas.add(venda);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return dadosPessoais.getNome();
	}
	
	public String getCpf() {
		return dadosPessoais.getCpf();
	}

	public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", nome=" + getNome() + ", cpf=" + getCpf() +  "]";
	}
	
}