package concurrency.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args){

        BlockingQueue<BlockingQueueMessage> queue = new ArrayBlockingQueue<BlockingQueueMessage>(1024);

        BlockingQueueProducer producer = new BlockingQueueProducer(queue);
        BlockingQueueConsumer consumer = new BlockingQueueConsumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        try {
        	//Thread.sleep(4000);        
			Thread.currentThread().sleep(4000);// Give all child threads enough time to complete. 
		} catch (InterruptedException e) {
			System.out.println("In interrupted catch block");
			Thread.currentThread().interrupt();//preserve the message
		    return;//Stop doing whatever I am doing and terminate
		}
        //return;
        System.exit(0);
    }
}