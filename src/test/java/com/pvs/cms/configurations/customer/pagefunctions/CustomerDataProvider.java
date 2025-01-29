package com.pvs.cms.configurations.customer.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class CustomerDataProvider {
	
	@DataProvider(name = "addCustomer")
	public static Object[][] addCustomerData() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\cms\\Configurations\\Customer\\addCustomer.json"; // Path

		List<Object[]> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] customerDetails = new Object[] { 
							data.get("customerName"), // Customer Name
							data.get("address1"), // Address 1
							data.get("address2"), // Address 2
							data.get("pincode"), // Pincode
							data.get("city"), // City
							data.get("state"), // State
							data.get("country"), // Country
							data.get("primaryContact"), // Primary Contact
							data.get("status") // Status
					};
					dataList.add(customerDetails);
				}
			}
		}

		return dataList.toArray(new Object[0][]);
	}

	@DataProvider(name = "editCustomer")
	public static Object[][] editCustomerData() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\admin\\Customer\\editCustomer.json"; // Path

		List<Object[]> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] customerDetails = new Object[] { 
							data.get("searchCustomer"), // Old Customer Name (Search
							data.get("customerName"), // New Customer Name (Updated)
							data.get("address1"), // Address 1
							data.get("address2"), // Address 2
							data.get("pincode"), // Pincode
							data.get("city"), // City
							data.get("state"), // State
							data.get("country"), // Country
							data.get("primaryContact"), // Primary Contact
							data.get("status") // Status
					};
					dataList.add(customerDetails);
				}
			}
		}

		return dataList.toArray(new Object[0][]);
	}
}
