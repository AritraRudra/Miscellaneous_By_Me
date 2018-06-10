package com.services;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.entity.Student;
import com.services.persistence.StudentPersistenceService;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
	@InjectMocks
	private StudentService objUnderTest;
	
	@Mock
	private Student student;

	private StudentPersistenceService studentPersistenceService;

	@Before
	public void setUp() throws Exception {
	}
	
	private void mockStudentPersistenceService(){
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testStudentService() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetStudentsAsList() {
		List<Student> studentsList = new ArrayList<Student>();
		studentsList.add(student);
		when(studentPersistenceService.getAllStudents()).thenReturn(studentsList);
		assertEquals(studentsList, objUnderTest.getStudentsAsList());
	}

	@Test
	public final void testGetStudentsCount() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testAddStudent() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testGetStudentByID() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public final void testDeleteStudentByID() {
		fail("Not yet implemented"); // TODO
	}

}
