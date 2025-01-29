package com.pvs.cms.userManagement.user.pagefunctions;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserMasterDataProvider {
	 @DataProvider(name = "addUser")
	    public static Object[][] addUserData() throws IOException {
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\UserManagement\\User\\addUser.json"; // Path to the JSON file

	        List<Object[]> dataList = new ArrayList<>();

	        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
	            JsonToken token = parser.nextToken();
	            if (token == JsonToken.START_ARRAY) {
	                Map<String, String> data;
	                while ((data = JsonUtil.readNextData(parser)) != null) {
	                    Object[] userDetails = new Object[]{
	                            data.get("username"),  // Username
	                            data.get("contact"),   // Contact
	                            data.get("userRole")   // User Role
	                    };
	                    dataList.add(userDetails);
	                }
	            }
	        }

	        return dataList.toArray(new Object[0][]);
	    }

	    @DataProvider(name = "editUser")
	    public static Object[][] editUserData() throws IOException {
	        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\UserManagement\\User\\editUser.json"; // Path to the JSON file

	        List<Object[]> dataList = new ArrayList<>();

	        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
	            JsonToken token = parser.nextToken();
	            if (token == JsonToken.START_ARRAY) {
	                Map<String, String> data;
	                while ((data = JsonUtil.readNextData(parser)) != null) {
	                    Object[] userDetails = new Object[]{
	                            data.get("searchUser"),    // Username to search for
	                            data.get("userRole"),    // New User Role
	                            data.get("userStatus")   // New User Status
	                    };
	                    dataList.add(userDetails);
	                }
	            }
	        }

	        return dataList.toArray(new Object[0][]);
	    }
}
