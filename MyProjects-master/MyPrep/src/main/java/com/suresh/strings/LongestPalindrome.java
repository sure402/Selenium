package com.suresh.strings;

class LongestPalindromeSolution {
	
	public static String longestPalindrome(String s) {

		boolean n = false;

		StringBuffer sbf = new StringBuffer(s);

		String reverseStr = new String(sbf.reverse().toString());
		if (s.equals(reverseStr)) {

			return reverseStr;

		}

		for (int i = 0; i <= s.length(); i++) {
			for (int j = 0; j < i + 1; j++) {
				if (reverseStr.contains(s.substring(j, j + s.length() - i))) {

					reverseStr = new String(s.substring(j, j + s.length() - i));
					n = true;
					break;

				}
			}
			if (n) {
				break;
			}

		}
		return longestPalindrome(reverseStr);

	}

}

public class LongestPalindrome {

	public static void main(String[] args) {
		String s1= "babad";
		System.out.println(LongestPalindromeSolution.longestPalindrome(s1));

	}

}