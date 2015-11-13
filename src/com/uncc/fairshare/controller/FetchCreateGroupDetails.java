package com.uncc.fairshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.FriendsList;
import com.uncc.fairshare.impl.AddBillImpl;
import com.uncc.fairshare.intf.AddBillIntf;
import com.uncc.fairshare.intf.FetchBillIntf;

/**
 * Servlet implementation class FetchCreateGroupDetails
 */
@WebServlet("/FetchCreateGroup")
public class FetchCreateGroupDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchCreateGroupDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		fetchDetails(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private FriendsList fetchDetails(HttpServletRequest request, HttpServletResponse response){
		
		if(null != request.getSession(false).getAttribute(CommonConstants.EMAIL)){
			
			String email = (String)request.getSession(false).getAttribute(CommonConstants.EMAIL);
			
			FriendsList friendsList = new FriendsList();
			
			friendsList.setUserEmail(email);
			AddBillIntf addBillObj ;
			addBillObj = new AddBillImpl();
			friendsList = addBillObj.fetchFriends(friendsList);
			
			if(null != friendsList){
				RequestDispatcher reqDisp = request.getRequestDispatcher("/jsp/creategroup.jsp");
				request.getSession(false).setAttribute(CommonConstants.JSP_FRIENDS_LIST, friendsList.getFriendsListMap());
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
		
		return null;
	}

}
