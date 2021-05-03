<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Assigned Projects</title>
</head>
<body bgcolor="#afcfaf">
	<button type="button"
		style="background-color: AliceBluestyle; height: 30px; width: 5%">
		<a href="/"><b>Home</b></a>
	</button>
	<button type="button"
		style="background-color: AliceBlue; height: 30px; width: 5%">
		<a href="/display_all_employee"><b>Back</b></a>
	</button>
	<div style="text-align: center">
		<h2>List of assigned projects</h2><br>
		<br>
	</div>
	<table align="center" border="3" style="width: 35%; text-align: center">
		<thead style="background-color: #609f60">
			<tr>
				<th>Project Id</th>
				<th>Project Name</th>
			</tr>
		</thead>
		<tbody style="background-color: Lavender">
			<c:forEach items="${projects}" var="project">
				<tr>
					<td>${project.id}</td>
					<td>${project.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>