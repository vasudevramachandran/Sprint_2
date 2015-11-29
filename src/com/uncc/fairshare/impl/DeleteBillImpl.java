/**
 * 
 */
package com.uncc.fairshare.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.uncc.fairshare.connection.DbConnect;
import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.constants.NamedQueries;
import com.uncc.fairshare.intf.DeleteBillIntf;

/**
 * @author temp
 *
 */
public class DeleteBillImpl implements DeleteBillIntf {

	/* (non-Javadoc)
	 * @see com.uncc.fairshare.intf.DeleteBillIntf#deleteBill(int)
	 */
	@Override
	public int deleteBill(int billId) {
		// TODO Auto-generated method stub
		int returnId = CommonConstants.INT_FAILURE;
		
		Connection conn = new DbConnect().getConnection();
		
		StringBuffer sBufObj = new StringBuffer(NamedQueries.SQL_DELETE_BILL);
		
		try {
			PreparedStatement prepState  = conn.prepareStatement(sBufObj.toString());
			prepState.setInt(CommonConstants.INT_INDEX_1,billId);
			
			returnId = prepState.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return returnId;
	}

}
