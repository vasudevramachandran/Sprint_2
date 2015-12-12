package com.uncc.fairshare.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uncc.fairshare.helper.BillDataFetch;
import com.uncc.fairshare.helper.FetchBillDetails;
import com.uncc.fairshare.impl.FetchBillImpl;
import com.uncc.fairshare.intf.FetchBillIntf;

public class FetchBillTest {

	private String userEmail = "vas@gmail.com";
	FetchBillDetails billDataFetch;
	@Before
	public void setUp() throws Exception {
		billDataFetch  = new FetchBillDetails();
		billDataFetch.setEmail(userEmail);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testFetchBillForUser() {
		//fail("Not yet implemented"); // TODO
		
		FetchBillIntf fetchBillTest = new FetchBillImpl();
		
		assertNotNull(fetchBillTest.fetchBillForUser(billDataFetch));
	}

}
