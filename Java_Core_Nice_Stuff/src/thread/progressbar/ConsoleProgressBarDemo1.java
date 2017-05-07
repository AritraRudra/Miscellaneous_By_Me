package thread.progressbar;

/**
 * Program for console progress bar in Java. Run from console, not eclipse.
 * 
 * @author Aritra
 */
public class ConsoleProgressBarDemo1 {

	static void updateProgress(double progressPercentage) {
		final int width = 50; // progress bar width in chars

		System.out.print("\r[");
		int i = 0;
		for (; i <= (int) (progressPercentage * width); i++) {
			System.out.print(".");
		}
		for (; i < width; i++) {
			System.out.print(" ");
		}
		System.out.print("]");
	}

	public static void main(String[] args) {
		try {
			for (double progressPercentage = 0.0; progressPercentage < 1.0; progressPercentage += 0.01) {
				updateProgress(progressPercentage);
				Thread.sleep(50);
			}
		} catch (InterruptedException e) {
		}
	}
}