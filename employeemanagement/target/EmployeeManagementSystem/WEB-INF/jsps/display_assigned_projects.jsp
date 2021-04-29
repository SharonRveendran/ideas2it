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
<body bgcolor="#afcfaf">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="employee?action=display_all_employee"><b>Back</b></a></button>
  <form action="employee?action=display_assigned_projects" method="post">
    <center>
    <h2>Enter Employee Id :</h2>
    <input type = "number" name = "employeeId">
    <input type = "submit" value = "Display Assigned Projects"/>
    </center>
  </form><br>
  <table align="center" border="3" style="width:50%;text-align:center">
    <thead style="background-color:#609f60">
      <tr>
        <th> Project Id</th>
        <th> Project Name</th>
      </tr>
    </thead>
    <tbody style="background-color:Lavender">
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