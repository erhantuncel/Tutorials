<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Multiple File Request Page</title>
</head>
<body>
	<form method="POST" action="uploadMultipleFile" enctype="multipart/form-data">
		File 1 to upload : <input type="file" name="file"><br />
		Name 1 : <input type="text" name="name"><br /><br />
		File 2 to upload : <input type="file" name="file"><br />
		Name 2 : <input type="text" name="name"><br /><br />
		<input type="submit" value="Upload">Press here to upload the file!
	</form>
</body>
</html>