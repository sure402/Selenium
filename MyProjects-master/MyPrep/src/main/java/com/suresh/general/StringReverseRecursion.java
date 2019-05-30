package com.suresh.general;

public class StringReverseRecursion {
	
	public static String recursiveMethod(String str)
	{
	     if ((null == str) || (str.length() <= 1))
	     {
	            return str;
	     }
	 
	     return recursiveMethod(str.substring(1)) + str.charAt(0);
	}

	public static void main(String[] args) {
		
		String s1 = "Java2novice";
		System.out.println(StringReverseRecursion.recursiveMethod(s1));

	}

}
