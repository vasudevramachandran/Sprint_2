/**
 * 
 */
package com.uncc.fairshare.connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author temp
 *
 */
public class DbConnect {
	
	public Connection getConnection(){
		
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/fsdb","root","Valiyavil@89");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found exception in getConnection" );
			e.printStackTrace();
			return conn;
		} catch (SQLException e) {
			System.out.println("SQL exception in getConnection");
			e.printStackTrace();
			return conn;
		}
		return conn;
		
	}

}
