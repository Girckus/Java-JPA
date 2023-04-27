package com.clubedolivro.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.clubedolivro.modelo.Categoria;
import com.clubedolivro.modelo.Editora;
import com.clubedolivro.modelo.Estoque;
import com.clubedolivro.modelo.Livro;

public class LivroDAO {

	private EntityManager em;

	public LivroDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Livro livro) {
		em.persist(livro);
	}
	
	public void atualizar(Livro livro) {
		em.merge(livro);
	}
	
	public void remover(Livro livro) {
		livro = em.merge(livro);
		this.em.remove(livro);
	}
	
	public Livro obter(Integer id) {
		return em.find(Livro.class, id);
	}
	
	public Livro obterComEditora(Integer id) {
		String jpql = "SELECT l FROM Livro l JOIN FETCH l.editora WHERE l.id = :id";
        return em.createQuery(jpql, Livro.class)
        		 .setParameter("id", id) 
        		 .getSingleResult();
	}
	
	public List<Livro> listar() {
		String jpql = "SELECT l FROM Livro l";
        return em.createQuery(jpql, Livro.class).getResultList();
	}
	
	public List<Livro> buscarPorNome(String nome) {
		String jpql = "SELECT l FROM Livro l WHERE l.nome = :nome";
        return em.createQuery(jpql, Livro.class)
        		 .setParameter("nome", nome) 
        		 .getResultList();
	}
	
	public List<Livro> buscarPorEditora(Editora editora) {
		String jpql = "SELECT l FROM Livro l WHERE l.editora.nome = :nome";
        return em.createQuery(jpql, Livro.class)
        		 .setParameter("nome", editora.getNome()) 
        		 .getResultList();
	}
	
	public List<Livro> buscarPorCategoria(Categoria categoria) {
		String jpql = "SELECT l FROM Livro l WHERE l.categoria = ?1";
        return em.createQuery(jpql, Livro.class)
        		 .setParameter(1, categoria) 
       		     .getResultList();
	}

	public BigDecimal buscarPrecoDoLivroComNome(String nome) {
		String jpql = "SELECT l.preco FROM Livro l WHERE l.nome = :nome";
		return em.createQuery(jpql, BigDecimal.class)
				 .setParameter("nome", nome)
				 .getSingleResult();
	}

	public List<Livro> filtrar(Optional<String> nome, Optional<String> autoria, Optional<Editora> editora, Optional<Categoria> categoria, Optional<BigDecimal> preco, Optional<Estoque> estoque) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		
		CriteriaQuery<Livro> query = cb.createQuery(Livro.class);
		Root<Livro> from = query.from(Livro.class);

		Predicate filtros = cb.and();
		if(!nome.isEmpty()) {
			filtros = cb.and(filtros, cb.equal(from.get("nome"), nome.get()));
		}
		
		if(!autoria.isEmpty()) {
			filtros = cb.and(filtros, cb.equal(from.get("autoria"), autoria.get()));
		}
		
		if(!editora.isEmpty()) {
			filtros = cb.and(filtros, cb.equal(from.get("editora.id"), editora.get().getId()));
		}
		
		if(!categoria.isEmpty()) {
			filtros = cb.and(filtros, cb.equal(from.get("categoria"), categoria.get()));
		}
		
		if(!preco.isEmpty()) {
			filtros = cb.and(filtros, cb.equal(from.get("preco"), preco.get()));
		}
		
		if(!estoque.isEmpty()) {
			filtros = cb.and(filtros, cb.equal(from.get("estoque.estoque"), estoque.get().getEstoque()));
		}
		
		query.where(filtros);
		
		return em.createQuery(query).getResultList();
	}
}