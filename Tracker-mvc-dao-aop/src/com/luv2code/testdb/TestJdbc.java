package com.luv2code.testdb;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String user = "springstudent";
		String pass = "springstudent";		
		
		// I needed to open MySQL workbench program before this would run

		try {
			System.out.println("Connecting to databases: " + jdbcUrl);
		
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection Successful!!");
			
		}catch(Exception exc) {
			exc.printStackTrace();
		}
	}

}
