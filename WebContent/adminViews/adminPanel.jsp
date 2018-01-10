<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.beans.AdminBean"
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
         <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Admin Panel</title>
		<link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="assets/images/solution-grey.png">
         
	</head>

	<body>
	
	
	
<div class="container-fluid">
        <div class="panel panel-success">
        
         <% if (session.getAttribute("admin") != null){ %>
			 <% AdminBean admin = ((AdminBean) (session.getAttribute("admin")));%> 
        
             <div class="panel-heading" align="center">
                <h4><b><font color="black" style="">Welcome <font color="Blue" style=""><%= admin.getUsername() %> </font>  To Task Manager </font>
                              	  				
                <a href="Admin?action=logout" style="float:right" class="btn btn-danger a-btn-slide-text">
											       <span class="glyphicon glyphicon-log-out" aria-hidden="true"></span>
											        <span><strong>Logout</strong></span>            
						</a>
                 </b></h4>
                
            </div>
         <% }else{ %>
            <div class="panel-heading" align="center">
                <h4><b><font color="black" style="">Welcome <font color="Blue" style=""> </font>  To Task Manager </font> </b></h4>
                
            </div>
         <%} %>
            <div class="panel-body"align="center">
                  
                <div class="container " style="">
    
                    <div class="panel panel-success" style="max-width: 60%;" align="left">
                        
                        <div class="panel-heading form-group">
                            <b><font color="gray">
                                Projects Control Panel</font> </b>
                        </div>
                    
                        <div class="panel-body" >
						 	<a href="Project?action=addProject" style="float:center" class="btn btn-primary a-btn-slide-text">
																	       <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
																	        <span><strong>Add New Project</strong></span>            
							</a>
							
                         <table border="1" cellpadding="5" cellspacing="5" class="table table-striped">
			        <tr>
			            <th>Project Name</th>
			            <th></th>
			            <th></th>
			        </tr>
			
			       			 <c:forEach items="${projects}" var="project">
								 <tr>
								 	<td>${project.projectName}</td>
								 	

								       <td>
											 	<a href="Project?action=edit&projectId=${project.projectId}" class="btn btn-warning a-btn-slide-text">
											        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
											        <span><strong>Edit</strong></span>            
											    </a>
										    </td>
										 	<td>
											 	<a href="Project?action=delete&projectId=${project.projectId}" class="btn btn-danger a-btn-slide-text">
											       <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
											        <span><strong>Delete</strong></span>            
											    </a>
										    </td>
								    

								  </tr>
							 </c:forEach>
							 
							 </table>

                        </div>
                    </div>
            </div>        
            <div class="container " ">
                    
                    <div class="panel panel-success" style="max-width: 60%;" align="left">
                        
                        <div class="panel-heading form-group">
                            <b><font color="gra">
                                Users Control Panel</font> </b>
                        </div>
                    
                        <div class="panel-body" >
						 	<a href="User?action=addUser" style="float:center" class="btn btn-primary a-btn-slide-text">
																	       <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
																	        <span><strong>Add User</strong></span>            
							</a>
                        
 				<table border="1" cellpadding="5" cellspacing="5" class="table table-striped">
			        <tr>
			            <th>First Name</th>
			            <th>Last Name</th>
			            <th>User Name</th>
			            <th>password</th>
			            <th></th>
			            <th></th>
			        </tr>
			
			       			 <c:forEach items="${users}" var="user">
								 <tr>
								 	<td>${user.firstName}</td>
								 	<td>${user.lastName}</td>
								 	<td>${user.username}</td>
								 	<td>${user.password}</td>

								       <td>
											 	<a href="User?action=edit&userId=${user.userId}" class="btn btn-warning a-btn-slide-text">
											        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
											        <span><strong>Edit</strong></span>            
											    </a>
										    </td>
										 	<td>
											 	<a href="User?action=delete&userId=${user.userId}" class="btn btn-danger a-btn-slide-text">
											       <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
											        <span><strong>Delete</strong></span>            
											    </a>
										    </td>
								    

								  </tr>
							 </c:forEach>
							 
							 </table>
                        </div>
                    </div>
                </div>
                
            </div>
            <div class="panel-footer" align="center"><font style="color: #111">Copyright @2017  <a href="http://istnetworks.com/">IST-RJB Devs</a>, All Rights Reserved. </font></div>
        </div>
    </div>
    
    
	</body>
</html>
