/**
 * 
 */
package com.uncc.fairshare.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uncc.fairshare.helper.User;
import com.uncc.fairshare.impl.UserLoginImpl;

import junit.framework.TestCase;

/**
 * @author temp
 *
 */
public class UserLoginTest extends TestCase {

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	
	private String userName ;
	private String passWord;
	
	private User user;
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		 userName = "vas@gmail.com";
		 passWord = "123";
		 
		 user = new User();
		 user.setEmail(userName);
		 user.setPassword(passWord);
	}

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#tearDown()
	 */
	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		userName = "";
		passWord = "";
		user = null;
	}

	/**
	 * Test method for {@link com.uncc.fairshare.impl.UserLoginImpl#validateUser(com.uncc.fairshare.helper.User)}.
	 */
	@Test
	private void ValidateUser() {
		
		User user = new User();
		
		user.setEmail("vas@gmail.com");
		user.setPassword("123");
		user.setValidUser(true);
		user.setUserName("Vasudev");
		
		User userAct = new UserLoginImpl().validateUser(user);
		
		assertEquals(user, userAct);
		createResult();
		
	}
	
	
	

}
