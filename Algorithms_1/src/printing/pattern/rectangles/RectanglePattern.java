package printing.pattern.rectangles;

/**
 * @author Aritra
 */
// https://www.faceprep.in/pattern-programs-in-c/
public class RectanglePattern {

	public static void main(String[] args) {
		printSolidRectangle(5, 8);
		System.out.println();
		System.out.println();
		printHollowRectangle(5, 8);
	}

	public static void printSolidRectangle(final int noOfRows, final int noOfCols) {
		for (int i = 1; i <= noOfRows; i++) {
			for (int j = 1; j <= noOfCols; j++) {
				System.out.print("X");
			}
			System.out.println();
		}
	}

	public static void printHollowRectangle(final int noOfRows, final int noOfCols) {
		for (int i = 1; i <= noOfRows; i++) {
			for (int j = 1; j <= noOfCols; j++) {
				if (i == 1 || i == noOfRows || j == 1 || j == noOfCols)
					System.out.print("X");
				else
					System.out.print(" ");
			}
			System.out.println();
		}
	}
}
