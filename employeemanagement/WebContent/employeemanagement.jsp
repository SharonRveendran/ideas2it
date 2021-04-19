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
<body>
  <h1 style="color:DarkSlateGray;font-size:40px;">Welcome to Employee Management</h1><br>
  <table class="center" border="4" style="width:60%;background-color:LightGray" align="center">
    <tr style="height:50px">
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue ; height:35px; width:80%"><a href="employee_form.jsp"><b>Create Employee</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="display_employee.jsp"><b>Display Employee</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="employee?action=display_all_employee"><b>Display all Employee</b></a></button></td>
    </tr>
    <tr style="height:50px">
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="delete_employee.jsp"><b>Delete Employee</b></a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="restore_employee.jsp"><b>Restore Employee</b></a></button></td>
      <td style="text-align:center"> <button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="update_employee.jsp"><b>Update Employee</b></a></button></tr></td>
    </tr>
    <tr style="height:50px">
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="employee?action=display_available_project"><b>Assign Projects</b></a></button>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="unassign_project.jsp"><b>Unassign Project</b></a></button>
      <td style="text-align:center"> <button type ="button" style="background-color:AliceBlue; height:35px; width:80%"><a href="display_assigned_projects.jsp"><b>Display Assigned Projects</b></a></button>
    </tr>
 </table>
</body>
</html>