package com.clubedolivro;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.clubedolivro.dao.EditoraDAO;
import com.clubedolivro.dao.LivroDAO;
import com.clubedolivro.dao.VendaDAO;
import com.clubedolivro.dao.VendedorDAO;
import com.clubedolivro.modelo.Categoria;
import com.clubedolivro.modelo.Editora;
import com.clubedolivro.modelo.Livro;
import com.clubedolivro.modelo.Venda;
import com.clubedolivro.modelo.VendaId;
import com.clubedolivro.modelo.Vendedor;
import com.clubedolivro.util.JPAUtil;

public class Application {

	public static void main(String[] args) {
		Editora novaEditora = new Editora(14, "Editora Teste");
		Livro novoLivro = new Livro(14, "Livro Teste", "Autor Teste", novaEditora, Categoria.BIOGRAFIA, new BigDecimal(100), null);
		
		EntityManager em = JPAUtil.getEntityManager();
		LivroDAO livroDAO = new LivroDAO(em);
		EditoraDAO editoraDAO = new EditoraDAO(em);
		VendedorDAO vendedorDAO = new VendedorDAO(em);
		VendaDAO vendaDAO = new VendaDAO(em);
		
		em.getTransaction().begin();

		editoraDAO.cadastrar(novaEditora);
		livroDAO.cadastrar(novoLivro);
		
		System.out.println();
		livroDAO.listar().forEach(l -> System.out.println(l.toString()));
		
		em.flush();
		em.clear();
		
		System.out.println();
		livroDAO.listar().forEach(l -> System.out.println(l.toString()));
		
		em.clear();
		novoLivro = em.merge(novoLivro);
		novoLivro.setAutoria("Autor Teste 2");
		em.flush();
		
		System.out.println();
		livroDAO.listar().forEach(l -> System.out.println(l.toString()));
		
		Livro livroRemover = livroDAO.obter(14);
		livroDAO.remover(livroRemover);
		
		Editora EditoraRemover = editoraDAO.obter(14);
		editoraDAO.remover(EditoraRemover);
		
		System.out.println();
		livroDAO.listar().forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		livroDAO.buscarPorNome("Mauricio - A História Que Não Está no Gibi").forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		livroDAO.buscarPorCategoria(Categoria.BIOGRAFIA).forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		livroDAO.buscarPorEditora(editoraDAO.obter(10)).forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		System.out.println(livroDAO.buscarPrecoDoLivroComNome("Mauricio - A História Que Não Está no Gibi"));
		
		Livro livroVenda = livroDAO.obter(10);
		Vendedor vendedorVenda = vendedorDAO.obter(1);
		VendaId vendaId1 = new VendaId(19, vendedorVenda.getId());
		Venda venda1 = new Venda(vendaId1, vendedorVenda, livroVenda, 2);
		
		vendedorVenda = vendedorDAO.obter(2);
		VendaId vendaId2 = new VendaId(20, vendedorVenda.getId());
		Venda venda2 = new Venda(vendaId2, vendedorVenda, livroVenda, 3);
		
		vendaDAO.cadastrar(venda1);
		vendaDAO.cadastrar(venda2);
		
		em.getTransaction().commit();
		
		System.out.println(vendaDAO.obter(vendaId1));
		System.out.println(vendaDAO.obter(vendaId2));
		
		System.out.println();
		livroDAO.listar().forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		vendedorDAO.listar().forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		vendaDAO.relatorioVendasPorVendedor().forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		vendaDAO.relatorioLivrosVendidos().forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		List<Livro> filtro1 = livroDAO.filtrar(Optional.of("Mauricio - A História Que Não Está no Gibi"), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
		filtro1.forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		List<Livro> filtro2 = livroDAO.filtrar(Optional.empty(), Optional.of("Maurício de Sousa"), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty());
		filtro2.forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		List<Livro> filtro3 = livroDAO.filtrar(Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(Categoria.BIOGRAFIA), Optional.empty(), Optional.empty());
		filtro3.forEach(l -> System.out.println(l.toString()));
		
		System.out.println();
		BigDecimal preco = new BigDecimal(29.9).setScale(2, RoundingMode.HALF_UP);
		List<Livro> filtro4 = livroDAO.filtrar(Optional.empty(), Optional.empty(), Optional.empty(), Optional.of(Categoria.BIOGRAFIA), Optional.of(preco), Optional.empty());
		filtro4.forEach(l -> System.out.println(l.toString()));
		
		em.close();

		
		em = JPAUtil.getEntityManager();
		livroDAO = new LivroDAO(em);
		
		Livro livroMauricio = livroDAO.obterComEditora(10);
		
		em.close();
		
		System.out.println(livroMauricio.getEditora().getNome());
	}
	
}