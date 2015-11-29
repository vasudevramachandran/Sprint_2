/**
 * 
 */
package com.uncc.fairshare.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.CreateBill;
import com.uncc.fairshare.helper.UserBill;
import com.uncc.fairshare.intf.SplitIntf;

/**
 * @author temp
 *
 */
public class SplitByPercentage implements SplitIntf {

	/* (non-Javadoc)
	 * @see com.uncc.fairshare.intf.SplitIntf#split()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public int split(CreateBill createBill) {
		// TODO Auto-generated method stub
		
		String adminEmail = createBill.getUserObj().getEmail();
		
		int billAmount = createBill.getBillAmount();
		double billShare = 0;
		
		UserBill userBillObj = null;
		
		HashMap<String, Integer> billDetails = createBill.getBillSplit();
		HashMap<String, UserBill> billMap = new HashMap<String, UserBill>();
		
		Iterator iteratorObj = billDetails.entrySet().iterator();
		while(iteratorObj.hasNext()){
			Map.Entry pair = (Map.Entry) iteratorObj.next();
			if(!adminEmail.equalsIgnoreCase((String)pair.getKey())){
				userBillObj = new UserBill();
				userBillObj.setGroupId(createBill.getGroupId());
				billShare = ( Double.valueOf( String.valueOf(billAmount)) * (( Double.valueOf(String.valueOf(Integer.parseInt(String.valueOf(pair.getValue()))/100.00)))));
				userBillObj.setBillShare(billShare);
				userBillObj.setBillOwed(CommonConstants.BOOLEAN_TRUE);
				userBillObj.setBillDesc(createBill.getBillName());
				billMap.put((String) pair.getKey(), userBillObj);
			}
		}
		/*userBillObj = new UserBill();
		userBillObj.setGroupId(createBill.getGroupId());
		userBillObj.setBillShare(billAmount);
		userBillObj.setBillShare(CommonConstants.DBL_VAL_0);
		userBillObj.setBillOwed(CommonConstants.BOOLEAN_FALSE);
		billMap.put(adminEmail, userBillObj);*/
		
		UserBill userDetail = new UserBill();
		userDetail.setSplitBill(billMap);
		userDetail.setAddedByEmail(adminEmail);
		userDetail.setGroupId(createBill.getGroupId());
		userDetail.setUserEmail(adminEmail);
		userDetail.setBillDesc(createBill.getBillName());
		
		int result = new CreateBillImpl().insertBill(userDetail);
		
		return result;
		
	}

}
