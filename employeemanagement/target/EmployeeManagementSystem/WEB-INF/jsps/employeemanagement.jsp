<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Management</title>
<style>
h1 {text-align: center;}
</style>
</head>
<body bgcolor="DarkSeaGreen">
  <button type ="button" style="background-color:AliceBluestyle ; height:30px; width:5%"><a href="EmployeeManagementSystem/welcome.jsp"><b>Home</b></a></button>
  <h1 style="color:DarkSlateGray;font-size:40px;">Welcome to Employee Management</h1><br>
  <table class="center" border="4" style="width:60%;background-color:#609f60" align="center">
    <tr style="height:50px">
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue ; height:35px; width:80%"><a href="employee_form"><b>Create Employee</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="employee?action=display_all_employee"><b>Display Employees</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="employee?action=display_available_project"><b>Assign Projects</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="unassign_project.jsp"><b>Unassign Project</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="restore_employee.jsp"><b>Restore Employee</b></a></button></td>
    </tr>
 </table>
</body>
</html>