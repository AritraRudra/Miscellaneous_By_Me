package concurrency.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class FactorialCalculator implements Callable<Long> {
	private Long number;

	public FactorialCalculator(Long number) {
		this.number = number;
	}

	@Override
	public Long call() throws Exception {
		Long result = 1L;
		if ((number == 0) || (number == 1)) {
			result = 1L;
		} else {
			for (int i = 2; i <= number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		System.out.println("Result for number - " + number + " -> " + result);
		return result;
	}
}