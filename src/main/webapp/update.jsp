<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>edit page</title>
</head>
<body>
<form action="update" method="post">
Name:<input type="text" name="name" value="${user.name }"/><br>
Mail:<input type="text" name="mail"  readonly="readonly" value="${user.mail}"/><br>
Mobile:<input type="text" name="mobile" value="${user.mobile}"/><br>
Password:<input type="text" name="password" value="${user.password}"><br>
<input type="submit" value="update">


</form>



</body>
</html>