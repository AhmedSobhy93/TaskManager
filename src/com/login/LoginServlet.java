package com.login;

import com.dao.*;
import com.beans.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {

			try
			{	    
			     UserBean user = new UserBean();
			     user.setUserName(request.getParameter("un"));
			     user.setPassword(request.getParameter("pw"));
			
			     user = UserDAO.login(user);
				   		    
				     if (user.isValid())
				     {
					        
				          HttpSession session = request.getSession(true);	    
				          session.setAttribute("currentSessionUser",user); 
				          response.sendRedirect("TaskController?action=listTask"); //logged-in page      		
				     }
					        
				     else 
				          response.sendRedirect("invalidLogin.jsp"); //error page 
				}catch (Throwable theException) 	    
			{
			     System.out.println(theException); 
			}
       }
	}