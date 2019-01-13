package comparator;

public class Employee implements Comparable<Employee> {
	private int id = -1;

	private int age = -1;

	private String firstName = null;

	private String lastName = null;

	public Employee(int id, int age, String firstName, String lastName) {
		super();
		this.id = id;
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	@Override
	public int compareTo(Employee emp) {
		return Integer.compare(this.getId(), emp.getId());
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Employee [id=").append(id).append(", age=").append(age).append(", firstName=").append(firstName).append(", lastName=").append(lastName).append("]");
		return builder.toString();
	}

}
