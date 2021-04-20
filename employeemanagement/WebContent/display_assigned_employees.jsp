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
<body bgcolor="#afcfaf">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="project?action=display_all_projects"><b>Back</b></a></button>
  <form action="project?action=display_assigned_employees"method="post">
   <center>
   <h2>Enter Project Id :</h2> 
    <input type = "number" name = "projectId">
    <input type = "submit" value = "Display Assigned Employees"/>
    </center>
  </form><br>
  <table align="center" border="3" style="width:40%;text-align:center">
    <thead style="background-color:#609f60">
      <tr>
        <th> Employee Id</th>
        <th> Employee Name</th>
      </tr>
    </thead>
    <tbody style="background-color:Lavender">
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