<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Employee</title>
<style>
h3 {text-align: center;}
</style>
</head>
<body bgcolor="#afcfaf">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="employeemanagement.jsp"><b>Back</b></a></button>
  <form action="employee?action=display_employee" method="post">
    <h3>Enter employee Id :</h3>
    <center><input type = "number" name = "id"r">
    <input type = "submit" value = "Search Employee"/>
    </center>
  </form><br><br>
  <table align="center" border="3" style="width:80%;text-align:center">
    <thead style="background-color:#609f60">
      <tr style="height: 50px">
        <th>Id</th>
        <th>Name</th>
        <th>Date of Birth</th>
        <th>Mobile Number</th>
        <th>Designation</th>
        <th>Salary</th>
        <th>Permanent Address</th>
        <th>Temporary Address</th>
      </tr>
    </thead>
    <tbody style="background-color:Lavender">
      <c:forEach items="${employeesDetails}" var = "employeeDetails">
      <tr style="height: 40px"> 
        <td>${employeeDetails.get("id")}</td>
        <td>${employeeDetails.get("name")}</td>
        <td>${employeeDetails.get("dob")}</td>
        <td>${employeeDetails.get("mobile")}</td>
        <td>${employeeDetails.get("designation")}</td>
        <td>${employeeDetails.get("salary")}</td>
        <td>${employeeDetails.get("permanentAddress")}</td>
        <td>${employeeDetails.get("temporaryAddress")}</td>             
      </tr>
      </c:forEach>
    </tbody>
  </table>
  <br>
      <center>
      <button type ="button" style="height:30px; width:15% "><a href="delete_employee.jsp"><b>Delete Employee</b></a></button></td>
      <button type ="button" style="height:30px; width:15%"><a href="update_employee.jsp"><b>Update Employee</b></a></button></tr></td>
      <button type ="button" style="height:30px; width:15%"><a href="display_assigned_projects.jsp"><b>Display Assigned Projects</b></a></button>
      </center>
      </body>
</html>