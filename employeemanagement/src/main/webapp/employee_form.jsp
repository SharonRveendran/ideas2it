<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Creation</title>
</head>
<body bgcolor="DarkSeaGreen">
<button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="employeemanagement.jsp"><b>Back</b></a></button>
  <form action="employee?action=create_or_update_employee" method="post">
  <center>
   <h2> Enter Employee Details</h2>
    <input type = "hidden" value="${employeeDetails.get(0)}" name = "id" /><br>
    <label><pre><b><h3>    Name          :</label><input type = "text" name = "name" value="${employeeDetails.get(1)}" required = "" /><br>
    <label>  Date of Birth :</label><input type = "date" name = "dob" value="${employeeDetails.get(2)}" required = "" /><br>
    <label>Mobile Number :</label><input type = "tel" pattern="[7-9][0-9]{9}" name = "mobile" value="${employeeDetails.get(3)}" required=""/><br>
    <label>Designation   :</label><input type = "text" name = "designation" value="${employeeDetails.get(4)}" required = "" /><br>
    <label>Salary        :</label><input type = "number" name = "salary" value="${employeeDetails.get(5)}" required = "" /><br>
    <h2>Enter Permanent Address Details</h2>
    <label></label>Door Number    : <input type = "text" name = "doorNumber" value="${employeeDetails.get(6)}" required = "" /><br>
    <label></label>Street         : <input type = "text" name = "street" value="${employeeDetails.get(7)}" required = "" /><br>
    <label></label>District       : <input type = "text" name = "district" value="${employeeDetails.get(8)}" required = "" /><br>
    <label></label>State          : <input type = "text" name = "state" value="${employeeDetails.get(9)}" required = "" /><br>
    <label></label>Country        : <input type = "text" name = "country" value="${employeeDetails.get(10)}" required = "" /><br>
    <h2>Enter Temporary Address Details</h2>
    <label></label>Door Number    : <input type = "text" name = "temporaryDoorNumber" value="${employeeDetails.get(11)}"/><br>
    <label></label>Street         : <input type = "text" name = "temporaryStreet" value="${employeeDetails.get(12)}"/><br>
    <label></label>District       : <input type = "text" name = "temporaryDistrict" value="${employeeDetails.get(13)}"/><br>
    <label></label>State          : <input type = "text" name = "temporaryState" value="${employeeDetails.get(14)}"/><br>
    <label></label>Country        : <input type = "text" name = "temporaryCountry" value="${employeeDetails.get(15)}" /><br></pre></b></h3>
    <input type = "submit" value = "Submit"/>
  </center>
  </form>
</body>
</html>
