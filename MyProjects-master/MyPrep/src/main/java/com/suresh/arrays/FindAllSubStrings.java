package com.suresh.arrays;

public class FindAllSubStrings {

	public static void main(String[] args) {
		String s1 = "ABC";
		char[] c1 = s1.toCharArray();
		for(int i=0; i<c1.length;i++) {
			for(int j=i; j<c1.length;j++) {
				for(int k=i;k<=j;k++) {
					System.out.println(c1[k]+" ");
				}
			}
		}

	}

}
