import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test_1 {
	public static String someString = "Static String";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// A.b.doSomething() // similar to syso
		System.out.println(new Test_1().getResult(2, Department.MARKETING));
		System.out.println(someString);
	}

	// Return Employees list with specified salary rank for the specified department (multiple employees with same salary
	// and belonging to the same department is possible)
	// TODO - incomplete
	List<Employee> getResult(final int rank, final Department department) {
		validateRange(rank, 1, 30);
		List<Employee> allEmployees = getAllEmployees();
		//final Map<Department, List<Employee>> allEmployeesMap = prepareEmployeesMap(allEmployees);
		List<Employee> employeesByRequiredDept = new ArrayList<>();
		for (Employee employee : allEmployees) 
			if(department == employee.getDepartment()) 
				employeesByRequiredDept.add(employee);
		
		employeesByRequiredDept.sort((Employee emp1, Employee emp2) -> Integer.compare(emp2.getSalary(), emp1.getSalary())); // decreasing order
		
		System.out.println(employeesByRequiredDept.stream().map(Employee::getSalary).filter(employeesByRequiredDept.get(0).getSalary()::equals).count());
		System.out.println();

		// https://stackoverflow.com/a/43670501/1679643
		System.out.println(employeesByRequiredDept.stream().filter(emp -> emp.getSalary().equals(employeesByRequiredDept.get(0).getSalary())).collect(Collectors.toList()));
		System.out.println();

		// TODO here
		return employeesByRequiredDept;
	}


	private List<Employee> getAllEmployees() {
		// maybe get from DB or file.
		// final Employee[] employees = {new Employee(100, Department.FINANCE),new Employee(101, Department.HR)};
		// return Arrays.asList(employees);
		final int numberOfEmployees = 100;
		final List<Employee> allEmp = new ArrayList<>(numberOfEmployees + 25);
		for (int i = 0; i < numberOfEmployees / Department.values().length; i++) {
			allEmp.add(new Employee(100 + i, Department.FINANCE));
		}
		for (int i = 0; i < numberOfEmployees / Department.values().length - 5; i++) {
			allEmp.add(new Employee(100 + i, Department.HR));
		}
		for (int i = 0; i < numberOfEmployees / Department.values().length - 3; i++) {
			allEmp.add(new Employee(100 + i, Department.OPERATIONS));
		}
		for (int i = 0; i < numberOfEmployees / Department.values().length + 8; i++) {
			allEmp.add(new Employee(100 + i, Department.MARKETING));
		}

		// Duplicate data
		for (int i = 0; i < numberOfEmployees / Department.values().length; i++) {
			allEmp.add(new Employee(100 + i, Department.MARKETING));
		}
		return allEmp;
	}

	private void validateRange(final int rank, int lowerBound, int upperBound) {
		if (lowerBound > rank || rank > upperBound || lowerBound > upperBound)
			throw new IllegalArgumentException();
	}

	private class Employee {
		private Department department;

		private Integer salary;

		public Department getDepartment() {
			return department;
		}

		public Integer getSalary() {
			return salary;
		}

		public Employee(Integer salary, Department department) {
			this.salary = salary;
			this.department = department;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Employee [department=").append(department).append(", salary=").append(salary).append("]");
			return builder.toString();
		}

	}

	private enum Department {
		MARKETING(1), FINANCE(2), OPERATIONS(3), HR(4);
		private final int department;

		private Department(int department) {
			this.department = department;
		}
	}
}

class Test_2 {
	static Test_1 obj = null;
	public static void main(String a[]) {
		// Test_1 obj = null;
		obj.someString = "Static String in Test_2";
		System.out.println(obj.someString);
		System.out.println(Test_1.someString);

		Test_1 obj2 = new Test_1();
		obj2.someString = "Static String in Test_2 with Object";
		System.out.println(obj2.someString);
		System.out.println(Test_1.someString);
		doSomeThing();
	}

	private static void doSomeThing() {
		System.out.println(Test_1.someString);
	}
}

class Test_3 {
	public static void main(String a[]) {
		System.out.println(Test_1.someString);
	}
}
