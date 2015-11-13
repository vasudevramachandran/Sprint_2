package com.uncc.fairshare.controller;

import java.io.IOException;






import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.BillDataFetch;
import com.uncc.fairshare.helper.FetchBillDetails;
import com.uncc.fairshare.impl.FetchBillImpl;
import com.uncc.fairshare.intf.FetchBillIntf;

/**
 * Servlet implementation class FetchBillDataServlet
 */
@WebServlet("/FetchBillDataServlet")
public class FetchBillDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBillDataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		fetchBillData(request,response);
		System.out.println("called");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		fetchBillData(request,response);
		System.out.println("called");
	}
	
	private void fetchBillData(HttpServletRequest request, HttpServletResponse response){
		
		if(null!= request.getParameter(CommonConstants.EMAIL)){
		
		FetchBillDetails billDataObj = new FetchBillDetails();
		
		FetchBillIntf fetchBill;
		billDataObj.setEmail((String)request.getParameter(CommonConstants.EMAIL));
		fetchBill = new FetchBillImpl();
		
		billDataObj = fetchBill.fetchBillForUser(billDataObj);
		
		RequestDispatcher reqDispObj = request.getRequestDispatcher("/jsp/deletebillpage.jsp");

		if(null!= billDataObj){
			request.setAttribute("billDataObj", billDataObj);
			try {
				reqDispObj.forward(request,response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			request.setAttribute("billDataObj", null);
			try {
				reqDispObj.forward(request, response);
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
