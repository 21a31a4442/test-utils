package com.pvs.cms.configurations.lessor.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.pvs.testframe.utils.JsonUtil;

public class LessorDataProvider {

	@DataProvider(name = "addLessorData")
    public static Object[][] addLessorData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\admin\\Lessor\\addLessor.json"; // Path to the JSON file
        
        List<Object[]> dataList = new ArrayList<>();

        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
            Map<String, String> data;
            while ((data = JsonUtil.readNextData(parser)) != null) {
                Object[] lessorDetails = new Object[] {
                        data.get("lessorName"),        // Lessor Name
                        data.get("lessorType"),        // Lessor Type
                        data.get("address1"),          // Address 1
                        data.get("address2"),          // Address 2
                        data.get("pincode"),           // Pincode
                        data.get("city"),              // City
                        data.get("state"),             // State
                        data.get("country"),           // Country
                        data.get("primaryContact"),    // Primary Contact
                        data.get("status")             // Status
                };
                dataList.add(lessorDetails);
            }
        }

        return dataList.toArray(new Object[0][]);
    }

    @DataProvider(name = "editLessorData")
    public static Object[][] editLessorData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\admin\\Lessor\\editLessor.json"; // Path to the JSON file
        
        List<Object[]> dataList = new ArrayList<>();

        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
            Map<String, String> data;
            while ((data = JsonUtil.readNextData(parser)) != null) {
                Object[] lessorDetails = new Object[] {
                        data.get("searchLessorName"),  // Search Lessor Name
                        data.get("lessorName"),         // Lessor Name
                        data.get("lessorType"),         // Lessor Type
                        data.get("address1"),           // Address 1
                        data.get("address2"),           // Address 2
                        data.get("pincode"),            // Pincode
                        data.get("city"),               // City
                        data.get("state"),              // State
                        data.get("country"),            // Country
                        data.get("primaryContact"),     // Primary Contact
                        data.get("status")              // Status
                };
                dataList.add(lessorDetails);
            }
        }

        return dataList.toArray(new Object[0][]);
    }
	
}
