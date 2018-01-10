package com.dao;

import com.beans.*;
import java.text.*;
import java.util.*;
import java.sql.*;
import com.db.*;

import sun.util.resources.CurrencyNames;

public class UserDAO {
	static Connection currentCon = null;
	static ResultSet rs = null;

	public UserDAO() {
		currentCon = ConnectionManager.getConnection();
	}

	public static UserBean login(UserBean bean) {

		// preparing some objects for connection
		Statement stmt = null;

		String username = bean.getUsername();
		String password = bean.getPassword();

		String searchQuery = "select * from users where username='" + username + "' AND password='" + password + "'";

		// "System.out.println" prints in the console; Normally used to trace
		// the process
		System.out.println("Your user name is " + username);
		System.out.println("Your password is " + password);
		System.out.println("Query: " + searchQuery);

		try {
			// connect to DB
			currentCon = ConnectionManager.getConnection();
			System.out.println("connection done");
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			// if user does not exist set the isValid variable to false
			if (!more) {
				System.out.println("Sorry, you are not a registered user! Please sign up first");
				bean.setValid(false);
			}

			// if user exists set the isValid variable to true
			else if (more) {
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				int user_id = rs.getInt("user_id");				
				
				System.out.println("Welcome " + firstName);
				bean.setFirstName(firstName);
				bean.setLastName(lastName);
				bean.setUserId(user_id);
				System.out.println("user_id-login-"+user_id);
				bean.setUserName(username);
				bean.setPassword(password);
				
				bean.setValid(true);
			}
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! " + ex);
		}

		// some exception handling
		finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception e) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception e) {
				}
				stmt = null;
			}

			if (currentCon != null) {
				try {
					currentCon.close();
				} catch (Exception e) {
				}

				currentCon = null;
			}
		}

		return bean;

	}

	public void addUser(UserBean user) {

		try {
			String query = "insert into users (FirstName,LastName,username,password) values(?,?,?,?)";
			PreparedStatement preparedStatement = currentCon.prepareStatement(query);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());

			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void deleteUser(int id) {
		String query = "delete from users where user_id=?";
		try {
			PreparedStatement preparedStatement = currentCon.prepareStatement(query);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateUser(UserBean user) {
		String query = "update users set FirstName=?,LastName=?,username=?,password=? where user_id=?";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = currentCon.prepareStatement(query);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getUsername());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setInt(5, user.getUserId());

			preparedStatement.executeUpdate();
			preparedStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public UserBean getUserById(int id) {
		UserBean user = new UserBean();

		String query = "select * from users where user_id=?";
		try {
			PreparedStatement preparedStatement = currentCon.prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet result = preparedStatement.executeQuery();
			while (result.next()) {
				user.setFirstName(result.getString("FirstName"));
				user.setLastName(result.getString("LastName"));
				user.setUserName(result.getString("username"));
				user.setPassword(result.getString("password"));

			}

			result.close();
			preparedStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;

	}

	public List<UserBean> getAllUsers() {
		List<UserBean> users = new ArrayList<UserBean>();
		UserBean user = null;

		String query = "select * from users";
		try {
			PreparedStatement preparedStatement = currentCon.prepareStatement(query);
			ResultSet result = preparedStatement.executeQuery();

			while (result.next()) {
				user = new UserBean();
				user.setFirstName(result.getString("FirstName"));
				user.setLastName(result.getString("LastName"));
				user.setUserName(result.getString("username"));
				user.setPassword(result.getString("password"));
				user.setUserId(result.getInt("user_id"));
				users.add(user);
			}

			result.close();
			preparedStatement.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;

	}

}