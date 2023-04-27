package com.clubedolivro.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("clube_do_livro");
	
	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
	
}