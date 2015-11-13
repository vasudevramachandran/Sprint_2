/**
 * 
 */
package com.uncc.fairshare.helper;

import java.util.HashMap;

/**
 * @author temp
 *
 */
public class FetchGroupDetails {
	
	private int groupId;
	
	private String groupName;
	private String groupDesc;
	
	private boolean isValidFetch;
	
	private HashMap<String, String> groupMemberMap;
	private HashMap<String , String> billDetailsMap;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public HashMap<String, String> getGroupMemberMap() {
		return groupMemberMap;
	}

	public void setGroupMemberMap(HashMap<String, String> groupMemberMap) {
		this.groupMemberMap = groupMemberMap;
	}

	public HashMap<String, String> getBillDetailsMap() {
		return billDetailsMap;
	}

	public void setBillDetailsMap(HashMap<String, String> billDetailsMap) {
		this.billDetailsMap = billDetailsMap;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public boolean isValidFetch() {
		return isValidFetch;
	}

	public void setValidFetch(boolean isValidFetch) {
		this.isValidFetch = isValidFetch;
	} 

}
