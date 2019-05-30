package com.suresh.strings;

import java.util.Arrays;

public class RotateAString {
	
	public static void leftRotate(String[] s1, int n) {
		
		String temp = null;
		for(int i=0;i<n;i++) {
			temp = s1[0];
			
			for(int j=0;j<s1.length-1;j++) {
				s1[j] = s1[j+1];
			}
	        s1[s1.length-1] = temp;
		}
		System.out.println(Arrays.toString(s1));
	}
	
	public static void main(String[] args) {
		
		String s1 = "Hello How are you my friend";
		String[] s1Array = s1.split(" ");
		
		RotateAString.leftRotate(s1Array, 2);
		
		
	}

}
