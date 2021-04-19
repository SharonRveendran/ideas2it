<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Employee</title>
</head>
<body>
  <form action="employee?action=display_employee" method="post">
    Enter employee Id :
    <input type = "number" name = "id">
    <input type = "submit" value = "Display Employee"/>
  </form><br><br>
  <table border="3" style="width:80%">
    <thead>
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
    <tbody>
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
      </tr>
      </c:forEach>
    </tbody>
  </table>
</body>
</html>