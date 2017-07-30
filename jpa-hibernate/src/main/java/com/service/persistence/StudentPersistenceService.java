package com.service.persistence;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

import com.entity.Address;
import com.entity.Student;

public final class StudentPersistenceService extends AbstractPersistenceService{
	
	public Student getStudentByID(final int id){
		try {
			return (Student) super.getEntityManager().createNamedQuery("Student.findByID").setParameter("id", id)
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
			return (List<Student>) AbstractPersistenceService.getEntityManager().createNamedQuery("Student.findByLastName").setParameter("lastName", lastName)
					.getResultList();
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
	
	public List<Student> getStudentsByAge(final int age){
		CriteriaBuilder criteriaBuilder = AbstractPersistenceService.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Student> studentQuery = criteriaBuilder.createQuery(Student.class);
		
		Root<Student> rootStudent = studentQuery.from(Student.class);
		Predicate condition = criteriaBuilder.equal(rootStudent.get("age"), age);
		studentQuery.where(condition);
		CriteriaQuery<Student> selectStudentQuery = studentQuery.select(rootStudent);
		selectStudentQuery.orderBy(criteriaBuilder.asc(rootStudent.get("age")));

		TypedQuery<Student> query = AbstractPersistenceService.getEntityManager().createQuery(selectStudentQuery);
		final List<Student> resultlist = (List<Student>)query.getResultList();
		return resultlist;
	}
	
	public List<Student> getStudentsByCountryUsingSQL(final String country){
		// Actual MySQL query => SELECT * FROM hbnt_student s, hbnt_address a WHERE s.address_id=(SELECT a.id FROM hbnt_address a WHERE a.Country='Country'); 
		final String sqlQuery = "SELECT s FROM Student s, Address a WHERE s.address=(SELECT a.id FROM Address a WHERE a.country='Country')";
		
		List<Student> resultList = (List<Student>) AbstractPersistenceService.getEntityManager().createQuery(sqlQuery).getResultList();
		return resultList;
	}
	
	// TODO using Criteria API, guess we need join and/or metamodel in JPA2.0 ??
	/*
	public List<Student> getStudentsByCountryUsingCriteria(final String country){
		CriteriaBuilder criteriaBuilder = AbstractPersistenceService.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Student> studentQuery = criteriaBuilder.createQuery(Student.class);

		Root<Student> rootStudent = studentQuery.from(Student.class);
		Join<Address> rootAddress = rootStudent.join("capital", JoinType.LEFT);
		SetJoin<Student, Address> setJoin = root.join(root.get("address"));
		Predicate p = criteriaBuilder.equal(setJoin.get(Addre), Status.DELIVERED)
		        .negate();
	    studentQuery.where(predicate);
		
		CriteriaQuery<Student> selectStudentQuery = studentQuery.select(addressId);
		selectStudentQuery.orderBy(criteriaBuilder.asc(studentRoot.get("age")));

		TypedQuery<Student> query = AbstractPersistenceService.getEntityManager().createQuery(selectStudentQuery);
		final List<Student> resultlist = (List<Student>)query.getResultList();
		return resultlist;
	}
	*/
	
	public List<Student> getAllStudents(){
		try {
			return (List<Student>) super.getEntityManager().createNamedQuery("Student.getAllStudents").getResultList();
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
}
