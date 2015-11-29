/**
 * 
 */
package com.uncc.fairshare.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;
import java.util.Map;

import com.uncc.fairshare.connection.DbConnect;
import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.constants.NamedQueries;
import com.uncc.fairshare.helper.UserBill;

/**
 * @author temp
 *
 */
public class CreateBillImpl {

	public int insertBill(UserBill userBill){
		
		Connection conn = new DbConnect().getConnection();
		
		int verify = 0;
		int verifyInsert = 0;
		
		try {
			int billNum = 0;
			
			StringBuffer sbufQry = new StringBuffer(" ");
			sbufQry.append(" SELECT MAX(BILL_ID) FROM BILL_DETAILS");
			Statement stObj = conn.createStatement();
			ResultSet rsSet = stObj.executeQuery(sbufQry.toString());
			while(rsSet.next()){
				billNum = rsSet.getInt(CommonConstants.INT_INDEX_1);
			}
			sbufQry = new StringBuffer(" Insert into bill_details(bill_id, bill_description, group_id, created_by, 	Created_on) ");
			sbufQry.append(" values(?,?,?,?, now())");	
			
			PreparedStatement psObj = conn.prepareStatement(sbufQry.toString());
			int j=0;
			j=j+1;
			psObj.setInt(j, billNum+1);j=j+1;
			psObj.setString(j, userBill.getBillDesc());j=j+1;
			psObj.setInt(j, userBill.getGroupId());j=j+1;
			psObj.setString(j, userBill.getAddedByEmail());j=j+1;
			
			String addedBy = userBill.getAddedByEmail();
			
			int iResult = psObj.executeUpdate();
			
			PreparedStatement prepState = conn.prepareStatement(NamedQueries.SQL_INSERT_GROUP_BILL);
			Iterator iteratorObj = userBill.getSplitBill().entrySet().iterator();
			
			while(iteratorObj.hasNext()){
				verify = verify + 1;
				Map.Entry pair = (Map.Entry) iteratorObj.next();
				String userEmail = (String) pair.getKey();
				UserBill userBillObj = (UserBill) pair.getValue();
				int i = 0;
				
				StringBuffer sBuf = new StringBuffer(" ");
				sBuf.append(" SELECT MAX(C_ID) FROM bill_master");
				Statement stateObj = conn.createStatement();
				ResultSet rsObj = stateObj.executeQuery(sBuf.toString());
				
				int maxVal = 0 ;
				 while(rsObj.next()){
					 maxVal = rsObj.getInt(CommonConstants.INT_INDEX_1) + 1;
				 }
				 i = i+1;
				 prepState.setInt(i, maxVal);i=i+1;
				 prepState.setInt(i, billNum + 1);i=i+1;
				 prepState.setInt(i, userBill.getGroupId());i=i+1;
				 prepState.setString(i, userEmail);i=i+1;
				 prepState.setString(i, addedBy);i=i+1;
				 prepState.setDouble(i, userBillObj.getBillShare());
				 
				 int iSetObj = prepState.executeUpdate();
				 
				 verifyInsert = verifyInsert + iSetObj;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(verify == verifyInsert){
			return CommonConstants.INT_INDEX_1;
		}else{
			return CommonConstants.INT_INDEX_0;
		}
		
		
		
	}
}
