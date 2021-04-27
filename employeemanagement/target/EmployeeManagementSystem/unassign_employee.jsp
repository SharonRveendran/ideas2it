<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Unassign employee Page</title>
</head>
<body bgcolor="DarkSeaGreen">
    <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
    <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="projectmanagement.jsp"><b>Back</b></a></button>
<form action="project?action=unassign_employee" method="post">
<center>
  <h2>Enter Project Id :</h2>
  <input type="number" name="projectId"/><br><br>
  <h2>Enter Employee Id :</h2>
  <input type="number" name="employeeId"/><br>
  <br><input type="submit" value="submit"/>
</center>
</form>
</body>
</html>