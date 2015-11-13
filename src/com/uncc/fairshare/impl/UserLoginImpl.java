package com.uncc.fairshare.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import com.sun.org.apache.bcel.internal.generic.GETSTATIC;
import com.uncc.fairshare.connection.DbConnect;
import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.User;
import com.uncc.fairshare.intf.UserLoginIntf;

public class UserLoginImpl implements UserLoginIntf {

	@Override
	public User validateUser(User user) {
		
		Connection conn = new DbConnect().getConnection();
		
		HashMap<String, String> friendsMap = new HashMap<String, String>();
		
		if(null != conn){
			
			StringBuffer sBuff = new StringBuffer(" ");
			
			sBuff.append(" SELECT * FROM USER WHERE EMAIL=? AND PASSWORD = ?");
			
			try {
				PreparedStatement prepState = conn.prepareStatement(sBuff.toString());
				int i=1;
				prepState.setString(i, user.getEmail());i=i+1;
				prepState.setString(i, user.getPassword());
				
				ResultSet resultSetObj = prepState.executeQuery();
				
				if(null != resultSetObj){
					while(resultSetObj.next()){
						user.setValidUser(true);
						user.setUserName(resultSetObj.getString("name"));
					}
				}
				
				if(user.isValidUser()){
					StringBuffer sbSelQry = new StringBuffer("");
					
					sbSelQry.append(" select u.name as name , ");
					sbSelQry.append(" u.email as email from friends f, user u "); 
					sbSelQry.append(" where f.user2=u.email and f.user1 = ?");
					
					PreparedStatement prepStateObj = conn.prepareStatement(sbSelQry.toString());
					
					prepStateObj.setString(CommonConstants.INT_PARAMETER_INDEX, user.getEmail());
					
					ResultSet rsObj = prepStateObj.executeQuery();
					if(null != rsObj){
						while(rsObj.next()){
							friendsMap.put(rsObj.getString(CommonConstants.COL_EMAIL), 
									rsObj.getString(CommonConstants.COL_NAME));
						}
						user.setFriendsMap(friendsMap);
					}
					
					HashMap<String, String> groupMap = new HashMap<String, String>();
					
					StringBuffer sBufGrp = new StringBuffer(" ");
					sBufGrp.append(" SELECT GROUP_ID, GROUP_NAME FROM GROUP_MEMBERS_LOOKUP WHERE GROUP_MEMBER_EMAIL = ?");
					PreparedStatement psPrepState = conn.prepareStatement(sBufGrp.toString());
					
					psPrepState.setString(CommonConstants.INT_INDEX_1, user.getEmail());
					
					ResultSet rsGrpObj = psPrepState.executeQuery();
					if(null != rsGrpObj){
						while(rsGrpObj.next()){
							groupMap.put(rsGrpObj.getString(CommonConstants.INT_INDEX_1), 
									rsGrpObj.getString(CommonConstants.INT_INDEX_2));
						}
						user.setGroupMap(groupMap);
					}
				}
				
			} catch (SQLException e) {
				// 
				e.printStackTrace();
			}finally{
				if(null!=conn){
					try {
						conn.close();
					} catch (SQLException e) {
						System.out.println("#addBill#Connection could not be closed in final block");
						e.printStackTrace();
					}
				}
			}
			
		}
		
		return user;
	}

}
