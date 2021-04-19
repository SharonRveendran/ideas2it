<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unassign project page</title>
</head>
<body>
  <form action="employee?action=unassign_project" method="post">
Enter Employee Id :
<br><input type="number" name="employeeId"/><br><br>
Enter Project Id :
<br><input type="number" name="projectId"/><br>
<br><input type="submit" value="submit"/>
</form>
</body>
</html>