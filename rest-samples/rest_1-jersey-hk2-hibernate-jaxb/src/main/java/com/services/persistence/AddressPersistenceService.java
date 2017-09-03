package com.services.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entity.Address;
import com.managers.persistence.StudentsPersistenceManager;

// Do we need this class as with the current design?

@Stateless
public class AddressPersistenceService {
	private final String className= AddressPersistenceService.class.getName();
	protected static final Logger LOGGER = LoggerFactory.getLogger(AddressPersistenceService.class);
	
	private static final EntityManager entityManager = StudentsPersistenceManager.INSTANCE.getEntityManager();
	
	private static EntityManager getEntityManager(){
		return AddressPersistenceService.entityManager;
	}

	private static void close(){
		StudentsPersistenceManager.INSTANCE.close();
	}

	private void persistNewEntity(final Object objToPersist){
		AddressPersistenceService.entityManager.getTransaction().begin();
		AddressPersistenceService.entityManager.persist(objToPersist);
		// Commit the transaction, which will cause the entity to be stored in the database
		AddressPersistenceService.entityManager.getTransaction().commit();
	}
	
	public void addAddress(final Address address){
		persistNewEntity(address);
	}

}
