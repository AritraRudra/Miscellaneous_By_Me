package javapuzzlers.chapter1;

public class Oddity {
	static final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print(isOdd_Wrong(0));
		System.out.print(", " + isOdd_Wrong(1));
		System.out.print(", " + isOdd_Wrong(2));
		System.out.print(", " + isOdd_Wrong(-1));
		System.out.print(", " + isOdd_Wrong(-2));
		System.out.print(", " + isOdd_Wrong(-5));
		System.out.print(", " + isOdd_Correct(-7));
		System.out.println(", " + isOdd_Correct(-8));

		// ################ Puzzle 3 - Long Division ################ //
		// When working with large numbers, watch out for overflow — it’s a silent killer.
		final long MICROS_PER_DAY_WRONG = 24 * 60 * 60 * 1000 * 1000;
		final long MILLIS_PER_DAY_WRONG = 24 * 60 * 60 * 1000;
		System.out.println(MICROS_PER_DAY_WRONG / MILLIS_PER_DAY_WRONG); // should print 1000
		/*
		 * The problem is that the computation of the constant MICROS_PER_DAY does
		 * overflow. Although the result of the computation fits in a long with room to
		 * spare, it doesn’t fit in an int. The computation is performed entirely in int
		 * arithmetic, and only after the computation completes is the result promoted
		 * to a long. By then, it’s too late: The computation has already overflowed,
		 * returning a value that is too low by a factor of 200. The promotion from int
		 * to long is a widening primitive conversion [JLS 5.1.2], which preserves the
		 * (incorrect) numerical value. This value is then divided by MILLIS_PER_DAY,
		 * which was computed correctly because it does fit in an int. The result of
		 * this division is 5. So why is the computation performed in int arithmetic?
		 * Because all the factors that are multiplied together are int values. When you
		 * multiply two int values, you get another int value. Java does not have target
		 * typing, a language feature wherein the type of the variable in which a result
		 * is to be stored influences the type of the computation.
		 */
		// Solution
		final long MICROS_PER_DAY= 24L * 60 * 60 * 1000 * 1000;
		final long MILLIS_PER_DAY= 24L * 60 * 60 * 1000;
		System.out.println(MICROS_PER_DAY / MILLIS_PER_DAY);
		
		
		// ################ Puzzle 4 - It’s Elementary ################ //
		System.out.println(12345 + 5432l);
		System.out.println(12345 + 5432L);
		
		
		// ################ Puzzle 5 - The Joy of Hex ################ //
		System.out.println(Long.toHexString(0x100000000L + 0xcafebabe)); // Should print 1cafebabe.
		System.out.println(Long.toHexString(0x100000000L + 0xcafebabeL));
	}

	public static boolean isOdd_Wrong(int i) {
		return i % 2 == 1;
	}

	public static boolean isOdd_Correct(int i) {
		return i % 2 != 0;
	}

	public static boolean isOdd_Faster(int i) {
		return (i & 1) != 0;
	}

}
