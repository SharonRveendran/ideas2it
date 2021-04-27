<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Restore Page</title>
</head>
<body bgcolor="DarkSeaGreen">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="employeemanagement.jsp"><b>Back</b></a></button>
  <form action="employee?action=restore_employee" method="post">
    <center>
    <h2>Enter employee Id :</h2>
    <input type = "number" name = "id">
    <input type = "submit" value = "Submit"/>
  </form>
  </center>
</body>
</html>