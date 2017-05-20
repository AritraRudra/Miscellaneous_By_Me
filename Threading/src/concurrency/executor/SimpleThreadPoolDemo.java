package concurrency.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPoolDemo {
	public static void main(String[] args) {
		MyThread_1 myThreadArray[] = new MyThread_1[5];
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < myThreadArray.length; i++) {
			myThreadArray[i] = new MyThread_1(" " + i);
			executor.execute(myThreadArray[i]);
		}
		executor.shutdown();
		while (!executor.isTerminated()) {

		}
		System.out.println("Finished all threads.");
	}
}