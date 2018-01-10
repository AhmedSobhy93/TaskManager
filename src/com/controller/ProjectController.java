package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.beans.AdminBean;
import com.beans.ProjectBean;
import com.beans.TaskBean;
import com.beans.UserBean;
import com.dao.ProjectDAO;
import com.dao.TaskDAO;

@WebServlet(name="ProjectController",urlPatterns={"/Project"})
public class ProjectController extends HttpServlet{

	private static String INSERT_OR_EDIT_PROJECT = "/adminViews/addProject.jsp";
    private static String LIST_PROJECTS = "/adminViews/adminPanel.jsp";
	private String ADD_PROJECT="adminViews/addProject.jsp";

    private ProjectDAO dao;
    
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		{
			String action="";
			String forward="";
			
			action = (request.getParameter("action")!= null) ? request.getParameter("action") : "null";
           
			dao=new ProjectDAO();
	       

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
		            int projectID = Integer.parseInt(request.getParameter("projectId"));
		            
		            dao.deleteById(projectID);
		            
		            ArrayList<ProjectBean> projects=new ArrayList<ProjectBean>();
				    ProjectDAO adminDao=new ProjectDAO();
				    
				    projects=adminDao.getAllProjects();
				    this.getServletContext().setAttribute("projects", projects);
		            	
			        try {
						response.sendRedirect("/TaskManagerIST/Admin");
						return;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        return;
	//	            forward = LIST_TASK;
	//	            request.setAttribute("tasks", dao.getAllTasks((page-1)*recordsPerPage,
	//                        recordsPerPage));    
	        	}
	        }else if(action.equalsIgnoreCase("addProject")){
				

				forward=ADD_PROJECT;
				System.out.println("GET");
				try {
					RequestDispatcher view = request.getRequestDispatcher(forward);
				      try {
						view.forward(request, response);
						return;
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (action.equalsIgnoreCase("edit")){
	            forward = INSERT_OR_EDIT_PROJECT;
	            int projectID = Integer.parseInt(request.getParameter("projectId"));
	            System.out.println(projectID);
	            ProjectBean project = dao.getProjectById(projectID);
	            request.setAttribute("project", project);
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
		
	}

	@Override
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		dao=new ProjectDAO();
//        int noOfRecords = dao.getNoOfRecords();
//        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);	        
        
		 ProjectBean project = new ProjectBean();
		 project.setProjectName(request.getParameter("projectName"));
//	       
	        String projectID = request.getParameter("projectID");
	        
	        System.out.println(projectID);
	        String action = request.getParameter("action1");
	        
	        System.out.println(action);
	        
	        if(!action.equals("edit"))
	        {
	        	System.out.println("Add project");
	            dao.addProject(project);
	        }
	        else 
	        {
	        	System.out.println("Edit project");
	            project.setProjectId(Integer.parseInt(projectID));
	            dao.updateById(project);
	        }
//	        request.setAttribute("noOfPages", noOfPages);
//	        request.setAttribute("currentPage", page);
//
//	        request.setAttribute("tasks", dao.getAllTasks((page-1)*recordsPerPage,
//                    recordsPerPage));
//	        RequestDispatcher view = request.getRequestDispatcher(LIST_TASK);
//
//	        view.forward(request, response);
	        
	        try {
	        	 ArrayList<ProjectBean> projects=new ArrayList<ProjectBean>();
				    ProjectDAO adminDao=new ProjectDAO();
				    
				    projects=adminDao.getAllProjects();
				    this.getServletContext().setAttribute("projects", projects);
		            	
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
