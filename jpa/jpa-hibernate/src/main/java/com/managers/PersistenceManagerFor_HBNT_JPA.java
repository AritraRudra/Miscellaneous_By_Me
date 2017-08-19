package com.managers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Aritra
 */
public enum PersistenceManagerFor_HBNT_JPA {
	INSTANCE;

	private EntityManagerFactory emFactory;

	private PersistenceManagerFor_HBNT_JPA() {
		// "hibernate_jpa" was the value of the name attribute of the persistence-unit element in persistence.xml.
		emFactory = Persistence.createEntityManagerFactory("hibernate_jpa");
	}

	/**
	 * Returns {@link EntityManager}.
	 * @param persistenceUnitName - Name of the attribute of the persistence-unit element.
	 * @return {@link EntityManager}.
	 */
	public EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public void close() {
		emFactory.close();
	}

}
