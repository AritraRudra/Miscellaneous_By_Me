package comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// https://stackoverflow.com/questions/45695015/chain-methods-in-comparator-comparing-of-list-in-java-8
// https://howtodoinjava.com/sort/groupby-sort-multiple-comparators/
// https://www.baeldung.com/java-8-comparator-comparing
public class ComparatorComparingJava8 {

	public static void main(String[] args) {
		List<Employee> list = Arrays.asList(new Employee(1, 34, "A", "B"), new Employee(4, 30, "C", "D"), new Employee(3, 31, "B", "A"), new Employee(2, 25, "D", "C"));
		// Collections.sort(list, new FirstNameSorter().thenComparing(new LastNameSorter()).thenComparing(new AgeSorter()));

		list.sort(Comparator.comparing(Employee::getId).thenComparing(Employee::getLastName).thenComparing(str -> str.getFirstName()).thenComparingInt(age -> age.getAge()));
		list.forEach(System.out::println);

		list.sort(Comparator.comparingInt(Employee::getAge).thenComparing(Employee::getLastName).thenComparing(str -> str.getFirstName()).thenComparing(age -> age.getId()));
		System.out.println(list);
	}

}
