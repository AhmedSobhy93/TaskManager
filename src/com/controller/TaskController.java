package com.controller;

import com.dao.*;
import com.beans.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TaskController", urlPatterns = { "/TaskController" })
public class TaskController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static String INSERT_OR_EDIT = "/task.jsp";
	private static String LIST_TASK = "/listTask.jsp";
	private TaskDAO dao;

	public TaskController() {
		super();

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		int page = 1;
		int recordsPerPage = 5;
		if (request.getParameter("page") != null)
			page = Integer.parseInt(request.getParameter("page"));

		dao = new TaskDAO();

		String forward = "";
		String action = "";
		if (request.getParameter("action") != null)
			action = request.getParameter("action");

		if (action.equalsIgnoreCase("delete")) {

			UserBean currentUser = ((UserBean) (request.getSession().getAttribute("currentSessionUser")));
			if (currentUser == null) {
				try {
					response.sendRedirect("/TaskManagerIST/");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
			} else {
				int taskID = Integer.parseInt(request.getParameter("taskID"));
				dao.deleteTask(taskID);

				try {
					response.sendRedirect("/TaskManagerIST/TaskController?action=listTask");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return;
				// forward = LIST_TASK;
				// request.setAttribute("tasks",
				// dao.getAllTasks((page-1)*recordsPerPage,
				// recordsPerPage));
			}
		} else if (action.equalsIgnoreCase("edit")) {
			forward = INSERT_OR_EDIT;
			int taskID = Integer.parseInt(request.getParameter("taskID"));
			TaskBean task = dao.getTaskById(taskID);
			request.setAttribute("task", task);
		} else if (action.equalsIgnoreCase("listTask")) {
			forward = LIST_TASK;
			request.setAttribute("tasks", dao.getAllTasks((page - 1) * recordsPerPage, recordsPerPage));
			/////////// test code///////////////
			System.out.println("tasks size Ctrl" + dao.getAllTasks((page - 1) * recordsPerPage, recordsPerPage).size());
			//
			int noOfRecords = dao.getNoOfRecords();
			System.out.println("ctrl >> noOfRecords" + noOfRecords);
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			for (TaskBean task : dao.getAllTasks((page - 1) * recordsPerPage, recordsPerPage)) {
				System.out.print("**Ctrl proj name>>" + task.getProjectName());
			}
			// for(int i=1;i<=dao.getAllTasks().size();i++){
			//// TaskBean task=new TaskBean();
			// System.out.print("Ctrl >>"+dao.getTaskById(i).getProjectName());
			//// System.out.println(task.getOwner());
			// }
			////////////////////////////////////
		} else if (action.equalsIgnoreCase("search")) {
			String searchKey = request.getParameter("searchKey");
			forward = LIST_TASK;
			request.setAttribute("tasks",
					dao.getTasksByProjectName(searchKey, (page - 1) * recordsPerPage, recordsPerPage));
			System.out.println("tasks size Ctrl" + dao.getAllTasks((page - 1) * recordsPerPage, recordsPerPage).size());
			//
			for (TaskBean task : dao.getTasksByProjectName(searchKey, (page - 1) * recordsPerPage, recordsPerPage)) {
				System.out.print("**Ctrl proj name>>" + task.getProjectName());
			}
			int noOfRecords = dao.getNoOfRecords();
			System.out.println("ctrl >> noOfRecords" + noOfRecords);
			int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			request.setAttribute("noOfPages", noOfPages);
			request.setAttribute("currentPage", page);
			request.setAttribute("searchKey", searchKey);
			// request.setAttribute("tasks",
			// dao.getTasksByProjectName(searchKey));

		} else {

			forward = INSERT_OR_EDIT;
			
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// int page = 1;
		// int recordsPerPage = 5;
		// if(request.getParameter("page") != null)
		// page = Integer.parseInt(request.getParameter("page"));

		dao = new TaskDAO();
		// int noOfRecords = dao.getNoOfRecords();
		// int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

		TaskBean task = new TaskBean();
		task.setProjectName(request.getParameter("projectName"));
		task.setOwner(Integer.valueOf(request.getParameter("owner")));
		task.setTitle(request.getParameter("title"));
		task.setComment(request.getParameter("comment"));
		//
		String taskID = request.getParameter("taskID");

		System.out.println(taskID);
		String action = request.getParameter("action1");

		System.out.println(action);

		if (!action.equals("edit")) {
			dao.addTask(task);
		} else {
			task.setTaskID(Integer.parseInt(taskID));
			dao.updateTask(task);
		}
		// request.setAttribute("noOfPages", noOfPages);
		// request.setAttribute("currentPage", page);
		//
		// request.setAttribute("tasks",
		// dao.getAllTasks((page-1)*recordsPerPage,
		// recordsPerPage));
		// RequestDispatcher view = request.getRequestDispatcher(LIST_TASK);
		//
		// view.forward(request, response);

		response.sendRedirect("/TaskManagerIST/TaskController?action=listTask");
	}

}
