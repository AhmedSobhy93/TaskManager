package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import com.beans.AdminBean;
import com.beans.ProjectBean;
import com.beans.UserBean;
import com.dao.ProjectDAO;
import com.dao.UserDAO;

@WebServlet(name="UserController",urlPatterns={"/User"})
public class UserController extends HttpServlet{

	private static String INSERT_OR_EDIT_USER = "/userViews/user.jsp";

    private UserDAO dao;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action="";
		String forward="";
		
		action = (request.getParameter("action")!= null) ? request.getParameter("action") : "null";
       
		dao=new UserDAO();
       

        if (action.equalsIgnoreCase("delete")){
        	
        	
        	AdminBean admin = ((AdminBean) (request.getSession().getAttribute("admin")));
        	if(admin==null){
        		try {
					response.sendRedirect("/TaskManagerIST/Admin");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		 return;
        	}else{
	            int userID = Integer.parseInt(request.getParameter("userId"));
	            
	            dao.deleteUser(userID);
	            
	            ArrayList<UserBean> users=new ArrayList<UserBean>();
			    UserDAO userDao=new UserDAO();
			    
			    users=(ArrayList<UserBean>) userDao.getAllUsers();
			    this.getServletContext().setAttribute("users", users);
	            	
		        try {
					response.sendRedirect("/TaskManagerIST/Admin");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        return;
//	            forward = LIST_TASK;
//	            request.setAttribute("tasks", dao.getAllTasks((page-1)*recordsPerPage,
//                        recordsPerPage));    
        	}
        }else if(action.equalsIgnoreCase("addUser")){
			

			forward=INSERT_OR_EDIT_USER;
			System.out.println("GET");
			try {
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
		} else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT_USER;
            int userID = Integer.parseInt(request.getParameter("userId"));
            System.out.println(userID);
            UserBean user = dao.getUserById(userID);
            request.setAttribute("user", user);
        } else{
        	
        	
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        try {
			view.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dao=new UserDAO();
//      int noOfRecords = dao.getNoOfRecords();
//      int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);	        
      
		 UserBean user = new UserBean();
		 
		 user.setFirstName(request.getParameter("firstName"));
		 user.setLastName(request.getParameter("lastName"));
		 user.setUserName(request.getParameter("username"));
		 user.setPassword(request.getParameter("password"));
		 
//	       
	        String userID = request.getParameter("userID");
	        
	        System.out.println(userID);
	        String action = request.getParameter("action1");
	        
	        System.out.println(action);
	        
	        if(!action.equals("edit"))
	        {
	        	System.out.println("Add user");
	        	dao.addUser(user);
	        }
	        else 
	        {
	        	user.setUserId(Integer.parseInt(request.getParameter("userID")));
	        	System.out.println("Edit User");
	        	dao.updateUser(user);
	        	
	        }
//	        request.setAttribute("noOfPages", noOfPages);
//	        request.setAttribute("currentPage", page);
//
//	        request.setAttribute("tasks", dao.getAllTasks((page-1)*recordsPerPage,
//                  recordsPerPage));
//	        RequestDispatcher view = request.getRequestDispatcher(LIST_TASK);
//
//	        view.forward(request, response);
	        
	        try {
	        	ArrayList<UserBean> users=new ArrayList<UserBean>();
			    UserDAO userDao=new UserDAO();
			    
			    users=(ArrayList<UserBean>) userDao.getAllUsers();
			    this.getServletContext().setAttribute("users", users);
		            	
			        try {
						response.sendRedirect("/TaskManagerIST/Admin");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		

	}
}
