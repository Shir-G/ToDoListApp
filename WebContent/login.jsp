<%@ page isErrorPage="false" errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="ex" uri="WEB-INF/mytld.tld"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<title>ToDoList - SignIn</title>
</head>
<body>
	<div style="text-align:center;position:relative; width:30%; left:35%; margin-top:10%;">
		<ex:title message="Login"/><br>
		<a href="/ToDoListApp/homePage.jsp" class="btn btn-default" style="position:absolute; right:10px;top:10px">Back</a>
		<form action="/ToDoListApp/Controller/login" method="get">
			<div class="form-group row">
			  <label class="col-md-3 col-form-label">User Name</label>
			  <div class="col-md-8">
			    <input type="text" name="userName" class="form-control" required/>
			  </div>
			</div>
			<div class="form-group row">
			  <label class="col-md-3 col-form-label">Password</label>
			  <div class="col-md-8">
			    <input type="text" name="password" class="form-control"required/>
			  </div>
			</div>
			<br>
			<input type="submit" value="Login" class="btn btn-default"/>
		</form>
	</div>
</body>
</html>