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
  <form action="employee?action=assign_project" method="post">
  Enter Employee Id :
  <br><input type = "number" name="id"/><br><br>
  Select projects<br>
  <c:forEach items="${projectsDetails}" var = "projectDetails">
      <br><input type="checkbox" name="xyz" value="${projectDetails.get(0)}"/>${projectDetails.get(1)}
  </c:forEach>
  <br><br><input type="submit" value="submit"/>
  </form>
</body>
</html>