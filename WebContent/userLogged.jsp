<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="com.beans.UserBean"
         
   %>
 
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
   "http://www.w3.org/TR/html4/loose.dtd">

   <html>

      <head>
         <meta http-equiv="Content-Type" 
            content="text/html; charset=windows-1256">
         <title>   User Logged Successfully   </title>
         <link href="css/bootstrap.min.css" rel="stylesheet">
         
      </head>
	
      <body>

    
    
    
         <center>
            
           <% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%> 
   Welcome <%= currentUser.getFirstName() + " " + currentUser.getLastName() %> 
         </center>

<h3>You can add new task from here</h3>
<a href='TaskController?action=""'>Add Task</a>

<h3>You can list all tasks from here</h3>
<a href="TaskController?action=listTask">List Tasks</a>
      </body>
	
   </html>