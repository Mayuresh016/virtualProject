package com.micropro.common.pharmazip;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestClass {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> limitedNumbers = numbers.stream()
		    .limit(3)
		    .collect(Collectors.toList());
		List<Integer> skippedNumbers = numbers.stream()
				.skip(2)
				 .filter(n -> n % 2 == 0)
				.collect(Collectors.toList());


		System.out.println(skippedNumbers );
	
		
	}
}
