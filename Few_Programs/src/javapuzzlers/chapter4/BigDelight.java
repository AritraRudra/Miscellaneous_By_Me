package javapuzzlers.chapter4;

public class BigDelight {

	private static final byte TARGET = (byte) 0x90;
	public static final int END = Integer.MAX_VALUE;
	public static final int START = END - 100;
	public static void main(String[] args) {

		// ################ Puzzle 24 - A Big Delight in Every Byte ################ //
		/*
		 * Avoid mixed-type comparisons, because they are inherently confusing (Puzzle 5). To help achieve this goal, use declared constants in
		 * place of “magic numbers.”
		 */
		for (byte b = Byte.MIN_VALUE; b < Byte.MAX_VALUE; b++) {
			if (b == 0x90) // Correct usage is => if (b == TARGET)
				System.out.print("Joy!");
		}


		// ################ Puzzle 25 - Inclement Increment ################ //
		/*
		 * Do not assign to the same variable more than once in a single expression.
		 */
		int j_25 = 0;
		for (int i = 0; i < 100; i++)
			j_25 = j_25++; // This is equivalent to int tmp = j; j = j + 1; j = tmp;
		System.out.println(j_25);

		
		
		// ################ Puzzle 26 - In the Loop ################ //
		/*
		 * Whenever you use an integral type, be aware of the boundary conditions.
		 */
		int count = 0;
		// for (int i = START; i <= END; i++)
			count++;
		System.out.println(count);

		for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++)
			func(i);

		// ################ Puzzle 35 - Minute by Minute ################ //
		/*
		 * The remainder and multiplication operators have the same precedence [JLS 15.17], so the expression ms % 60*1000 is equivalent to (ms % 60)
		 * * 1000.
		 * Better to always replace all magic numbers with appropriately named constants:
		 */
		int minutes = 0;
		for (int ms = 0; ms < 60 * 60 * 1000; ms++)
			if (ms % 60 * 1000 == 0) // The compiler, however, ignores this white space, so never use spacing to express grouping; use parentheses.
										// Spacing can be deceptive, but parentheses never lie.
				minutes++;
		System.out.println(minutes);
	}

	private static void func(Object obj) {
		// System.out.println(obj);
	}

}
