<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete page</title>
</head>
<body bgcolor="DarkSeaGreen">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="employee?action=display_all_employee"><b>Back</b></a></button>
  <form action="employee?action=delete_employee" method="post">
    <center><br>
    <h2>Enter employee Id :</h2>
    <input type = "number" name = "id">
    <input type = "submit" value = "Submit"/>
    </center>
  </form>
  <br><center><h2>${message}</h2></center>
</body>
</html>