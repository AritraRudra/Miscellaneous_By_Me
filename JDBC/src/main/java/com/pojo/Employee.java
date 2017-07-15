package com.pojo;

public class Employee extends Person {
	private double salary;
	private EmployerEnum employer;

	public Employee() {
		super();
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public EmployerEnum getEmployer() {
		return employer;
	}

	public void setEmployer(EmployerEnum employer) {
		this.employer = employer;
	}

}
