package com.pvs.cms.configurations.vendor.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class VendorDataProvider {

	@DataProvider(name = "addVendor")
	public static Object[][] addVendorData() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\cms\\Configurations\\Vendor\\addVendor.json"; // Path

		List<Object[]> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] vendorDetails = new Object[] { 
							data.get("vendorName"), // Vendor Name
							data.get("address1"), // Address 1
							data.get("address2"), // Address 2
							data.get("pincode"), // Pincode
							data.get("city"), // City
							data.get("state"), // State
							data.get("country"), // Country
							data.get("primaryContact"), // Primary Contact
							data.get("status") // Status
					};
					dataList.add(vendorDetails);
				}
			}
		}

		return dataList.toArray(new Object[0][]);
	}

	@DataProvider(name = "editVendor")
	public static Object[][] editVendorData() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\admin\\Vendor\\editVendor.json"; // Path

		List<Object[]> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] vendorDetails = new Object[] { 
							data.get("searchVendor"), // Old Vendor Name (Search
							data.get("vendorName"), // New Vendor Name (Updated)
							data.get("address1"), // Address 1
							data.get("address2"), // Address 2
							data.get("pincode"), // Pincode
							data.get("city"), // City
							data.get("state"), // State
							data.get("country"), // Country
							data.get("primaryContact"), // Primary Contact
							data.get("status") // Status
					};
					dataList.add(vendorDetails);
				}
			}
		}

		return dataList.toArray(new Object[0][]);
	}

}
