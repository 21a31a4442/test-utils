package com.pvs.cms.configurations.commodity.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class CommodDataProvider {

	@DataProvider(name = "addCommodity")
	public static Object[][] addCommodityData() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\cms\\Configurations\\Commodity\\addCommodity.json"; // Path

		List<Object[]> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] commodityDetails = new Object[] { 
							data.get("commodityName"), // Commodity Name
							data.get("brand"), // Brand
							data.get("marking"), // Marking
							data.get("bagWeight"), // Bag Weight
							data.get("emptyBagWeight"), // Empty Bag Weight
							data.get("cargoType"), // Cargo Type
					};
					dataList.add(commodityDetails);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}

	@DataProvider(name = "editCommodity")
	public static Object[][] editCommodityData() throws IOException {
		String filePath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\cms\\Configurations\\Commodity\\editCommodity.json"; // Path

		List<Object[]> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] commodityDetails = new Object[] { 
							data.get("searchCommodityName"), // Search Commodity Name
							data.get("searchBrand"),
							data.get("commodityName"), // Commodity Name
							data.get("brand"), // Brand
							data.get("marking"), // Marking
							data.get("bagWeight"), // Bag Weight
							data.get("emptyBagWeight"), // Empty Bag Weight
							data.get("cargoType"), // Cargo Type
							data.get("status"), // Status (for Edit)
					};
					dataList.add(commodityDetails);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
}
