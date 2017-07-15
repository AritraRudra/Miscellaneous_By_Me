package com.dbhandler.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import com.dbhandler.helper.SQLAgent;
import com.pojo.Employee;

public class EmployeeDAO {
	
	private static final String TABLE_EMPLOYEE = "TABLE_EMPLOYEE";
	private static final String COLUMN_NAME = "NAME";
	private static final String COLUMN_ID = "ID";

	// Try custom annotation injection maybe, but then can it still be static
	private SQLAgent sqlAgent = SQLAgent.getInstance();
	
	public Employee[] getEmployeesByName(final String name) throws NamingException, SQLException{
		final String queryString = "SELECT * FROM "+TABLE_EMPLOYEE+" WHERE "+COLUMN_NAME +"=?";
		final ResultSet rs = sqlAgent.dbSelectQuery(queryString, name);
		return getEmployeesFromResultSet(rs);
	}

	private Employee[] getEmployeesFromResultSet(final ResultSet rs) throws SQLException {
		Employee emp = null;
		final ArrayList<Employee> empArrList = new ArrayList<Employee>(); 
		while(rs.next()){
			emp = new Employee();
			empArrList.add(emp);
			//empArrList.add(rs)
		}
		return empArrList.toArray(new Employee[0]);
	}
	
}