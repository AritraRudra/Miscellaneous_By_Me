package thread.progressbar;

/**
 * Program for console progress in Java.
 * Run from console, not eclipse.
 * 
 * @author Aritra
 *
 */
public class ConsoleProgressBarDemo3 {

	public static void main(String[] args) {
		char[] animationChars = new char[] { '|', '/', '-', '\\' };

		for (int i = 0; i <= 100; i++) {
			System.out.print("Processing: " + i + "% " + animationChars[i % 4] + "\r");

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Processing: Done!          ");
	}
}