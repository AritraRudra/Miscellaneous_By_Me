package com.dbhandler.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.dbhandler.helper.SQLAgent;
import com.pojo.Employee;
import com.pojo.Person;


@RunWith(MockitoJUnitRunner.class)
public class EmployeeDAOTest {
	@Mock
	private Connection conn;
	@Mock
	private SQLAgent sqlAgent;
	@Mock
	private EmployeeDAO empDao;
	@Mock
	private Employee employee;
	@Mock
	private Employee[] employees;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		assertNotNull(sqlAgent);
		assertNotNull(empDao);
		assertNotNull(employee);
		assertNotNull(employees);
		Mockito.when(sqlAgent.getConnection()).thenReturn(conn);
		/*
		mockEmployee();
		mockEmployees();
		*/
	}
	
	private Employee mockEmployee(){
		return (Mockito.mock(Employee.class));
	}
	
	private Employee[] mockEmployees(){
		return (Mockito.mock(Employee[].class));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void test() {
		//fail("Not yet implemented"); // TODO
	}
	
	@Test
	public void testInsertNewEmployee()throws NamingException, SQLException{
		mockDBInsert("sqlInsert");
		Assert.assertEquals(1, empDao.insertNewEmployee(employee));
	}
	
	private void mockDBInsert(final String insertString) throws NamingException, SQLException {
		Mockito.when(sqlAgent.dbInsert(insertString)).thenReturn(1);
	}

	@Test
	public void testInsertNewEmployees()throws NamingException, SQLException{
		mockDBInsert("sqlInsert");
		Assert.assertEquals(1, empDao.insertNewEmployees(employees));
	}

}
