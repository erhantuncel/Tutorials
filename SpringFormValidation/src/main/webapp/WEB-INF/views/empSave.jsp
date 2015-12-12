<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Save Page</title>
<style>
.error {
	color: #ff0000;
	font-style: italic;
	font-weight: bold;
}
</style>
</head>
<body>

	<springForm:form method="POST" commandName="employee" action="save.do">
		<table>
			<tr>
				<td>Employee ID:</td>
				<td><springForm:input path="id" /></td>
				<td><springForm:errors path="id" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Employee Name:</td>
				<td><springForm:input path="name" /></td>
				<td><springForm:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td>Employee Role:</td>
				<td>
					<springForm:select path="role">
						<springForm:option value="" lable="Select Role"/>
						<springForm:option value="ceo" lable="CEO"/>
						<springForm:option value="developer" lable="Developer"/>
						<springForm:option value="manager" lable="Manager"/>
					</springForm:select>
				</td>
				<td><springForm:errors path="role" cssClass="error" /></td>
			</tr>
			<tr>
				<td colspan="3"><input type="submit" value="Save Employee"></td>
			</tr>
		</table>
	</springForm:form>
	
</body>
</html>