
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256" import="com.beans.AdminBean"%>


<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="assets/css/bootstrap.min.css" rel="stylesheet">
<link rel="shortcut icon" href="assets/images/solution-grey.png">
<%
	if (request.getParameter("action").equals("edit")) {
%>
<title>Edit Project</title>
<%
	} else {
%>
<title>Add New Project</title>
<%
	}
%>

</head>
<body>


	<div class="container-fluid">
		<div class="panel panel-success">
			<%
				if (session.getAttribute("admin") != null) {
			%>
			<%
				AdminBean admin = ((AdminBean) (session.getAttribute("admin")));
			%>

			<div class="panel-heading" align="center">
				<h4>
					<b><font color="black" style="">Welcome <font
							color="Blue" style=""><%=admin.getUsername()%> </font> To Task
							Manager
					</font> <a href="logout.jsp" style="float: right"
						class="btn btn-danger a-btn-slide-text"> <span
							class="glyphicon glyphicon-log-out" aria-hidden="true"></span> <span><strong>Logout</strong></span>
					</a></b>
				</h4>


			</div>




			<div class="panel-body" align="center">

				<div class="container " style="margin-top: 10%; margin-bottom: 10%;">

					<div class="panel panel-success" style="max-width: 35%;"
						align="left">

						<div class="panel-heading form-group">
							<b><font color="white"> Add Project</font> </b>
						</div>

						<div class="panel-body">
							<form action="Project" method="post">
								<fieldset>


									<label for="projectName">Project Name</label> <input
										type="text" name="projectName" placeholder="Project Name"
										class="form-control"
										value="<c:out value="${project.projectName}"/>" /> <input
										type="hidden" name="action1"
										value="<%=request.getParameter("action")%>" /> <input
										type="hidden" name="projectID"
										value="<%=request.getParameter("projectId")%>" />
									<hr>
									<%
										if (request.getParameter("action").equals("edit")) {
									%>
									<!--  <input type="submit" value="Edit Task" font-size:1.1em;" class="btn btn-small btn btn-warning  btn-block"/>-->
									<button type="submit"
										class="form-control btn btn-warning a-btn-slide-text">
										<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
										<span><strong>Edit Project</strong></span>
									</button>

									<%
										} else {
									%>
									<!--  <input type="submit" value="Add Task" font-size:1.1em;" class="btn btn-small btn btn-primary  btn-block"> -->
									<button type="submit"
										class="form-control btn btn-primary a-btn-slide-text">
										<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
										<span><strong>Add Project</strong></span>
									</button>

									<%
										}
									%>

								</fieldset>
							</form>
						</div>
					</div>

				</div>

			</div>
			<%
				} else {
			%>
			<div class="panel-heading" align="center">
				<h4>
					<b><font color="black" style="">Welcome <font
							color="Blue" style=""> </font> To Task Manager
					</font> </b>
				</h4>

			</div>

			<div class="panel-body" align="center">

				<div class="container " style="margin-top: 10%; margin-bottom: 10%;">

					<div class="panel panel-success" style="max-width: 100%;"
						align="left">

						<div class="panel-heading form-group">
							<h2>
								<b><font color="white"> Please login Again </font> </b>
							</h2>

							<a href="/TaskManager/Admin"
								class="btn btn-primary a-btn-slide-text"> <span
								class="glyphicon glyphicon-log-in" aria-hidden="true"></span> <span><strong>Login</strong></span>
							</a>
						</div>
					</div>
				</div>
			</div>


			<%
				}
			%>
			<div class="panel-footer" align="center">
				<font style="color: #111">Copyright @2017 <a
					href="http://istnetworks.com/">IST-RJB Devs</a>, All Rights
					Reserved.
				</font>
			</div>
		</div>
	</div>


</body>
</html>