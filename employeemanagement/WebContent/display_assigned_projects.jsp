<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Assigned Projects</title>
</head>
<body>
  <form action="employee?action=display_assigned_projects" method="post">
    Enter Employee Id :
    <input type = "number" name = "employeeId">
    <input type = "submit" value = "Display Assigned Projects"/>
  </form><br>
  <table border="1">
    <thead>
      <tr>
        <th> Project Id</th>
        <th> Project Name</th>
      </tr>
    </thead>
    <tbody>
      <c:forEach items="${projectsDetails}" var = "projectDetails">
      <tr> 
        <td>${projectDetails.get("id")}</td>
        <td>${projectDetails.get("name")}</td>
      </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>