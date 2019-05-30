package com.suresh.general;

// Java implementation using Hashing
import java.io.*;
 

//O(n) complexity
class TwoSumProblemWithLessComplexity
{
    private static final int MAX = 100000; // Max size of Hashmap
 
    static void printpairs(int arr[],int sum)
    {
        // Declares and initializes the whole array as false
        boolean[] binmap = new boolean[MAX];
        int count =0;
        for (int i=0; i<arr.length; ++i)
        {
            int temp = sum-arr[i];
 
            // checking for condition
            if (temp>=0 && binmap[temp])
            {
            	count++;
                System.out.println("Pair with given sum " +
                                    sum + " is (" + arr[i] +
                                    ", "+temp+")"+" " +"and count of such pairs"+" "+count);
            }
            binmap[arr[i]] = true;
        }
    }
 
    // Main to test the above function
    public static void main (String[] args)
    {
        int A[] = {1, 4, 45, 6, 10, 8};
        int sum = 16;
        printpairs(A,  sum);
    }
}
 
// This article is contributed by Aakash Hasija
