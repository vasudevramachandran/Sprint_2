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

public static final String SQL_INSERT_GROUP_BILL = "INSERT INTO `bill_master`(`C_ID`,`BILL_ID`, `GROUP_ID`, `USER_ID`, `ADDED_BY`, `CREATED_DATE`, `OWE_PAYED`, `OWE_AMOUNT`, `SETTLED`, `ACTIVE`) VALUES "
													+ "(?,?,?,?,?,now(),NULL,?,NULL,1);"; 
public static final String SQL_FETCH_GROUP_BILLS =	 " Select bm.bill_id, bm.group_id, bm.user_id, u.name as User_name, "+
													 " bm.added_by, bm.OWE_AMOUNT, bd.BILL_DESCRIPTION, (select name from "+
													 " user where email = bd.CREATED_BY) , gd.group_name as groupname from bill_master bm, group_desc gd, user u, "+
													 " bill_details bd where bm.group_id = ? and bm.USER_ID = u.email "+
													 " and active = 1 and bd.BILL_ID=bm.BILL_ID and gd.group_id= bm.group_id";
public static final String SQL_DELETE_BILL = " UPDATE BILL_MASTER SET ACTIVE = 0 WHERE BILL_ID = ? ";

public static final String SQL_DELETE_MEMBER = " DELETE FROM GROUP_MEMBERS_LOOKUP WHERE GROUP_MEMBER_EMAIL = ? AND GROUP_ID = ? ";

}
