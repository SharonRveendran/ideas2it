<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Management</title>
<style>
h1 {text-align: center;}
</style>
</head>
<body bgcolor="DarkSeaGreen">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <h1 style="color:DarkSlateGray;font-size:40px">Welcome to Project Management</h1><br>
  <table class="center" border="4" style="width:60%;background-color:LightGray" align="center">
    <tr>
     
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue ; height:35px; width:80%"><a href="project_form.jsp"><b>Create Project</b></a></button></td>
     <td style="text-align:center"> <button type ="button" style="background-color:AliceBlue ; height:35px; width:80%"><a href="project?action=display_all_projects"><b>Display Projects</b></a></button></td>
  
 
  	  <td style="text-align:center"><button type ="button" style="background-color:AliceBlue ; height:35px; width:80%"><a href="restore_project.jsp"><b>Restore Project</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue ; height:35px; width:80%""><a href="project?action=display_available_employees"><b>Assign Employees</b></a></button></td>

  
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue ; height:35px; width:80%"><a href="unassign_employee.jsp"><b>Unassign Employee</b></a></button></td>
      
      
    </tr>
  </table>
</body>
</html>
