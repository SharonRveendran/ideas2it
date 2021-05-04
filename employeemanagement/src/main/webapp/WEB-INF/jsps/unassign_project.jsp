<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unassign project page</title>
</head>
<body bgcolor="DarkSeaGreen">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="/"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="/display_all_employee"><b>Back</b></a></button>
  <form action="/unassign_project/${employeeId}" method="post">
      <div style="text-align:center"><h2>Select  projects</h2></div>
  <c:forEach items="${projects}" var = "project">
      <p style="margin-left:47%"><input type="checkbox" name="projects" value="${project.id}"/>${project.id}:${project.name}</p>
  </c:forEach>
  <div style="text-align:center"><br><br><input type="submit" value="submit"/></div>
</form>
</body>
</html>
