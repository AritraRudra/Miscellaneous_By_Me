/**
 * Using an array to pass a variable number of
 * arguments to a method. This is the old-style
 * approach to variable-length arguments.
 */
public class VarArgsAsArray {
	static void varArgsTest(int varArgs[]) {
		System.out.print("Number of variable arguments: " + varArgs.length + " Contents: ");
		for (int x : varArgs)
			System.out.print(x + " ");
		System.out.println();
	}

	public static void main(String args[]) {
		// An array must be created to hold the arguments, one of the drawbacks !!
		int n1[] = { 10 };
		int n2[] = { 1, 2, 3 };
		int n3[] = {};
		varArgsTest(n1); // 1 arg
		varArgsTest(n2); // 3 args
		varArgsTest(n3); // no args
	}
}