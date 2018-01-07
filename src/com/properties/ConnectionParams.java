package com.properties;

import java.io.FileInputStream;
import java.util.Properties;

public class ConnectionParams {
	private static Properties myresources=null;
	
	
	public static Properties getProperties(){
		
		myresources = new Properties();

		try {
		    FileInputStream in = new FileInputStream(
		            "C:\\IST\\props.properties");//path of the properties file

		        try {

		            myresources.load(in);

		            } catch (Exception e) {

		                e.printStackTrace();

		                }
		            } 

		        catch (Exception e) {

		            e.printStackTrace();

		        }
	        
		        return myresources;
	}
	
}
