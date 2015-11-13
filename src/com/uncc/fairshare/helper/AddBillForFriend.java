/**
 * 
 */
package com.uncc.fairshare.helper;

/**
 * @author temp
 *
 */
public class AddBillForFriend {
	
	private int userId;
	private int friendId;
	private long billAmount;
	
	private String billDescription;
	private String userName;
	private String friendName;
	private String friendsEmail;
	private String userEmail;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	public long getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(long billAmount) {
		this.billAmount = billAmount;
	}

	public String getBillDescription() {
		return billDescription;
	}

	public void setBillDescription(String billDescription) {
		this.billDescription = billDescription;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFriendName() {
		return friendName;
	}

	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}

	public String getFriendsEmail() {
		return friendsEmail;
	}

	public void setFriendsEmail(String friendsEmail) {
		this.friendsEmail = friendsEmail;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
