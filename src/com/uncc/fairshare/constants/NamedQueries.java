/**
 * 
 */
package com.uncc.fairshare.constants;

/**
 * @author temp
 *
 */
public class NamedQueries {
	
	
public static final String SQL_GROUP_FETCH_DETAILS_USING_ID = " SELECT GROUP_ID, GROUP_NAME, GROUP_DESC, CREATE_DATE FROM GROUP_DESC WHERE GROUP_ID =? ";

public static final String SQL_GROUP_FETCH_GRP_FRNDS_USING_ID = " SELECT U.NAME, G.GROUP_MEMBER_EMAIL FROM GROUP_MEMBERS_LOOKUP G, USER U WHERE G.GROUP_MEMBER_EMAIL = U.EMAIL AND GROUP_ID = ?";
 
}
