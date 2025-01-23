package com.pvs.admin.cctype.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class CcTypeDataProvider {

	@DataProvider(name = "addCcType")
	public static Object[][] addCcType() throws IOException{
		
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\Cctype\\addCctype.json";
		List<Object> dataList = new ArrayList<>();
		
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] addCctype = new Object[]{
						data.get("CostCenterType")
					};
					dataList.add(addCctype);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
		
	}
	
	@DataProvider(name = "editCcType")
	public static Object[][] editCcType() throws IOException{
		
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\Cctype\\editCctype.json";
		List<Object> dataList = new ArrayList<>();
		
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			if(token == JsonToken.START_OBJECT) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] editCctype = new Object[] {
							data.get("searchCctype"),
							data.get("CostCenterType")
					};
					dataList.add(editCctype);
				}
			}	
		}
		return dataList.toArray(new Object[0][]);
	}
	
}
