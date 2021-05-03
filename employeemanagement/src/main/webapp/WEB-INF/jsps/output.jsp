<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error page</title>
</head>
<body bgcolor="DarkSeaGreen">
	<button type="button"
		style="background-color: AliceBluestyle; height: 30px; width: 5%">
		<a href="/"><b>Home</b></a>
	</button>
	<button type="button"
		style="background-color: AliceBlue; height: 30px; width: 5%">
		<a href="/display_all_employee"><b>Back</b></a>
	</button>
	<br>
	<div style="text-align: center">
		<h1>${message}</h1>
	</div>
</body>
</html>