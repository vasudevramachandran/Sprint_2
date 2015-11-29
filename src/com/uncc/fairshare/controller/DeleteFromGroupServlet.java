package com.uncc.fairshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.impl.DeleteBillImpl;
import com.uncc.fairshare.intf.DeleteBillIntf;

/**
 * Servlet implementation class DeleteFromGroupServlet
 */
@WebServlet("/DeleteFromGrp")
public class DeleteFromGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteFromGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		deleteBill(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		deleteBill(request,response);
	}
	
	private void deleteBill(HttpServletRequest request, HttpServletResponse response){
		if(null != request.getParameter(CommonConstants.JSP_BILL_ID)){
			int billId = Integer.parseInt(request.getParameter(CommonConstants.JSP_BILL_ID));
			
			int groupId = Integer.parseInt(request.getParameter(CommonConstants.JSP_GROUP_ID));
			
			DeleteBillIntf deleteBillObj = null;
			deleteBillObj = new DeleteBillImpl();
			int returnId = deleteBillObj.deleteBill(billId);
			
			boolean returnVal = (returnId > 0) ? true : false;
			
			if(returnVal){
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
			}else{
				
			}
			
		}
	}

}
