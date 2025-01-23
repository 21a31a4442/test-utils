package com.pvs.testframe.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

public class JsonUtil {

	public static JsonParser getJsonParser(String filePath) throws IOException {
		JsonFactory factory = new JsonFactory();
		return factory.createParser(new File(filePath));
	}

	// Method to read the next JSON object as a Map<String, String>
	public static Map<String, String> readNextData(JsonParser parser) throws IOException {
		if (parser.nextToken() != JsonToken.START_OBJECT) {
			return null; // End of array or invalid format
		}
		Map<String, String> data = new HashMap<>();
		while (parser.nextToken() != JsonToken.END_OBJECT) {
			String fieldName = parser.getCurrentName();
			parser.nextToken(); // Move to value
			String value = parser.getText();
			data.put(fieldName, value);
		}
		return data;
	}
}



			
			
			
			
//			String string = Arrays.toString(data);
//			System.out.println(string[]);
//			System.out.println(java.util.Arrays.toString(data));
	

//	public static void main(String[] args) throws JsonParseException, IOException {
//		String filePath = "D:\\sample.json";
//		try {
//			JsonParser parser = getJsonParser(filePath);
//			JsonToken token = parser.nextToken();
//
//			if (token == JsonToken.START_ARRAY) {
//				System.out.println("Start of JSON array found.");
//				Map<String, String> data;
//				while ((data = readNextData(parser)) != null) {
//					// Call the test method for each JSON object
//					testJsonData(data);
//				}
//			} else if (token == JsonToken.START_OBJECT) {
//				System.out.println("Start of a single JSON object found.");
//				Map<String, String> data = readNextData(parser);
//				if (data != null) {
//					// Call the test method
//					testJsonData(data);
//				}
//			} else {
//				System.out.println("Invalid JSON format or empty file.");
//			}
//
//			parser.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// Test method to process JSON data
//
//	public static void testJsonData(Map<String, String> data) {
//		System.out.println("Testing with data: " + data.get("username"));
//		System.out.println(data.get("password"));
//	}

