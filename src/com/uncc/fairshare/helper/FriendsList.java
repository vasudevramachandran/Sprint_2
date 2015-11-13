package com.uncc.fairshare.helper;

import java.util.HashMap;

public class FriendsList {

	private int userID;
	
	private String userEmail;
	
	private HashMap<Integer, String> friendsMap;
	
	private HashMap<String, String> friendsListMap;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public HashMap<Integer, String> getFriendsMap() {
		return friendsMap;
	}

	public void setFriendsMap(HashMap<Integer, String> friendsMap) {
		this.friendsMap = friendsMap;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public HashMap<String, String> getFriendsListMap() {
		return friendsListMap;
	}

	public void setFriendsListMap(HashMap<String, String> friendsListMap) {
		this.friendsListMap = friendsListMap;
	}
}
