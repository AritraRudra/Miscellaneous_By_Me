package concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class BlockingQueueConsumer implements Runnable {

	private BlockingQueue<BlockingQueueMessage> msgQueue;

	public BlockingQueueConsumer(BlockingQueue<BlockingQueueMessage> queue) {
		this.msgQueue = queue;
	}

	@Override
	public void run() {
		try {
			BlockingQueueMessage msg;
			// consuming messages until exit message is received
			while (! (msg = msgQueue.take()).getMessage().contains("Exit")){
				Thread.sleep(100);
				System.out.println("BlockingQueueConsumer : Message - " + msg.getMessage() + " consumed.");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}