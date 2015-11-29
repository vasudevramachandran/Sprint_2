/**
 * 
 */
package com.uncc.fairshare.helper;

import java.util.HashMap;

/**
 * @author temp
 *
 */
public class CreateBill {
	
	private String billName;
	
	private int groupId; 
	private int billAmount;
	private int numOfPpl;
	
	private User userObj;
	
	private HashMap<String, Integer> billSplit;

	public String getBillName() {
		return billName;
	}

	public void setBillName(String billName) {
		this.billName = billName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(int billAmount) {
		this.billAmount = billAmount;
	}

	public HashMap<String, Integer> getBillSplit() {
		return billSplit;
	}

	public void setBillSplit(HashMap<String, Integer> billSplit) {
		this.billSplit = billSplit;
	}

	public User getUserObj() {
		return userObj;
	}

	public void setUserObj(User userObj) {
		this.userObj = userObj;
	}

	public int getNumOfPpl() {
		return numOfPpl;
	}

	public void setNumOfPpl(int numOfPpl) {
		this.numOfPpl = numOfPpl;
	}
	
	
}
