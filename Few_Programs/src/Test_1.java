import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Test_1 {
	public static String someString = "Static String";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// A.b.doSomething() // similar to syso
		System.out.println(new Test_1().getResult(2, Department.MARKETING));
		System.out.println(someString);
		// System.out.println(Long.valueOf("123.45")); //NFE
	}

	// Return Employees list with specified salary rank for the specified department (multiple employees with same salary
	// and belonging to the same department is possible)
	// TODO - improve the complexity
	List<Employee> getResult(final int rank, final Department department) {
		validateRange(rank, 1, 30);
		final List<Employee> allEmployees = getAllEmployees();
		//final Map<Department, List<Employee>> allEmployeesMap = prepareEmployeesMap(allEmployees);
		final List<Employee> employeesByRequiredDept = new ArrayList<>();
		for (Employee employee : allEmployees) 
			if(department == employee.getDepartment()) 
				employeesByRequiredDept.add(employee);
		
		employeesByRequiredDept.sort((Employee emp1, Employee emp2) -> Integer.compare(emp2.getSalary(), emp1.getSalary())); // decreasing order
		
		/*
		 * System.out.println(employeesByRequiredDept.stream().map(Employee::getSalary).filter(employeesByRequiredDept.get(0).getSalary()::equals).count());
		 * System.out.println();
		 * 
		 * // https://stackoverflow.com/a/43670501/1679643 System.out.println(employeesByRequiredDept.stream().filter(emp ->
		 * emp.getSalary().equals(employeesByRequiredDept.get(0).getSalary())).collect(Collectors.toList())); System.out.println();
		 */
		System.out.println(employeesByRequiredDept);
		final Map<Integer, List<Employee>> rankMap = prepareRankMap(employeesByRequiredDept);

		rankMap.forEach((k, v) -> {
			System.out.println("Rank : " + k + " Count : " + v.size());
			if (rank == k) {
				System.out.println("All employees having salary rank : " + rank + ": is " + v);
			}
		});

		assert rankMap.size() == new TreeSet<>(employeesByRequiredDept).size();
		return rankMap.get(rank);
	}

	// TODO - improve the complexity
	private Map<Integer, List<Employee>> prepareRankMap(final List<Employee> employeesByRequiredDept) {
		int tempRank = 1;
		final Map<Integer, List<Employee>> rankMap = new HashMap<>();
		int previousSalary = employeesByRequiredDept.get(0).getSalary();
		for (int i = 1; i < employeesByRequiredDept.size(); i++) {
			int incrementCount = 0;
			Employee employee = employeesByRequiredDept.get(i);
			List<Employee> sameSalariedEmployees = new ArrayList<>();
			sameSalariedEmployees.add(employeesByRequiredDept.get(i - 1));
			if (previousSalary == employee.getSalary()) {
				sameSalariedEmployees.add(employee);
				incrementCount++;
				for (int j = i + 1; j < employeesByRequiredDept.size(); j++) {
					Employee emp = employeesByRequiredDept.get(j);
					if (previousSalary == emp.getSalary()) {
						sameSalariedEmployees.add(emp);
						incrementCount++;
					} else {
						break;
					}
				}
				i = i + incrementCount;
			}
			if (i == employeesByRequiredDept.size())
				previousSalary = employeesByRequiredDept.get(i - 1).getSalary();
			else
				previousSalary = employeesByRequiredDept.get(i).getSalary();
			rankMap.put(tempRank++, sameSalariedEmployees);
		}
		return rankMap;
	}


	private List<Employee> getAllEmployees() {
		// maybe get from DB or file.
		// final Employee[] employees = {new Employee(100, Department.FINANCE),new Employee(101, Department.HR)};
		// return Arrays.asList(employees);
		final int numberOfEmployees = 100;
		final List<Employee> allEmp = new ArrayList<>(2 * numberOfEmployees);
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

		// Duplicate some data
		for (int i = 0; i < numberOfEmployees / Department.values().length; i++) {
			allEmp.add(new Employee(100 + i, Department.MARKETING));
		}
		for (int i = 0; i < numberOfEmployees / Department.values().length - 8; i++) {
			allEmp.add(new Employee(100 + i, Department.MARKETING));
		}
		for (int i = 0; i < numberOfEmployees / Department.values().length + 8; i++) {
			allEmp.add(new Employee(100 + i, Department.MARKETING));
		}
		for (int i = numberOfEmployees / Department.values().length + 8; i >= 0; i--) {
			allEmp.add(new Employee(100 + i, Department.MARKETING));
		}
		for (int i = numberOfEmployees / 8; i < numberOfEmployees / Department.values().length - 10; i++) {
			allEmp.add(new Employee(100 + i, Department.MARKETING));
		}
		return allEmp;
	}

	private void validateRange(final int rank, int lowerBound, int upperBound) {
		if (lowerBound > rank || rank > upperBound || lowerBound > upperBound)
			throw new IllegalArgumentException();
	}

	private class Employee implements Comparable<Employee> {
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

		@Override
		public int compareTo(Employee otherEmployee) {
			return -this.salary.compareTo(otherEmployee.getSalary());
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
