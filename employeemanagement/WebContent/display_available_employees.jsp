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
  <form action="project?action=assign_employee" method="post">
  Enter project id :
  <input type = "number" name="id"/>
  <br><br></>Select Employees <br>
    <c:forEach items="${employeesDetails}" var = "employeeDetails">
      <tr> <br><input type="checkbox" name="employee_assignment" value="${employeeDetails.get(0)}"/>${employeeDetails.get(1)}
  </c:forEach>
   <br><br><input type="submit" value="submit"/>
  </form>
</body>
</html>
