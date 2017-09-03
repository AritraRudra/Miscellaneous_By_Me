package com.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.entity.Student;
import com.services.persistence.StudentPersistenceService;

@Stateless
public class StudentService {
	private final String className= StudentService.class.getName();
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentService.class);
	@Inject
	private StudentPersistenceService studentPersistenceService;
	//private StudentPersistenceService studentPersistenceService = new StudentPersistenceService();
	
	
	// Default constructor needed which will be used by framework for injection
	public StudentService(){
		
	}

	public List<Student> getStudentsAsList() {
		return studentPersistenceService.getAllStudents();
	}

	public int getStudentsCount() {
		return this.getStudentsAsList().size();
	}

	public void addStudent(final Student student) {
		studentPersistenceService.addStudent(student);
	}

	public Student getStudentByID(int studentID) {
		LOGGER.info(className+"getStudentByID");
		return studentPersistenceService.getStudentByID(studentID);
	}
}
