package concurrency.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Callable_Future_Examle {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

		List<Future<Long>> resultList = new ArrayList<>();

		Random random = new Random();

		for (int i = 0; i < 4; i++) {
			Long number = 10 + (long) random.nextInt(10);
			FactorialCalculator calculator = new FactorialCalculator(number);
			Future<Long> result = executor.submit(calculator);
			resultList.add(result);
		}

		for (Future<Long> future : resultList) {
			try {
				System.out.println(
						"Future result is - " + future.get() + "; And Task done is " + future.isDone());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		// shut down the executor service now
		executor.shutdown();
	}
}