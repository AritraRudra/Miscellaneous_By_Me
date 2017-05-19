package thread.progressbar;

public class ProgressBarDemo_1 {

	static final boolean lockObjArray[]={true,false,false,false,false,false,false};//{null,null,null};	//First one should to be true as the first ( t1/t2/t3) thread will access this 
	public static void main(String[] args) {
		Object lockObj = null;//new Object();
		/*
		static final Object lockObjArray[]= new Object[3];
		for (int i = 0; i < lockObjArray.length; i++) {
			lockObjArray[i]=null;
		}
		*/
		ProgressBarRotating_1 runnableDemo = new ProgressBarRotating_1();
		Thread t1 = new Thread(runnableDemo);
		Thread t2 = new Thread(runnableDemo);
		Thread t3 = new Thread(runnableDemo);
		t1.start();
		t2.start();
		t3.start();
		
		try{
			Thread.sleep(6000);
		}catch(InterruptedException intrExp){
			intrExp.printStackTrace();
		}

		runnableDemo.setStop(true);
		
		try{
			t1.join();
			t2.join();
			t3.join();
		}catch(InterruptedException intrExp){
			intrExp.printStackTrace();
		}
		
		System.out.println("\nGot you.");
	}
}

class ProgressBarRotating_1 implements Runnable{
	
	private String threadName;
	private Object lockObj=null;
	//private static Object lockObjArray[];
	private boolean stop = false;

	public ProgressBarRotating_1() {
		super();
	}

	public ProgressBarRotating_1(String threadName) {
		super();
		this.threadName = threadName;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	@Override
	public void run() {
		try{
			while(!stop){
				for (int i = 0; i < ProgressBarDemo_1.lockObjArray.length; i++) {
					synchronized (ProgressBarDemo_1.lockObjArray) {
						System.out.println(Thread.currentThread().getId()+" ");
						if (ProgressBarDemo_1.lockObjArray[i]) {
							if (i == 0) {
								//System.out.print("\rWorking /");
								System.out.print("\rWaiting for Meow /");
							} else if (i == 1 || i == 3 || i == 6) {
								//System.out.print("\rWorking |");
								System.out.print("\rWaiting for Meow |");
							} else if (i == 2 || i == 4) {
								//System.out.print("\rWorking -");
								System.out.print("\rWaiting for Meow -");
							} else if (i == 5) {
								//System.out.print("\rWorking \\");
								System.out.print("\rWaiting for Meow \\");
							}
							ProgressBarDemo_1.lockObjArray[i] = false;
							if ((i + 1) == ProgressBarDemo_1.lockObjArray.length)
								ProgressBarDemo_1.lockObjArray[0] = true;
							else
								ProgressBarDemo_1.lockObjArray[i + 1] = true;
							
							/*
							ProgressBarDemo_1.lockObjArray.notifyAll();
							ProgressBarDemo_1.lockObjArray.wait();
							*/
						}
					}
					// sleep here, not outside loop
					Thread.sleep(300);
				}
			}
			//System.out.println("\nGot you.");
		}catch(InterruptedException intrExp){
			intrExp.printStackTrace();
		}
	}
}