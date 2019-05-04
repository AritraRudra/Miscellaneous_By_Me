package javapuzzlers.chapter2;

/*
 * Chapter 2 : Expressive Puzzlers
 */
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

		
		// ################ Puzzle 8 - Dos Equis ################ //
		/*
		 * Mixedtype computation can be confusing.
		 * Rules for determining the result type
		 * of a conditional expression are too long and complex to reproduce in their
		 * entirety, but here are three key points. <ul>. If the second and third
		 * operands have the same type, that is the type of the conditional expression.
		 * In other words, you can avoid the whole mess by steering clear of mixed-type
		 * computation. <ul>. If one of the operands is of type T where T is byte,
		 * short, or char and the other operand is a constant expression of type int
		 * whose value is representable in type T, the type of the conditional
		 * expression is T. <ul>. Otherwise, binary numeric promotion is applied to the
		 * operand types, and the type of the conditional expression is the promoted
		 * type of the second and third operands.
		 * 
		 * It is generally best to use the same type for the second and third operands
		 * in conditional expressions
		 */
		char x8 = 'X';
		int i8 = 0;
		System.out.println(true ? x8 : 0);
		System.out.println(false ? i8 : x8);
		
		
		// ################ Puzzle 9 - Tweedledum ################ //
		/*
		 * The Java language specification says that the compound assignment E1 op= E2
		 * is equivalent to the simple assignment E1 = (T) ((E1) op (E2)), where T is
		 * the type of E1, except that E1 is evaluated only once [JLS 15.26.2]. In other
		 * words, compound assignment expressions automatically cast the result of the
		 * computation they perform to the type of the variable on their left-hand side.
		 * If the type of the result is identical to the type of the variable, the cast
		 * has no effect. If, however, the type of the result is wider than that of the
		 * variable, the compound assignment operator performs a silent narrowing
		 * primitive conversion [JLS 5.1.3]. Attempting to perform the equivalent simple
		 * assignment would generate a compilation error, with good reason.
		 */
		short x9 = 0;
		int i9 = 123456;
		x9 += i9; // Contains a hidden cast!, no compilation errors
		//x9 = x9 + i9; // Won’t compile - "possible loss of precision", compiler avoids causing such errors
		System.out.println(x9);
		
		
		// ################ Puzzle 10 - Tweedledee ################ //
		Object x10 = "Buy ";
		String i10 = "Effective Java!";
		x10 = x10 + i10;
		System.out.println(x10);
		x10 = "Buy ";
		i10 = "Effective Java!";
		x10 += i10;
		System.out.println(x10);
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
