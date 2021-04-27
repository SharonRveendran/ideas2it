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
<body bgcolor="DarkSeaGreen">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="employeemanagement.jsp"><b>Back</b></a></button>
  <form action="employee?action=assign_project" method="post">
  <center><h2>Enter Employee Id :</h2>
  <br><input type = "number" name="id"/><br><br>
   <h2>Select projects</h2></center>
  <c:forEach items="${projectsDetails}" var = "projectDetails">
      <p style="margin-left:45%"><input type="checkbox" name="xyz" value="${projectDetails.get(0)}"/>${projectDetails.get(1)}</p>
  </c:forEach>
  <center><br><br><input type="submit" value="submit"/><center>
  </form>
</body>
</html>