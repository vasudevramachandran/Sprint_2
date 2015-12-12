package com.uncc.fairshare.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uncc.fairshare.connection.DbConnect;

public class DbConnectTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testGetConnection() {
		//fail("Not yet implemented"); // TODO
		assertNotNull(new DbConnect().getConnection());
		
	}

}
