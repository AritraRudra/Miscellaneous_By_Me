package com.dbhandler.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
import com.pojo.EmployerEnum;
import com.pojo.GenderEnum;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeDAOTest {
	private static EmployeeDAO objectUnderTest;
	@Mock
	private SQLAgent sqlAgent;
	@Mock
	private static EmployeeDAO employeeDAOMock;
	@Mock
	private Employee employee;

	// Don't mock Object array this way, arrays can't be mocked in this way as they are final.
	// https://groups.google.com/forum/#!topic/mockito/Dl0Y6xVSFEc
	//@Mock
	//private Employee[] employees;// = new Employee[5];

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		objectUnderTest = new EmployeeDAO();
		assertNotNull(objectUnderTest);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		objectUnderTest = null;
		employeeDAOMock = null;
		System.gc();
	}

	@Before
	public void setUp() throws Exception {
		assertNotNull(sqlAgent);
		assertNotNull(objectUnderTest);
		assertNotNull(employeeDAOMock);
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
		//fail("Not yet implemented");
	}
	
	@Test
	public void testInsertNewEmployee()throws NamingException, SQLException{
		mockDBInsert("sqlInsert");
		assertEquals(1, employeeDAOMock.insertNewEmployee(employee));
	}
	
	private void mockDBInsert(final String insertString) throws NamingException, SQLException {
		// No need for the the below line as it is not known how internally it would work.
		// We are mocking objectUnderTest class, not SQLAgent class.
		//Mockito.when(sqlAgent.dbInsert(insertString)).thenReturn(1);
		Mockito.when(employeeDAOMock.insertNewEmployee(employee)).thenReturn(1);
	}

	@Test
	public void testInsertNewEmployees()throws NamingException, SQLException{
		Mockito.when(employeeDAOMock.insertNewEmployees(mockEmployees())).thenReturn(1);
		assertEquals(1, employeeDAOMock.insertNewEmployees(mockEmployees()));
	}
	
	@Test
	public void testGetEmployeeByNameNotPresent() throws NamingException, SQLException{
		assertEquals(0, objectUnderTest.getEmployeesByName("DUMMY_NAME").length);
	}
	
	@Test
	public void testGetEmployeesByNamePresent() throws NamingException, SQLException{
		final String empName = "TEMP_NAME"; 
		final Employee employee = new Employee(empName, 99, GenderEnum.FEMALE,
				Double.MAX_VALUE, EmployerEnum.SELF_EMPLOYED);
		if(objectUnderTest.insertNewEmployee(employee) == 1){
			objectUnderTest.getEmployeesByName(empName);
		}else{
			fail("Data was not present");
		}
		// For multiple employees with same name
		final Employee empArr[] = objectUnderTest.getEmployeesByName(empName);
		for (final Employee emp : empArr) {
			assertEquals(empName, emp.getName());
		}
		// Cleanup the inserted entry
		assertTrue(objectUnderTest.removeEmployeesByName(empName) >= 1);
	}

}
