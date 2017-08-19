package com.service.persistence;

import javax.persistence.EntityManager;

import com.managers.PersistenceManagerFor_HBNT_JPA;

public abstract class AbstractPersistenceService implements PersistenceService{
	private static final EntityManager entityManager = PersistenceManagerFor_HBNT_JPA.INSTANCE.getEntityManager();
	
	protected static EntityManager getEntityManager(){
		return AbstractPersistenceService.entityManager;
	}

	protected static void close(){
		PersistenceManagerFor_HBNT_JPA.INSTANCE.close();
	}

	protected void persistNewEntity(final Object objToPersist){
		AbstractPersistenceService.entityManager.getTransaction().begin();
		AbstractPersistenceService.entityManager.persist(objToPersist);
		// Commit the transaction, which will cause the entity to be stored in the database
		AbstractPersistenceService.entityManager.getTransaction().commit();
	}

}
