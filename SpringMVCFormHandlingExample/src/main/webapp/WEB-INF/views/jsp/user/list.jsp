<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../fragments/header.jsp" />

	<div class="container">
	
		<c:if test="${not empty msg }">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">x</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>
		
			<h1>All Users</h1>

		<table class="table table-stripped table-hover table-condensed">
			<thead>
				<tr>
					<th>#ID</th>
					<th>Name</th>
					<th>Email</th>
					<th>Framework</th>
					<th>Action</th>
				</tr>
			</thead>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.id}</td>
					<td>${user.name}</td>
					<td>${user.email}</td>
					<td>
						<c:forEach items="${user.framework}" var="framework" varStatus="loop">
							${framework}
						</c:forEach>
					</td>
					<td>
						<spring:url value="/users/${user.id}" var="userUrl" />
						<spring:url value="/users/${user.id}/delete" var="deleteUrl" />
						<spring:url value="/users/${user.id}/update" var="updateUrl" />
						<button type="button" class="btn btn-info" onclick="location.href='${userUrl}'">Query</button>
						<button type="button" class="btn btn-primary" onclick="location.href='${updateUrl}'">Update</button>
						<button type="button" class="btn btn-danger" onclick="location.href='${deleteUrl}'">Delete</button>
					</td>
				</tr>				
			</c:forEach>
		</table>
	</div>
	
	<jsp:include page="../fragments/footer.jsp" />
</body>
</html>
