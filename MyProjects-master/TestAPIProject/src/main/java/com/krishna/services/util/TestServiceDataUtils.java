package com.krishna.services.util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Util class to combine two data providers.This will be very useful if we want to use same url with multiple combinations
 * of data.
 * 
 *  
 * @author kabothu 
 */

public class TestServiceDataUtils {
	
	private static final TestServiceConfigFileUtil configFileUtil = new TestServiceConfigFileUtil();
	
	public static Object[][] combine(Object[][] a1, Object[][] a2){
        List<Object[]> objectCodesList = new LinkedList<Object[]>();
        for(Object[] o : a1){
            for(Object[] o2 : a2){
            	objectCodesList.add(concatAll(o, o2));
            }
        }
        return objectCodesList.toArray(new Object[0][0]);
    }
	
	@SafeVarargs
	public static <T> T[] concatAll(T[] first, T[]... rest) {
		// calculate the total length of the final object array after the concat
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		// copy the first array to result array and then copy each array
		// completely to result
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		
		for (T[] array : rest) {
			int i = 0;
			while(i < array.length) {
				result[offset] = array[i];
				i++;
				offset++;
			}	
		}
		return result;
	}
}