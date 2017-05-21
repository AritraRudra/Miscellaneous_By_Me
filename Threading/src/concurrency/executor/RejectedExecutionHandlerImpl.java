package concurrency.executor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Executors class provide simple implementation of ExecutorService using
 * ThreadPoolExecutor but ThreadPoolExecutor provides much more feature than
 * that. We can specify the number of threads that will be alive when we create
 * ThreadPoolExecutor instance and we can limit the size of thread pool and
 * create our own RejectedExecutionHandler implementation to handle the jobs
 * that can't fit in the worker queue.
 * 
 * Example from
 * http://www.journaldev.com/1069/threadpoolexecutor-java-thread-pool-example-executorservice
 *
 */
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println(r.toString() + " is rejected");
	}
}