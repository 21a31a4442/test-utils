package com.pvs.cms.userManagement.contact.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class ContactDataProvider {
	
	@DataProvider(name = "addContact")
	public static Object[][] addContactData() throws IOException {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\UserManagement\\Contact\\addContact.json"; // Path

	    List<Object[]> dataList = new ArrayList<>();

	    try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
	        JsonToken token = parser.nextToken();
	        if (token == JsonToken.START_ARRAY) {

	            Map<String, String> data;
	            while ((data = JsonUtil.readNextData(parser)) != null) {

	                Object[] contactDetails = new Object[] {
	                        data.get("firstName"), // First Name
	                        data.get("lastName"), // Last Name
	                        data.get("dob"), // Date of Birth
	                        data.get("email"), // Email
	                        data.get("mobile"), // Mobile
	                        data.get("address1"), // Address 1
	                        data.get("address2"), // Address 2
	                        data.get("pincode"), // Pincode
	                        data.get("city"), // City
	                        data.get("state"), // State
	                        data.get("country"), // Country
	                        data.get("contactType") // Contact Type
	                };
	                dataList.add(contactDetails);
	            }
	        }
	    }

	    return dataList.toArray(new Object[0][]);
	}

	
	@DataProvider(name = "editContact")
	public static Object[][] editContactData() throws IOException {
	    String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\UserManagement\\Contact\\editContact.json"; // Path

	    List<Object[]> dataList = new ArrayList<>();

	    try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
	        JsonToken token = parser.nextToken();
	        if (token == JsonToken.START_ARRAY) {

	            Map<String, String> data;
	            while ((data = JsonUtil.readNextData(parser)) != null) {

	                Object[] contactDetails = new Object[] {
	                        data.get("searchEmail"), // Email to search for the contact
	                        data.get("firstName"), //  first Name
	                        data.get("lastName"), //  last Name
	                        data.get("dob"), //  Date of Birth
	                        data.get("email"), //  Email
	                        data.get("mobile"), //  Mobile
	                        data.get("address1"), //  Address 1
	                        data.get("address2"), //  Address 2
	                        data.get("pincode"), //  Pincode
	                        data.get("city"), //  City
	                        data.get("state"), //  State
	                        data.get("country"), //  Country
	                        data.get("contactType") //  Contact Type
	                };
	                dataList.add(contactDetails);
	            }
	        }
	    }

	    return dataList.toArray(new Object[0][]);
	}

}
