package concurrency.blockingqueue;

import java.util.concurrent.BlockingQueue;

public class BlockingQueueProducer implements Runnable{
	private BlockingQueue<BlockingQueueMessage> msgQueue;

	public BlockingQueueProducer(BlockingQueue<BlockingQueueMessage> msgQueue) {
		this.msgQueue = msgQueue;
	}

	@Override
	public void run() {
		// producing messages
		for (int i = 0; i < 5; i++) {
			BlockingQueueMessage msg = new BlockingQueueMessage("I'm msg " + i);
			try {
				Thread.sleep(100);
				msgQueue.put(msg);
				System.out.println("BlockingQueueProducer : Message - " + msg.getMessage() + " produced.");
			} catch (Exception e) {
				System.out.println("Exception:" + e);
			}
		}

		// adding exit message
		BlockingQueueMessage msg = new BlockingQueueMessage(
				"All done from Producer side. Produced 5 BlockingQueueMessages");
		try {
			msgQueue.put(msg);
			System.out.println("BlockingQueueProducer : Exit Message - " + msg.getMessage());
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}
}