package com.uncc.fairshare.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uncc.fairshare.impl.FriendsDao;

public class FriendsDaoTest {

	String userEmail = "vas@gmail.com"; 
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testValidate() {
		//fail("Not yet implemented"); // TODO
		FriendsDao frndsDao = new FriendsDao();
		assertNotNull(frndsDao.validate(userEmail, userEmail));
	}

}
