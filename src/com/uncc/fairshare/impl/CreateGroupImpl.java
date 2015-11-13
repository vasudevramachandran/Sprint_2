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
import com.uncc.fairshare.helper.CreateGroupDetail;
import com.uncc.fairshare.intf.CreateGroupIntf;

public class CreateGroupImpl implements CreateGroupIntf {

	@Override
	public CreateGroupDetail createGroup(CreateGroupDetail createGroupObj) {
		// TODO Auto-generated method stub
		
		Connection conn = new DbConnect().getConnection();
		int iMaxVal = 0;
		if(null!=createGroupObj && !createGroupObj.getFriendListMap().isEmpty()){
			StringBuffer sBufQry = new StringBuffer(" ");
			sBufQry.append(" SELECT MAX(group_id)+1 from fsdb.group_desc");
			try{
				Statement stateObj = conn.createStatement();
				
				ResultSet rsObj = stateObj.executeQuery(sBufQry.toString());
				if(null!= rsObj){
					while(rsObj.next()){
						iMaxVal = rsObj.getInt(CommonConstants.INT_INDEX_1);
					}
				}
			}catch(SQLException sql){
				
			}
			
			StringBuffer sBCreateGrp = new StringBuffer(" ");
			sBCreateGrp.append(" INSERT INTO `fsdb`.`group_desc`(`group_id`, ");
			sBCreateGrp.append(" `group_name`, ")
			.append(" `group_desc`, ")
			.append(" `create_date`) ")
			.append(" VALUES (?,?,?,now() )");
			
			try {
				int i = 1;
				PreparedStatement prepState = conn.prepareStatement(sBCreateGrp.toString());
				prepState.setInt(i, iMaxVal);i=i+1;
				prepState.setString(i, createGroupObj.getGroupName());i=i+1;
				prepState.setString(i, CommonConstants.SQL_GROUP_DESC_CNST);i=i+1;
				
				int response = prepState.executeUpdate();
				
				if(CommonConstants.INT_SUCCESS == response){
					createGroupObj.setCreated(true);
				}else{
					createGroupObj.setCreated(false);
				}
				if(createGroupObj.isCreated()){
					//for(int j=0; j<createGroupObj.getFriendListMap().size();j++){
					Iterator iHasMap = createGroupObj.getFriendListMap().entrySet().iterator();
					while (iHasMap.hasNext()){
						Map.Entry pair = (Map.Entry) iHasMap.next();
						StringBuffer sBuffSelQry = new StringBuffer(" ");
						sBuffSelQry.append(" SELECT max(c_id)+1 from group_members_lookup");
						Statement stateQryObj = conn.createStatement();
						int maxValId = 0;
						ResultSet rsSelObj = stateQryObj.executeQuery(sBuffSelQry.toString());
						if(null != stateQryObj){
							while(rsSelObj.next()){
								maxValId = rsSelObj.getInt(CommonConstants.INT_INDEX_1);
							}
						}
						
						StringBuffer sBufQryIns = new StringBuffer(" ");
						sBufQryIns.append(" INSERT INTO `fsdb`.`group_members_lookup`  ")
						.append(" (`c_id`,`group_id`,`group_member_email`, `group_name`) ")
						.append(" VALUES (?,?,?,?)");
						
						PreparedStatement prepStateObj = conn.prepareStatement(sBufQryIns.toString());
						int iVal = 1;
						prepStateObj.setInt(iVal, maxValId);iVal=iVal+1;
						prepStateObj.setInt(iVal, iMaxVal);iVal=iVal+1;
						prepStateObj.setString(iVal, (String) pair.getKey());iVal=iVal+1;
					    prepStateObj.setString(iVal, createGroupObj.getGroupName());
						
						int result = prepStateObj.executeUpdate();
					}
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return createGroupObj;
	}

}
