package com.pvs.admin.status.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class StatusDataProvider {
	@DataProvider(name = "addStatus")
	public static Object[][] addStatusData() throws IOException {
		String filePath = System.getProperty("user.dir") +"\\src\\test\\resources\\testData\\admin\\Status\\addStatus.json"; // Path
	
		List<Object[]> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {

				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {

					Object[] statusDetails = new Object[] { 
							data.get("statusCode"), // Status code
							data.get("statusName"), // Status name
							data.get("statusModule") // Status module
					};
					dataList.add(statusDetails);
				}
			}
		}

		return dataList.toArray(new Object[0][]);
	}

	@DataProvider(name = "editStatus")	
	public static Object[][] editStatusData() throws IOException {
		String filePath = System.getProperty("user.dir") +"\\src\\test\\resources\\testData\\admin\\Status\\editStatus.json"; // Path
	
		List<Object[]> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {

				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {

					Object[] statusDetails = new Object[] {
							data.get("searchStatus"), // Search status
							data.get("searchModule"), // Search module
							data.get("statusCode"), // Status code
							data.get("statusName"), // Status name
							data.get("statusModule") // Status module
					};
					dataList.add(statusDetails);
				}
			}
		}

		return dataList.toArray(new Object[0][]);
	}
	
}
