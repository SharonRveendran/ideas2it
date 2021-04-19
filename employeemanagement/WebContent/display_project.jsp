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
<body>
  <form action="project?action=display_project" method="post">
    Enter project Id :
    <input type = "number" name = "id">
    <input type = "submit" value = "Display Project"/>
  </form><br><br>
  <table border="3">
    <thead>
      <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Manager Name</th>
        <th>Start Date</th>
        <th>End Date</th>
      </tr>
    </thead>
    <tbody>
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
  </table>
</body>
</html>