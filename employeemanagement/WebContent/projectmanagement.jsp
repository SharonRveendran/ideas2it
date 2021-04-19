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
<body>
  <table class="center" border="4" style="width:60%;background-color:LightGray" align="center">
    <h1 style="color:DarkSlateGray;font-size:40px">Welcome to Project Management</h1><br>
     <tr>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue"><a href="project_form.jsp">Create Project</a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue"><a href="display_project.jsp">Display Project</a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue"><a href="project?action=display_all_projects">Display All Project</a></button></td>
    </tr>
    <tr>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue"><a href="delete_project.jsp">Delete Project</a></button></td>
  	  <td style="text-align:center"><button type ="button" style="background-color:AliceBlue"><a href="restore_project.jsp">Restore Project</a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue"><a href="project?action=display_available_employees">Assign Employees</a></button></td>
    </tr>
    <tr>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue"><a href="unassign_employee.jsp">Unassign Employee</a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue"><a href="display_assigned_employees.jsp">Display Assigned Employees</a></button></td>
      <td style="text-align:center"><button type ="button" style="background-color:AliceBlue"><a href="update_project.jsp">Update Project</a></button></td>
    </tr>
  </table>
</body>
</html>
