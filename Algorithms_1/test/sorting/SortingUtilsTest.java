/**
 * 
 */
package sorting;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Aritra
 */
public class SortingUtilsTest {
	/** Name of this class */
	private final String className = getClass().getName();
	/** Logger for this class */
	//protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected final Logger logger = Logger.getLogger(getClass().getName()); 
	

	private int noOfArrayElms;
	private int[] intArray;
	private char[] charArray;
	private double[] doubleArray;
	private String[] stringArray;
	private Random random;


	@Before
	public void setUp(){
		logger.entering(className, "setUp()");

		int i, randForInt, randForChar, randForString;
		double randForDouble;
		this.noOfArrayElms = 10;
		this.random = new Random();
		
		// Initializing the arrays.
		this.intArray = new int[this.noOfArrayElms];
		this.charArray = new char[this.noOfArrayElms];
		this.doubleArray = new double[this.noOfArrayElms];
		this.stringArray = new String[this.noOfArrayElms];
		
		// Assigning values in the arrays.
		for (i = 0; i < this.noOfArrayElms; i++){
			//randForInt = random.nextInt();	//A-1, B-2, ..., Z-26 so, nos gen-> (0 - 25)
			randForInt = this.random.nextInt(1000);
			randForDouble = (double)this.random.nextInt(9999)/(this.random.nextDouble() + 0.00001d);	// to avoid divide by zero
			randForChar = this.random.nextInt(26);
			randForString = this.random.nextInt(26);
			
			this.intArray[i] = randForInt;
			this.charArray[i] = (char)(randForChar + 65);
			this.doubleArray[i] = randForDouble;
			//this.stringArray[i] = "Str_" + Character.toString((char)(randForString + 65));
			this.stringArray[i] = "Str_" + Character.toString((char) (randForString + 65))
									+ "_" + randForInt + "_" + randForDouble + "_" + randForChar;
		}
		logger.exiting(className, "setUp()");
	}

	@After
	public void tearDown(){
		logger.entering(className, "tearDown()");
		//System.out.println("In tearDown().");
		logger.exiting(className, "tearDown()");
	}

	/**
	 * Test method for {@link sorting.SortingUtils#bubbleSort(T[], java.util.Comparator, boolean)}.
	 */
	@Test
	public final void testBubbleSort() {
		//System.out.println("In testBubbleSort.");
		logger.entering(className, "testBubbleSort()");
		logger.fine("Unsorted Array : \n"+arrayToCSV(this.stringArray));
		String ourSortedTestStringArray[] = SortingUtils.bubbleSort(this.stringArray, null, true);
		logger.fine("Our sorted Array : \n"+arrayToCSV(this.stringArray));
		Arrays.sort(this.stringArray);
		logger.fine("Java sorted Array : \n"+arrayToCSV(this.stringArray));
		assertArrayEquals(this.stringArray, ourSortedTestStringArray);
		
		// String array check in descending order
		logger.fine("Unsorted Array : \n"+arrayToCSV(this.stringArray));
		ourSortedTestStringArray = SortingUtils.bubbleSort(this.stringArray, null, false);
		logger.fine("Our sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		Arrays.sort(this.stringArray);
		logger.fine("Java sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		assertArrayEquals(this.stringArray, ourSortedTestStringArray);
		logger.exiting(className, "testBubbleSort()");
	}

	/**
	 * Test method for {@link sorting.SortingUtils#selectionSort(T[], java.util.Comparator, boolean)}.
	 */
	@Test
	public final void testSelectionSort() {
		/*
		System.out.println("In testSelectionSort.");
		this.printArray(this.stringArray);
		*/
		logger.entering(className, "testSelectionSort()");
		logger.fine("Unsorted Array : \n"+arrayToCSV(this.stringArray));
		String ourSortedTestStringArray[] = SortingUtils.selectionSort(this.stringArray, null, true);
		logger.fine("Our sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		Arrays.sort(this.stringArray);
		logger.fine("Java sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		assertArrayEquals(this.stringArray, ourSortedTestStringArray);
		
		// String array check in descending order
		logger.fine("Unsorted Array : \n"+arrayToCSV(this.stringArray));
		ourSortedTestStringArray = SortingUtils.selectionSort(this.stringArray, null, false);
		logger.fine("Our sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		Arrays.sort(this.stringArray);
		logger.fine("Java sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		assertArrayEquals(this.stringArray, ourSortedTestStringArray);
		logger.exiting(className, "testSelectionSort()");
	}

	/**
	 * Test method for {@link sorting.SortingUtils#insertionSort(T[], java.util.Comparator, boolean)}.
	 */
	@Test
	public final void testInsertionSort() {
		/*
		System.out.println("In testInsertionSort.");
		this.printArray(this.stringArray);
		*/
		// String array check in ascending order
		logger.entering(className, "testInsertionSort()");
		logger.fine("Unsorted Array : \n"+arrayToCSV(this.stringArray));
		String ourSortedTestStringArray[] = SortingUtils.insertionSort(this.stringArray, null, true);
		logger.fine("Our sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		Arrays.sort(this.stringArray);
		logger.fine("Java sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		assertArrayEquals(this.stringArray, ourSortedTestStringArray);
		
		// String array check in descending order
		logger.fine("Unsorted Array : \n"+arrayToCSV(this.stringArray));
		ourSortedTestStringArray = SortingUtils.insertionSort(this.stringArray, null, false);
		logger.fine("Our sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		Arrays.sort(this.stringArray);
		logger.fine("Java sorted Array : \n"+arrayToCSV(this.stringArray));
		//this.printArray(this.stringArray);
		assertArrayEquals(this.stringArray, ourSortedTestStringArray);

		logger.exiting(className, "testInsertionSort()");
	}

	/**
	 * Converts and returns an array to single string with the array elements
	 * joined using comma (,). If the array is empty, it will return an empty
	 * string.
	 * 
	 * @param array
	 *            - The array.
	 * @return The comma separated single string.
	 */
	//@SuppressWarnings("unused")
	private final <T> String arrayToCSV(final T array[]) {
		if (array.length > 0) {
			StringBuilder result = new StringBuilder();
			for (T t : array) {
				result.append(t.toString());
				result.append(",");
			}
			return result.toString();
		} else {
			return "";
		}
	}

	@SuppressWarnings("unused")
	private final <T> void printArray(final T arrToPrint[]){
		for (T t : arrToPrint) {
			System.out.print(t.toString()+" ");
		}
		System.out.println();
	}
}
