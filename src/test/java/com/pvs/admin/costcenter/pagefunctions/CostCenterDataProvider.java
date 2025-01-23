package com.pvs.admin.costcenter.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class CostCenterDataProvider {

	@DataProvider(name = "addCostcenter")
	public static Object[][] addEntity() throws IOException {
		String filepath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\admin\\Costcenter\\addCostcenter.json";

		List<Object> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filepath)) {
			JsonToken token = parser.nextToken();

			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] addCostcenter = new Object[] { 
							data.get("CcCode"), data.get("EntityParent"),data.get("incDate"), 
							data.get("gstNum"), data.get("address1"), data.get("address2"),
							data.get("city"), data.get("state"), data.get("country"), 
							data.get("pincode"),data.get("primaryContact"), data.get("status")

					};
					dataList.add(addCostcenter);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}

	@DataProvider(name = "editCostcenter")
	public static Object[][] editEntity() throws IOException {
		String filepath = System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\admin\\Costcenter\\editCostcenter.json";

		List<Object> dataList = new ArrayList<>();
		try (JsonParser parser = JsonUtil.getJsonParser(filepath)) {
			JsonToken token = parser.nextToken();

			if (token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {
					Object[] editCostcenter = new Object[] { 
							data.get("searchCcCode"),
							data.get("CcCode"), data.get("EntityParent"),data.get("incDate"), 
							data.get("gstNum"), data.get("address1"), data.get("address2"),
							data.get("city"), data.get("state"), data.get("country"), 
							data.get("pincode"),data.get("primaryContact"), data.get("status")

					};
					dataList.add(editCostcenter);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}

}
