<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Creation</title>
</head>
<body bgcolor="DarkSeaGreen">
	<button type="button"
		style="background-color: AliceBluestyle; height: 30px; width: 5%">
		<a href="/"><b>Home</b></a>
	</button>
	<button type="button"
		style="background-color: AliceBlue; height: 30px; width: 5%">
		<a href="/employee_management"><b>Back</b></a>
	</button>
	<form:form action="/create_employee" method="post"
		modelAttribute="employee">
		<div style="text-align: center">
			<br>
			<br>
			<h2>Enter Employee Details</h2>
			<br>
			<form:hidden path="id" value="${employee.id}" />
			<p>
				Name:
				<form:input path="name" value="${employee.name}" required="true" />
			</p>
			<p>
				Designation:
				<form:input path="designation" value="${employee.designation}"
					required="true" />
			</p>
			<p>
				salary:
				<form:input path="salary" type="number" value="${employee.salary}"
					required="true" />
			</p>
			<p>
				Mobile:
				<form:input path="mobile" type="tel" pattern="[7-9][0-9]{9}"
					value="${employee.mobile}" required="true" />
			</p>
			<p>
				Dob:
				<form:input path="dob" type="date" value="${employee.dob}"
					required="true" />
				<br><br>
			<br><br><h3>Enter Permanent Address Details</h3>
			<p>
				Door No:
				<form:input type="text" path="addresses[0].doorNumber"
					value="${employee.addresses[0].doorNumber}" required="true" />
				<br>
			<p>
				Street:
				<form:input type="text" path="addresses[0].street"
					value="${employee.addresses[0].street}" required="true" />
				<br>
			<p>
				District:
				<form:input type="text" path="addresses[0].district"
					value="${employee.addresses[0].district}" required="true" />
				<br>
			<p>
				State:
				<form:input type="text" path="addresses[0].state"
					value="${employee.addresses[0].state}" required="true" />
				<br>
			<p>
				Country:
				<form:input type="text" path="addresses[0].country"
					value="${employee.addresses[0].country}" required="true" />
				<br>
				<form:hidden path="addresses[0].addressType" value="Permanent" />
				<br>
			<br><br><h3>Enter Temporary Address Details</h3>
			<p>
				Door No:
				<form:input type="text" path="addresses[1].doorNumber"
					value="${employee.addresses[1].doorNumber}" />
				<br>
			<p>
				Street:
				<form:input type="text" path="addresses[1].street"
					value="${employee.addresses[1].street}" />
				<br>
			<p>
				District:
				<form:input type="text" path="addresses[1].district"
					value="${employee.addresses[1].district}" />
				<br>
			<p>
				State:
				<form:input type="text" path="addresses[1].state"
					value="${employee.addresses[1].state}" />
				<br>
			<p>
				Country:
				<form:input type="text" path="addresses[1].country"
					value="${employee.addresses[1].country}" />
				<br>
				<form:hidden path="addresses[1].addressType" value="Temporary" />
				<input type="submit" value="Submit">
		</div>
	</form:form>
</body>
</html>
