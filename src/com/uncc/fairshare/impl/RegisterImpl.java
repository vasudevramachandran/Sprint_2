package com.uncc.fairshare.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.uncc.fairshare.connection.DbConnect;
import com.uncc.fairshare.helper.User;

public class RegisterImpl {  
    public static boolean validate(String email, String name, String pass) {          
        boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
	  
        	conn= new DbConnect().getConnection();
            try { 
            pst = conn.prepareStatement("select * from User where email=?");  
            pst.setString(1, email);  
  
            rs = pst.executeQuery();  
            status = rs.next();  
  
        } catch (Exception e) {  
            System.out.println(e+"ASAS");  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return status;  
    }  
    
    public  boolean registerdata(User user) {          
        boolean status = false;  
        Connection conn = null;  
        PreparedStatement pst = null;  
        ResultSet rs = null;  
  
        	conn = new DbConnect().getConnection();
            try { 
            pst = conn.prepareStatement("Insert into User values(?,?,?)");  
            pst.setString(1, user.getEmail());  
            pst.setString(2, user.getPassword());  
            pst.setString(3, user.getUserName());  
            int temp=0;
            temp = pst.executeUpdate();  
            if(temp>0)
            	status=true;
        } catch (Exception e) {  
            System.out.println(e+"ASAS");  
        } finally {  
            if (conn != null) {  
                try {  
                    conn.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (pst != null) {  
                try {  
                    pst.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
            if (rs != null) {  
                try {  
                    rs.close();  
                } catch (SQLException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return status;  
    }  
    
    
}  