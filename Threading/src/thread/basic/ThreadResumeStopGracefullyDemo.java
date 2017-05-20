package thread.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// http://docs.oracle.com/javase/8/docs/technotes/guides/concurrency/threadPrimitiveDeprecation.html
// http://geekexplains.blogspot.se/2008/07/alternatives-of-stop-suspend-resume-of.html
// http://geekexplains.blogspot.se/2008/06/thread-problem-whatll-threadobject-null.html
// https://www.tutorialspoint.com/java/java_thread_control.htm
// http://stackoverflow.com/questions/11856287/how-to-stop-and-resume-thread-safely-in-java
// http://stackoverflow.com/questions/1036754/difference-between-wait-and-sleep
// http://stackoverflow.com/questions/3194545/how-to-stop-a-java-thread-gracefully
class MyThread1 implements Runnable {
	private String threadName;
	private volatile Thread theCurrentThread;
	private volatile boolean needToPause = false;
	
	public MyThread1(String name){
		threadName = name;
		System.out.println("Creating thread "+threadName);
	}
	
	public void startThread(){
		System.out.println("Starting thread " + threadName);
		if(theCurrentThread == null){
			theCurrentThread = new Thread(this, threadName);
			theCurrentThread.start();
		}
	}
	
	protected Thread getCurrentThread(){
		return theCurrentThread;
	}

	// Testing suspend - resume of thread
	@Override
	public void run() {
		System.out.println("Running thread "+theCurrentThread.getName());
		//theCurrentThread = Thread.currentThread();
		System.out.println(" ThreadID : " + theCurrentThread.getId());
		try {
			for(int i = 5; i > 0; i--) {
	            System.out.println("Thread : " + threadName + ", " + i);
	            // Let the thread sleep for a while.
	            Thread.sleep(1000);
	            pausePoint();	// place where you want to pause the thread
	            // Below for suspend-resume without method pausePoint()
	            /*
	            synchronized(this) {
	               while(needToPause) {
	                  wait();
	               }
	            }
	            */
			}
		} catch (InterruptedException e) {
			//e.printStackTrace();
			System.out.println("Thread " +  threadName + " interrupted.");
			theCurrentThread.interrupt();
		}
		System.out.println("Thread " +  threadName + " finished working.");
	}
	
	private synchronized void pausePoint() throws InterruptedException {
		//System.out.println("Thread " +  threadName + " is being paused.");
        while (needToPause) {
            wait();
        }
        //System.out.println("Thread " +  threadName + " is being un-paused.");
    }

    public synchronized void pause() {
    	System.out.println("Thread " +  threadName + " is being paused.");
        needToPause = true;
    }

    public synchronized void resume() {
    	System.out.println("Thread " +  threadName + " is being un-paused.");
        needToPause = false;
        this.notify();
        //this.notifyAll();
    }
	
	public void stop() {
		System.out.println("Thread "+ threadName+" is being stopped.");
		Thread t = theCurrentThread;
		theCurrentThread = null;
		t.interrupt();
	}
}

public class ThreadResumeStopGracefullyDemo{

	public static void main(String a[]) {
		/*
		ExecutorService executor = Executors.newFixedThreadPool(3);
		executor.submit(new MyThread1());
		executor.submit(new MyThread1());
		executor.submit(new MyThread1());
		executor.shutdownNow();
		*/
		
		MyThread1 t1 =  new MyThread1("A");
		MyThread1 t2 =  new MyThread1("B");
		//Thread t1 = new Thread(new MyThread1());
		t1.startThread();
		t2.startThread();
		//t1.interrupt();
		//t1.stop();
		try {
			//Thread.sleep(2000);
			
			t1.pause();
			t1.getCurrentThread().sleep(5000);
			//t1.pausePoint();
			//Thread.sleep(1000);
			//t1.getCurrentThread().sleep(1000);
			t1.resume();
			
			t2.pause();
			t2.getCurrentThread().sleep(500);
			//t2.pausePoint();
			//Thread.sleep(1000);
			t2.resume();
			
			//t1.resume();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("Thread interrupted.");
		}
		try {
			System.out.println("Waiting for threads to finish.");
			t1.getCurrentThread().join();
			t2.getCurrentThread().join();
			System.out.println("Threads finished their work.");
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		t1.stop();
		t2.stop();
	    System.out.println("Main thread exiting.");
	    
	    
	    
	    
	    
		/*
		Integer l1 =10;
		Integer l2;
		Integer l3;
		l2 = l1;
		l2++;
		l3=l1;
		System.out.println(l3.equals(l1));
		System.out.println(l2 ==l3);
		System.out.println(l1+ " " +l2+ " " +l3);
		*/
	}
}