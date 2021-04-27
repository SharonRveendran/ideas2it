<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Project Creation</title>
</head>
<body bgcolor="DarkSeaGreen">
  <button type ="button" style="background-color:AliceBluestyle; height:30px; width:5%"><a href="welcome.jsp"><b>Home</b></a></button>
  <button type ="button" style="background-color:AliceBlue; height:30px; width:5%"><a href="projectmanagement.jsp"><b>Back</b></a></button>
  <form action="project?action=create_or_update_project" method="post">
    <center>
    <h2>Enter Project Details</h2>
    <input type = "hidden" value="${projectDetails.get(0)}" name = "id" />
    <pre><b>
    <label>Name         :</label><input type = "text"  name = "name" value="${projectDetails.get(1)}" required = "" /><br>
    <label>Manager Name :</label><input type = "text"  name = "managerName" value="${projectDetails.get(2)}"  required = "" /><br>
    <label>  Start Dater  :</label><input type = "date" name = "startDate" value="${projectDetails.get(3)}"  required = "" /><br>
    <label>  End Date     :</label><input type = "date" name = "endDate" value="${projectDetails.get(4)}"  required = "" /><br>
    <input type = "submit" value = "Submit"/>
    </b></pre>
    </center>
  </form>
</body>
</html>