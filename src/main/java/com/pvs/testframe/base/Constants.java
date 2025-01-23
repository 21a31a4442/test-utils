package com.pvs.testframe.base;


import java.nio.file.Paths;
import java.util.Properties;

import com.pvs.testframe.utils.PropertyReader;
import com.pvs.testframe.utils.PropertyUtil;

public class Constants {

//	public static final InputStream in = PropertyReader.class.getClassLoader().getResourceAsStream("url.properties");
	private static final String filepath = "C:\\Users\\BHASKARA TEJA\\eclipse-workspace\\pvs-consultancy\\src\\main\\resources\\url.properties";
	
	private static final Properties prop = PropertyReader.readPropertyFile(Paths.get(filepath));
	
	public static final String username = PropertyUtil.getProperties("username");
	public static final String password = PropertyUtil.getProperties("password");
	
	public static final String base_url = prop.getProperty("base_url");
	public static final String login_url = prop.getProperty("login_url");
	public static final String admin_home = prop.getProperty("admin_home");
	public static final String status_url = prop.getProperty("status_url");
	public static final String contact_type_url = prop.getProperty("contact_type_url");
	public static final String cctype_url = prop.getProperty("cctype_url");
	public static final String country_url = prop.getProperty("country_url");
	public static final String state_url = prop.getProperty("state_url");
	public static final String city_url = prop.getProperty("city_url");
	public static final String dept_url = prop.getProperty("dept_url");
	public static final String desn_url = prop.getProperty("desn_url");
	public static final String entity_url = prop.getProperty("entity_url");
	public static final String costcenter_url = prop.getProperty("costcenter_url");
	public static final String pages_url = prop.getProperty("pages_url");
	public static final String roles_url = prop.getProperty("roles_url");
	public static final String permissions_url = prop.getProperty("permissions_url");
	public static final String cms_url = prop.getProperty("cms_url");
	public static final String inward_stock_url = prop.getProperty("inward_stock_url");
	public static final String outward_stock_url = prop.getProperty("outward_stock_url");
	public static final String manage_compartment_url = prop.getProperty("manage_compartment_url");
	public static final String inward_lease_url = prop.getProperty("inward_lease_url");
	public static final String outward_lease_url = prop.getProperty("outward_lease_url");
	public static final String customer_url = prop.getProperty("customer_url");
	public static final String vendor_url = prop.getProperty("vendor_url");
	public static final String lessor_url = prop.getProperty("lessor_url");
	public static final String warehouse_url = prop.getProperty("warehouse_url");
	public static final String delivery_url = prop.getProperty("delivery_url");
	public static final String commodity_url = prop.getProperty("commodity_url");
	public static final String miller_url = prop.getProperty("miller_url");
	public static final String stockreport_url = prop.getProperty("stockreport_url");
	public static final String outward_lease_report_url = prop.getProperty("outward_lease_report_url");
	public static final String inward_lease_report_url = prop.getProperty("inward_lease_report_url");
	public static final String contact_url = prop.getProperty("contact_url");
	public static final String employee_url = prop.getProperty("employee_url");
	public static final String user_url = prop.getProperty("user_url");
	
}
	
//		if(prop != null) {
//			base_url = prop.getProperty("base_url");
//			
//		}

	
	
	//	public static String department = PropertyUtil.getProperties("name");
	
//	public static void main(String[] args) {
//		System.out.println();
//		System.out.println(department);
//	}
	
	
//	private static String getprop(String name) {
//		String string = PropertyUtil.getProperties(name);
//		return string;
//	}
	
	
//	public static void main(String[] args) {
//		PropertyUtil prop =  new PropertyUtil();
//		String properties = prop.getProperties("name");
//		System.out.println(properties);
//	}
	

