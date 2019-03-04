package Thread;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class FactorialCalculator implements Callable<Integer>{

	private Integer number ;
	
	
	public FactorialCalculator(Integer number) {
		//super();
		this.number = number;
	}

	@Override
	public Integer call() throws Exception {
		int result = 1;
        if ((number == 0) || (number == 1)) {
            result = 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(200);
            }
        }
        System.out.println("Result for number - " + number + " -> " + result);
        return result;
	}

}
