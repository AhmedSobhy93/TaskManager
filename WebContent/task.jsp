
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         import="com.beans.UserBean"
         
   %>

   
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link href="assets/css/bootstrap.min.css" rel="stylesheet">
 <%
				    if (request.getParameter("action").equals("edit")) {
				    	%>
						<title>Edit Task</title>				    	
				    	<% 
				    } else {
						%>
						<title>Add New Task</title>				    	
						<% 
				    }
				%>

</head>
<body>

	  
<div class="container-fluid">
        <div class="panel panel-success">
            <% if (session.getAttribute("currentSessionUser") != null){ %>
			 <% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%> 
        
             <div class="panel-heading" align="center">
                <h4><b><font color="black" style=""> <a href='TaskController?action=""' style="float:left">Add New Task</a>Welcome <font color="Blue" style=""><%= currentUser.getFirstName() + " " + currentUser.getLastName() %> </font>  To Task Manager </font> <a href='logout.jsp'  style="float:right">Log out</a></b></h4>
                      
                
            </div>
        
         
         
         
            <div class="panel-body"align="center">
                  
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
    
                    <div class="panel panel-success" style="max-width: 35%;" align="left">
                        
                        <div class="panel-heading form-group">
                            <b><font color="white">
                                Add Task</font> </b>
                        </div>
                    
                        <div class="panel-body" >
 <form action="TaskController?action=listTask" method="post">
        <fieldset>
     
	           
	                <label for="projectName">Project Name</label>
	                    <select name="projectName"  class="form-control">
	                    
				            <option value="TTS">TTS</option>
				            <option value="Support">Support</option>
				            <option value="IPCC">IPCC</option>
				        
				        </select>
	           
	                <label for="title">Title</label> <input type="text"
	                    name="title" value="<c:out value="${task.title}" />"
	                    placeholder="Task Title" class="form-control" />
	           
	                <label for="comment">Comment</label> 
	                    <textarea name="comment" placeholder="Please add Complete Info About your task,IP's,Passwords,Notes and any thing that will help others." class="form-control" rows="5">${task.comment}</textarea>
	            
	            
	             <% if (session.getAttribute("currentSessionUser") != null){ %>
		        
		           <input type="hidden" name="owner" value="<%= currentUser.getUsername() %>"/>
		         <% }else{
		        	 %>
		         
		         <%} %>
	            
	  				  		 	
	  		 	<input type="hidden" name="action1" value="<%= request.getParameter("action") %>"/>
	  			<input type="hidden" name="taskID" value="<%= request.getParameter("taskID") %>"/>
	  			<hr>
	  			<%
				    if (request.getParameter("action").equals("edit")) {
				    	%>
				    	<input type="submit" value="Edit Task" font-size:1.1em;" class="btn btn-small btn btn-success  btn-block"/>
				    	
				    	<% 
				    } else {
						%>
			             	   		<input type="submit" value="Add Task" font-size:1.1em;" class="btn btn-small btn btn-primary  btn-block"�>   	
						    
				    	<% 
				    }
				%>
	          
        </fieldset>
    </form>
                        </div>
                    </div>
                    
                </div>
                
            </div>
                  <% }else{
        	 %>
            <div class="panel-heading" align="center">
                <h4><b><font color="black" style="">Welcome <font color="Blue" style=""> </font>  To Task Manager </font> </b></h4>
                
            </div>
            
             <div class="panel-body"align="center">
                  
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
    
                    <div class="panel panel-success" style="max-width: 100%;" align="left">
                        
                        <div class="panel-heading form-group">
                            <h2><b><font color="white">
                                Please contact with Administrator to register or login if you've credentials  </font> </b></h2>
                                
	    					<a href="/TaskManagerIST" class="btn btn-primary a-btn-slide-text">
									       <span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
									        <span><strong>Login</strong></span>            
									    </a>
                       </div>
                    </div>
                 </div>
              </div>
                       
                       
         <%} %>
            <div class="panel-footer" align="center"><font style="color: #111">Copyright @2017  <a href="http://istnetworks.com/">IST-RJB Devs</a>, All Rights Reserved. </font></div>
        </div>
    </div>
    
   
</body>
</html>