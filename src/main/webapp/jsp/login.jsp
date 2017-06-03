<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="pagecontent" var="rb" />
<!DOCTYPE html>
<head>
<title>Login</title>
</head>
<body>
	<%@ include file="/jsp/head.jspf"%>

	<c:set var = "role" value = "${userType }"/>
	<c:choose>
		<c:when test="${fn:containsIgnoreCase(role, 'DRIVER')}" >
			<jsp:forward page="/jsp/work/driverPage.jsp" />
		</c:when>
		<c:when test="${fn:containsIgnoreCase(role, 'DISPATCHER')}" >
			<jsp:forward page="/jsp/work/dispatcherPage.jsp" />
		</c:when>
		<c:when test="${fn:containsIgnoreCase(role, 'ADMINISTRATOR')}" >
			<jsp:forward page="/jsp/work/adminPage.jsp" />
		</c:when>
		<c:otherwise>

		</c:otherwise>
	</c:choose>



	<form align="center" name="loginForm" method="POST"	action="/WEB/controller" >
		<input type="hidden" name="command" value="LOGIN" />
		<fmt:message key="label.login" bundle="${rb}" />:
		<br />
		<input type="text" pattern="[A-Za-zА-Яа-яёЁ0-9]{5,20}" name="login" required/>
		<br />
		<fmt:message key="label.password"  bundle="${rb}" />:
		<br />
		<input type="password" name="password" pattern="[A-Za-zА-Яа-яёЁ0-9]{3,20}" required/>
		<br />
		${errorMessage} <br />
		<input type="submit" value="<fmt:message key="button.login" bundle="${rb}" />" />
	</form>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>