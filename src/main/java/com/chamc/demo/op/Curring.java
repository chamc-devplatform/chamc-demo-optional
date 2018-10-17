package com.chamc.demo.op;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntFunction;

public class Curring {
	public static IntFunction<Integer> curringInt(Function<List<Integer>, Integer> fn) {
		final List<Integer> result = new ArrayList<>();
		return arg -> {
			if (arg != Integer.MAX_VALUE) {
				result.add(arg);
			} else {
				return fn.apply(result);
			}
			return null;
		};
	}
}
