package com.chamc.demo.op;

import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntFunction;
import java.util.function.Supplier;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

@SuppressWarnings("all")
public class DemoOptionalTests {

	@Test
	public void test1() {
		List<String> list = new ArrayList<String>();
		list.add("1"); list.add("2"); list.add("3");

		list.stream().filter(x -> {
			System.out.println(x);
			return true;
		});
		
		list.stream().filter(x -> {
			System.out.println(x);
			return true;
		}).count();
	}
	
	@Test
	public void test2() {
		Integer v1 = Integer.valueOf(0);
		 
		Supplier<Integer> v2 = () -> Integer.valueOf(0);
	}
	
	@Test
	public void test3() {
		System.out.println(LazyCompute.of(() -> compute(42))
		  .map(t -> compute(13))
		  .flatMap(t -> lazyCompute(15))
		  .filter(v -> v > 0)
		  .get()
		  .orElse(-1));
	}
	
	private static int compute(int val) {
        int result = ThreadLocalRandom.current().nextInt() % val;
        System.out.println("Computed: " + result);
        return result;
    }

    private static LazyCompute<Integer> lazyCompute(int val) {
        return LazyCompute.of(() -> {
            int result = ThreadLocalRandom.current().nextInt() % val;
            System.out.println("Computed: " + result);
            return result;
        });
    }
    
    @Test
	public void test4() {
		IntFunction<Integer> currPrice = Curring.curringInt(items ->
	    	items.stream().mapToInt(i -> i).sum());
		
		currPrice.apply(1);
		currPrice.apply(2);
		currPrice.apply(3);
		currPrice.apply(4);

		int result = currPrice.apply(Integer.MAX_VALUE);
		assertThat(result, IsEqual.equalTo(10));
	}

}
