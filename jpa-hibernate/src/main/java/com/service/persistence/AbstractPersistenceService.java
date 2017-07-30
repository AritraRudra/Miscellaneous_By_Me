package com.service.persistence;

import javax.persistence.EntityManager;

import com.managers.PersistenceManagerFor_HBNT_JPA;

public abstract class AbstractPersistenceService implements PersistenceService{
	protected static EntityManager entityManager = null;
	
	protected static void init(){
		if(AbstractPersistenceService.entityManager == null)
			AbstractPersistenceService.entityManager = PersistenceManagerFor_HBNT_JPA.INSTANCE.getEntityManager();
	}

	public static EntityManager getEntityManager(){
		init();
		return AbstractPersistenceService.entityManager;
	}

	public static void close(){
		PersistenceManagerFor_HBNT_JPA.INSTANCE.close();
	}

	public void persistNewEntity(final Object objToPersist){
		AbstractPersistenceService.entityManager.getTransaction().begin();
		AbstractPersistenceService.entityManager.persist(objToPersist);
		// Commit the transaction, which will cause the entity to be stored in the database
		AbstractPersistenceService.entityManager.getTransaction().commit();
	}
}
