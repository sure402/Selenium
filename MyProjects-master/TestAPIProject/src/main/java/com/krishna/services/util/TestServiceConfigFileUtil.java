package com.krishna.services.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Util class to get property value from properties file.
 *  
 * @author kabothu 
 */
public class TestServiceConfigFileUtil {

	String result = "";
	InputStream inputStream = null;
	
	public String getPropertyValue(String propFile, String key) {

		String currentDir = System.getProperty("user.dir");
		String filePath = (currentDir + propFile).replace('\\','/');
		File file = new File(filePath);

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop.getProperty(key);
	}
}