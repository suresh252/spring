<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>gmail login</title>
</head>
<body>
${sms}
<form action="loginUser" method="post">
Email:<input type="text" name="mail"><br>
Password:<input type="password"name="password"><br>
<input type="submit" value="Login">

<a href="home.jsp">Register Here</a>
</form>

</body>
</html>