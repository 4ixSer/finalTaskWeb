<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="pagecontent" var="rb" />
<!DOCTYPE html>
<head>
<title><fmt:message key="label.title" bundle="${rb}" /></title>
</head>
<body>
	<h2>
		<p align="center">
			<fmt:message key="label.welcome" bundle="${rb}" />
		</p>
	</h2>


	<form align="center" name="loginForm" method="POST" action="http://localhost:8080/WEB/controller" accept-charset="Windows-1251">
		<input type="hidden" name="command" value="REGISTRATION" /> <input
			type="text" name="login" value="login" /> <input type="password"
			name="password" value="password" /> <input type="text" name="email"
			value="Email" /> <input type="text" name="name" value="name" /> <select
			name="role"required ">
			<option value="1"> <fmt:message key="label.admin" bundle="${rb}" /> </option>
			<option value="2"> <fmt:message key="label.dispatcher" bundle="${rb}" /></option>
			<option selected value="3"> <fmt:message key="label.driver" bundle="${rb}" /></option>
		</select> <input type="submit" value="<fmt:message key="label.register" bundle="${rb}" />">
		<p align="center">
			${Message} <br>
		</p>
	</form>
	<br />

	<form align="center" name="findAllForm" method="POST"
		action="http://localhost:8080/WEB/controller"">
		<input type="hidden" name="command" value="findall" /> </select> <input
			type="submit" value="найти всех юзеров">
	</form>
	<br />

	<table align="center">
		<tr>
			<th>ID</th>
			<th>Login</th>
			<th>Password</th>
			<th>Name</th>
			<th>email</th>
			<th>role</th>
		</tr>

		<c:forEach var="elem" items="${users}" varStatus="status">
			<tr>
				<td><c:out value="${ elem.id }" /></td>
				<td><c:out value="${ elem.login }" /></td>
				<td><c:out value="${ elem.password }" /></td>
				<td><c:out value="${ elem.name }" /></td>
				<td><c:out value="${ elem.email }" /></td>
				<td><c:out value="${ elem.type }" /></td>

			</tr>
		</c:forEach>

	</table>

	<br />
	<p align="right">
		<a href="http://localhost:8080/WEB/controller?command=logout">Logout</a>
	</p>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>