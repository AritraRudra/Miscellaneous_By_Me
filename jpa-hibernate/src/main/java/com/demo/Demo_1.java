package com.demo;

/**
 * 
 * @author Aritra
 */

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import com.entity.Address;
import com.entity.Student;
import com.managers.PersistenceManagerFor_HBNT_JPA;
import com.service.persistence.StudentPersistenceService;

public class Demo_1 {
	
	public static void main(String args[]){
		//samplePersist();
		
		StudentPersistenceService studentDAO = new StudentPersistenceService();
		List<Student> studentsList = studentDAO.getAllStudents();
		for (Student student : studentsList) {
			System.out.println(student);
		}
	}
	
	private static void samplePersist(){
		final EntityManager em = PersistenceManagerFor_HBNT_JPA.INSTANCE.getEntityManager();
		Address address = new Address("Street", "City", "Country", 96L);
		Student student = new Student(9, new Date(), "LastName", address);
		em.getTransaction().begin();
		
		// first persist address as student has dependency on address ( one to one mapping )
		// To persist student, we need address to be already persisted.
		// although this will be handled by hibernate, if <property name="hibernate.hbm2ddl.auto" value="create" /> is set to create.
		// If will alter the table and add foreign key constraint.
		em.persist(address);	// insert into hbnt_ADDRESS (City, Country, Zip_Code, Street) values (?, ?, ?, ?)
		em.persist(student);
		
		em.getTransaction().commit();
		em.close();
		
		PersistenceManagerFor_HBNT_JPA.INSTANCE.close();
	}
	
}
