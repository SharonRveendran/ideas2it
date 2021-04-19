<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Project</title>
</head>
<body>
  <form action="project?action=update_project" method="post">
    Enter project Id :
    <input type = "number" name = "id">
    <input type = "submit" value = "Update Project"/>
  </form><br><br>
</body>
</html>
