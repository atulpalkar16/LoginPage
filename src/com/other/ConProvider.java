package com.other;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConProvider {
	final public static String url="jdbc:mysql://localhost:3306/login";
	final public static String uname="root";
	final public static String pass="root";
	
	Connection con= null;
	public Connection getConn() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con= DriverManager.getConnection(url, uname, pass);
		
		return con;
	}
	
	 public void closeConnection(Connection con) {
	        if (con != null) {
	            try {
	                con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
}
