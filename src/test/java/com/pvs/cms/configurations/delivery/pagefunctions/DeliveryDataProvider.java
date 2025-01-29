package com.pvs.cms.configurations.delivery.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class DeliveryDataProvider {

	 @DataProvider(name = "addDelivery")
	    public static Object[][] addDeliveryData() throws IOException {
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\Configurations\\Delivery\\addDelivery.json"; // Path

	        List<Object[]> dataList = new ArrayList<>();

	        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
	            JsonToken token = parser.nextToken();
	            if (token == JsonToken.START_ARRAY) {

	                Map<String, String> data;
	                while ((data = JsonUtil.readNextData(parser)) != null) {

	                    Object[] deliveryDetails = new Object[] {
	                            data.get("deliveryName") // Delivery Name
	                    };
	                    dataList.add(deliveryDetails);
	                }
	            }
	        }

	        return dataList.toArray(new Object[0][]);
	    }

	    @DataProvider(name = "editDelivery")
	    public static Object[][] editDeliveryData() throws IOException {
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\Configurations\\Delivery\\editDelivery.json"; // Path

	        List<Object[]> dataList = new ArrayList<>();

	        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
	            JsonToken token = parser.nextToken();
	            if (token == JsonToken.START_ARRAY) {

	                Map<String, String> data;
	                while ((data = JsonUtil.readNextData(parser)) != null) {

	                    Object[] deliveryDetails = new Object[] {
	                    		data.get("searchDelivery"),
	                            data.get("deliveryName") // Delivery Name
	                    };
	                    dataList.add(deliveryDetails);
	                }
	            }
	        }

	        return dataList.toArray(new Object[0][]);
	    }

	  
}
