<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Restore Page</title>
</head>
<body bgcolor="DarkSeaGreen">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="/"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="/employee_management"><b>Back</b></a></button>
     <div style="text-align:center">
       <form action="/restore_employee">
         <h2>Enter employee Id :</h2>
         <input type = "number" name = "id" required="true"/>
         <input type = "submit" value = "Submit"/>
       </form>
     </div>
</body>
</html>