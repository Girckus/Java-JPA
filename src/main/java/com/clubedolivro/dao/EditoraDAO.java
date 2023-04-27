package com.clubedolivro.dao;

import javax.persistence.EntityManager;

import com.clubedolivro.modelo.Editora;

public class EditoraDAO {

	private EntityManager em;

	public EditoraDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(Editora editora) {
		em.persist(editora);
	}
	
	public void remover(Editora editora) {
		editora = em.merge(editora);
		this.em.remove(editora);
	}
	
	public Editora obter(Integer id) {
		return em.find(Editora.class, id);
	}
	
}