package com.pvs.testframe.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class PropertyUtil {

	private static Logger logger = LogManager.getLogger(PropertyUtil.class);
	private static Properties configprop = null;
	
	public PropertyUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String getProperties(String propertyName) {
		logger.info("Get the properties from Properties file");
		if (configprop == null) {
			try {
				//store the config.properties file in src/main/resources
				InputStream in = PropertyUtil.class.getClassLoader().getResourceAsStream("config.properties");
				configprop = new Properties();
				configprop.load(in);
				configprop.getProperty(propertyName);
				logger.info("propertyName: {}",propertyName);
				logger.info("configprop.getProperty(propertyName): {}",configprop.getProperty(propertyName));
			} catch (IOException io) {
				logger.info("Error while creating the config file");
			}
		}

		return configprop.getProperty(propertyName);
	}
	
//	public static void main(String[] args) {
//		String properties = getProperties("name");
//		System.out.println(properties);
//		}

}
