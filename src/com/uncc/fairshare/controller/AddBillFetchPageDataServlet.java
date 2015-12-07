package com.uncc.fairshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.FriendsList;
import com.uncc.fairshare.impl.AddBillImpl;
import com.uncc.fairshare.intf.AddBillIntf;

/**
 * Servlet implementation class AddBillFetchDataServlet
 */
//@WebServlet("/AddBillFetchDataServlet")
@WebServlet("/FetchPageData")
public class AddBillFetchPageDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBillFetchPageDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response){
		
		
		try{
			
		if(null != request.getSession(false).getAttribute(CommonConstants.EMAIL)){
		
		//int userId = Integer.parseInt((String)request.getParameter("userId"));
		 String email = (String )  request.getSession(false).getAttribute(CommonConstants.EMAIL);
		
		FriendsList flListObj = new FriendsList();
		//flListObj.setUserID(userId);
		flListObj.setUserEmail(email);
		AddBillIntf addBill;
		
		addBill = new AddBillImpl();
		addBill.fetchFriends(flListObj);
		
		request.setAttribute("friendsList", flListObj.getFriendsListMap());
		
		RequestDispatcher dispObj = request.getRequestDispatcher("/jsp/addbillforuser.jsp");
		try {
			dispObj.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }//if block close
			
		}catch(NullPointerException npe){
			RequestDispatcher dispObj = request.getRequestDispatcher("/jsp/home.jsp");
			request.setAttribute("Error", "Something went wrong");
			try {
				dispObj.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
