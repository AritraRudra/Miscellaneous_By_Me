package sorting;

import java.util.Comparator;

public class SortingUtil {

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
	public static <T> T[] bubbleSort(final T[] arrayToSort, final Comparator<? super T> comp,
			final boolean sortInAscendingOrder) {
		int i, j, len_1 = arrayToSort.length - 1;
		T temp;

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
	public static <T> T[] selectionSort(final T[] arrayToSort, final Comparator<? super T> comp,
			final boolean sortInAscendingOrder) {
		int i, j, len = arrayToSort.length;
		T temp;

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
	public static <T> T[] isertionSort(final T[] arrayToSort, final Comparator<? super T> comp,
			final boolean sortInAscendingOrder) {
		int i, j, k, len = arrayToSort.length;
		T temp;

		if (sortInAscendingOrder) {
			for (i = 1; i <= len; i++) {
				for (j = 0; j < i; j++) {
					if (comp.compare(arrayToSort[j], arrayToSort[i]) > 0) {
						temp = arrayToSort[j];
						arrayToSort[j] = arrayToSort[i];
						for (k = i; k > j; k++) {
							arrayToSort[k] = arrayToSort[k - 1];
						}
						arrayToSort[k + 1] = temp;
					}
				}
			}
		} else {
			for (i = 1; i <= len; i++) {
				for (j = 0; j < i; j++) {
					if (comp.compare(arrayToSort[j], arrayToSort[i]) < 0) {
						temp = arrayToSort[j];
						arrayToSort[j] = arrayToSort[i];
						for (k = i; k > j; k++) {
							arrayToSort[k] = arrayToSort[k - 1];
						}
						arrayToSort[k + 1] = temp;
					}
				}
			}
		}
		return arrayToSort;
	}

}