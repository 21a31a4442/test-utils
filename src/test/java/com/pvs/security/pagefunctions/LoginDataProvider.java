package com.pvs.security.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class LoginDataProvider {
	@DataProvider(name = "loginData")
	public Object[][] getLoginData() throws IOException {
		String filePath = "src\\test\\resources\\testData\\security\\Login\\loginData.json"; // Path

		List<Object[]> dataList = new ArrayList<>();

		try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
			JsonToken token = parser.nextToken();
			if (token == JsonToken.START_ARRAY) {

				Map<String, String> data;
				while ((data = JsonUtil.readNextData(parser)) != null) {

					Object[] loginDetails = new Object[] {
							data.get("username"), // Username
							data.get("password"), // Password
					};
					dataList.add(loginDetails);
				}
			}
		}

		return dataList.toArray(new Object[0][]);
	}

}
