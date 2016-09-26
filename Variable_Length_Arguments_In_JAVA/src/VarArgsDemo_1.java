/**
 * Java Variable Length Arguments demo
 * @since JDK 1.5
 */
public class VarArgsDemo_1 {
	static void varArgsTest(int... varArgs) {
		System.out.print("Number of variable arguments: " + varArgs.length + " Contents: ");

		for (int x : varArgs)
			System.out.print(x + " ");
		System.out.println();
	}
	
	 
	 /**
	  * A method can have “normal” parameters along with a variable-length parameter.
	  * 
	  * Rule 1. However there can be only one variable argument in a method.
	  * 	e.g., this will give compile error => varArgsWithNormalArgs(int normalArg1, boolean ... varArgs1, String normalArg2, int ... varArgs2){}
	  * Rule 2. Variable argument (varargs) must be the last argument.
	  * 	e.g., this will give compile error => varArgsWithNormalArgs(int normalArg1, boolean ... varArgs, String normalArg2){}
	  * 
	  * @param normalArg1
	  * @param normalArg2
	  * @param normalArg3
	  * @param varArgs
	  */
	static void varArgsWithNormalArgs(int normalArg1, boolean normalArg2, String normalArg3, int ... varArgs){
		System.out.println("Normal arguments : "+normalArg1+" "+normalArg2 + " "+normalArg3);
		System.out.print("Number of variable arguments: " + varArgs.length + " Contents: ");
		
		for (int x : varArgs)
			System.out.print(x + " ");
		System.out.println();
	}

	
	/**
	 * They can be overloaded also
	 * 
	 * @param normalArg1
	 * @param normalArg2
	 * @param varArgs
	 */
	static void varArgsWithNormalArgs(int normalArg1, boolean normalArg2, int ... varArgs){
		System.out.println("Normal arguments : "+normalArg1+" "+normalArg2);
		System.out.print("Number of variable arguments: " + varArgs.length + " Contents: ");
		
		for (int x : varArgs)
			System.out.print(x + " ");
		System.out.println();
	}
	
	/**
	 * Different overloaded versions
	 * @param varArgs
	 */
	static void varArgsOverloadingTest(int... varArgs) {
		System.out.print("varArgsOverloadingTest(int ...): " + "Number of args: " + varArgs.length + " Contents: ");
		
		for (int x : varArgs)
			System.out.print(x + " ");
		System.out.println();
	}

	/**
	 * Different overloaded versions
	 * @param varArgs
	 */
	static void varArgsOverloadingTest(boolean... varArgs) {
		System.out.print("varArgsOverloadingTest(boolean ...) " + "Number of args: " + varArgs.length + " Contents: ");
		for (boolean x : varArgs)
			System.out.print(x + " ");
		System.out.println();
	}

	/**
	 * Different overloaded versions
	 * @param str
	 * @param varArgs
	 */
	static void varArgsOverloadingTest(String str, int... varArgs) {
		System.out.print("varArgsOverloadingTest(String, int ...): " + str + varArgs.length + " Contents: ");
		for (int x : varArgs)
			System.out.print(x + " ");
		System.out.println();
	}
	
	public static void main(String args[]) {
		// No need for arrays unlike the previous one.
		// Method vaTest() can be called with a variable number of arguments.
		varArgsTest(10); // 1 arg
		varArgsTest(1, 2, 3); // 3 args
		varArgsTest(); // no args
		
		
		varArgsWithNormalArgs(7, false, 8,9,10);
		varArgsWithNormalArgs(7, true, "Some String", 8,9,10);
		
		/* Var Args overloading examples */
		varArgsOverloadingTest(1, 2, 3);
		varArgsOverloadingTest("Testing: ", 10, 20);
		varArgsOverloadingTest(true, false, false);
		
		/* Be careful, as this will raise an ambiguity error.
		 * Here it is unknown which among varArgsOverloadingTest(int ...) and varArgsOverloadingTest(boolean ...) to be called.
		 * Both are equally valid. Thus, the call is inherently ambiguous.
		 */
		//varArgsOverloadingTest();
		
		/*
		 * Taken from Herbert Schildt the complete reference java, Chapter 7.
		 * <p>
		 * Here is another example of ambiguity. The following overloaded
		 * versions of vaTest( ) are inherently ambiguous even though one takes
		 * a normal parameter: 
		 * static void vaTest(int ... v) { // ... 
		 * static void vaTest(int n, int ... v) { // ...
		 * Although the parameter lists of vaTest( ) differ, there is no way for the compiler to resolve the
		 * following call: 
		 * vaTest(1) 
		 * Does this translate into a call to
		 * vaTest(int …), with one varargs argument, or into a call to
		 * vaTest(int, int …) with no varargs arguments?
		 * There is no way for the compiler to answer this question. Thus, the situation is ambiguous.
		 * </p>
		 */
	}
}