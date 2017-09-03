package com.managers.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Aritra
 */
public enum StudentsPersistenceManager {
	INSTANCE;

	private EntityManagerFactory emFactory;

	private StudentsPersistenceManager() {
		// "students" was the value of the name attribute of the persistence-unit element in persistence.xml.
		emFactory = Persistence.createEntityManagerFactory("student");
	}

	/**
	 * Returns {@link EntityManager}.
	 * 
	 * @param persistenceUnitName
	 *            - Name of the attribute of the persistence-unit element.
	 * @return {@link EntityManager}.
	 */
	public EntityManager getEntityManager() {
		return emFactory.createEntityManager();
	}

	public void close() {
		emFactory.close();
	}

}
