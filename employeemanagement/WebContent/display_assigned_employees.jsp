<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Assigned Employees</title>
</head>
<body>
  <form action="project?action=display_assigned_employees"method="post">
    Enter Project Id :
    <input type = "number" name = "projectId">
    <input type = "submit" value = "Display Assigned Employees"/>
  </form><br>
  <table border="1">
    <thead>
      <tr>
        <th> Employee Id</th>
        <th> Employee Name</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${employeesDetails}" var = "employeeDetails">
      <tr> 
        <td>${employeeDetails.get("id")}</td>
        <td>${employeeDetails.get("name")}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>