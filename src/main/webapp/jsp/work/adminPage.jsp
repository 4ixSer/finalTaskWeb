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
		<input type="hidden" name="command" value="REGISTRATIONUSER" /> <input
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
		<input type="hidden" name="command" value="FINDALLUSER" /> </select> <input
			type="submit" value="найти всех юзеров">
	</form>
	<br />

	<table align="center">
		<tr>
			<th>ID</th>
			<th>Login</th>
			<th>Name</th>
			<th>Email</th>
			<th>Role</th>
		</tr>

		<c:forEach var="elem" items="${users}" varStatus="status">
			<tr align="center">
				<td><c:out value="${ elem.id }" /></td>
				<td><c:out value="${ elem.login }" /></td>
				<td><c:out value="${ elem.name }" /></td>
				<td><c:out value="${ elem.email }" /></td>
				<td><c:out value="${ elem.type }" /></td>
				<td><a href="http://localhost:8080/WEB/controller?command=DELETEUSER&id=${elem.id }">Delete</a></td>
			</tr>
		</c:forEach>

	</table>


	<form align="center" name="findAllCarForm" method="POST"
		action="http://localhost:8080/WEB/controller"">
		<input type="hidden" name="command" value="ADDCAR" /> </select> <input
			type="submit" value="найти все машины">
	</form>
	<br />
	<table align="center">
		<tr>
			<th>ID</th>
			<th>namber</th>
			<th>type</th>
			<th>carryingCar</th>
			<th>amountCar</th>
			<th>enginePower</th>
			<th>statuc</th>
			<th>comments</th>
		</tr>

		<c:forEach var="elem" items="${cars}" varStatus="status">
			<tr align="center">
				<td><c:out value="${ elem.id }" /></td>
				<td><c:out value="${ elem.namber }" /></td>
				<td><c:out value="${ elem.type }" /></td>
				<td><c:out value="${ elem.carryingCar }" /></td>
				<td><c:out value="${ elem.amountCar }" /></td>
				<td><c:out value="${ elem.enginePower }" /></td>
				<td><c:out value="${ elem.statusCar }" /></td>
				<td><c:out value="${ elem.comments }" /></td>
				<td><a href="http://localhost:8080/WEB/controller?command=deleteCar&id=${elem.id }">Delete</a></td>
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