package com.uncc.fairshare.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.uncc.fairshare.helper.FetchGroupDetails;
import com.uncc.fairshare.impl.FetchGroupImpl;
import com.uncc.fairshare.intf.FetchGroupIntf;

public class FetchGroupTest {
	
	FetchGroupDetails fetchGroupDetail = new FetchGroupDetails();
	int groupId = 1117; 
	
	
	@Before
	public void setUp() throws Exception {
		fetchGroupDetail.setGroupId(groupId);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testFetchGroupDetails() {
		//fail("Not yet implemented"); // TODO
		
		FetchGroupIntf fetchGroupDetails = new FetchGroupImpl();
		assertNotNull(fetchGroupDetails.fetchGroupDetails(fetchGroupDetail));
	}

}
