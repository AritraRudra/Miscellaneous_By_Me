package com.services.persistence;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entity.Student;
import com.managers.persistence.StudentsPersistenceManager;
@Stateless
public final class StudentPersistenceService{
	private final String className= StudentPersistenceService.class.getName();
	protected static final Logger LOGGER = LoggerFactory.getLogger(StudentPersistenceService.class);

	private static final EntityManager entityManager = StudentsPersistenceManager.INSTANCE.getEntityManager();
	
	private static EntityManager getEntityManager(){
		return StudentPersistenceService.entityManager;
	}

	private static void close(){
		StudentsPersistenceManager.INSTANCE.close();
	}

	private void persistNewEntity(final Object objToPersist){
		StudentPersistenceService.entityManager.getTransaction().begin();
		StudentPersistenceService.entityManager.persist(objToPersist);
		// Commit the transaction, which will cause the entity to be stored in the database
		StudentPersistenceService.entityManager.getTransaction().commit();
	}
	
	public void addStudent(final Student student){
		persistNewEntity(student);
	}
	
	public Student getStudentByID(final int id){
		try {
			return (Student) getEntityManager().createNamedQuery("Student.findByID").setParameter("id", id)
					.getSingleResult();
		} catch (final NoResultException nrfEx) {
			return null;
		}
		/*
		finally{
			// do we need to open and close a transaction for read only purpose ??, guess not, atleast in JSE
			//super.close();
		}
		*/
	}
	
	public List<Student> getStudentsByLastName(final String lastName){
		try {
			return (List<Student>) getEntityManager().createNamedQuery("Student.findByLastName")
					.setParameter("lastName", lastName).getResultList();
		} catch (final NoResultException nrfEx) {
			return null;
		}
	}
	
	public List<Student> getStudentsByAge(final int age){
		CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Student> studentQuery = criteriaBuilder.createQuery(Student.class);
		
		Root<Student> rootStudent = studentQuery.from(Student.class);
		Predicate condition = criteriaBuilder.equal(rootStudent.get("age"), age);
		studentQuery.where(condition);
		CriteriaQuery<Student> selectStudentQuery = studentQuery.select(rootStudent);
		selectStudentQuery.orderBy(criteriaBuilder.asc(rootStudent.get("age")));

		TypedQuery<Student> query = getEntityManager().createQuery(selectStudentQuery);
		final List<Student> resultlist = (List<Student>)query.getResultList();
		return resultlist;
	}
	
	public List<Student> getStudentsByCountryUsingSQL(final String country){
		// Actual MySQL query => 
		// SELECT * FROM hbnt_student s, hbnt_address a
		// WHERE s.address_id=(SELECT a.id FROM hbnt_address a
		// WHERE a.Country='Country');
		final String sqlQuery = "SELECT s FROM Student s, Address a WHERE s.address="
					+"(SELECT a.id FROM Address a WHERE a.country='Country')";
		
		List<Student> resultList = (List<Student>) getEntityManager().createQuery(sqlQuery).getResultList();
		return resultList;
	}
	
	// TODO using Criteria API, guess we need join and/or metamodel in JPA2.0 ??
	
	public List<Student> getStudentsByCountryUsingCriteria(final String country){
		CriteriaBuilder criteriaBuilder =getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Student> studentQuery = criteriaBuilder.createQuery(Student.class);

		Root<Student> rootStudent = studentQuery.from(Student.class);
		Predicate predicate = criteriaBuilder.equal(rootStudent.join("address").get("Country"), country);
	    studentQuery.where(predicate);

		TypedQuery<Student> query = getEntityManager().createQuery(studentQuery);
		final List<Student> resultlist = (List<Student>)query.getResultList();
		return resultlist;
	}
	
	public List<Student> getAllStudents(){
		try {
			return (List<Student>) StudentPersistenceService.getEntityManager()
					.createNamedQuery("Student.getAllStudents").getResultList();
		} catch (final NoResultException nrfEx) {
			return null;
		}
	}
	
}
