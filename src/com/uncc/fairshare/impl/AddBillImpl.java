/**
 * 
 */
package com.uncc.fairshare.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.uncc.fairshare.connection.DbConnect;
import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.AddBill;
import com.uncc.fairshare.helper.AddBillForFriend;
import com.uncc.fairshare.helper.FriendsList;
import com.uncc.fairshare.intf.AddBillIntf;

/**
 * @author temp
 *
 */
public class AddBillImpl implements AddBillIntf {
	
	
	
	

	/* (non-Javadoc)
	 * @see com.uncc.fairshare.intf.AddBillIntf#addBill(com.uncc.fairshare.helper.AddBill)
	 */
	@Override
	public int addBill(AddBillForFriend billObj) {
		// TODO Auto-generated method stub
		int addStatus = CommonConstants.INT_FAILURE;
		
		Connection conn = new DbConnect().getConnection();
		
		StringBuffer sBufSelQuery = new StringBuffer(" ");
		
		sBufSelQuery.append(" SELECT MAX(BILL_ID) FROM fsdb.FRIENDS_BILL");
		
		Statement stateObj = null;
		ResultSet resultSetObj = null;
		int maxIdVal = 0;
		try {
			stateObj = conn.createStatement();
			
			resultSetObj = stateObj.executeQuery(sBufSelQuery.toString());
			
			while(resultSetObj.next()){
				
				maxIdVal = resultSetObj.getInt(1);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
		
		
		
		StringBuffer sBufQuery = new StringBuffer(" INSERT INTO `friends_bill`");
		
		sBufQuery.append(" ( bill_id, ");
		sBufQuery.append(" create_date,");
		sBufQuery.append(" created_by_name,");
		sBufQuery.append(" owed_by_name, bill_description,bill_active, bill_value ");
		sBufQuery.append(" , created_by_emailid , owed_by_emailid ) ");
		sBufQuery.append(" VALUES");
		sBufQuery.append(" (?, now(),?,?,?,?,?,?,?);");
		
		
		PreparedStatement prepStateObj = null;
		int rsObj = 0;
		
		int i = 1;
		try {
			conn = new DbConnect().getConnection();
			if(null!=conn && !conn.isClosed()){
				
				prepStateObj = conn.prepareStatement(sBufQuery.toString());
				
				prepStateObj.setInt(i, maxIdVal + 1);i=i+1;
				prepStateObj.setString(i, billObj.getUserName().toUpperCase());i=i+1;
				prepStateObj.setString(i, billObj.getFriendName().toUpperCase());i=i+1;
				prepStateObj.setString(i, billObj.getBillDescription().toUpperCase());i=i+1;
				prepStateObj.setInt(i, 1); i=i+1;
				prepStateObj.setLong(i, billObj.getBillAmount());i = i+1;
				prepStateObj.setString(i, billObj.getUserEmail()); i=i+1;
				prepStateObj.setString(i, billObj.getFriendsEmail());;
				
				
				addStatus = prepStateObj.executeUpdate();
				//conn.commit();
				/*Statement state = conn.createStatement();
				String query = " UPDATE KEY_LOOK_UP SET FRIENDS_BILL ="+(maxIdVal+1)+ " WHERE FRIENDS_BILL="+maxIdVal;
				state.executeUpdate(query);
				conn.commit();*/
			}
		} catch (SQLException e) {
			System.out.println("addBill connection null check");
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
		return addStatus;
	}

	@Override
	public FriendsList fetchFriends(FriendsList friendsList) {
		
		ResultSet resultSetObj=null;
		
		Connection conn = null;
		
		StringBuffer sBufQuery = new StringBuffer(" ");
		
		sBufQuery.append(" select u.name as name , ");
		sBufQuery.append(" u.email as email from friends f, user u "); 
		sBufQuery.append(" where f.user2=u.email and f.user1 = ?");
		
		conn = new DbConnect().getConnection(); 
		
		if(null != conn){
			
		int i = 1;
		
		try {
			PreparedStatement prepStateObj = conn.prepareStatement(sBufQuery.toString());
			
			prepStateObj.setString(i, friendsList.getUserEmail());
			
			resultSetObj = prepStateObj.executeQuery();
			
			HashMap<String, String> friendsListMap = new HashMap<>();
			while(resultSetObj.next()){
				friendsListMap.put(resultSetObj.getString(2), resultSetObj.getString(1));
			}
			
			friendsList.setFriendsListMap(friendsListMap);
		} catch (SQLException e) {
			friendsList.setFriendsMap(null);
			
			return friendsList;
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
		
	}//if connection null close
		
		
		return friendsList;
	}

}
