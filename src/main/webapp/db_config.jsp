<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DB Config</title>
</head>
<%
String host = "";
String port = "";
String databaseName = ""; 
String userName = "";
String password = "";
if(request.getParameterMap().containsKey("host"))
{
	host = request.getParameter("host");
}
if(request.getParameterMap().containsKey("port"))
{
	port = request.getParameter("port");
}
if(request.getParameterMap().containsKey("database_name"))
{
	databaseName = request.getParameter("database_name");
}
if(request.getParameterMap().containsKey("user_name"))
{
	userName = request.getParameter("user_name");
}
if(request.getParameterMap().containsKey("password"))
{
	password = request.getParameter("password");
}
String error = null;
if(request.getParameterMap().containsKey("error"))
{
	error = request.getParameter("error");	
}
%>
<body>
<form action="/indexer/initialize_db" method ="POST">
<%
if(error!=null)
{
%>
<h3 style="color : red">Error : <%=error%></h3>
<%	
}
%>  
<table>
<tr><td><label>Host</label></td><td><input type="text" name="host" value="<%=host%>" /></td></tr>
<tr><td><label>Port</label></td><td><input type="text" name="port"  value="<%=port%>"/></td></tr>
<tr><td><label>Database Name</label></td><td><input type="text" name="database_name"  value="<%=databaseName%>"/></td></tr>
<tr><td><label>User Name</label></td><td><input type="text" name="user_name"  value="<%=userName%>"/></td></tr>
<tr><td><label>Password</label></td><td><input type="text" name="password"  value="<%=password%>"/></td></tr>
<tr><td></td><td><input type="submit" value="Save and Start"/></td></tr>
</table>
<br/>  
<br/>  
<br/>  
<br/>  
<br/>    
</form>  
</body>
</html>