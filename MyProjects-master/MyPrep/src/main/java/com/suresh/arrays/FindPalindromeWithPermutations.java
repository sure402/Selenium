package com.suresh.arrays;

import java.util.ArrayList;
import java.util.List;


//https://javarevisited.blogspot.com/2015/08/how-to-find-all-permutations-of-string-java-example.html
class PermutationAndPalindromeCheck{
	
	static List<String> list = new ArrayList<String>();
	public static List<String> permutation(String prefix, String str) {
		
	    int n = str.length();
	    String s2=null;
	    if (n == 0) {
	    	list.add(prefix);
	    }
	   
	    else {
	    	
	        for (int i = 0; i < n; i++) {
	        	permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n));
	        }
	    }
	    
	    return list;
	}
}
	
public class FindPalindromeWithPermutations {
	
	public static void main(String[] args) {
		
		String s1 = "aab";
		System.out.println(PermutationAndPalindromeCheck.permutation("", s1));
	}
}