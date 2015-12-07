package com.uncc.fairshare.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.constants.JspUrlPath;
import com.uncc.fairshare.helper.FetchGroupDetails;
import com.uncc.fairshare.impl.FetchGroupImpl;
import com.uncc.fairshare.intf.FetchGroupIntf;

/**
 * Servlet implementation class FetchGroupDetails
 */
@WebServlet("/FetchGroup")
public class FetchGroupDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchGroupDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		fetchDetails(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		fetchDetails(request,response);
	}
	
	private FetchGroupDetails fetchDetails(HttpServletRequest request, HttpServletResponse response){
		
		FetchGroupDetails fetchGroupObj = null;
		if(null != request.getParameter(CommonConstants.JSP_GROUP_ID)){
			
			 fetchGroupObj = new FetchGroupDetails();
			
			fetchGroupObj.setGroupId(Integer.parseInt((String) 
					request.getParameter(CommonConstants.JSP_GROUP_ID)));
			
			FetchGroupIntf fetchGroupIntf ;
			fetchGroupIntf = new FetchGroupImpl();
			fetchGroupObj = fetchGroupIntf.fetchGroupDetails(fetchGroupObj);
			
			if(fetchGroupObj.isValidFetch()){
				
				// RequestDispatcher reqDisp = request.getRequestDispatcher("/jsp/mygroup.jsp");
				 RequestDispatcher reqDisp = request.getRequestDispatcher("/jsp/mygroupdetails.jsp");
				 request.setAttribute(CommonConstants.JSP_FETCH_GRP_OBJ, fetchGroupObj);
				 
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
		return fetchGroupObj; 
	}

}
