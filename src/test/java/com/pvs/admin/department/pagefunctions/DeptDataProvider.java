package com.pvs.admin.department.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class DeptDataProvider {

	@DataProvider(name = "addDept")
	public static Object[][] addDept() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\Department\\addDept.json";
		List<Object> dataList = new ArrayList<>();
		
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] addDept = new Object[] {
							data.get("deptName"),
							data.get("deptCode"),
							data.get("status")
					};
					dataList.add(addDept);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
	
	@DataProvider(name = "editDept")
	public static Object[][] editDept() throws IOException{
		String filepath = System.getProperty("user.dir")+"\\src\\test\\resources\\testData\\admin\\Department\\editDept.json";
		List<Object> dataList = new ArrayList<>();
		
		try(JsonParser parser = JsonUtil.getJsonParser(filepath)){
			JsonToken token = parser.nextToken();
			if(token == JsonToken.START_ARRAY) {
				Map<String, String> data;
				while((data = JsonUtil.readNextData(parser)) != null) {
					Object[] editDept = new Object[] {
							data.get("searchDept"),
							data.get("searchCode"),
							data.get("deptName"),
							data.get("deptCode"),
							data.get("status")
					};
					dataList.add(editDept);
				}
			}
		}
		return dataList.toArray(new Object[0][]);
	}
	
}
