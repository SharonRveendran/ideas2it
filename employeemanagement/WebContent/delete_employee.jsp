<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete page</title>
</head>
<body>
  <form action="employee?action=delete_employee" method="post">
    Enter employee Id :
    <input type = "number" name = "id">
    <input type = "submit" value = "Submit"/>
  </form>
</body>
</html>