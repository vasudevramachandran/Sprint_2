/**
 * 
 */
package com.uncc.fairshare.helper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author temp
 *
 */
public class User {
	
	private String userName;
	private String email;
	private String password;
	
	private boolean isValidUser;
	
	private HashMap<String, String> friendsMap;

	private HashMap<String, String> groupMap;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isValidUser() {
		return isValidUser;
	}

	public void setValidUser(boolean isValidUser) {
		this.isValidUser = isValidUser;
	}

	public HashMap<String, String> getFriendsMap() {
		return friendsMap;
	}

	public void setFriendsMap(HashMap<String, String> friendsMap) {
		this.friendsMap = friendsMap;
	}

	public HashMap<String, String> getGroupMap() {
		return groupMap;
	}

	public void setGroupMap(HashMap<String, String> groupMap) {
		this.groupMap = groupMap;
	}

}
