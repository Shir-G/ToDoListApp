<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="ex" uri="WEB-INF/mytld.tld"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<title>ToDoList</title>
<style>
	.btn{
		width: 15%;
	}
	.welcome {
		font-size: 450%;
	}
</style>
</head>
<body>
	<div style="text-align:center; margin-top:10%;">
<%
		Cookie[] cookies = request.getCookies();
		Cookie userCookie = null;
		if (cookies != null) {
			for (Cookie cookie : cookies) if (cookie.getName().equals("userName")) userCookie = cookie; 
		}
		
		if (userCookie != null && userCookie.getValue() != null) {
%>
			<p class="welcome">Welcome Back <%= userCookie.getValue() %></p>
			<br><br>
			<form action="/ToDoListApp/Controller/login" method="post">
				<button class="btn btn-default">Enter App</button>
				<input type="hidden" name="userFromCookie" value="<%= userCookie.getValue() %>">
			</form>
<%
		} else {
%>		
			<ex:title message="Hello!"/>
			<a href="/ToDoListApp/login.jsp" class="btn btn-default">Login</a>
			<a href="/ToDoListApp/register.jsp" class="btn btn-default">Register</a>
<%
		}
%>		
	</div>
</body>
</html>