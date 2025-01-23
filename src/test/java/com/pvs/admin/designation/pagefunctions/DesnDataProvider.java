package com.pvs.admin.designation.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class DesnDataProvider {

	@DataProvider(name = "addDesn")
	public static Object[][] addDesn() throws IOException {
		String filepath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\admin\\Designation\\addDesn.json";
		List<Object> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filepath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] addDesn = new Object[] { 
							data.get("desnName"), 
							data.get("desnCode"), 
							data.get("status") 
						};
					dataList.add(addDesn);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}

	@DataProvider(name = "editDesn")
	public static Object[][] editDesn() throws IOException {
		String filepath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\admin\\Designation\\editDesn.json";
		List<Object> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filepath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] editDesn = new Object[] { data.get("searchDesn"), data.get("searchCode"),
							data.get("desnName"), data.get("desnCode"), data.get("status") };
					dataList.add(editDesn);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}

}
