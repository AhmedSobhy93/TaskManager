package com.controller;

import java.util.ArrayList;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import com.beans.ProjectBean;
import com.beans.UserBean;
import com.dao.ProjectDAO;
import com.dao.UserDAO;

public class IndexController extends HttpServlet{

	 public void init() {
		    System.out.println( getServletName() + ": initialised" );
		    
		    ArrayList<ProjectBean> projects=new ArrayList<ProjectBean>();
		    ProjectDAO adminDao=new ProjectDAO();
		    
		    projects=adminDao.getAllProjects();
		    this.getServletContext().setAttribute("projects", projects);
		    
		    ArrayList<UserBean> users=new ArrayList<UserBean>();
		    UserDAO userDao=new UserDAO();
		    
		    users=(ArrayList<UserBean>) userDao.getAllUsers();
		    this.getServletContext().setAttribute("users", users);
		    
		  }
	

}
