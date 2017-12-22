<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DB Config</title>
<style>
table {
    border-collapse: collapse;
}

table, td, th {
    border: 1px solid black;
}
</style>
</head>
<%
JSONObject obj = null;
if(request.getAttribute("schema_details")!=null)
{
	obj = new JSONObject(request.getAttribute("schema_details").toString());	
	System.out.println(obj.toString());
}

String error = null;
if(request.getAttribute("error")!=null)
{
	error = request.getAttribute("error").toString();	
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
<table style="border: 1px solid black;">
<tr><td><b>Table Name</b></td><td><b>Column Name</b></td><td><b>Data Type</b></td><td><b>Index Name</b></td><td> <b>Add/Remove</b></td></tr>
<%
if(obj!=null)
{
JSONArray array = obj.getJSONArray("data");
if(array.length()>0)
{
	for(int i=0;i<array.length();i++)
	{
		JSONObject o = array.getJSONObject(i);
		%>		
		<tr><td><%=o.get("tableName")%></td><td><%=o.get("columnName") %></td><td><%=o.get("dataType") %></td><td><%=o.get("indexName") %></td><td><input type="checkbox"></td></tr>
		<%
	}
}	
}	
%>
</table>
<br/>  
<br/>  
<br/>  
<br/>  
<br/>    
</form>  
</body>
</html>