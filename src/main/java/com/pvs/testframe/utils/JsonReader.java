package com.pvs.testframe.utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
	
	private static final Map<String, Object> dictionary = new HashMap<>();
	
	public static void loadJsonData(String jsonFilePath, Map<String, Object> dictionary) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // Read the JSON file into an array of objects
        Object[] testData = objectMapper.readValue(new File(jsonFilePath), Object[].class);

        // Loop through the array and add data to the dictionary
        for (int Q = 0; Q < testData.length; Q++) {
            // Example logic: you can adjust these keys based on your JSON structure
            dictionary.put("TEST_DATA_FILE" + Q, testData[Q]);

            // Assuming responseChannel is an array or list and the data structure is similar
            // For example, it could be an array of objects containing a field 'channel'
            dictionary.put("DataChannel" + (Q + 1), testData[Q]); // Modify this as per your data
        }
    }
	


	    // Dictionary to store the test data
	    

	    /**
	     * Test method that uses the data loaded into the dictionary.
	     */
	    
	    public static void testDataDriven(String filepath) throws IOException {
	        // Path to the JSON file passed as parameter
//	        String jsonFilePath = "path/to/your/large_data.json";

	        // Load data into the dictionary
	        loadJsonData(filepath, dictionary);

	        // Now, you can use the data in your tests
	        // Loop through the dictionary and extract the data for testing
	        for (int Q = 0; Q < dictionary.size() / 2; Q++) {
	            // Retrieve data from dictionary
	            Object testDataFile = dictionary.get("TEST_DATA_FILE" + Q);
	            Object dataChannel = dictionary.get("DataChannel" + (Q + 1));

	            // Example test logic (you can replace with your actual test assertions)
	            System.out.println("Test Data for Q=" + Q + ": " + testDataFile);
	            System.out.println("Data Channel for Q=" + (Q + 1) + ": " + dataChannel);

	            // Example assertion (you can modify this based on your test data)
	           
	        }
	    }
	    
//	    public static void main(String[] args) throws IOException {
//			testDataDriven("D:\\testing files\\sample.json");
//		}
//	
	}
	
	
	


