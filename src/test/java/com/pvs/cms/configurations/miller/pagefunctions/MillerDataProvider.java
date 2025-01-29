package com.pvs.cms.configurations.miller.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class MillerDataProvider {

	 @DataProvider(name = "addMiller")
	    public static Object[][] addMillerData() throws IOException {
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\Configurations\\Miller\\addMiller.json"; // Path
	    
	        List<Object[]> dataList = new ArrayList<>();
	    
	        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
	            JsonToken token = parser.nextToken();
	            if (token == JsonToken.START_ARRAY) {
	                Map<String, String> data;
	                while ((data = JsonUtil.readNextData(parser)) != null) {
	                    Object[] millerDetails = new Object[] {
	                        data.get("millerName"), // Miller Name
	                        data.get("gstNumber"), // GST Number
	                        data.get("place"), // Place
	                        data.get("address"), // Address
	                        data.get("status") // Status
	                    };
	                    dataList.add(millerDetails);
	                }
	            }
	        }
	        return dataList.toArray(new Object[0][]);
	    }

	    @DataProvider(name = "editMiller")
	    public static Object[][] editMillerData() throws IOException {
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\Configurations\\Miller\\editMiller.json"; // Path
	    
	        List<Object[]> dataList = new ArrayList<>();
	    
	        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
	            JsonToken token = parser.nextToken();
	            if (token == JsonToken.START_ARRAY) {
	                Map<String, String> data;
	                while ((data = JsonUtil.readNextData(parser)) != null) {
	                    Object[] millerDetails = new Object[] {
	                        data.get("searchMillerName"), // Search Miller Name
	                        data.get("millerName"), // Miller Name
	                        data.get("gstNumber"), // GST Number
	                        data.get("place"), // Place
	                        data.get("address"), // Address
	                        data.get("status") // Status (for Edit)
	                    };
	                    dataList.add(millerDetails);
	                }
	            }
	        }
	        return dataList.toArray(new Object[0][]);
	    }
	
}
