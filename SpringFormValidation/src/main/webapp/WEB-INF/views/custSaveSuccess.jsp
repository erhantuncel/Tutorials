<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Saved Successfully</title>
</head>
<body>
	<h3>Customer Saved Successfully</h3>
	
	<strong>Customer Name : ${customer.name }</strong><br>
	<strong>Customer Email : ${customer.email }</strong><br>
	<strong>Customer Age : ${customer.age }</strong><br>
	<strong>Customer Gender : ${customer.gender }</strong><br>
	<strong>Customer Birthday : <fmt:formatDate value="${customer.birthday}" type="date" /></strong><br>
</body>
</html>