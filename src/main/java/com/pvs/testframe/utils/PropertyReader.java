package com.pvs.testframe.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.compress.utils.FileNameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.firefox.FileExtension;

public class PropertyReader {

	private static Logger logger = LogManager.getLogger(PropertyReader.class);
	private static Properties getApplicationProp = null;

	public static synchronized Properties readPropertyFile(Path path) {
		File file = new File(path.toString());

		if (!file.exists()) {
			logger.error("File not found");
			return new Properties();
		}

		if (FileNameUtils.getExtension(path).equals("properties")) {
			getApplicationProp = new Properties();
			try {
				FileInputStream fis = new FileInputStream(file);
				getApplicationProp.load(fis);
			} catch (IOException e) {
				logger.error("Error reading properties file");
			}
		}

		else {
			logger.warn("The file is not a valid .properties file: {}",file.getName());
			return new Properties();
		}

		return getApplicationProp;
	}
	
	
	
//	public static void main(String[] args) {
//		String filepath = "D:\\testing files\\user.properties";
//		Properties properties = readPropertyFile(Paths.get(filepath));
//		if(properties!=null) {
//			String name = properties.getProperty("city");
//			System.out.println(name);
//		}
//		
//	}

}
