package concurrency.forkjoinpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * This example creates a ForkJoinPool with a parallelism level of 4 used by {@link RecursiveAction}
 * A {@link RecursiveAction} is a task which does not return any value. It just does some work, e.g. writing data to disk, and then exits.
 * 
 * Example from http://tutorials.jenkov.com/java-util-concurrent/java-fork-and-join-forkjoinpool.html
 */
public class MyRecursiveActionDemo{
	public static void main(String a[]){
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		MyRecursiveAction myRecursiveAction = new MyRecursiveAction(96);//24,48
		forkJoinPool.invoke(myRecursiveAction);
	}
}

class MyRecursiveAction extends RecursiveAction {

	private long workLoad = 0;

    public MyRecursiveAction(long workLoad) {
        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {

        //if work is above threshold, break tasks up into smaller tasks
        if(this.workLoad > 16) {
            System.out.println("Splitting workLoad : " + this.workLoad);

            List<MyRecursiveAction> subtasks =
                new ArrayList<MyRecursiveAction>();

            subtasks.addAll(createSubtasks());

            for(RecursiveAction subtask : subtasks){
                subtask.fork();
            }

        } else {
            System.out.println("Doing workLoad myself: " + this.workLoad);
        }
    }

    private List<MyRecursiveAction> createSubtasks() {
        List<MyRecursiveAction> subtasks =
            new ArrayList<MyRecursiveAction>();

        MyRecursiveAction subtask1 = new MyRecursiveAction(this.workLoad / 2);
        MyRecursiveAction subtask2 = new MyRecursiveAction(this.workLoad / 2);

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }
}