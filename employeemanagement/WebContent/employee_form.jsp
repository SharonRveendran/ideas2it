<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Creation</title>
</head>
<body>
  <form action="employee?action=create_or_update_employee" method="post">
    Enter Employee Details<br><br>
    <input type = "hidden" value="${employeeDetails.get(0)}" name = "id" /><br>
    <label>Name          :<br></label><input type = "text" name = "name" value="${employeeDetails.get(1)}" required = "" /><br>
    <label>Date of Birth :<br></label><input type = "date" name = "dob" value="${employeeDetails.get(2)}" required = "" /><br>
    <label>Mobile Number :<br></label><input type = "tel" pattern="[7-9][0-9]{9}" name = "mobile" value="${employeeDetails.get(3)}" required=""/><br>
    <label>Designation   :<br></label><input type = "text" name = "designation" value="${employeeDetails.get(4)}" required = "" /><br>
    <label>Salary        :<br></label><input type = "number" name = "salary" value="${employeeDetails.get(5)}" required = "" /><br><br>
    Enter Permanent Address Details<br><br>
    <label></label>Door Number : <br><input type = "text" name = "doorNumber" value="${employeeDetails.get(6)}" required = "" /><br>
    <label></label>Street      : <br><input type = "text" name = "street" value="${employeeDetails.get(7)}" required = "" /><br>
    <label></label>District    : <br><input type = "text" name = "district" value="${employeeDetails.get(8)}" required = "" /><br>
    <label></label>State       :<br> <input type = "text" name = "state" value="${employeeDetails.get(9)}" required = "" /><br>
    <label></label>Country     : <br><input type = "text" name = "country" value="${employeeDetails.get(10)}" required = "" /><br><br>
    Enter Temporary Address Details<br><br>
    <label></label>Door Number :<br> <input type = "text" name = "temporaryDoorNumber" value="${employeeDetails.get(11)}"/><br>
    <label></label>Street      : <br><input type = "text" name = "temporaryStreet" value="${employeeDetails.get(12)}"/><br>
    <label></label>District    : <br><input type = "text" name = "temporaryDistrict" value="${employeeDetails.get(13)}"/><br>
    <label></label>State       : <br><input type = "text" name = "temporaryState" value="${employeeDetails.get(14)}"/><br>
    <label></label>Country     : <br><input type = "text" name = "temporaryCountry" value="${employeeDetails.get(15)}" /><br>
    <input type = "submit" value = "Submit"/>
  </form>
</body>
</html>
