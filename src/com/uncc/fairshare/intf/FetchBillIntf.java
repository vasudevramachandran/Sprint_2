/**
 * 
 */
package com.uncc.fairshare.intf;

import com.uncc.fairshare.helper.BillDataFetch;
import com.uncc.fairshare.helper.FetchBillDetails;

/**
 * @author vasudev
 *
 */
public interface FetchBillIntf {
	
	public FetchBillDetails fetchBillForUser(FetchBillDetails billDataObj);
	
	public int deleteBill(int deleteId);

}
