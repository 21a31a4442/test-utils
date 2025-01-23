package com.pvs.admin.ctype.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class ContactTypeDataProvider {

	@DataProvider(name = "addCType")
	public static Object[][] addCtypeData() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\Ctype\\addCtype.json";
		
		List<Object> dataList = new ArrayList<>();
		
		try(JsonParser parser =  JsonUtil.getJsonParser(filepath)) {
			JsonToken token = parser.nextToken();
			
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] addCtype = new Object[] {
							data.get("name"),
							data.get("status")
					};
					dataList.add(addCtype);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
	
	@DataProvider(name = "editCType")
	public static Object[][] editCtype() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\Ctype\\editCtype.json";
		
		List<Object> dataList = new ArrayList<>();
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] editCtype = new Object[] {
							data.get("searchCtype"),
							data.get("searchStatus"),
							data.get("Ctype"),
							data.get("status")
					};
					dataList.add(editCtype);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
	
}
