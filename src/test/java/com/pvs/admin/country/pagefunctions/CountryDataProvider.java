package com.pvs.admin.country.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class CountryDataProvider {

	@DataProvider(name = "addCountry")
	public static Object[][] addCountry() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\Country\\addCountry.json";
		List<Object> dataList = new ArrayList<>();
		
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] addCountry = new Object[] {
							data.get("country"),
							data.get("code"),
							data.get("currency")
					};
					dataList.add(addCountry);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
	
	@DataProvider(name = "editCountry")
	public static Object[][] editCountry() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\Country\\editCountry.json";
		List<Object> dataList = new ArrayList<>();
		
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] editCountry = new Object[] {
							data.get("searchCountry"),
							data.get("searchCode"),
							data.get("searchCurrency"),
							data.get("newCountry"),
							data.get("newCode"),
							data.get("newCurrency")
					};
					dataList.add(editCountry);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
	
	
}
