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
import com.uncc.fairshare.impl.RegisterImpl;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/Register")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		registerUser(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void registerUser(HttpServletRequest request, HttpServletResponse response){
		if(null != request.getParameter(CommonConstants.USER_NAME) ){
		
			String userName = request.getParameter(CommonConstants.USER_NAME);
			String email = request.getParameter(CommonConstants.EMAIL);
			String passWord = request.getParameter(CommonConstants.PASS_WORD);
			
			User user = new User();
			user.setUserName(userName);
			user.setEmail(email);
			user.setPassword(passWord);
			
			boolean register = new RegisterImpl().registerdata(user);
			
			if(register){
				HttpSession session = request.getSession(false);
				session.setAttribute(CommonConstants.EMAIL, email);
				session.setAttribute(CommonConstants.USER_NAME, userName);
				session.setAttribute("registerSuccess", "true");
				request.setAttribute("registerSuccess", "true");
				RequestDispatcher reqDisp = request.getRequestDispatcher("/jsp/login.jsp");
				try {
					reqDisp.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else{
				HttpSession session = request.getSession(false);
				session.setAttribute(CommonConstants.EMAIL, email);
				session.setAttribute(CommonConstants.USER_NAME, userName);
				session.setAttribute("registerSuccess", "false");
				request.setAttribute("registerSuccess", "false");
				RequestDispatcher reqDisp = request.getRequestDispatcher("/jsp/login.jsp");
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
