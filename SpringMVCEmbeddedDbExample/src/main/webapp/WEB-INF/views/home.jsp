<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=ISO-8859-9"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>Kullan�c� Listesi</h1>
 
 
<table>
	<tr>
		<td colspan=5 align="right">
			<form action="<c:url value="/searchByCountry"/>" method="POST">
				�lke : 
				<input type="text" name="country" />
				<input type="submit" value="Ara" />
			</form>
		
		</td>
	</tr>
	<tr>
		<td>No</td>
		<td>�S�M</td>
		<td>YA�</td>
		<td>�LKE</td>
		<td>�EH�R</td>
	</tr>
		<c:forEach items="${users }" var="user">
			<tr>
				<td>${user.id }</td>
				<td>${user.name }</td>
				<td>${user.age }</td>
				<td>${user.country }</td>
				<td>${user.city }</td>
			</tr>
		</c:forEach>
</table>
</body>
</html>
