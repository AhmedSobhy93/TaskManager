package com.dao;

import com.beans.*;
import java.text.*;
import java.util.*;

import org.apache.catalina.connector.Request;

import java.sql.*;
import com.db.*;
   
public class TaskDAO {

      static Connection conn = null;
      static ResultSet rs = null;  
      private int noOfRecords;

	
      public TaskDAO(){
    	  conn = ConnectionManager.getConnection();
      }
      
      public void addTask(TaskBean task) {
  		try {
              String query = "insert into task (projectName, owner, title, comment) values (?,?,?,?)";
              PreparedStatement preparedStatement = conn.prepareStatement( query );
              preparedStatement.setString( 1, task.getProjectName() );
              preparedStatement.setInt( 2, task.getOwner() );
              preparedStatement.setString( 3, task.getTitle() );
              preparedStatement.setString( 4, task.getComment() );
              preparedStatement.executeUpdate();
              preparedStatement.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }
  		
  	}

  	
  	public void deleteTask(int taskID) {
  		try {
              String query = "delete from task where taskID=?";
              PreparedStatement preparedStatement = conn.prepareStatement(query);
              preparedStatement.setInt(1, taskID);
              preparedStatement.executeUpdate();
              preparedStatement.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }
  	}

  	public void updateTask(TaskBean task) {
  		 try {
  	            String query = "update task set projectName=?, owner=?, title=?, comment=? where taskID=?";
  	            PreparedStatement preparedStatement = conn.prepareStatement( query );
  	            preparedStatement.setString( 1, task.getProjectName() );
  	            preparedStatement.setInt( 2, task.getOwner() );
  	            preparedStatement.setString( 3, task.getTitle() );
  	            preparedStatement.setString( 4, task.getComment() );
  	            preparedStatement.setInt(5, task.getTaskID());
  	            preparedStatement.executeUpdate();
  	            preparedStatement.close();
  	        } catch (SQLException e) {
  	            e.printStackTrace();
  	        }
  	}

  	
  	public List<TaskBean> getAllTasks( int offset,
            int noOfRecords) {
  		List<TaskBean> tasks = new ArrayList<TaskBean>();
          try {
              Statement statement = conn.createStatement();
              ResultSet resultSet = statement.executeQuery( "select * from task limit "
                 + offset + ", " + noOfRecords);
              while( resultSet.next() ) {
                  TaskBean task = new TaskBean();
                  task.setTaskID( resultSet.getInt( "taskID" ) );
//                  System.out.print("Dao >>"+task.getTaskID());
                  task.setProjectName( resultSet.getString( "projectName" ) );
//                  System.out.print("Dao >>"+task.getProjectName());
                  task.setOwner( resultSet.getInt( "owner" ) );
                  task.setTitle( resultSet.getString( "title" ) );
                  task.setComment( resultSet.getString( "comment" ) );
                  task.setDateInserted(resultSet.getString("DateInserted"));
                  tasks.add(task);
              }
              resultSet.close();
              
              rs = statement.executeQuery("SELECT COUNT(*) FROM task");
              if(rs.next()){
                  this.noOfRecords = rs.getInt(1);
                  System.out.println("this noOfRecords"+noOfRecords);
              }
              
              statement.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }
          return tasks	;
  	}

  	
  	public TaskBean getTaskById(int taskID) {
  		TaskBean task = new TaskBean();
          try {
              String query = "select * from task where taskID=?";
              PreparedStatement preparedStatement = conn.prepareStatement( query );
              preparedStatement.setInt(1, taskID);
			ResultSet resultSet = preparedStatement.executeQuery();
              while( resultSet.next() ) {
              	task.setTaskID( resultSet.getInt( "taskID" ) );
                  task.setProjectName( resultSet.getString( "projectName" ) );
                  task.setOwner( resultSet.getInt( "owner" ) );
                  task.setTitle( resultSet.getString( "title" ) );
                  task.setComment( resultSet.getString( "comment" ) );
                  System.out.println("task.getComment"+task.getComment());
              }
              resultSet.close();
              preparedStatement.close();
          } catch (SQLException e) {
              e.printStackTrace();
          }
          return task;
  	}
  	
  	public List<TaskBean> getTasksByProjectName(String searchKey, int offset,
            int noOfRecords){
  		
  		List<TaskBean> tasks = new ArrayList<TaskBean>();
  		
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery( "select * from task where projectName like '%"+searchKey+"%'limit "
                 + offset + ", " + noOfRecords );
            while( resultSet.next() ) {
                TaskBean task = new TaskBean();
                task.setTaskID( resultSet.getInt( "taskID" ) );
//                System.out.println(task.getTaskID());
                task.setProjectName( resultSet.getString( "projectName" ) );
//                System.out.println(task.getProjectName());
                task.setOwner( resultSet.getInt( "owner" ) );
                task.setTitle( resultSet.getString( "title" ) );
                task.setComment( resultSet.getString( "comment" ) );
                task.setDateInserted(resultSet.getString("DateInserted"));
                tasks.add(task);
            }
            resultSet.close();
            rs = statement.executeQuery("SELECT COUNT(*) FROM task where projectName like '%"+searchKey+"%'");
            if(rs.next()){
                this.noOfRecords = rs.getInt(1);
                System.out.println("this noOfRecords"+noOfRecords);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tasks	;
  	}
	
    public int getNoOfRecords() {
        return noOfRecords;
    }
	
}
