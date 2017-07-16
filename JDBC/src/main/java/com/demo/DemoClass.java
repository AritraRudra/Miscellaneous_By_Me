package com.demo;

import java.sql.SQLException;

import javax.naming.NamingException;

import com.dbhandler.dao.EmployeeDAO;
import com.pojo.Employee;
import com.pojo.EmployerEnum;
import com.pojo.GenderEnum;

public class DemoClass {

	public static void main(String[] args) {

		EmployeeDAO empDao = new EmployeeDAO();
		Employee emp = new Employee("A", 1, GenderEnum.MALE, 1000000000.00, EmployerEnum.SELF_EMPLOYED);
		/*
		try {
			empDao.insertNewEmployee(emp);
			empDao.insertNewEmployee(null);
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NullPointerException npe){
			npe.printStackTrace();
		}
		*/
		try {
			emp = empDao.getEmployeesByName("A")[0];
			System.out.println(emp.toString());
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		
	}
}