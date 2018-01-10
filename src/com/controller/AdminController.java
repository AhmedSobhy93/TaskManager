package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.beans.AdminBean;
import com.beans.ProjectBean;
import com.dao.ProjectDAO;

@WebServlet(name = "AdminController", urlPatterns = {"/Admin"})
public class AdminController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4798720584843444114L;
	private String action=null;
	private String forward="";
	private String ADMIN_LOGIN="adminViews/admin.jsp";
	private String ADMIN_PANEL="adminViews/adminPanel.jsp";
	private String ADD_PROJECT="adminViews/addProject.jsp";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
	{
//		System.out.println("GET");
		
		action = (request.getParameter("action")!= null) ? request.getParameter("action") : "null";

		if(action.equalsIgnoreCase("")){
				    
				
		
		}
//		else if(action.equalsIgnoreCase("addProject")){
//			
//
//			forward=ADD_PROJECT;
//			System.out.println("GET");
//			try {
//				RequestDispatcher view = request.getRequestDispatcher(forward);
//			      try {
//					view.forward(request, response);
//				} catch (ServletException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		else if(action.equalsIgnoreCase("logout")){
			System.out.println("GET-logout");
			try {
				
				forward=ADMIN_LOGIN;
				HttpSession session=request.getSession();
				session.invalidate();
				RequestDispatcher view = request.getRequestDispatcher(forward);
			      try {
					view.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("GET-action default");
							
				HttpSession session=request.getSession();
				if(session.getAttribute("admin")!=null){
					//logged in page
					forward=ADMIN_PANEL;
					RequestDispatcher view = request.getRequestDispatcher(forward);
				      try {
						view.forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					forward=ADMIN_LOGIN;
					//login page
					RequestDispatcher view = request.getRequestDispatcher(forward);
				      try {
						view.forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			
		}
	}
	
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 action = (request.getParameter("action")!= null) ? request.getParameter("action") : "null";

			if(action.equalsIgnoreCase("login")){
				try
				{	    
					forward=ADMIN_PANEL;
					System.out.println("Login");
				     AdminBean admin = new AdminBean();
				     admin.setUsername(request.getParameter("un"));
				     admin.setPassword(request.getParameter("pw"));
				
				     if(admin.getUsername().equals("admin") && admin.getPassword().equals("admin")){
				    	 //valid admin
				    	 HttpSession session = request.getSession(true);	    
				          session.setAttribute("admin",admin); 
//				          response.sendRedirect("AdminController?action=adminpanel"); //logged-in page
				          
				          RequestDispatcher view = request.getRequestDispatcher(forward);
					      view.forward(request, response);
				     }			    
					 else {
					      response.sendRedirect("invalidLogin.jsp"); //error page 
					      System.out.println("Invalid Login");
					} 
						
				}
				catch (Throwable theException) 	    
				{
				     System.out.println(theException); 
				}
			
			}
//			else if(action.equalsIgnoreCase("addProject")){
//				//admin panel
//				
//				ProjectBean project=new ProjectBean();
//				ProjectDAO projectDao=new ProjectDAO();
//				
//				project.setProjectName(request.getParameter("projectName"));
//				
//				projectDao.addProject(project);
//				
//				ArrayList<ProjectBean> projects=new ArrayList<ProjectBean>();
//			    ProjectDAO adminDao=new ProjectDAO();
//			    
//			    projects=adminDao.getAllProjects();
//			    this.getServletContext().setAttribute("projects", projects);
//			    
//				forward=ADMIN_PANEL;
//				
//				RequestDispatcher view = request.getRequestDispatcher(forward);
//			      try {
//					view.forward(request, response);
//				} catch (ServletException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			else{
				System.out.println("GET");
				try {
					
					forward=ADMIN_LOGIN;
					
					RequestDispatcher view = request.getRequestDispatcher(forward);
				      try {
						view.forward(request, response);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	 }
}
