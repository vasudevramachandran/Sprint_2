/**
 * 
 */
package com.uncc.fairshare.helper;

import java.util.HashMap;

/**
 * @author temp
 *
 */
public class FetchBillDetails {
	
	private int userId;
	
	private HashMap<Integer, BillDataFetch> billDetails;
	
	private String email;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public HashMap<Integer, BillDataFetch> getBillDetails() {
		return billDetails;
	}

	public void setBillDetails(HashMap<Integer, BillDataFetch> billDetails) {
		this.billDetails = billDetails;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
