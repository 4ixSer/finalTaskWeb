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
		<input type="hidden" name="command" value="REGISTRATIONUSER" />
		<input type="text" name="login" value="login" />
		<input type="password" name="password" value="password" />
		<input type="text" name="email"	value="Email" />
		<input type="text" name="name" value="name" />
		<select	name="role"required ">
			<option value="1"> <fmt:message key="label.admin" bundle="${rb}" /> </option>
			<option value="2"> <fmt:message key="label.dispatcher" bundle="${rb}" /></option>
			<option selected value="3"> <fmt:message key="label.driver" bundle="${rb}" /></option>
		</select> <input type="submit" value="<fmt:message key="label.register" bundle="${rb}" />">
		<p align="center">
			${Message1} <br>
		</p>
	</form>
	<br />

	<form align="center" name="loginForm" method="POST" action="http://localhost:8080/WEB/controller" accept-charset="Windows-1251">
		<input type="hidden" name="command" value="ADDCAR" />
		<input type="text" name="namber" value="namber" />

		<select name="type" required ">
			<option  selected value="1"> <fmt:message key="car.type.PLATFORM" bundle="${rb}" /> </option>
			<option value="2"> <fmt:message key="car.type.VAN" bundle="${rb}" /></option>
			<option  value="3"> <fmt:message key="car.type.TANK" bundle="${rb}" /></option>
			<option value="4"> <fmt:message key="car.type.SUGGESTION" bundle="${rb}" /></option>
			<option value="5"> <fmt:message key="car.type.SORTEMENT" bundle="${rb}" /></option>
			<option value="6"> <fmt:message key="car.type.BOARD" bundle="${rb}" /></option>
			<option value="7"> <fmt:message key="car.type.CONTAINER" bundle="${rb}" /></option>
			<option value="8"> <fmt:message key="car.type.GASOLINE" bundle="${rb}" /></option>
			<option value="9"> <fmt:message key="car.type.TANKER" bundle="${rb}" /></option>
			<option value="10"> <fmt:message key="car.type.TENT" bundle="${rb}" /></option>
			<option value="11"> <fmt:message key="car.type.AVTOVOSCH" bundle="${rb}" /></option>
			<option value="12"> <fmt:message key="car.type.REFRIGERATOR" bundle="${rb}" /></option>
			<option value="13"> <fmt:message key="car.type.OTHERS" bundle="${rb}" /></option>

		</select>

		<input type="text" name="carrying" value="Carrying" />
		<input type="text" name="amount"	value="Amount" />
		<input type="text" name="engine" value="Engine" />
		<input type="text" name="comments" value="Comments" />

		<select
			name="statusCar" required ">
			<option selected  value="1"> <fmt:message key="car.status.FREE" bundle="${rb}" /> </option>
			<option value="2"> <fmt:message key="car.status.USED" bundle="${rb}" /></option>
			<option value="3"> <fmt:message key="car.status.BROKEN" bundle="${rb}" /></option>
		</select>

		<input type="submit" value="<fmt:message key="car.CREATE" bundle="${rb}" />">
		<p align="center">
			${Message2} <br>
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
		<input type="hidden" name="command" value="FINDALLCARS" /> </select> <input
			type="submit" value="найти все машины">
	</form>
	<br />
	<table align="center">
		<tr>
			<th>ID</th>
			<th><fmt:message key="car.Namber" bundle="${rb}" /></th>
			<th><fmt:message key="car.Type" bundle="${rb}" /></th>
			<th><fmt:message key="car.Carrying" bundle="${rb}" /></th>
			<th><fmt:message key="car.Amount" bundle="${rb}" /></th>
			<th><fmt:message key="car.EnginePower" bundle="${rb}" /></th>
			<th><fmt:message key="car.Statuc" bundle="${rb}" /></th>
			<th><fmt:message key="car.Comments" bundle="${rb}" /></th>
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
				<td><a href="http://localhost:8080/WEB/controller?command=DELETECAR&id=${elem.id }">Delete</a></td>
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