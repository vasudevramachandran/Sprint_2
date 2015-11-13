package com.uncc.fairshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.AddBill;
import com.uncc.fairshare.helper.AddBillForFriend;
import com.uncc.fairshare.impl.AddBillImpl;
import com.uncc.fairshare.intf.AddBillIntf;

/**
 * Servlet implementation class AddBillServlet
 */
//@WebServlet("/AddBillServlet")
@WebServlet("/AddBill")
public class AddBillServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBillServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getSessionValue(request, response);
		System.out.println("called");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getSessionValue(request, response);
		System.out.println("called");
		
	}
	
	private void getSessionValue(HttpServletRequest request, HttpServletResponse response){
		
		AddBillForFriend addBillObj = new AddBillForFriend();
		
		//System.out.println("value-->"+request.getParameter("userId"));
		
		//addBillObj.setUserId(Integer.parseInt((String)request.getParameter("userId")));
		//addBillObj.setFriendId(Integer.parseInt((String)request.getParameter("friendIdVal")));
		//addBillObj.setBillAmount(Integer.parseInt((String)request.getParameter("billAmount")));
		
		addBillObj.setUserEmail((String) request.getParameter(CommonConstants.EMAIL));
		addBillObj.setFriendsEmail((String)request.getParameter(CommonConstants.JSP_FRND_EMAIL));
		addBillObj.setBillAmount(Integer.parseInt((String)request.getParameter(CommonConstants.JSP_BILL_AMT)));
		
		addBillObj.setBillDescription((String)request.getParameter(CommonConstants.JSP_BILL_DESC));
		//addBillObj.setFriendName((String)request.getParameter("friendNameVal"));
		addBillObj.setUserName((String)request.getParameter(CommonConstants.USER_NAME));
		//addBillObj.setUserEmail("niranjan@gmail.com");
		
		addBillObj.setFriendName((String)request.getParameter(CommonConstants.JSP_FRND_NAME));
		
			
		int statusVal = CommonConstants.INT_FAIlURE;
		
		statusVal = new AddBillImpl().addBill(addBillObj);
		
		if(statusVal == CommonConstants.INT_SUCCESS){
			request.setAttribute("status", CommonConstants.INT_SUCCESS);
		}else{
			request.setAttribute("status", CommonConstants.INT_FAIlURE);
		}
		
		
		RequestDispatcher reqDisp = request.getRequestDispatcher("/jsp/home.jsp");
		
		try {
			reqDisp.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
	}

}
