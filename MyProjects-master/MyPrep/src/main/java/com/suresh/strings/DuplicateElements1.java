package com.suresh.strings;

import java.util.HashSet;

public class DuplicateElements1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] nums = { 1, 1, 2, 5, 3, 4, 73, 6, 2, 2, 47 };

		HashSet<Integer> numbers = new HashSet<Integer>();

		for (int n : nums)

		{

			numbers.add(n);

		}

		for (int k : numbers)

		{

			System.out.println(k);

		}

	}

}
