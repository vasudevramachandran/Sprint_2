package com.uncc.fairshare.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.uncc.fairshare.constants.CommonConstants;
import com.uncc.fairshare.helper.CreateGroupDetail;
import com.uncc.fairshare.helper.User;
import com.uncc.fairshare.impl.CreateGroupImpl;
import com.uncc.fairshare.intf.CreateGroupIntf;

/**
 * Servlet implementation class CreateGroupServlet
 */
@WebServlet("/CreateGroup")
public class CreateGroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateGroupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		createGroup(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		createGroup(request,response);
	}
	
	private void createGroup(HttpServletRequest request, HttpServletResponse response){
		
		CreateGroupDetail createGroupObj = new CreateGroupDetail();
		
		CreateGroupIntf createGroupImplObj = null;
		
		createGroupImplObj = new CreateGroupImpl();
		
		if(null != request.getParameter(CommonConstants.JSP_GROUP_NAME) ||
				null!= request.getParameter(CommonConstants.JSP_SEL_FRND_LIST)){
			String groupName = (String) request.getParameter(CommonConstants.JSP_GROUP_NAME);
			String selectedString = (String) request.getParameter(CommonConstants.JSP_SEL_FRND_LIST);
			
			HashMap<String,String> friendsMap = new HashMap<String, String>();
			
			String[] splitString = selectedString.split(",");
			for(int i=0; i< splitString.length; i++){
				String[] subSplit = splitString[i].toString().split("-");
				friendsMap.put(subSplit[CommonConstants.INT_INDEX_0], subSplit[CommonConstants.INT_INDEX_1]);
			}
			
			if(null!= friendsMap && !friendsMap.isEmpty()){
				createGroupObj.setGroupName(groupName);
				createGroupObj.setFriendListMap(friendsMap);
				createGroupObj = createGroupImplObj.createGroup(createGroupObj);
				RequestDispatcher reqDisp = request.getRequestDispatcher("/jsp/home.jsp");
				
					request.getSession(false).setAttribute(CommonConstants.JSP_CREATE_GRP_OBJ, createGroupObj);
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
	}

}
