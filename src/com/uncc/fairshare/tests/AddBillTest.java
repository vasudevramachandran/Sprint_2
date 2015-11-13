package com.uncc.fairshare.tests;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uncc.fairshare.helper.AddBill;
import com.uncc.fairshare.helper.AddBillForFriend;
import com.uncc.fairshare.helper.FriendsList;
import com.uncc.fairshare.impl.AddBillImpl;

public class AddBillTest {
	
	private AddBillForFriend addBillObj = new AddBillForFriend();
	private FriendsList friendsList = new FriendsList();
	@Before
	public void setUp() throws Exception {
		
		addBillObj.setUserEmail("vas@gmail.com");
		addBillObj.setFriendsEmail("sai@uncc.edu");
		addBillObj.setBillAmount(55);
		addBillObj.setBillDescription(" Walmart ");
		addBillObj.setUserName("Vasu");
		addBillObj.setFriendName("Sai Krishna");
		
		friendsList = new FriendsList();
		friendsList.setUserEmail("vas@gmail.com");
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddBill() {
		//fail("Not yet implemented");
		AddBillImpl addBillObj = new AddBillImpl();
		
	 int result = addBillObj.addBill(this.addBillObj);
	 assertEquals(1, result);
	}

	@Test
	public void testFetchFriends() {
		//fail("Not yet implemented");
		
		FriendsList frndObj = new FriendsList();
		
		
		frndObj.setUserEmail("vas@gmail.com");
		HashMap<String, String> friendsList = new HashMap<String, String>();
		
		friendsList.put("sai@uncc.edu", "Sai Krishna");
		friendsList.put("kishore@gmail.com", "Kishore");
		friendsList.put("jiadi@gmail.com", "Jiadi");
		frndObj.setFriendsListMap(friendsList);
		
		AddBillImpl addBillObj = new AddBillImpl();
		this.friendsList = addBillObj.fetchFriends(this.friendsList);
		
		assertEquals( this.friendsList,frndObj);
		
		
		
	}

}
