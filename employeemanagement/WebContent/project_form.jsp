<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Creation</title>
</head>
<body>
  <form action="project?action=create_or_update_project" method="post">
    Enter Project Details<br><br>
    <input type = "hidden" value="${projectDetails.get(0)}" name = "id" /><br>
    <label>Name         :<br></label><input type = "text"  name = "name" value="${projectDetails.get(1)}" required = "" /><br>
    <label>Manager Name :<br></label><input type = "text"  name = "managerName" value="${projectDetails.get(2)}"  required = "" /><br>
    <label>Start Dater  :<br></label><input type = "date" name = "startDate" value="${projectDetails.get(3)}"  required = "" /><br>
    <label>End Date     :<br></label><input type = "date" name = "endDate" value="${projectDetails.get(4)}"  required = "" /><br>
    <input type = "submit" value = "Submit"/>
  </form>
</body>
</html>