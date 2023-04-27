package com.clubedolivro.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.clubedolivro.dto.RelatorioLivrosVendidosDTO;
import com.clubedolivro.dto.RelatorioVendasVendedorDTO;
import com.clubedolivro.modelo.Venda;
import com.clubedolivro.modelo.VendaId;

public class VendaDAO {

	private EntityManager em;

	public VendaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Venda venda) {
		em.persist(venda);
	}
	
	public Venda obter(VendaId id) {
		return em.find(Venda.class, id);
	}
	
	public List<RelatorioVendasVendedorDTO> relatorioVendasPorVendedor() {
		String jpql = """
						SELECT new com.clubedolivro.dto.RelatorioVendasVendedorDTO(vendedor.dadosPessoais.nome, SUM(venda.quantidade) AS quantidadeVendas, MAX(venda.date))
						FROM Venda venda
						JOIN venda.vendedor vendedor
						GROUP BY vendedor.dadosPessoais.nome
						ORDER BY quantidadeVendas DESC
					  """;
		return em.createQuery(jpql, RelatorioVendasVendedorDTO.class)
				 .getResultList();
	}
	
	public List<RelatorioLivrosVendidosDTO> relatorioLivrosVendidos() {
		return em.createNamedQuery("Venda.relatorioLivrosVendidos", RelatorioLivrosVendidosDTO.class)
				 .getResultList();
	}

}