package concurrency.forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * This example creates a ForkJoinPool with a parallelism level of 4 used by {@link RecursiveTask}
 * 
 * A {@link RecursiveTask} is a task that returns a result. It may split its work up
 * into smaller tasks, and merge the result of these smaller tasks into a
 * collective result. The splitting and merging may take place on several
 * levels.
 * 
 * Example from http://tutorials.jenkov.com/java-util-concurrent/java-fork-and-join-forkjoinpool.html
 *
 */
public class MyRecursiveTaskDemo {
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);//8,16
		MyRecursiveTask myRecursiveTask = new MyRecursiveTask(128);

		// Get the final result out from the ForkJoinPool.invoke() method call.
		long mergedResult = forkJoinPool.invoke(myRecursiveTask);

		System.out.println("mergedResult = " + mergedResult);
	}
}


/**
 * The class MyRecursiveTask extends RecursiveTask<Long> which means that the
 * result returned from the task is a Long .
 * 
 * The MyRecursiveTask example also breaks the work down into subtasks, and
 * schedules these subtasks for execution using their fork() method.
 * 
 * Additionally, this example then receives the result returned by each subtask
 * by calling the join() method of each subtask. The subtask results are merged
 * into a bigger result which is then returned. This kind of joining / mergining
 * of subtask results may occur recursively for several levels of recursion.
 */
class MyRecursiveTask extends RecursiveTask<Long> {

	private long workLoad = 0;

	public MyRecursiveTask(long workLoad) {
		this.workLoad = workLoad;
	}

	protected Long compute() {

		// if work is above threshold, break tasks up into smaller tasks
		if (this.workLoad > 16) {
			System.out.println("Splitting workLoad : " + this.workLoad);

			List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>();
			subtasks.addAll(createSubtasks());

			for (MyRecursiveTask subtask : subtasks) {
				subtask.fork();
			}

			long result = 0;
			for (MyRecursiveTask subtask : subtasks) {
				result += subtask.join();
			}
			return result;

		} else {
			System.out.println("Doing workLoad myself: " + this.workLoad);
			return workLoad * 3;
		}
	}

	private List<MyRecursiveTask> createSubtasks() {
		List<MyRecursiveTask> subtasks = new ArrayList<MyRecursiveTask>();

		MyRecursiveTask subtask1 = new MyRecursiveTask(this.workLoad / 2);
		MyRecursiveTask subtask2 = new MyRecursiveTask(this.workLoad / 2);

		subtasks.add(subtask1);
		subtasks.add(subtask2);

		return subtasks;
	}
}