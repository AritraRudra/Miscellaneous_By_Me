package models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

// https://dzone.com/articles/a-guide-to-streams-in-java-8-in-depth-tutorial-wit
class StreamsTestWithEmployees {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@Test
	void whenIncrementSalaryForEachEmployee_thenApplyNewSalary() {
		final List<Employee> empList = prepareEmployeesList();
		// This will effectively call the incrementSalary() on each element in the empList.
		// forEach() is a terminal operation, which means that, after the operation is performed, the stream pipeline is considered consumed, and can
		// no longer be used.
		empList.stream().forEach(e -> e.incrementSalary(10000.0));
		System.out.println(empList);
	}

	/**
	 * map() produces a new stream after applying a function to each element of the original stream. The new stream could be of different type.
	 * This example converts the stream of Integers into the stream of Employees.
	 */
	@Test
	public void whenMapIdToEmployees_thenGetEmployeeStream() {
		Integer[] empIds = { 1, 2, 3 };
		List<Employee> employees = Stream.of(empIds).map(this::findById).collect(Collectors.toList());
		assertEquals(employees.size(), empIds.length);
	}

	@Test
	public void testGetSameSalariedEmplyees() {
		final List<Employee> empList = prepareEmployeesList();
		assertEquals(findById(2).getEmpId(), getSameSalariedEmplyees(empList, 2000000.0).get(0).getEmpId());
	}

	// A stream pipeline consists of a stream source, followed by zero or more intermediate operations, and a terminal operation.
	@Test
	public void whenStreamCount_thenGetElementCount() {
		long empCount = prepareEmployeesList().stream()
				.filter(e -> e.getSalary() > 2000000.0)
				.count();
		assertEquals(empCount, 2);
	}

	// Short-circuiting operations allow computations on infinite streams to complete in finite time
	@Test
	public void whenLimitInfiniteStream_thenGetFiniteElements() {
		Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);
		List<Integer> collect = infiniteStream
				.skip(3)
				.limit(5)
				.collect(Collectors.toList());
		assertEquals(collect, Arrays.asList(16, 32, 64, 128, 256));
	}
	
	// Comparison Based Stream Operations
	@Test
	public void whenSortStream_thenGetSortedStream() {
	    List<Employee> employees = prepareEmployeesList().stream()
	      .sorted((e1, e2) -> e1.getLastName().compareTo(e2.getLastName()))
	      .collect(Collectors.toList());
	    
	    assertEquals(employees.get(0).getLastName(), "Bezos");
	    assertEquals(employees.get(1).getLastName(), "Gates");
	    assertEquals(employees.get(2).getLastName(), "Page");
	}
	
	// Note that short-circuiting will not be applied for sorted().
	@Test
	public void whenFindMin_thenGetMinElementFromStream() {
	    Employee firstEmp = prepareEmployeesList().stream()
	      .min((e1, e2) -> e1.getEmpId() - e2.getEmpId())
	      .orElseThrow(NoSuchElementException::new);
	    assertEquals(firstEmp.getEmpId(), 1);
	}

	// We can also avoid defining the comparison logic by using Comparator.comparing()
	@Test
	public void whenFindMax_thenGetMaxElementFromStream() {
		final List<Employee> empList = prepareEmployeesList();
	    Employee maxSalEmp = empList.stream()
	      .max(Comparator.comparing(Employee::getSalary))
	      .orElseThrow(NoSuchElementException::new);
	    
	    empList.sort(Comparator.comparingDouble(Employee::getSalary));
	    assertEquals(empList.get(empList.size()-1).getSalary(), maxSalEmp.getSalary());
	}
	
	// distinct() does not take any argument and returns the distinct elements in the stream, eliminating duplicates. It uses the equals() method of
	// the elements to decide whether two elements are equal or not. The distinct operation is a stateful pipeline operation
	// https://stackoverflow.com/questions/27870136/java-lambda-stream-distinct-on-arbitrary-key
	// https://stackoverflow.com/questions/23699371/java-8-distinct-by-property
	// https://stackoverflow.com/questions/48165456/retrieve-distinct-element-based-on-multiple-attributes-of-java-object-using-java
	// https://stackoverflow.com/questions/27911406/java8-streams-remove-duplicates-with-stream-distinct
	@Test
	public void whenApplyDistinct_thenRemoveDuplicatesFromStream() {
		List<Integer> intList = Arrays.asList(2, 5, 3, 2, 4, 3);
		List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());

		assertEquals(distinctIntList, Arrays.asList(2, 5, 3, 4));
	}
	
	// allMatch, anyMatch, and noneMatch
	// These operations all take a predicate and return a boolean. Short-circuiting is applied and processing is stopped as soon as the answer is
	// determined
	@Test
	public void whenApplyMatch_thenReturnBoolean() {
		List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
		boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
		// allMatch() checks if the predicate is true for all the elements in the stream. Here, it returns false as soon as it encounters 5, which is
		// not divisible by 2.
		boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
		// anyMatch() checks if the predicate is true for any one element in the stream. Here, again short-circuiting is applied and true is returned
		// immediately after the first element.
		boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
		// noneMatch() checks if there are no elements matching the predicate. Here, it simply returns false as soon as it encounters 6, which is
		// divisible by 3.

		assertEquals(allEven, false);
		assertEquals(oneEven, true);
		assertEquals(noneMultipleOfThree, false);
	}
	
	@Test
	public void whenApplySumOnIntStream_thenGetSum() {
	    Double avgSal = prepareEmployeesList().stream()
	      .mapToDouble(Employee::getSalary)
	      .average()
	      .orElseThrow(NoSuchElementException::new);
		assertEquals(avgSal, new Double(2275000.0));
	}

	// Reduction Operations
	// A reduction operation (also called as fold) takes a sequence of input elements and combines them into a single summary result by repeated
	// application of a combining operation.
	// T reduce(T identity, BinaryOperator<T> accumulator)
	@Test
	public void whenApplyReduceOnStream_thenGetValue() {
		Double sumSal = prepareEmployeesList().stream()
				.map(Employee::getSalary)
				.reduce(0.0, Double::sum);
		// Here, we start with the initial value of 0 and repeated apply Double::sum() on elements of the stream. Effectively we've implemented the
		// DoubleStream.sum() by applying reduce() on Stream.
		assertEquals(sumSal, new Double(9100000.0));
	}

	@Test
	public void whenCollectByJoining_thenGetJoinedString() {
	    String empNames = prepareEmployeesList().stream()
	      .map(Employee::getFirstName)
	      .collect(Collectors.joining(", "))
	      .toString();
		assertEquals(empNames, "Jeff, Bill, Larry, Pichai");
	}

	// summarizingDouble
	// summarizingDouble() is another interesting collector — which applies a double-producing mapping function to each input element and returns a
	// special class containing statistical information for the resulting values:
	@Test
	public void whenApplySummarizing_thenGetBasicStats() {
		DoubleSummaryStatistics stats = prepareEmployeesList().stream().collect(Collectors.summarizingDouble(Employee::getSalary));

		assertEquals(4, stats.getCount());
		assertEquals(9100000.0, stats.getSum(), 0);
		assertEquals(100000.0, stats.getMin(), 0);
		assertEquals(4000000.0, stats.getMax(), 0);
		assertEquals(2275000, stats.getAverage(), 0);
	}

	// partitioningBy
	@Test
	public void whenStreamPartition_thenGetMap() {
		List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
		Map<Boolean, List<Integer>> isEven = intList.stream().collect(Collectors.partitioningBy(i -> i % 2 == 0));

		assertEquals(isEven.get(true).size(), 4);
		assertEquals(isEven.get(false).size(), 1);
	}

	private List<Employee> prepareEmployeesList() {
		final List<Employee> empList = new ArrayList<>();
		Employee emp = new Employee.Builder(1, "Bezos", 100000.0).firstName("Jeff").title("Founder and CEO").build();
		empList.add(emp);
		emp = new Employee.Builder(2, "Gates", 2000000.0).firstName("Bill").title("Founder").build();
		empList.add(emp);
		emp = new Employee.Builder(3, "Page", 3000000.0).firstName("Larry").title("Founder and CEO").build();
		empList.add(emp);
		emp = new Employee.Builder(4, "Sundar", 4000000.0).firstName("Pichai").title("CEO").build();
		empList.add(emp);
		return empList;
	}

	// Lazy Evaluation
	// Computation on the source data is only performed when the terminal operation is initiated, and source elements are consumed only as needed.All
	// intermediate operations are lazy, so they're not executed until a result of a processing is actually needed.
	private Employee findById(int id) {
		final List<Employee> empList = prepareEmployeesList();
		Employee employee = empList.stream()
				.filter(e -> e != null)
				.filter(e -> e.getEmpId() == id)
				.findFirst()
				.orElse(null);
		return employee;
	}
	
	private List<Employee> getSameSalariedEmplyees(List<Employee> empList, double salary) {
		final List<Employee> sameSalariedEmployees = empList.stream().
				filter(e -> e != null)
				.filter(e -> e.getSalary() == salary)
				.collect(Collectors.toList());
		return sameSalariedEmployees;
	}

}
