package com.clubedolivro.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.clubedolivro.modelo.Vendedor;

public class VendedorDAO {

	private EntityManager em;

	public VendedorDAO(EntityManager em) {
		this.em = em;
	}
	
	public Vendedor obter(Integer id) {
		return em.find(Vendedor.class, id);
	}
	
	public List<Vendedor> listar() {
		String jpql = "SELECT v FROM Vendedor v";
        return em.createQuery(jpql, Vendedor.class).getResultList();
	}
}