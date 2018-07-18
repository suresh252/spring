<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function deleteUser(mail) {
	alert("delete triggered for : " + mail);
	document.forms[0].action="${pageContext.request.contextPath}/deleteUser?mail="+mail;
	document.forms[0].method="post";
	document.forms[0].submit();
}
function editUser(mail) {
	alert("edit mail triggered!!");
	document.forms[0].action="${pageContext.request.contextPath}/editUser?mail="+mail;
	document.forms[0].method="post";
	document.forms[0].submit();
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>gmail profile list</title>
</head>
<body>
<form action="DeleteAll" method="post">
	<table border=2> 
		<tr style="color: red">
			<th>Name</th>
			<th>Mail</th>
			<th>Mobile</th>
			<th>Password</th>
		</tr>
	
		<c:forEach items="${userlist}" var="user1">
			<tr>
			<td><c:out value="${user1.name}" /></td>
			<td><c:out value="${user1.mail}" /></td>
			<td><c:out value="${user1.mobile}" /></td>
			<td><c:out value="${user1.password}" /></td>
			
			<td><input type="checkbox" name="DeleteMultiple" value="${user1.mail}" />
			<td><input type="button" value="Edit" onclick="editUser('${user1.mail}')" /> </td>            
            <td><input type="button"Delete" value="Delete" onclick="deleteUser('${user1.mail}')" /> </td></tr>
		</c:forEach>
	</table>
	<input type="submit" value="DeleteAll"/>
	</form>
</body>
</html>