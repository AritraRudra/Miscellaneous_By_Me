package sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class SortingUtils {

	/**
	 * Sort an array in the specified order using bubble sort technique.<br>
	 * 
	 * The first element is compared with the next element if it is greater then
	 * they are swapped.After first iteration, the largest element is placed at
	 * the end position.<br>
	 * Best case, average case and worst case complexities are O(n^2).
	 * 
	 * @param arrayToSort
	 *            - The array to be sorted.
	 * @param comp
	 *            - The comparator to determine the order of the array. A null
	 *            value indicates that the elements' natural ordering should be
	 *            used.
	 * @param sortInAscendingOrder
	 *            - If true then sorted elements will be in ascending order and
	 *            if false then in descending order.
	 * @return The sorted array.
	 * @throws ClassCastException
	 *             If the array contains elements that are not mutually
	 *             comparable using the specified comparator.
	 * @throws IllegalArgumentException
	 *             (optional) If the comparator is found to violate the
	 *             Comparator contract.
	 */
	public static <T> T[] bubbleSort(final T[] arrayToSort, Comparator<? super T> comp,
			final boolean sortInAscendingOrder) {
		int i, j, len_1 = arrayToSort.length - 1;
		T temp;
		
		if (comp == null)
			comp = getNaturalOrderingBasedComparator(arrayToSort[0]);

		if (sortInAscendingOrder) {
			for (i = 0; i < len_1; i++) {
				for (j = 0; j < (len_1 - i); j++) {
					if (comp.compare(arrayToSort[j], arrayToSort[j + 1]) > 0) {
						temp = arrayToSort[j];
						arrayToSort[j] = arrayToSort[j + 1];
						arrayToSort[j + 1] = temp;
					}
				}
			}
		} else {
			for (i = 0; i < len_1; i++) {
				for (j = 0; j < (len_1 - i); j++) {
					if (comp.compare(arrayToSort[j], arrayToSort[j + 1]) < 0) {
						temp = arrayToSort[j];
						arrayToSort[j] = arrayToSort[j + 1];
						arrayToSort[j + 1] = temp;
					}
				}
			}
		}
		return arrayToSort;
	}

	/**
	 * Sort an array in the specified order using selection sort technique.<br>
	 * 
	 * The first element is compared with all other elements and after first
	 * iteration, the smallest element is placed at the first (0 th) position.
	 * <br>
	 * Best case, average case and worst case complexities are O(n^2).
	 * 
	 * @param arrayToSort
	 *            - The array to be sorted.
	 * @param comp
	 *            - The comparator to determine the order of the array. A null
	 *            value indicates that the elements' natural ordering should be
	 *            used.
	 * @param sortInAscendingOrder
	 *            - If true then sorted elements will be in ascending order and
	 *            if false then in descending order.
	 * @return The sorted array.
	 * @throws ClassCastException
	 *             If the array contains elements that are not mutually
	 *             comparable using the specified comparator.
	 * @throws IllegalArgumentException
	 *             (optional) If the comparator is found to violate the
	 *             Comparator contract.
	 */
	public static <T> T[] selectionSort(final T[] arrayToSort, Comparator<? super T> comp,
			final boolean sortInAscendingOrder) {
		int i, j, len = arrayToSort.length;
		T temp;

		if(comp == null)
			comp = getNaturalOrderingBasedComparator(arrayToSort[0]);
		if (sortInAscendingOrder) {
			for (i = 0; i < (len - 1); i++) {
				for (j = (i + 1); j < len; j++) {
					if (comp.compare(arrayToSort[i], arrayToSort[j]) > 0) {
						temp = arrayToSort[i];
						arrayToSort[i] = arrayToSort[j];
						arrayToSort[j] = temp;
					}
				}
			}
		} else {
			for (i = 0; i < (len - 1); i++) {
				for (j = (i + 1); j < len; j++) {
					if (comp.compare(arrayToSort[i], arrayToSort[j]) < 0) {
						temp = arrayToSort[i];
						arrayToSort[i] = arrayToSort[j];
						arrayToSort[j] = temp;
					}
				}
			}
		}
		return arrayToSort;
	}

	/**
	 * Sort an array in the specified order using insertion sort technique.<br>
	 * 
	 * The first iteration starts with comparison of second element with the
	 * first element. In the second iteration, the third element is compared
	 * with the first and second element.In every iteration, the n th element is
	 * compared with all the (n - 1) elements.<br>
	 * 
	 * Best case, average case and worst case complexities are (n - 1), O(n^2)
	 * and O(n^2).
	 * 
	 * @param arrayToSort
	 *            - The array to be sorted.
	 * @param comp
	 *            - The comparator to determine the order of the array. A null
	 *            value indicates that the elements' natural ordering should be
	 *            used.
	 * @param sortInAscendingOrder
	 *            - If true then sorted elements will be in ascending order and
	 *            if false then in descending order.
	 * @return The sorted array.
	 * @throws ClassCastException
	 *             If the array contains elements that are not mutually
	 *             comparable using the specified comparator.
	 * @throws IllegalArgumentException
	 *             (optional) If the comparator is found to violate the
	 *             Comparator contract.
	 */
	public static <T> T[] insertionSort(final T[] arrayToSort, Comparator<? super T> comp,
			final boolean sortInAscendingOrder) {
		int i, j, k, len = arrayToSort.length;
		T temp;

		if (comp == null)
			comp = getNaturalOrderingBasedComparator(arrayToSort[0]);

		if (sortInAscendingOrder) {
			for (i = 1; i < len; i++) {
				for (j = 0; j < i; j++) {
					if (comp.compare(arrayToSort[j], arrayToSort[i]) > 0) {
						temp = arrayToSort[j];
						arrayToSort[j] = arrayToSort[i];
						for (k = i; k > j; k--) {
							arrayToSort[k] = arrayToSort[k - 1];
						}
						arrayToSort[k + 1] = temp;
					}
				}
			}
		} else {
			for (i = 1; i < len; i++) {
				for (j = 0; j < i; j++) {
					if (comp.compare(arrayToSort[j], arrayToSort[i]) < 0) {
						temp = arrayToSort[j];
						arrayToSort[j] = arrayToSort[i];
						for (k = i; k > j; k--) {
							arrayToSort[k] = arrayToSort[k - 1];
						}
						arrayToSort[k + 1] = temp;
					}
				}
			}
		}
		return arrayToSort;
	}

	@SuppressWarnings("rawtypes")
	private static <T> Comparator getNaturalOrderingBasedComparator(final T t) {
		// From jdk 8, we have support to obtain a natural ordering comparator.
		// Although Collections and Arrays already had somehow implemented natural ordering in older jdks.
		
		// http://stackoverflow.com/questions/3241063/does-a-natural-comparator-exist-in-the-standard-api
		if(t instanceof Integer){
			return(Comparator.<Integer>naturalOrder());
		}else if(t instanceof Float){
			return(Comparator.<Float>naturalOrder());
		}else if(t instanceof Double){
			return(Comparator.<Double>naturalOrder());
		}else if(t instanceof Boolean){
			return(Comparator.<Boolean>naturalOrder());
		}else if(t instanceof Character){
			return(Comparator.<Character>naturalOrder());
		}else if(t instanceof String){
			return(Comparator.<String>naturalOrder());
		}
		return null;
	}
	
	private static final <T> void printArray(final T arrToPrint[]){
		for (T t : arrToPrint) {
			System.out.print(t.toString()+" ");
		}
		System.out.println();
	}
	
	// Main class for testing whether it works or not, now we are doing this using JUnits.
	public static void main(String[] a){
		int noOfArrayElms;
		int[] intArray;
		char[] charArray;
		double[] doubleArray;
		String[] stringArray;
		Random random;
		
		int i, randForInt, randForChar, randForString;
		double randForDouble;
		noOfArrayElms = 10;
		random = new Random();
		
		// Initializing the arrays.
		intArray = new int[noOfArrayElms];
		charArray = new char[noOfArrayElms];
		doubleArray = new double[noOfArrayElms];
		stringArray = new String[noOfArrayElms];
		
		// Assigning values in the arrays.
		for (i = 0; i < noOfArrayElms; i++){
			randForInt = random.nextInt(1000);
			randForDouble = (double)random.nextInt(9999)/(random.nextDouble() + 0.00001d);	// to avoid divide by zero
			randForChar = random.nextInt(26);
			randForString = random.nextInt(26);
			
			intArray[i] = randForInt;
			charArray[i] = (char)(randForChar + 65);
			doubleArray[i] = randForDouble;
			stringArray[i] = "Str_" + Character.toString((char)(randForString + 65));
		}
		
		System.out.println("In testSelectionSort.");
		printArray(stringArray);
		//String ourSortedTestStringArray[] = SortingUtils.insertionSort(stringArray, null, true);
		//String ourSortedTestStringArray[] = SortingUtils.selectionSort(stringArray, null, true);
		printArray(stringArray);
		Arrays.sort(stringArray);
		printArray(stringArray);
	}
}