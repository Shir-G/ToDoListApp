<jsp:useBean id="todo" scope="session" class="il.ac.shenkar.todolist.model.ToDoListException"/>
<jsp:setProperty name="todo" property="errorMsg" param="errorMsg"/>
<%@ page isErrorPage="true" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="ex" uri="WEB-INF/mytld.tld"%>
<head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div style="text-align:center;position:relative; width:30%; left:35%; margin-top:10%;">
		<img src="/ToDoListApp/images/error.png"><br><br>
		<ex:title>Error!</ex:title>
<%
		if (exception != null) {
%>
			<p style="font-size:150%;">Message = <%= exception.getMessage() %></p>
<%
		}
%>
		<!--  Error Message = <%//=request.getAttribute("errorMsg") %> -->
			  Error Message = <%= session.getAttribute(todo) %>
				
		<br><br>
		<a href="/ToDoListApp/homePage.jsp" class="btn btn-default">Back to App</a>
	</div>
</body>
</html>