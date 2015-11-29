package com.uncc.fairshare.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.uncc.fairshare.impl.*;

/**
 * Servlet implementation class FriendsServlet
 */
@WebServlet("/FriendsServlet")
public class FriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");    
        PrintWriter out = response.getWriter();    
        String em=request.getParameter("femail");
        String sd=request.getParameter("uemail");
        int fl=0;
        for(int i=0;i<em.length();i++)
        {
        	if(em.charAt(i)=='@')
        	{
        		fl=1;
        	}
        	else if(em.charAt(i)=='.'&&fl==1)
        	{
        		fl=2;
        		break;
        	}
        }

        if(fl!=2)
        {
            out.print("<p style=\"color:red\">Sorry email is not in the required format!</p>");    
            RequestDispatcher rd=request.getRequestDispatcher("invitefriends.jsp");    
            rd.include(request,response);    
        }  
        if(!(FriendsDao.validate(sd,em))){
        	FriendsDao.invitefriends(sd,em);
            out.print("<p style=\"color:green\"> Invite sent successfully!</p>");                	
            RequestDispatcher rd=request.getRequestDispatcher("invitefriends.jsp");    
            rd.include(request,response);    
        }    
        else{    
            out.print("<p style=\"color:red\">Sorry some error!</p>");    
            RequestDispatcher rd=request.getRequestDispatcher("invitefriends.jsp");    
            rd.include(request,response);    
        }    
  
        out.close();    
    }    
    
}
