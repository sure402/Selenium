package com.suresh.strings;

import java.util.Scanner;

public class BinaryToDecimal {

	public int getDecimalFromBinary(int binary){
        
        int decimal = 0;
        int power = 0;
        while(true){
            if(binary == 0){
                break;
            } else {
                int tmp = binary%10;
                decimal += tmp*Math.pow(2, power);
                binary = binary/10;
                power++;
            }
        }
        return decimal;
    }
     
    public static void main(String a[]){
        BinaryToDecimal bd = new BinaryToDecimal();
        System.out.println("11 ===> "+bd.getDecimalFromBinary(11));
        System.out.println("110 ===> "+bd.getDecimalFromBinary(110));
        System.out.println("11001 ===> "+bd.getDecimalFromBinary(11001));
    }
}
