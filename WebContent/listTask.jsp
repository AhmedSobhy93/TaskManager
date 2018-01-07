<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.beans.UserBean"
    import="com.beans.TaskBean"
    import="java.util.*"
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tasks List</title>
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link href="assets/css/css.css" rel="stylesheet">
<link rel="shortcut icon" href="assets/images/solution-grey.png">
</head>
<body>

          
	
<div class="container-fluid">
        <div class="panel panel-success">
            <% if (session.getAttribute("currentSessionUser") != null){ %>
			 <% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%> 
        
             <div class="panel-heading" align="center">
                <h4><b><font color="black" style=""> <a href="TaskController?action='add'" style="float:left">Add New Task</a>Welcome <font color="Blue" style=""><%= currentUser.getFirstName() + " " + currentUser.getLastName() %> </font>  To Task Manager </font> <a href='logout.jsp'  style="float:right">Log out</a></b></h4>
                      
                
            </div>
         
            <div class="panel-body"align="center">
                  
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
    
                    <div class="panel panel-success" style="max-width: 100%;" align="left">
                        <div class="panel-heading form-group">
                            <h2><b><font color="white">
                                All Available Tasks</font> </b></h2>
                                <br>
	    					<form action="TaskController" method="get">
	    					<!--  
	    						<div class="col-md-4" >
			                	<input type="search" name="searchKey" placeholder="Enter Project Name"  class="form-control"/>
			                	
			                	</div>
			                	
								<div class="col-md-4" >
								  <button type="submit"  class="btn btn-primary a-btn-slide-text"><span class="glyphicon glyphicon-search" aria-hidden="true"></span>
										<span><strong>Search</strong></span></button>
								  
								</div>
								-->
							<input type="hidden" value="search" name="action"/>
								
							 
							  <div class="input-group">
							   <div class="input-group-addon">
								<span class="glyphicon glyphicon-search"></span> 
							   </div>
			                	<input type="search" name="searchKey" placeholder="Enter Project Name"  class="form-control"/>
							  </div>
							 
 
								
			                </form>
                       </div>
                        
	               <br>
         <div class="panel-body" >

			    <table border="1" cellpadding="5" cellspacing="5" class="table table-striped">
			        <tr>
			            <th>Project Name</th>
			            <th>Title</th>
			            <th>Owner</th>
			            <th>Comment</th>
			            <th>Inserted Date</th>
			            <th></th>
			            <th></th>
			        </tr>
			
			       			 <c:forEach items="${tasks}" var="task">
								 <tr>
								 	<td>${task.projectName}</td>
								 	<td>${task.title}</td>
								 	<td>${task.owner}</td>
								 	<td>${task.comment}</td>
								 	<td>${task.dateInserted}</td>

								<c:choose>
								    <c:when test="${task.owner.equals(sessionScope.currentSessionUser.firstName.concat(' ').concat(sessionScope.currentSessionUser.lastName))}">
								       <td>
											 	<a href="TaskController?action=edit&taskID=${task.taskID}" class="btn btn-warning a-btn-slide-text">
											        <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
											        <span><strong>Edit</strong></span>            
											    </a>
										    </td>
										 	<td>
											 	<a href="TaskController?action=delete&taskID=${task.taskID}" class="btn btn-danger a-btn-slide-text">
											       <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
											        <span><strong>Delete</strong></span>            
											    </a>
										    </td>
								    </c:when>
								    
								    <c:otherwise>
								        <td></td>
								        <td></td>
								    </c:otherwise>
								</c:choose>



								 	
								    
								  </tr>
							 </c:forEach>
			    </table>
			    
			    
			    <% 
			    if(request.getParameter("action").equals("listTask")){
			    	
			    	%>
			<ul class="pagination">
			
			    <%--For displaying Previous link except for the 1st page --%>
			    <c:if test="${currentPage != 1}">
			        <li><a href="TaskController?action=listTask&page=${currentPage - 1}">Previous</a></li>
			    </c:if>
			
			    <%--For displaying Page numbers.
			    The when condition does not display a link for the current page--%>
			        
			            <c:forEach begin="1" end="${noOfPages}" var="i">
			                <c:choose>
			                    <c:when test="${currentPage eq i}">
			                       <li><a> ${i}</a></li>
			                    </c:when>
			                    <c:otherwise>
			                        <li><a href="TaskController?action=listTask&page=${i}">${i}</a></li>
			                    </c:otherwise>
			                </c:choose>
			            </c:forEach>
			       
			
			    <%--For displaying Next link --%>
			    <c:if test="${currentPage lt noOfPages}">
			        <li><a href="TaskController?action=listTask&page=${currentPage + 1}">Next</a></li>
			    </c:if>
</ul>
<% }else{%>
	<ul class="pagination">
			
			    <%--For displaying Previous link except for the 1st page --%>
			    <c:if test="${currentPage != 1}">
			        <li><a href="TaskController?searchKey=<%=request.getParameter("searchKey") %>&action=search&page=${currentPage - 1}">Previous</a></li>
			    </c:if>
			
			    <%--For displaying Page numbers.
			    The when condition does not display a link for the current page--%>
			        
			            <c:forEach begin="1" end="${noOfPages}" var="i">
			                <c:choose>
			                    <c:when test="${currentPage eq i}">
			                       <li><a> ${i}</a></li>
			                    </c:when>
			                    <c:otherwise>
			                        <li><a href="TaskController?searchKey=<%=request.getParameter("searchKey") %>&action=search&page=${i}">${i}</a></li>
			                    </c:otherwise>
			                </c:choose>
			            </c:forEach>
			       
			
			    <%--For displaying Next link --%>
			    <c:if test="${currentPage lt noOfPages}">
			        <li><a href="TaskController?searchKey=<%=request.getParameter("searchKey") %>&action=search&page=${currentPage + 1}">Next</a></li>
			    </c:if>
</ul>
<%} %>
<%
System.out.println(request.getAttribute("tasks"));
System.out.println("noOfPages"+request.getAttribute("noOfPages"));
System.out.println("currentPage"+request.getAttribute("currentPage"));
System.out.println("action"+request.getParameter("action"));

%>
                       
    
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
                                Pleace contact with Administrator to register or login if you've credentials  </font> </b></h2>
                                
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