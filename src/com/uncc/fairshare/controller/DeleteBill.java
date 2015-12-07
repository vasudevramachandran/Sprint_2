package com.uncc.fairshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uncc.fairshare.impl.FetchBillImpl;
import com.uncc.fairshare.intf.FetchBillIntf;

/**
 * Servlet implementation class DeleteBill
 */
@WebServlet("/DeleteBill")
public class DeleteBill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteBill() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("test");
		deleteBill(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("test");
		deleteBill(request,response);
	}
	
	private void deleteBill(HttpServletRequest request, HttpServletResponse response){
	
		int returnVal = 0;
		if(null != request.getParameter("deleteId")){
			
			int deleteId = Integer.parseInt(request.getParameter("deleteId"));
			
			FetchBillIntf fetchBillObj;
			fetchBillObj = new FetchBillImpl();
			returnVal = fetchBillObj.deleteBill(deleteId);
			
			RequestDispatcher requDispatcher = request.getRequestDispatcher("/jsp/home.jsp");
			
			if(returnVal == 1){
			try {
				request.setAttribute("validDelete", 1);
				requDispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else{
				try {
					request.setAttribute("validDelete", 0);
					requDispatcher.forward(request, response);
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

}
