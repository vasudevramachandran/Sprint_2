    package com.uncc.fairshare.impl;  
      
    import java.sql.Connection;  
    import java.sql.DriverManager;  
    import java.sql.PreparedStatement;  
    import java.sql.ResultSet;  
    import java.sql.SQLException;  
      
    public class FriendsDao {  
        public static boolean validate(String sd, String em) {          
            boolean status = false;  
            Connection conn = null;  
            PreparedStatement pst = null;  
            ResultSet rs = null;  
      
            String url = "jdbc:mysql://localhost:3306/";  
            String dbName = "fsdb";  
            String driver = "com.mysql.jdbc.Driver";  
            String userName = "root";  
            String password = "";  
            try {  
                Class.forName(driver).newInstance();  
                conn = DriverManager  
                        .getConnection(url + dbName, userName, password);  
      
                pst = conn.prepareStatement("select * from Friends where (user1=? and user2=?) or (user1=? and user2=?)");  
                pst.setString(1, sd);  
                pst.setString(2, em);  
                pst.setString(3, em);  
                pst.setString(4, sd);  
      
                rs = pst.executeQuery();  
                status = rs.next();  
      
            } catch (Exception e) {  
                System.out.println(e);  
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
        
        public static String invitefriends(String sd, String em)
        {
            String name = "";  
            Connection conn = null;  
            PreparedStatement pst = null;  
            ResultSet rs = null;  
      
            String url = "jdbc:mysql://localhost:3306/";  
            String dbName = "fsdb";  
            String driver = "com.mysql.jdbc.Driver";  
            String userName = "root";  
            String password = "root";  
            try {  
                Class.forName(driver).newInstance();  
                conn = DriverManager  
                        .getConnection(url + dbName, userName, password);  
      
                pst = conn.prepareStatement("insert into Friends values(?,?)");  
                pst.setString(1, sd);  
                pst.setString(2, em);  
                     
                int temp;
                temp=pst.executeUpdate();  
            } catch (Exception e) {  
                System.out.println(e);  
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
            return name;          	
        }
        
    }  
    
    
    
