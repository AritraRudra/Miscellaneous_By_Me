package com.dbhandler.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.dbhandler.helper.SQLAgent;
import com.pojo.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDAOTest {
	@Mock
	private SQLAgent sqlAgent;
	@Mock
	private EmployeeDAO empDao;
	@Mock
	private Employee employee;

	// Don't mock Object array this way, arrays can't be mocked in this way as they are final.
	// https://groups.google.com/forum/#!topic/mockito/Dl0Y6xVSFEc
	//@Mock
	//private Employee[] employees;// = new Employee[5];

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
		/*
		mockEmployee();
		mockEmployees();
		*/
	}
	/*
	private Employee mockEmployee(){
		return (Mockito.mock(Employee.class));
	}
	*/
	// this also does not work
	/*
	private Employee[] mockEmployees(){
		return (Mockito.mock(Employee[].class));
	}
	*/
	
	private Employee[] mockEmployees(){
		return (new Employee[5]);
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
		assertEquals(1, empDao.insertNewEmployee(employee));
	}
	
	private void mockDBInsert(final String insertString) throws NamingException, SQLException {
		// No need for the the below line as it is not known how internally it would work.
		// We are mocking empDAO class, not SQLAgent class.
		//Mockito.when(sqlAgent.dbInsert(insertString)).thenReturn(1);
		Mockito.when(empDao.insertNewEmployee(employee)).thenReturn(1);
	}

	@Test
	public void testInsertNewEmployees()throws NamingException, SQLException{
		Mockito.when(empDao.insertNewEmployees(mockEmployees())).thenReturn(1);
		assertEquals(1, empDao.insertNewEmployees(mockEmployees()));
	}

}
