package thread.progressbar;

public class ConsoleProgress {

	public static void main(String[] args) {
		/*
		ProgressChar1 pc1 = new ProgressChar1();
		ProgressChar2 pc2 = new ProgressChar2();
		pc1.start();
		pc2.start();
		*/
		/*
		for (int x = 0; x < 10; x++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException intrExp) {
			}
		}
		*/
		/*
		try {
			Thread.sleep(6000);
		} catch (InterruptedException intrExp) {
		}
		pc1.showProgress = false;
		pc2.showProgress = false;
		
		/*
		ProgressChar2 pc2 = new ProgressChar2();
		pc2.start();
		for (int x = 0; x < 5; x++) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException intrExp) {
			}
		}
		pc2.showProgress = false;
		*/

		//System.out.println("\nDone.");
		Object lockObj = new Object();
		ProgressChar pc1 = new ProgressChar('/',lockObj);
		ProgressChar pc2 = new ProgressChar('-',lockObj);
		ProgressChar pc3 = new ProgressChar('\\',lockObj);
		pc1.start();
		pc2.start();
		pc3.start();

		try {
			Thread.sleep(6000);
		} catch (InterruptedException intrExp) {
		}
		
		pc1.setShowProgress(false);
		pc2.setShowProgress(false);
		pc3.setShowProgress(false);
	}
}

class ProgressChar1 extends Thread {
	boolean showProgress = true;
	public void run() {
		char ch='/';
		//System.out.print("Processing ");
		while (showProgress) {
			System.out.print(ch); 
			try { Thread.sleep(100); }
			catch (Exception e) {};
		}
	}
}

class ProgressChar2 extends Thread {
	boolean showProgress = true;
	public void run() {
		char ch='-';
		//System.out.print("Processing ");
		while (showProgress) {
			System.out.print(ch); 
			try { Thread.sleep(100); }
			catch (Exception e) {};
		}
	}
}

class ProgressChar extends Thread {
	private boolean showProgress = true;
	private char charToPrint;
	private Object lockObj;

	public ProgressChar(char charToPrint, Object obj) {
		super();
		this.charToPrint = charToPrint;
		this.lockObj = obj;
	}

	public void setShowProgress(boolean showProgress) {
		this.showProgress = showProgress;
	}

	public void run() {
		// System.out.print("Processing ");
		while (showProgress) {
			//System.out.print(charToPrint);
			try {
				synchronized(lockObj){
					System.out.print(charToPrint);
				}
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}
}

class ProgressCharRun implements Runnable {
	private boolean showProgress = true;
	private char charToPrint;
	private Object lockObj;

	public ProgressCharRun(char charToPrint, Object obj) {
		super();
		this.charToPrint = charToPrint;
		this.lockObj = obj;
	}

	public void setShowProgress(boolean showProgress) {
		this.showProgress = showProgress;
	}

	public void run() {
		// System.out.print("Processing ");
		while (showProgress) {
			//System.out.print(charToPrint);
			try {
				synchronized(lockObj){
					System.out.print(charToPrint);
				}
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}
}