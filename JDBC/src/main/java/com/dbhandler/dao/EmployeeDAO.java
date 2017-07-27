package com.dbhandler.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.dbhandler.helper.SQLAgent;
import com.pojo.Employee;
import com.pojo.EmployerEnum;
import com.pojo.GenderEnum;

public class EmployeeDAO {
	
	private static final String TABLE_EMPLOYEE = "employee";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_ID = "ID";
	private static final String COLUMN_AGE = "AGE";
	private static final String COLUMN_GENDER = "GENDER";
	private static final String COLUMN_SALARY = "SALARY";
	private static final String COLUMN_EMPLOYER = "EMPLOYER";

	// Try custom annotation injection maybe, but then can it still be static
	private SQLAgent sqlAgent = SQLAgent.getInstance();

	public int insertNewEmployee(final Employee newEmployee)throws NamingException, SQLException{
		if(newEmployee == null)
			throw new NullPointerException("Employee object is null.");
		final String insertString = "INSERT INTO "+TABLE_EMPLOYEE +"( "
				+COLUMN_ID+","+COLUMN_NAME+","+COLUMN_AGE+","+COLUMN_GENDER+","
				+ COLUMN_SALARY+","+COLUMN_EMPLOYER+" ) "
				+"VALUES (?, ?, ?, ?, ?, ? )";
		final Object[] params = getParametersArrayFromSingleEmployee(newEmployee);
		return sqlAgent.dbInsert(insertString, params);
	}
	
	// TODO batch insertion
	public int insertNewEmployees(final Employee[] newEmployees) throws NamingException, SQLException{
		final String insertString = "INSERT INTO "+TABLE_EMPLOYEE +"( "
				+COLUMN_ID+","+COLUMN_NAME+","+COLUMN_AGE+","+COLUMN_GENDER+","
				+ COLUMN_SALARY+","+COLUMN_EMPLOYER+" ) "
				+"VALUES (?, ?, ?, ?, ?, ? )";
		return sqlAgent.dbInsert(insertString, newEmployees);
	}
	
	public Employee[] getEmployeesByName(final String name) throws NamingException, SQLException{
		final String queryString = "SELECT * FROM "+TABLE_EMPLOYEE+" WHERE "+COLUMN_NAME +"=?";
		final ResultSet rs = sqlAgent.dbSelectQuery(queryString, name);
		return getEmployeesFromResultSet(rs);
	}
	
	public Employee[] getEmployeeByID(final long id) throws NamingException, SQLException{
		final String queryString = "SELECT * FROM "+TABLE_EMPLOYEE+" WHERE "+COLUMN_ID +"=?";
		final ResultSet rs = sqlAgent.dbSelectQuery(queryString, id);
		return getEmployeesFromResultSet(rs);
	}
	
	public int removeEmployeeByID(final long id) throws NamingException, SQLException{
		final String deleteString = "DELETE FROM "+TABLE_EMPLOYEE+" WHERE "+COLUMN_ID +"=?";
		return (sqlAgent.dbDelete(deleteString, id));
	}
	
	public int removeEmployeesByName(final String name) throws NamingException, SQLException{
		final String deleteString = "DELETE FROM "+TABLE_EMPLOYEE+" WHERE "+COLUMN_NAME +"=?";
		return (sqlAgent.dbDelete(deleteString, name));
	}

	// Below ones are utility methods.
	private static Object[] getParametersArrayFromSingleEmployee(final Employee emp){
		final Object[] args = { emp.getId(),
				emp.getName(), emp.getAge(),
				emp.getGender(), emp.getSalary(), emp.getEmployer()
			};
		return args;
	}

	/**
	 * Create an employee from an SQL {@link ResultSet}.
	 *
	 * @param rs
	 *          The SQL ResultSet.
	 * @return A {@link Employee} instance.
	 * @throws SQLException
	 * @throws NamingException
	 */
	private static Employee createEmployeeFromResultSet(final ResultSet rs) throws SQLException {
		final Employee empFromResultSet = new Employee(
				rs.getLong(COLUMN_ID),
				rs.getString(COLUMN_NAME),
				rs.getInt(COLUMN_AGE),
				GenderEnum.valueOf(rs.getString(COLUMN_GENDER)),
				rs.getDouble(COLUMN_SALARY),
				EmployerEnum.valueOf(rs.getString(COLUMN_EMPLOYER))
		    );
		return empFromResultSet;
	}

	private static Employee[] getEmployeesFromResultSet(final ResultSet rs) throws SQLException {
		final ArrayList<Employee> empArrList = new ArrayList<Employee>(); 
		while(rs.next()){
			final Employee emp = createEmployeeFromResultSet(rs);
			empArrList.add(emp);
		}
		return empArrList.toArray(new Employee[0]);
	}
	
}