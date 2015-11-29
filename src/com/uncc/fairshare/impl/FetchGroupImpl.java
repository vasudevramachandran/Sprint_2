/**
 * 
 */
package com.uncc.fairshare.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.uncc.fairshare.connection.DbConnect;
import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.constants.NamedQueries;
import com.uncc.fairshare.helper.FetchBillDetails;
import com.uncc.fairshare.helper.FetchGroupDetails;
import com.uncc.fairshare.helper.GroupBillDetails;
import com.uncc.fairshare.intf.FetchGroupIntf;

/**
 * @author temp
 *
 */
public class FetchGroupImpl implements FetchGroupIntf {

	private Connection conn;
	
	public FetchGroupImpl(){
		
		conn = new DbConnect().getConnection();
		
	}
	
	@Override
	public FetchGroupDetails fetchGroupDetails(FetchGroupDetails fetchGroupObj) {
		
		try{
			
			StringBuffer sBufObj = new StringBuffer(" ");
			sBufObj.append(NamedQueries.SQL_GROUP_FETCH_DETAILS_USING_ID);
			
			try {
				PreparedStatement prepStateObj = conn.prepareStatement(sBufObj.toString());
				
				prepStateObj.setInt(CommonConstants.INT_INDEX_1, fetchGroupObj.getGroupId());
				
				ResultSet rsObj = prepStateObj.executeQuery();
				
				if(null != rsObj){
					while(rsObj.next()){
						fetchGroupObj.setGroupId(rsObj.getInt(CommonConstants.INT_INDEX_1));
						fetchGroupObj.setGroupName(rsObj.getString(CommonConstants.INT_INDEX_2));
						fetchGroupObj.setGroupDesc(rsObj.getString(CommonConstants.INT_INDEX_3));
						fetchGroupObj.setValidFetch(true);
					}
					
					if(null != rsObj){
						rsObj.close();
					}if(null != prepStateObj){
						prepStateObj.close();
					}
					
					StringBuffer sBuffFrnds = new StringBuffer(" ");
					sBuffFrnds.append(NamedQueries.SQL_GROUP_FETCH_GRP_FRNDS_USING_ID);
					
					PreparedStatement psPrepObj = conn.prepareStatement(sBuffFrnds.toString());
					
					psPrepObj.setInt(CommonConstants.INT_INDEX_1, fetchGroupObj.getGroupId());
					
					ResultSet rsFrndsObj = psPrepObj.executeQuery();
					
					if(null != rsFrndsObj){
						HashMap<String, String> friendsMap = new HashMap<String, String>();
						while(rsFrndsObj.next()){
							friendsMap.put(rsFrndsObj.getString(CommonConstants.INT_INDEX_2), rsFrndsObj.getString(CommonConstants.INT_INDEX_1));
							
						}
						
						fetchGroupObj.setGroupMemberMap(friendsMap);
					}
				}
				//insert code for retrieving bill details
				StringBuffer sBufQry = new StringBuffer(NamedQueries.SQL_FETCH_GROUP_BILLS);
				PreparedStatement psPreState = conn.prepareStatement(sBufQry.toString());
				
				psPreState.setInt(1, fetchGroupObj.getGroupId());
				
				ResultSet rsBillObj = psPreState.executeQuery();
				
				if(null != rsBillObj){
				//	while(rsBillObj.next()){
					GroupBillDetails grpBillObj = null;
					HashMap<Integer, GroupBillDetails> billMap = new HashMap<Integer, GroupBillDetails>();
					int k=0;
					while(rsBillObj.next()){
						grpBillObj = new GroupBillDetails();
						
						
						grpBillObj.setBillId(rsBillObj.getInt(CommonConstants.INT_INDEX_1));
						grpBillObj.setGroupId(rsBillObj.getInt(CommonConstants.INT_INDEX_2));
						grpBillObj.setOwedByEmail(rsBillObj.getString(CommonConstants.INT_INDEX_3));
						grpBillObj.setOwedByName(rsBillObj.getString(CommonConstants.INT_INDEX_4));
						grpBillObj.setAddedByEmail(rsBillObj.getString(CommonConstants.INT_INDEX_5));
						grpBillObj.setBillShare(rsBillObj.getDouble(CommonConstants.INT_INDEX_6));
						grpBillObj.setBillName(rsBillObj.getString(CommonConstants.INT_INDEX_7));
						grpBillObj.setAddedByName(rsBillObj.getString(CommonConstants.INT_INDEX_8));
						grpBillObj.setGroupName(rsBillObj.getString(CommonConstants.INT_INDEX_9));
						k=k+1;
						billMap.put(rsBillObj.getInt(CommonConstants.INT_INDEX_1)+k, grpBillObj);
					}
					fetchGroupObj.setGroupBillDetails(billMap);
					
					HashMap<Integer, GroupBillDetails> hashMapObj = new HashMap<Integer, GroupBillDetails>();
					hashMapObj = fetchGroupObj.getGroupBillDetails();
					
					System.out.println(" Hashmap size --> "+hashMapObj.size());
					Iterator iterate = hashMapObj.entrySet().iterator();
					while(iterate.hasNext()){
						Map.Entry pair = (Map.Entry) iterate.next();
						System.out.println("key ---> "+ pair.getKey());
						System.out.println("Name --> "+((GroupBillDetails)pair.getValue()).getOwedByName());
					}
				//}
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
		}catch(NullPointerException npe){
			
		}
			
		return fetchGroupObj;
	}

	
}
