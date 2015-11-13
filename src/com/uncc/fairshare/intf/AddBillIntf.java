/**
 * 
 */
package com.uncc.fairshare.intf;

import com.uncc.fairshare.helper.AddBill;
import com.uncc.fairshare.helper.AddBillForFriend;
import com.uncc.fairshare.helper.FriendsList;

/**
 * @author temp
 *
 */
public interface AddBillIntf {

	public FriendsList fetchFriends(FriendsList friendsList);
	
	public int addBill(AddBillForFriend billObj);
	
}
