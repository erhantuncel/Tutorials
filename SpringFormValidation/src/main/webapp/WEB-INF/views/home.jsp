<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h3>
	<a href="<c:url value="/cust/save"/>">Customer Save</a><br>
	<a href="<c:url value="/emp/save"/>">Employee Save</a><br>  
</h3>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
