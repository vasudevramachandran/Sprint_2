package com.uncc.fairshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.impl.DeleteMemberImpl;
import com.uncc.fairshare.intf.DeleteBillIntf;
import com.uncc.fairshare.intf.DeleteMemberIntf;

/**
 * Servlet implementation class DeleteMemberFromGroupServlet
 */
@WebServlet("/DeleteMemFromGrp")
public class DeleteMemberFromGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteMemberFromGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		deleteMember(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void deleteMember(HttpServletRequest request, HttpServletResponse response){
		int result = CommonConstants.INT_FAILURE;
		
		if(null != request.getParameter(CommonConstants.JSP_USER_ID)){
			if(null != request.getParameter(CommonConstants.JSP_GROUP_ID)){
				String userId = request.getParameter(CommonConstants.JSP_USER_ID);
				int groupId = Integer.parseInt(request.getParameter(CommonConstants.JSP_GROUP_ID));
				
				DeleteMemberIntf deleteMemObj = null;
				deleteMemObj = new DeleteMemberImpl();
				result = deleteMemObj.deleteGroupMember(userId, groupId);
			}
			
			boolean isSuccess = (result>0)? true: false;
			
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
