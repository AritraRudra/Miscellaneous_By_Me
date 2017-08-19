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
		showAllStudents(studentDAO);
		System.out.println();
		showStudentsByAge(studentDAO, 9);
		System.out.println();
		showStudentsWithAgeLessThen(studentDAO, 11);
		System.out.println();
		showStudentsWithAgeMoreThen(studentDAO, 7);
		System.out.println();
		showStudentsByCountry(studentDAO, "Country");
		System.out.println();
		//showStudentsByCountryUsingCriteriaAPI(studentDAO, "Country");
		
		showStudentsByAge(studentDAO, 10);
		System.out.println();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		studentDAO.closeStudentPersistenceService();
	}

	private static void showAllStudents(final StudentPersistenceService studentDAO){
		List<Student> studentsList = studentDAO.getAllStudents();
		for (Student student : studentsList) {
			System.out.println(student);
		}
	}
	
	private static void showStudentsByAge(final StudentPersistenceService studentDAO, final int age){
		List<Student> studentsList = studentDAO.getStudentsByAge(age);
		for (Student student : studentsList) {
			System.out.println(student);
		}
	}
	
	private static void showStudentsWithAgeMoreThen(final StudentPersistenceService studentDAO, final int age){
		List<Student> studentsList = studentDAO.getStudentsWithAgeMoreThen(age);
		for (Student student : studentsList) {
			System.out.println(student);
		}
	}
	
	private static void showStudentsWithAgeLessThen(final StudentPersistenceService studentDAO, final int age){
		List<Student> studentsList = studentDAO.getStudentsWithAgeLessThen(age);
		for (Student student : studentsList) {
			System.out.println(student);
		}
	}
	
	private static void showStudentsByCountry(StudentPersistenceService studentDAO, String country) {
		List<Student> studentsList = studentDAO.getStudentsByCountryUsingSQL(country);
		for (Student student : studentsList) {
			System.out.println(student);
		}
	}
	
	private static void showStudentsByCountryUsingCriteriaAPI(StudentPersistenceService studentDAO, String country) {
		List<Student> studentsList = studentDAO.getStudentsByCountryUsingCriteria(country);
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
