<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Employee</title>
<style>
h3 {
	text-align: center;
}
</style>
</head>
<body bgcolor="#afcfaf">
	<button type="button"
		style="background-color: AliceBluestyle; height: 30px; width: 5%">
		<a href="/"><b>Home</b></a>
	</button>
	<button type="button"
		style="background-color: AliceBlue; height: 30px; width: 5%">
		<a href="/employee_management"><b>Back</b></a>
	</button>
	<div style="text-align: center">
		<h2>${message}</h2>
		<form:form action="/display_employee" method="post">
			<h3>Enter employee Id :</h3>
			<input type="number" name="id" />
			<input type="submit" value="Search Employee" />
		</form:form>
		<br>
		<br>
	</div>
	<table align="center" border="3" style="width: 90%; text-align: center ; height: 60px">
		<thead style="background-color: #609f60">
			<tr style="height: 50px">
				<th>Id</th>
				<th style="width:7%">Name</th>
				<th>Date of Birth</th>
				<th>Mobile Number</th>
				<th style="width:10%">Designation</th>
				<th style="width:6%">Salary</th>
				<th style="width:12%">Permanent Address</th>
				<th style="width:12%">Temporary Address</th>
				<th style="width:12%">Assign/Unassign</th>
				<th>Delete</th>
				<th>Update</th>
				<th style="width:12%">Show Projects</th>
			</tr>
		</thead>
		<tbody style="background-color: Lavender">
			<c:forEach items="${employees}" var="employee">
				<tr style="height: 60px">
					<td>${employee.id}</td>
					<td>${employee.name}</td>
					<td>${employee.dob}</td>
					<td>${employee.mobile}</td>
					<td>${employee.designation}</td>
					<td>${employee.salary}</td>
					<td>${employee.addresses[0]}</td>
					<td>${employee.addresses[1]}</td>
					<td>
						<button style="background-color: yellowgreen ; height: 30px ; font-weight:bold"
							onclick="document.location.href='/display_available_projects/${employee.getId()}'">Assign</button>
						/
						<button style="background-color: yellowgreen ; height: 30px ; font-weight:bold"
							onclick="document.location.href='/get_assigned_projects/${employee.getId()}'">UnAssign</button>
					</td>
					<td><button style="background-color: red; color: white ; height: 30px ; font-weight:bold"
							onclick="document.location.href='/delete_employee/${employee.getId()}'">Delete</button></td>
					<td><button style="background-color: yellow ; height: 30px ; font-weight:bold"
							onclick="document.location.href='/update_employee/${employee.getId()}'">Update</button></td>
					<td ><button style="background-color: yellowgreen ; height: 30px ; font-weight:bold"
							onclick="document.location.href='/display_assigned_projects/${employee.getId()}'">Assigned
							Projects</button></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>