<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unassign employee Page</title>
</head>
<body>
<form action="project?action=unassign_employee" method="post">
Enter Project Id :
<br><input type="number" name="projectId"/><br><br>
Enter Employee Id :
<br><input type="number" name="employeeId"/><br>
<br><input type="submit" value="submit"/>
</form>
</body>
</html>