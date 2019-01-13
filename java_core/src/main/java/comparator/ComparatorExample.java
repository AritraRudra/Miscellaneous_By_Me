package comparator;

import java.util.Random;
import java.util.Arrays;

public class ComparatorExample{
	public static void main(String[] args) {
		int i, randForInt, randForDouble, randForChar, randForString, noOfArrayElms = 10;
		Random random = new Random();
		ComparatorClass comparator[] = new ComparatorClass[noOfArrayElms];
		//ComparatorClass comparator = new ComparatorClass(0, 0.0, 'A', "Str_A");
		
		for (i = 0; i < noOfArrayElms; i++){
			//randForInt = random.nextInt();	//A-1, B-2, ..., Z-26 so, nos gen-> (0 - 25)
			//randForDouble = random.nextInt();
			randForInt = random.nextInt(1000);
			randForDouble = random.nextInt(9999);
			randForChar = random.nextInt(26);
			randForString = random.nextInt(26);
			comparator[i] = new ComparatorClass(randForInt, ((double)randForDouble),
							((char)(randForChar + 65)), ("Str_" + Character.toString((char)(randForString + 65))));
			comparator[i].printData();
		}
		System.out.println();
		
		//Arrays.sort(comparator,comparator.intComparator);
		Arrays.sort(comparator,ComparatorClass.intComparator);
		//Arrays.sort(indivWithFitness,null);
		//Arrays.sort(indivWithFitness);
		System.out.println("Sorted in ascending order of integer values");
		for (i = 0; i < noOfArrayElms; i++)
			comparator[i].printData();
		System.out.println();
		
		Arrays.sort(comparator,ComparatorClass.doubleComparator);		
		System.out.println("Sorted in ascending order of double values");
		for (i = 0; i < noOfArrayElms; i++)
			comparator[i].printData();
		System.out.println();
		
		Arrays.sort(comparator,ComparatorClass.charComparator);		
		System.out.println("Sorted in ascending order of char values");
		for (i = 0; i < noOfArrayElms; i++)
			comparator[i].printData();
		System.out.println();
		
		Arrays.sort(comparator,ComparatorClass.stringComparator);		
		System.out.println("Sorted in ascending order of string values");
		for (i = 0; i < noOfArrayElms; i++)
			comparator[i].printData();
	}

}