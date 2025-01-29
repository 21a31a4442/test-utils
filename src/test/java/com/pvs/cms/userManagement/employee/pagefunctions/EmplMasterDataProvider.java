package com.pvs.cms.userManagement.employee.pagefunctions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.pvs.testframe.utils.JsonUtil;

public class EmplMasterDataProvider {
	
	@DataProvider(name = "addEmployee")
    public static Object[][] addEmployeeData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\UserManagement\\Employee\\addEmployee.json"; // Path

        List<Object[]> dataList = new ArrayList<>();

        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
            JsonToken token = parser.nextToken();
            if (token == JsonToken.START_ARRAY) {

                Map<String, String> data;
                while ((data = JsonUtil.readNextData(parser)) != null) {

                    Object[] employeeDetails = new Object[] { 
                            data.get("firstName"), // First Name
                            data.get("lastName"), // Last Name
                            data.get("dob"), // Date of Birth
                            data.get("email"), // Email
                            data.get("personalEmail"),
                            data.get("mobile"), // Mobile
                            data.get("address1"), // Address 1
                            data.get("address2"), // Address 2
                            data.get("pincode"), // Pincode
                            data.get("city"), // City
                            data.get("state"), // State
                            data.get("country"), // Country
                            data.get("employeeType"), // Employee Type
                            data.get("doj"), // Date of Joining
                            data.get("exitDate"), // Exit Date
                            data.get("entity"), // Entity
                            data.get("department"), // Department
                            data.get("designation"), // Designation
                            data.get("imagePath") // Image Path
                    };
                    dataList.add(employeeDetails);
                }
            }
        }

        return dataList.toArray(new Object[0][]);
    }

    // Data provider for editing an employee
    @DataProvider(name = "editEmployee")
    public static Object[][] editEmployeeData() throws IOException {
        String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\cms\\UserManagement\\Employee\\editEmployee.json"; // Path

        List<Object[]> dataList = new ArrayList<>();

        try (JsonParser parser = JsonUtil.getJsonParser(filePath)) {
            JsonToken token = parser.nextToken();
            if (token == JsonToken.START_ARRAY) {

                Map<String, String> data;
                while ((data = JsonUtil.readNextData(parser)) != null) {

                    Object[] employeeDetails = new Object[] { 
                            data.get("searchEmail"), // Search by Email
                            data.get("firstName"), // First Name
                            data.get("lastName"), // Last Name
                            data.get("dob"), // Date of Birth
                            data.get("email"),
                            data.get("personalEmail"),
                            data.get("mobile"), // Mobile
                            data.get("address1"), // Address 1
                            data.get("address2"), // Address 2
                            data.get("pincode"), // Pincode
                            data.get("city"), // City
                            data.get("state"), // State
                            data.get("country"), // Country
                            data.get("employeeType"), // Employee Type
                            data.get("doj"), // Date of Joining
                            data.get("exitDate"), // Exit Date
                            data.get("entity"), // Entity
                            data.get("department"), // Department
                            data.get("designation"), // Designation
                            data.get("imagePath") // Image Path
                    };
                    dataList.add(employeeDetails);
                }
            }
        }

        return dataList.toArray(new Object[0][]);
    }

    
    
}
