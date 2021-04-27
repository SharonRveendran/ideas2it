<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#afcfaf">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="projectmanagement.jsp"><b>Back</b></a></button>
  <center>
  <form action="project?action=display_project" method="post">
    <h2>Enter project Id :</h2>
    <input type = "number" name = "id">
    <input type = "submit" value = "Search Project"/>
  </form><br><br>
  <table align="center" border="3" style="width:50%;text-align:center">
    <thead style="background-color:#609f60">
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Manager Name</th>
        <th>Start Date</th>
        <th>End Date</th>
      </tr>
    </thead>
    <tbody style="background-color:Lavender">
      <c:forEach items="${projectsDetails}" var = "projectDetails">
      <tr> 
        <td>${projectDetails.get("id")}</td>
        <td>${projectDetails.get("name")}</td>
        <td>${projectDetails.get("managerName")}</td>
        <td>${projectDetails.get("startDate")}</td>
        <td>${projectDetails.get("endDate")}</td>
      </tr>
      </tr>
      </c:forEach>
      </tbody>
     </table><br>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue;height:30px; width:15% "><a href="delete_project.jsp"><b>Delete Project</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue ;height:30px; width:15% "><a href="update_project.jsp"><b>Update Project</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue ;height:30px; width:15% "><a href="display_assigned_employees.jsp"><b>Display Assigned Employees</b></a></button></td>
      </center>
</body>
</html>