/**
 * 
 */
package com.uncc.fairshare.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.uncc.fairshare.connection.DbConnect;
import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.BillDataFetch;
import com.uncc.fairshare.helper.FetchBillDetails;
import com.uncc.fairshare.intf.FetchBillIntf;

/**
 * @author vasudev
 *
 */
public class FetchBillImpl implements FetchBillIntf{

	@Override
	public FetchBillDetails fetchBillForUser(FetchBillDetails billDataObj) {
		
		Connection conn = null;
		Statement stateObj= null;
		
		FetchBillDetails fetchDetailsObj = new FetchBillDetails();
		
		ResultSet rsObj = null;
		conn = new DbConnect().getConnection();
		
		StringBuffer sBufQuery = new StringBuffer(" ");
		
		sBufQuery.append(" SELECT ");
		sBufQuery.append(" BILL_ID, CREATED_BY_NAME ");
		sBufQuery.append(" , OWED_BY_NAME, BILL_DESCRIPTION, BILL_VALUE, created_by_emailid, owed_by_emailid ");
		sBufQuery.append(" FROM FRIENDS_BILL WHERE BILL_ACTIVE = 1 AND created_by_emailid = '"+billDataObj.getEmail()+"'");
		
		if (null != conn){
			
			try {
				stateObj = conn.createStatement();
				
				rsObj = stateObj.executeQuery(sBufQuery.toString());
				
				if(rsObj !=null){
					BillDataFetch billFetchObj;
					
					HashMap<Integer, BillDataFetch> billMap = new HashMap<Integer, BillDataFetch>();
					while(rsObj.next()){
						
						billFetchObj = new BillDataFetch();
						billFetchObj.setBillId(rsObj.getInt(CommonConstants.SQL_BILL_ID));
						billFetchObj.setBillDescription(rsObj.getString(CommonConstants.SQL_BILL_DESC));
						billFetchObj.setBillAmount(rsObj.getLong(CommonConstants.SQL_BILL_AMT));
						billFetchObj.setFriendName(rsObj.getString(CommonConstants.SQL_OWED_BY_NAME));
						billFetchObj.setFriendEmail(rsObj.getString(CommonConstants.SQL_OWED_BY_EMAILID));
						billFetchObj.setUserName(rsObj.getString(CommonConstants.SQL_CREATED_BY_NAME));
						billFetchObj.setUserEmail(rsObj.getString(CommonConstants.SQL_CREATED_BY_EMAIL));
						billMap.put(billFetchObj.getBillId(), billFetchObj);
					}
					
					fetchDetailsObj.setBillDetails(billMap);
				}	
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
		
		return fetchDetailsObj;
	}

	@Override
	public int deleteBill(String deleteId) {
			
		Connection conn = new DbConnect().getConnection();
		int result = 0;
		StringBuffer sBufQuery = new StringBuffer(" ");
		sBufQuery.append(" DELETE FROM FRIENDS_BILL WHERE BILL_ID IN ("+deleteId+")");
		
		Statement stateObj = null;
		try {
			stateObj = conn.createStatement();
			result = stateObj.executeUpdate(sBufQuery.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		
		return result;
	}

	
}
