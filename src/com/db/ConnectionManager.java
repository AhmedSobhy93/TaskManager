package com.db;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.properties.ConnectionParams;


public class ConnectionManager {

   static Connection con;
   static String url;
         
   public static void getFiles(){
		
		File file = new File(".");
		for(String fileNames : file.list()) System.out.println(fileNames);
		int i=90;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("src/com/ist/file.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    System.out.println(line);
		    i=1;
		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		        System.out.println(line);
		        i++;
		    }
		    String everything = sb.toString();
		    System.out.println(everything);
		    System.out.println(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
   }
   
   public static Connection getConnection()
   {
//		String fullPath = context.getRealPath("/assets/servers.txt");
//
	   Properties props=ConnectionParams.getProperties();
	   
      try
      {
//         String url =  "jdbc:mysql://localhost/taskdb"; 
    	  String url=props.getProperty("connectionurl");
    	  String userName=props.getProperty("username");
    	  String password=props.getProperty("password");
    	  
//         Class.forName("com.mysql.jdbc.Driver");
    	  Class.forName(props.getProperty("driver", "com.mysql.jdbc.Driver"));
         try
         {            	
//            con = DriverManager.getConnection(url,"root","123"); 
        	 con = DriverManager.getConnection(url,userName,password);							
              
         }
         
         catch (SQLException ex)
         {
            ex.printStackTrace();
         }
      }

      catch(ClassNotFoundException e)
      {
         System.out.println(e);
      }

   return con;
}
}
