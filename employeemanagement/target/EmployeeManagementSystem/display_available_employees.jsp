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
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="projectmanagement.jsp"><b>Back</b></a></button>
  <form action="project?action=assign_employee" method="post">
  <center><h2>Enter project id :</h2>
  <input type = "number" name="id"/>
  <center><input type="submit" value="submit"/></center>
  <br><br></><h2>Select Employees </h2></center>
    <c:forEach items="${employeesDetails}" var = "employeeDetails">
      <p style="margin-left:45%"><input type="checkbox" name="employee_assignment" value="${employeeDetails.get(0)}"/>${employeeDetails.get(1)}</p>
  </c:forEach>
   <center><input type="submit" value="submit"/></center>
  </form>
</body>
</html>
