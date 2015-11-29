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
import com.uncc.fairshare.intf.DeleteMemberIntf;

/**
 * @author temp
 *
 */
public class DeleteMemberImpl implements DeleteMemberIntf {

	@Override
	public int deleteGroupMember(String userId, int groupId) {
		
		int resultVal = CommonConstants.INT_FAILURE;
		
		Connection conn = new DbConnect().getConnection();
		
		try {
			PreparedStatement prepState = conn.prepareStatement(NamedQueries.SQL_DELETE_MEMBER);
			
			prepState.setString(1, userId);
			prepState.setInt(2, groupId);
			
			resultVal = prepState.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultVal;
	}

}
