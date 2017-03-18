<%@page import="java.util.ArrayList"%>
<%@ page isErrorPage="false" errorPage="error.jsp" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="il.ac.shenkar.todolist.model.*,java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<link rel="stylesheet" href="/ToDoListApp/style/style.css" type="text/css">
    
    <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> 
    <script type="text/javascript" src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"></script>
	
	<script type="text/javascript" src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.2.1/css/bootstrap-combined.min.css">
    <link rel="stylesheet" type="text/css" href="http://vitalets.github.com/x-editable/assets/x-editable/bootstrap-editable/css/bootstrap-editable.css">
  	<script type="text/javascript" src="http://vitalets.github.com/x-editable/assets/x-editable/bootstrap-editable/js/bootstrap-editable.js"></script>
	<script type="text/javascript" src="http://vitalets.github.com/x-editable/assets/mockjax/jquery.mockjax.js"></script>
	
     <style type="text/css">
     a{
     color: black;
     font-size: 120%;
     text-decoration: none;
     }
     </style>
     
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6">
				<div class="todolist not-done" style="position:relative">
					<h1>TodoListApp</h1>
<%
					int userId = -1;
					if (request.getSession().getAttribute("userId") != null) userId = (int)request.getSession().getAttribute("userId");
					List<Item> items = null;
					if (request.getAttribute("items") != null) {
						items = (List<Item>)request.getAttribute("items");
%>
						<form class="checkboxForm" action="/ToDoListApp/Controller/logout" method="get">
							<button style="position:absolute;right:10px;top:10px" class="btn btn-default">Logout</button>
							<input type="hidden" name="userId" value="<%= userId %>">
						</form>
						<form action="/ToDoListApp/Controller/addItem" method="get">
							<input type="text" name="itemName" class="form-control add-todo"/>
							<input type="hidden" name="userId" value="<%= userId %>"/>
						</form>
						<form class="checkboxForm" action="/ToDoListApp/Controller/deleteAll" method="get">
							<button id="checkAll" class="btn btn-success">Mark all as done</button>
							<input type="hidden" name="userId" value="<%= userId %>">
						</form>
						<hr>
						
						<form class="checkboxForm" action="/ToDoListApp/Controller/deleteItem" method="get">
						<ul id="sortable" class="list-unstyled ui-sortable">
<%
						for (Item item: items) {
%>
						<li class="ui-state-default">
						    <div class="checkbox">
						       	<label>
						           	<input class="inp2" type="checkbox" name="itemId" value="<%= item.getId() %>">
						           	<!-- <a href="#" id="<%="id"+item.getId() %>" data-text="text"><%= item.getDescription() %></a>-->
						           	<a href="#" class="username1" data-showbuttons="true" data-mode="popup" data-placement="right" data-name="<%= item.getId() %>"><%= item.getDescription() %></a>
						        </label>
						    </div>
						    
						</li>
<%
						}
%>
						</ul>
						</form>
<%
					}
%>	                
	                <div class="todo-footer">
	                    <strong><span class="count-todos">3</span></strong> Items Left
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<script type="text/javascript" src="/ToDoListApp/scripts/script.js"></script>
	
	<script>
	// setting defaults for the editable
	$.fn.editable.defaults.mode = 'inline';
	$.fn.editable.defaults.showbuttons = false;
	$.fn.editable.defaults.url = '/ToDoListApp/Controller/*';
	$.fn.editable.defaults.type = 'text';

	// make username1 editable
	$('.username1').editable({
	    type: 'text',
	    pk: 1,
	    url: '/ToDoListApp/Controller/updateItem',
	    title: 'Update Item Description'
	});
	</script>
</body>
</html>