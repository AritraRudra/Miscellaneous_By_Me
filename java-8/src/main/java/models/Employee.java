package models;

public class Employee {

	private int empId;
	private String firstName;
	private String lastName;
	private double salary;
	private Address address;
	private String title;

	private Employee(Builder builder) {
		this.empId = builder.empId;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.salary = builder.salary;
		this.address = builder.address;
		this.title = builder.title;
	}

	public static class Builder {
		// Required parameters
		private int empId;
		private String lastName;
		private double salary;
		// Optional parameters - initialized to default values
		private Address address;
		private String firstName = "NO_FIRST_NAME";
		private String title = "NOT_ASSIGNED";

		public Builder(int empId, String lastName, double salary) {
			this.empId = empId;
			this.lastName = lastName;
			this.salary = salary;
		}

		public Builder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder address(Address address) {
			this.address = address;
			return this;
		}

		public Employee build() {
			return new Employee(this);
		}
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [empId=");
		builder.append(empId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", salary=");
		builder.append(salary);
		builder.append(", address=");
		builder.append(address);
		builder.append(", title=");
		builder.append(title);
		builder.append("]");
		return builder.toString();
	}

	public void incrementSalary(double d) {
		this.salary += d;
	}

}
