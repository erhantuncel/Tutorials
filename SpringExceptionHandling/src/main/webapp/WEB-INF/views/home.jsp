<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-9"
    pageEncoding="ISO-8859-9"%>
<html>
<head>
	<title>Home</title>
</head>
<body>

	<h3>Hello ${employee.name}!</h3>
	<h4>Your ID is ${employee.id}</h4>
</body>
</html>
