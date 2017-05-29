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
	<%@ include file="/jsp/head.jspf"%>

	<form align="center" name="loginForm" method="POST"	action="/WEB/controller" >
		<input type="hidden" name="command" value="LOGIN" />
		<fmt:message key="label.login" bundle="${rb}" />:
		<br />
		<input type="text" pattern="[A-Za-zА-Яа-яёЁ0-9]{5,20}" name="login" placeholder="login" />
		<br />
		<fmt:message key="label.password"  bundle="${rb}" />:
		<br />
		<input type="password" name="password" pattern="[A-Za-zА-Яа-яёЁ0-9]{3,20}" placeholder="password" />
		<br />
		${errorLoginPassMessage} <br /> ${wrongAction} <br /> ${nullPage} <br />
		<input type="submit"
			value="<fmt:message key="label.logIN" bundle="${rb}" />" />
	</form>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>