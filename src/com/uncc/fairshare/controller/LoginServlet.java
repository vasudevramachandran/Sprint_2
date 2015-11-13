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
import com.uncc.fairshare.helper.User;
import com.uncc.fairshare.impl.UserLoginImpl;
import com.uncc.fairshare.intf.UserLoginIntf;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		handleUserLogin(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void handleUserLogin(HttpServletRequest request, HttpServletResponse response){
		
		User userObj = new User();
		
		String email="";
		String passWord = "";
		
		UserLoginIntf userLogin = null;
		
		RequestDispatcher reqDispatch = null;
		
		if(null != request.getParameter(CommonConstants.EMAIL)){
			
			email = request.getParameter(CommonConstants.EMAIL);
			passWord = request.getParameter(CommonConstants.PASS_WORD);
			
			userObj.setEmail(email);
			userObj.setPassword(passWord);
			
			userLogin = new UserLoginImpl();
			userObj = userLogin.validateUser(userObj);
			
			if(userObj.isValidUser()){
				reqDispatch = request.getRequestDispatcher("/jsp/home.jsp");
				try {
					HttpSession session = request.getSession(true);
					session.setAttribute(CommonConstants.EMAIL, userObj.getEmail());
					System.out.println("session set variable -- "+ session.getAttribute(CommonConstants.EMAIL));
					session.setAttribute(CommonConstants.USER_NAME, userObj.getUserName());
					session.setAttribute(CommonConstants.OBJ_USER, userObj);
					
					reqDispatch.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				reqDispatch = request.getRequestDispatcher("/jsp/login.jsp");
				request.setAttribute(CommonConstants.ERR_LOGIN_ERR, CommonConstants.ERMSG_LOGIN_MSG);
				try {
					reqDispatch.forward(request, response);
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
