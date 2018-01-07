<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="com.beans.UserBean"
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
         <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
		<title>Login Page</title>
		<link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link rel="shortcut icon" href="assets/images/solution-grey.png">
         
	</head>

	<body>
	
	
	
<div class="container-fluid">
        <div class="panel panel-success">
        
         <% if (session.getAttribute("currentSessionUser") != null){ %>
			 <% UserBean currentUser = ((UserBean) (session.getAttribute("currentSessionUser")));%> 
        
             <div class="panel-heading" align="center">
                <h4><b><font color="black" style="">Welcome <font color="Blue" style=""><%= currentUser.getFirstName() + " " + currentUser.getLastName() %> </font>  To Task Manager </font> </b></h4>
                
            </div>
         <% }else{ %>
            <div class="panel-heading" align="center">
                <h4><b><font color="black" style="">Welcome <font color="Blue" style=""> </font>  To Task Manager </font> </b></h4>
                
            </div>
         <%} %>
            <div class="panel-body"align="center">
                  
                <div class="container " style="margin-top: 10%; margin-bottom: 10%;">
    
                    <div class="panel panel-success" style="max-width: 35%;" align="left">
                        
                        <div class="panel-heading form-group">
                            <b><font color="white">
                                Login Form</font> </b>
                        </div>
                    
                        <div class="panel-body" >

                        <form action="login" >
                            <div class="form-group">
                                <label for="exampleInputEmail1">User Name</label> <input
                                    type="text" class="form-control" name="un" id="txtUserName"
                                    placeholder="Enter User Name" required="required">
                                    
                            </div>
                            <div class="form-group">
                                <label for="exampleInputPassword1">Password</label> <input
                                    type="password" class="form-control" name="pw" id="txtPass"
                                    placeholder="Password" required="required">
                            </div>
                            <div>
				  				<p></p>
				  			</div>
                            <button type="submit" style="width: 100%; font-size:1.1em;" class="btn btn-large btn btn-success btn-lg btn-block" ><b>Login</b></button>
                                                   
                        </form>

                        </div>
                    </div>
                    
                </div>
                
            </div>
            <div class="panel-footer" align="center"><font style="color: #111">Copyright @2017  <a href="http://istnetworks.com/">IST-RJB Devs</a>, All Rights Reserved. </font></div>
        </div>
    </div>
    
    
	</body>
</html>
