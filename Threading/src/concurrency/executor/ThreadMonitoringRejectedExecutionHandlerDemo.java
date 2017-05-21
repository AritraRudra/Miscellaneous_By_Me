package concurrency.executor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ThreadPoolExecutor provides several methods using which we can find out the
 * current state of executor, pool size, active thread count and task count. So
 * this is a monitor thread that will print the executor information at certain
 * time interval.
 * 
 * Example from
 * http://www.journaldev.com/1069/threadpoolexecutor-java-thread-pool-example-executorservice
 *
 */
public class ThreadMonitoringRejectedExecutionHandlerDemo {

	public static void main(String args[]) throws InterruptedException {
		// RejectedExecutionHandler implementation
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
		// Get the ThreadFactory implementation to use
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		// creating the ThreadPoolExecutor
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
		// start the monitoring thread
		MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
		Thread monitorThread = new Thread(monitor);
		monitorThread.start();
		// submit work to the thread pool
		for (int i = 0; i < 10; i++) {
			executorPool.execute(new MyThread_1("cmd" + i));
		}

		Thread.sleep(30000);
		// shut down the pool
		executorPool.shutdown();
		// shut down the monitor thread
		Thread.sleep(5000);
		monitor.shutdown();
	}
}