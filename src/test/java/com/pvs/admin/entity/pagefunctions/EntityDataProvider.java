package com.pvs.admin.entity.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class EntityDataProvider {

	@DataProvider(name = "addEntity")
	public static Object[][] addEntity() throws IOException {
		String filepath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\admin\\Entity\\addEntity.json";

		List<Object> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filepath)) {
			JsonToken token = parser.nextToken();

			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] addEntity = new Object[] { 
							data.get("Name"), data.get("CcCode"), 
							data.get("CIN"), data.get("incDate"), 
							data.get("gstNum"), data.get("address1"), 
							data.get("address2"), data.get("city"), 
							data.get("state"), data.get("country"), 
							data.get("pincode"), data.get("primaryContact"),
							data.get("status")
							
							
					
					};
					dataList.add(addEntity);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}

	@DataProvider(name = "editEntity")
	public static Object[][] editEntity() throws IOException {
		String filepath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\admin\\Entity\\editEntity.json";

		List<Object> dataList = new ArrayList<>();
		try (JsonParser parser = JsonUtil.getJsonParser(filepath)) {
			JsonToken token = parser.nextToken();

			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] editEntity = new Object[] { 
							data.get("searchEntity"), 
							data.get("entityName"), data.get("CIN"), 
							data.get("incDate"), data.get("status"),
							
							
							};
					dataList.add(editEntity);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}

}
