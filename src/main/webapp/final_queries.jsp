<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Indexer</title>
</head>
<body>
<%
if(request.getAttribute("queries")!=null)
{
ArrayList <String> quesries =  (ArrayList <String>)request.getAttribute("queries");	
for(String str : quesries)
{
%>
<%=str%>
<br>
<%	
}
}
%>

</body>
</html>