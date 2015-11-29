/**
 * 
 */
package com.uncc.fairshare.helper;

/**
 * @author temp
 *
 */
public class GroupBillDetails {

	private int billId;
	private int groupId;
	private double billShare;
	
	private String owedByName;
	private String owedByEmail;
	private String billName;
	private String addedByEmail;
	private String addedByName;
	private String groupName;
	
	public int getBillId() {
		return billId;
	}
	public void setBillId(int billId) {
		this.billId = billId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public double getBillShare() {
		return billShare;
	}
	public void setBillShare(double billShare) {
		this.billShare = billShare;
	}
	public String getOwedByName() {
		return owedByName;
	}
	public void setOwedByName(String owedByName) {
		this.owedByName = owedByName;
	}
	public String getOwedByEmail() {
		return owedByEmail;
	}
	public void setOwedByEmail(String owedByEmail) {
		this.owedByEmail = owedByEmail;
	}
	public String getBillName() {
		return billName;
	}
	public void setBillName(String billName) {
		this.billName = billName;
	}
	public String getAddedByEmail() {
		return addedByEmail;
	}
	public void setAddedByEmail(String addedByEmail) {
		this.addedByEmail = addedByEmail;
	}
	public String getAddedByName() {
		return addedByName;
	}
	public void setAddedByName(String addedByName) {
		this.addedByName = addedByName;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
