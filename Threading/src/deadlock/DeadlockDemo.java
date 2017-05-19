package deadlock;

public class DeadlockDemo implements Runnable{

	public static Object lock1 = new Object();
	public static Object lock2 = new Object();
	/*
	 * This method request two locks.
	 */
	public void method1() {
		synchronized (lock1) {
			try{
				System.out.println(Thread.currentThread().getName()+" aquired lock on lock1 object");
				Thread.sleep(500);
				synchronized (lock2) {
					System.out.println(Thread.currentThread().getName()+" aquired lock on lock2 object");
				}
			} catch (InterruptedException e) {
				// e.printStackTrace();
				System.out.println("Thread " + Thread.currentThread().getName()
						+ " interrupted.");
				Thread.currentThread().interrupt();
			}
		}
	}
	
	/*
	 * This method also requests same two lock but in exactly. Opposite order
	 * i.e. first Integer and then String. This creates potential deadlock, if
	 * one thread holds String lock and other holds Integer lock and they wait
	 * for each other, forever.
	 */
	public void method2() {
		synchronized (lock2) {
			try{
				System.out.println(Thread.currentThread().getName()+" aquired lock on lock2 object");
				Thread.sleep(500);
				synchronized (lock1) {
					System.out.println(Thread.currentThread().getName()+" aquired lock on lock1 object");
				}
			}catch (InterruptedException e) {
				// e.printStackTrace();
				System.out.println("Thread " + Thread.currentThread().getName()
						+ " interrupted.");
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public static void main(String[] args) {
		
		/*
		 * One way to create thread.
		 */
		/*
		Thread t = new Thread(new Runnable() { public void run() { 
			  // your code goes here... 
			}});
		*/
		
		
		/*
		 * Another way of creating thread, implementing class runnable is not required here.
		 */
		/*
		Thread t = new Thread() {
			@Override
			public void run() {
				System.out.println("Running thread "
						+ Thread.currentThread().getName());
				System.out.println(" ThreadID : "
						+ Thread.currentThread().getId());
				try {
					createDeadlock.method1();
					System.out.println("Sleeping for 500 mil");
					Thread.sleep(500);
					createDeadlock.method2();
				} catch (InterruptedException e) {
					// e.printStackTrace();
					System.out.println("Thread "
							+ Thread.currentThread().getName()
							+ " interrupted.");
					Thread.currentThread().interrupt();
				}
				System.out.println("Thread " + Thread.currentThread().getName()
						+ " finished working.");
			}
		};
		t.start();
		*/
		
		DeadlockDemo demo = new DeadlockDemo();
		Thread t1 = new Thread(demo);
		Thread t2 = new Thread(demo);
		t1.start();
		t2.start();
		
		
		
	}
	
	@Override
	public void run() {
		System.out.println("Running thread " + Thread.currentThread().getName());
		System.out.println(" ThreadID : " + Thread.currentThread().getId());		
		try {
			method1();
			//System.out.println("Sleeping for 500 mil");
			System.out.println("Sleeping for 100 mil");
			// Change the sleeping period for deadlock/ thread switch to occur. But it should be timed with method1 and 2 sleep times.
			Thread.sleep(100);
			method2();
		} catch (InterruptedException e) {
			// e.printStackTrace();
			System.out.println("Thread " + Thread.currentThread().getName()
					+ " interrupted.");
			Thread.currentThread().interrupt();
		}
		System.out.println("Thread " + Thread.currentThread().getName()
				+ " finished working.");
	}
}



/**
 * Java program to create a deadlock by imposing circular wait.
 */
class CreateDeadLock{
	public static Object lock1 = new Object();
	public static Object lock2 = new Object();
	/*
	 * This method request two locks.
	 */
	public void method1() {
		synchronized (lock1) {
			System.out.println("Aquired lock on lock1 object");
			synchronized (lock2) {
				System.out.println("Aquired lock on lock2 object");
			}
		}
	}
	
	/*
	 * This method also requests same two lock but in exactly. Opposite order
	 * i.e. first Integer and then String. This creates potential deadlock, if
	 * one thread holds String lock and other holds Integer lock and they wait
	 * for each other, forever.
	 */
	public void method2() {
		synchronized (lock2) {
			System.out.println("Aquired lock on lcok2 object");
			synchronized (lock1) {
				System.out.println("Aquired lock on lock1 object");
			}
		}
	}
}