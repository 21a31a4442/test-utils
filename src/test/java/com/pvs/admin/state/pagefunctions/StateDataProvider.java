package com.pvs.admin.state.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class StateDataProvider {

	@DataProvider(name = "addState")
	public static Object[][] addState() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\State\\addState.json";
		List<Object> dataList = new ArrayList<>();
		
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] addState = new Object[] {
						data.get("state"),
						data.get("country")
					};
					dataList.add(addState);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
	

	@DataProvider(name = "editState")
	public static Object[][] editState() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\State\\editState.json";
		List<Object> dataList = new ArrayList<>();
		
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] editState = new Object[] {
						data.get("searchState"),
						data.get("state"),
						data.get("country")
					};
					dataList.add(editState);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
}
