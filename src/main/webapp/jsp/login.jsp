<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="pagecontent" var="rb" />
<!DOCTYPE html>
<head>
<title>Login</title>
</head>
<body>

	<form align="center" name="loginForm" method="POST"		action="http://localhost:8080/WEB/controller"		accept-charset="Windows-1251">
		<input type="hidden" name="command" value="login" />
		<fmt:message key="label.login" bundle="${rb}" />:
		<br />
		<input type="text" name="login" value="" />
		<br />
		<fmt:message key="label.password" bundle="${rb}" />:
		<br />
		<input type="password" name="password" value="" />
		<br />
		${errorLoginPassMessage} <br /> ${wrongAction} <br /> ${nullPage} <br />
		<input type="submit"
			value="<fmt:message key="label.logIN" bundle="${rb}" />" />
	</form>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>