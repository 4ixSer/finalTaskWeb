<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<head>
<title>Login</title>
</head>
<body>
	<form align="center" name="loginForm" method="POST" action="http://localhost:8080/WEB/controller"   accept-charset="Windows-1251" >
		<input type="hidden" name="command" value="login" />
		Логин:<br />
		<input type="text" name="login" value="" /> <br />
		Password:<br />
		<input type="password" name="password" value="" /> <br />
		${errorLoginPassMessage} <br /> ${wrongAction} <br /> ${nullPage} <br />
		<input type="submit" value="Log in" />
	</form>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>