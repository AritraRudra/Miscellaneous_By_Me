package com.pojo;

public class Employee extends Person {
	private double salary;
	private EmployerEnum employer;
	

	/**
	 * Use this constructor everywhere except when populating data from database.
	 * @param name
	 * @param age
	 * @param gender
	 * @param salary
	 * @param employer
	 */
	public Employee(final String name, final int age, final GenderEnum gender, final double salary, final EmployerEnum employer) {
		super(name, age, gender);
		this.salary = salary;
		this.employer = employer;
	}

	/**
	 * Use only when populating data from database.
	 * @param id
	 * @param name
	 * @param age
	 * @param gender
	 * @param salary
	 * @param employer
	 */
	public Employee(final long id, final String name, final int age, final GenderEnum gender, final double salary, final EmployerEnum employer) {
		super(id, name, age, gender);
		this.salary = salary;
		this.employer = employer;
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

	@Override
	public String toString(){
		return (super.getId()+" "+super.getName()+" "+super.getAge()+" "+super.getGender()
			+" "+this.employer+" "+this.salary);
	}
}
