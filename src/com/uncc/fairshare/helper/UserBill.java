/**
 * 
 */
package com.uncc.fairshare.helper;

import java.util.HashMap;

/**
 * @author temp
 *
 */
public class UserBill {
	
	private String userEmail;
	private String addedByEmail;
	private String billDesc;
	
	private int groupId;
	
	private double billShare;
	
	private boolean billOwed;
	
	private HashMap<String, UserBill> splitBill;

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAddedByEmail() {
		return addedByEmail;
	}

	public void setAddedByEmail(String addedByEmail) {
		this.addedByEmail = addedByEmail;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public boolean isBillOwed() {
		return billOwed;
	}

	public void setBillOwed(boolean billOwed) {
		this.billOwed = billOwed;
	}

	public double getBillShare() {
		return billShare;
	}

	public void setBillShare(double billShare) {
		this.billShare = billShare;
	}

	public HashMap<String, UserBill> getSplitBill() {
		return splitBill;
	}

	public void setSplitBill(HashMap<String, UserBill> splitBill) {
		this.splitBill = splitBill;
	}

	public String getBillDesc() {
		return billDesc;
	}

	public void setBillDesc(String billDesc) {
		this.billDesc = billDesc;
	}
	
	

}
