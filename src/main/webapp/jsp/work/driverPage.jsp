<%@ page language="java" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="pagecontent" var="rb" />
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
</head>
<body>
	<%@ include file="/jsp/head.jspf"%>
	<h2>
		<p align="center">
			<fmt:message key="label.welcome" bundle="${rb}" />, ${name}.
		</p>
	</h2>

		<!-- регистрация заявки   -->
	<form align="center" name="formRequest" method="POST" action="http://localhost:8080/WEB/controller" accept-charset="Windows-1251">
		<input type="hidden" name="command" value="addRequest" />

		<input type="datetime-local" name="date" value="2017-04-10T20:37:40" max="2026-04-20T00:00:00" min="2016-04-10T00:00:00"/>

		<input type="submit" value="<fmt:message key="menu.register.user" bundle="${rb}" />">
		<p align="center">
			${Message1} <br>
		</p>
	</form>
	<br />

	<!-- logout -->
	<p align="right">
		<a href="http://localhost:8080/WEB/controller?command=logout"><fmt:message key="label.logout" bundle="${rb}" /></a>
	</p>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>