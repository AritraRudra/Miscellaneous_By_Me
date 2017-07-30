package com.service.persistence;

import java.util.List;

import javax.persistence.NoResultException;

import com.entity.Student;

public final class StudentPersistenceService extends AbstractPersistenceService{
	
	public Student getStudentByID(final int id){
		super.init();
		try {
			return (Student) entityManager.createNamedQuery("Student.findByID").setParameter("id", id)
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
	
	public List<Student> getStudentByLastName(final String lastName){
		super.init();
		try {
			return (List<Student>) entityManager.createNamedQuery("Student.findByLastName").setParameter("lastName", lastName)
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
	
	public List<Student> getAllStudents(){
		super.init();
		try {
			return (List<Student>) entityManager.createNamedQuery("Student.getAllStudents").getResultList();
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
