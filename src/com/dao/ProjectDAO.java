package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import com.beans.ProjectBean;
import com.db.ConnectionManager;

public class ProjectDAO {

	static Connection conn = null;
	static ResultSet rs = null;

	public ProjectDAO() {
		conn = ConnectionManager.getConnection();
	}

	public void addProject(ProjectBean project) {
		String query = "insert into project  (project_name) values (?)";

		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, project.getProjectName());

			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<ProjectBean> getAllProjects() {

		ArrayList<ProjectBean> projects = new ArrayList<ProjectBean>();

		String query = "select * from project";

		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			while (result.next()) {
				ProjectBean project = new ProjectBean();
				project.setProjectName(result.getString("project_name"));
				project.setProjectId(result.getInt("project_id"));
				projects.add(project);
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println("exception" + e.getMessage());
			e.printStackTrace();
		}

		return projects;
	}

	public void deleteById(int id) {
		String query = "delete from project where project_id=?";
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateById(ProjectBean project) {
		String query = "update project set project_name=? where project_id=?";

		try {
			System.out.println("updateBYId");
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, project.getProjectName());
			preparedStatement.setInt(2, project.getProjectId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ProjectBean getProjectById(int id) {
		String query = "select * from project where project_id=?";
		ProjectBean project = new ProjectBean();
		try {
			System.out.println("getProjectById");
			System.out.println("id"+id);
			PreparedStatement preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				System.out.println(result.getInt("project_id"));
				project.setProjectId(result.getInt("project_id"));
				project.setProjectName(result.getString("project_name"));
			}
			result.close();
			preparedStatement.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return project;
	}

	public static void main(String[] args) {
		ProjectDAO dao = new ProjectDAO();

		ArrayList<ProjectBean> projects = new ArrayList<ProjectBean>();

		// projects= getAllProjects();

		Iterator<ProjectBean> iterator = projects.iterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next().getProjectName());
		}
	}

}
