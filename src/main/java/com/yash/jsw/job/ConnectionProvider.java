package com.yash.jsw.job;

import java.sql.Connection;
import java.sql.DriverManager;



public class ConnectionProvider {

	
	
	public static Connection get(){
	
		try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conio=DriverManager.getConnection("jdbc:mysql://localhost:3306/jsw","root","root");
				
				
				return conio;
		}
		catch(Exception e){
			
			return null;               
		}
	}
	
	
	
	
	
}
