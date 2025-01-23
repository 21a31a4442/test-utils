package com.pvs.admin.city.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class CityDataProvider {
	@DataProvider(name = "addCity")
	public static Object[][] addCity() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\City\\addCity.json";
		List<Object> dataList = new ArrayList<>();
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser))!=null) {
					Object[] addCity = new Object[] {
							data.get("cityName"),
							data.get("state"),
							data.get("country")
					};
					dataList.add(addCity);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
	
	@DataProvider(name = "editCity")
	public static Object[][] editCity() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\City\\editCity.json";
		List<Object> dataList = new ArrayList<>();
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser))!=null) {
					Object[] editCity = new Object[] {
							data.get("searchCity"),
							data.get("cityName"),
							data.get("state"),
							data.get("country")
					};
					dataList.add(editCity);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
	
}
