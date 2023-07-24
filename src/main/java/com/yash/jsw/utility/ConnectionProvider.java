package com.yash.jsw.utility;

import java.sql.*;

public class ConnectionProvider {

	public static Connection con = null;
	
	public static Connection getConn()
	{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/jsw","root","root");
				return con;
			} catch (Exception e) {
				e.printStackTrace();
			}
		return con;
	}
	
	public static void closeConn()
	{
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
