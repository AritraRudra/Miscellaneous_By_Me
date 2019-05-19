package javapuzzlers.chapter7;

import java.math.BigInteger;

public class BigProblem {

	public static void main(String[] args) {
		puzzle_56();
		

	}

	// ################ Puzzle 56 - Big Problem ################ //
	/*
	 * Do not be misled into thinking that immutable types are mutable.
	 */
	private static void puzzle_56() {
		BigInteger fiveThousand = new BigInteger("5000");
		BigInteger fiftyThousand = new BigInteger("50000");
		BigInteger fiveHundredThousand = new BigInteger("500000");
		BigInteger total = BigInteger.ZERO;

		total.add(fiveThousand);
		total.add(fiftyThousand);
		total.add(fiveHundredThousand);
		System.out.println(total);

		// Fix
		total = total.add(fiveThousand);
		total = total.add(fiftyThousand);
		total = total.add(fiveHundredThousand);
		System.out.println(total);
	}

}
