<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Restore Project</title>
</head>
<body bgcolor="DarkSeaGreen">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="projectmanagement.jsp"><b>Back</b></a></button>
  <form action="project?action=restore_project" method="post">
    <center>
    <h2>Enter project Id :</h2>
    <input type = "number" name = "id">
    <input type = "submit" value = "Submit"/>
    </center>
  </form>
</body>
</html>