package comparator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ChainedMultiComparator implements Comparator<ComparatorClass> {

	private List<Comparator<ComparatorClass>> listComparators;

	@SafeVarargs
	public ChainedMultiComparator(Comparator<ComparatorClass>... comparators) {
		this.listComparators = Arrays.asList(comparators);
	}

	@Override
	public int compare(ComparatorClass objOne, ComparatorClass objTwo) {
		for (Comparator<ComparatorClass> comparator : listComparators) {
			int result = comparator.compare(objOne, objTwo);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		int i, randForInt, randForDouble, randForChar, randForString, noOfArrayElms = 10;
		Random random = new Random();
		ComparatorClass comparator[] = new ComparatorClass[noOfArrayElms];
		// ComparatorClass comparator = new ComparatorClass(0, 0.0, 'A', "Str_A");

		for (i = 0; i < noOfArrayElms; i++) {
			// randForInt = random.nextInt(); //A-1, B-2, ..., Z-26 so, nos gen-> (0 - 25)
			// randForDouble = random.nextInt();
			randForInt = random.nextInt(1000);
			randForDouble = random.nextInt(9999);
			randForChar = random.nextInt(26);
			randForString = random.nextInt(26);
			comparator[i] = new ComparatorClass(randForInt, (randForDouble), ((char) (randForChar + 65)), ("Str_" + Character.toString((char) (randForString + 65))));
			comparator[i].printData();
		}
		System.out.println("Data before Sorting : ");
		for (i = 0; i < noOfArrayElms; i++)
			comparator[i].printData();
		System.out.println("\nData afterSorting : ");

		Arrays.sort(comparator,
				new ChainedMultiComparator(ComparatorClass.intComparator, ComparatorClass.charComparator, ComparatorClass.stringComparator, ComparatorClass.doubleComparator));

		for (i = 0; i < noOfArrayElms; i++)
			comparator[i].printData();
	}

}
