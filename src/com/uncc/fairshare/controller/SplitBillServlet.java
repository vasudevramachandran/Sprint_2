package com.uncc.fairshare.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.CreateBill;
import com.uncc.fairshare.helper.User;
import com.uncc.fairshare.impl.SplitByPercentage;
import com.uncc.fairshare.impl.SplitEqually;
import com.uncc.fairshare.intf.SplitIntf;

/**
 * Servlet implementation class SplitBillServlet
 */
@WebServlet("/SplitBill")
public class SplitBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SplitBillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		splitBill(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		splitBill(request,response);
	}
	
	private void splitBill(HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("called servlet");
		
		String split = request.getParameter(CommonConstants.JSP_SHARE);
		String share = request.getParameter(CommonConstants.JSP_BILL_SPLIT);
		String billDesc = request.getParameter(CommonConstants.JSP_BILL_DESC);
		
		int groupId = Integer.parseInt(request.getParameter(CommonConstants.JSP_GROUP_ID));
		int billAmount = Integer.parseInt(request.getParameter(CommonConstants.JSP_BILL_AMT));
		int billSplitType = Integer.parseInt(request.getParameter(CommonConstants.JSP_SPLIT_TYPE));
		int numOfPpl = Integer.parseInt(request.getParameter(CommonConstants.JSP_NUM_USER));
		
		SplitIntf splitObj  = null;
		
		CreateBill createBill = new CreateBill();
		
		createBill.setBillName(billDesc);
		
		User user = (User)request.getSession(false).getAttribute(CommonConstants.OBJ_USER);
		
		//if(split!=null && !split.equalsIgnoreCase(CommonConstants.EMPTY_STRING)){
		//	if(null != share && !share.equalsIgnoreCase(CommonConstants.EMPTY_STRING)){
				
				HashMap<String, Integer> billSplit = new HashMap<String, Integer>();
				HashMap<String, Integer> billSplitEqual = new HashMap<String, Integer>();
				
				if(billSplitType != 0){
				String tempStr = share.substring(0,share.length()-1);
				
				String[] UserArr = tempStr.split(",");
				for(int i = 0; i < UserArr.length ; i++){
					  String[] subStr = UserArr[i].split("-");
					  String tempEmail = subStr[CommonConstants.INT_INDEX_0];
					  int billAmt = Integer.parseInt(subStr[CommonConstants.INT_INDEX_1]);
					  if(tempEmail!= request.getSession(false).getAttribute(CommonConstants.EMAIL)){
						  billSplit.put(tempEmail, billAmt);
					  }
					}
				}else{
					
					HashMap<String, String> groupMembers = (HashMap<String, String>) request.getSession(false).getAttribute(CommonConstants.JSP_SESSION_GROUP); 
					Iterator iterate = groupMembers.entrySet().iterator();
					while(iterate.hasNext()){
						Map.Entry pair = (Map.Entry) iterate.next();
						billSplitEqual.put((String) pair.getKey(), 0);
					}
				}
				
				if(billSplitType == 0){
					createBill.setNumOfPpl(numOfPpl);
				}
				
				createBill.setGroupId(groupId);
				createBill.setBillAmount(billAmount);
				createBill.setBillSplit(billSplit);
				
				createBill.setUserObj(user);
				if(billSplitType == 1){
					splitObj = new SplitByPercentage();
					splitObj.split(createBill);
				}else{
					createBill.setBillSplit(billSplitEqual);
					splitObj = new SplitEqually();
					splitObj.split(createBill);
				}
				
				RequestDispatcher reqDispObj = request.getRequestDispatcher("/jsp/home.jsp");
				try {
					reqDispObj.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			//}
		//}
		
	}

}
