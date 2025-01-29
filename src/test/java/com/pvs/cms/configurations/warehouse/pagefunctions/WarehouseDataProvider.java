package com.pvs.cms.configurations.warehouse.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class WarehouseDataProvider {

	@DataProvider(name = "addWarehouse")
    public static Object[][] addWarehouseData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\Configurations\\Warehouse\\addWarehouse.json"; // Path
    
        List<Object[]> dataList = new ArrayList<>();
    
        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
            JsonToken token = parser.nextToken();
            if (token == JsonToken.START_ARRAY) {
                Map<String, String> data;
                while ((data = JsonUtil.readNextData(parser)) != null) {
                    Object[] warehouseDetails = new Object[] {
                        data.get("whouseName"), // Warehouse Name
                        data.get("whouseCode"), // Warehouse Code
                        data.get("lessor"), // Lessor
                        data.get("address1"), // Address 1
                        data.get("address2"), // Address 2
                        data.get("pincode"), // Pincode
                        data.get("city"), // City
                        data.get("state"), // State
                        data.get("country"), // Country
                        data.get("capacitySqft"), // Capacity (Sq.ft.)
                        data.get("primContact"), // Primary Contact
                        data.get("status") // Status
                    };
                    dataList.add(warehouseDetails);
                }
            }
        }
        return dataList.toArray(new Object[0][]);
    }

    @DataProvider(name = "editWarehouse")
    public static Object[][] editWarehouseData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\Configurations\\Warehouse\\editWarehouse.json"; // Path
    
        List<Object[]> dataList = new ArrayList<>();
    
        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
            JsonToken token = parser.nextToken();
            if (token == JsonToken.START_ARRAY) {
                Map<String, String> data;
                while ((data = JsonUtil.readNextData(parser)) != null) {
                    Object[] warehouseDetails = new Object[] {
                        data.get("searchWhouseName"), // Search Warehouse Name
                        data.get("searchWhouseCode"), // Search Warehouse Code
                        data.get("whouseName"),
                        data.get("whouseCode"),
                        data.get("lessor"), // Lessor
                        data.get("address1"), // Address 1
                        data.get("address2"), // Address 2
                        data.get("pincode"), // Pincode
                        data.get("city"), // City
                        data.get("state"), // State
                        data.get("country"), // Country
                        data.get("capacitySqft"), // Capacity (Sq.ft.)
                        data.get("primContact"), // Primary Contact
                        data.get("status") // Status
                    };
                    dataList.add(warehouseDetails);
                }
            }
        }
        return dataList.toArray(new Object[0][]);
    }
	
}
