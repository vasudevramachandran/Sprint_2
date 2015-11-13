/**
 * 
 */
package com.uncc.fairshare.helper;

import java.util.HashMap;

/**
 * @author temp
 *
 */
public class CreateGroupDetail {
	
	private String groupName;
	private String createdByName;
	private String createdByEmail;
	
	private boolean isCreated;
	
	private HashMap<String, String> friendListMap;
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public HashMap<String, String> getFriendListMap() {
		return friendListMap;
	}
	public void setFriendListMap(HashMap<String, String> friendListMap) {
		this.friendListMap = friendListMap;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public String getCreatedByEmail() {
		return createdByEmail;
	}
	public void setCreatedByEmail(String createdByEmail) {
		this.createdByEmail = createdByEmail;
	}
	public boolean isCreated() {
		return isCreated;
	}
	public void setCreated(boolean isCreated) {
		this.isCreated = isCreated;
	}

}
