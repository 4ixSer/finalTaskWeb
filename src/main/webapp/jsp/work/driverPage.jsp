<%@ page language="java" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="pagecontent" var="rb" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.title.driver" bundle="${rb}" /></title>
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
		<input type="datetime-local" name="date" value="2017-04-10T20:37:40" />
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

		<input type="number" name="carrying" step="0.1" placeholder="Carrying" />
		<input type="number" name="amount" step="0.1"	placeholder="Amount" />
		<input type="number" name="engine" step="0.1" placeholder="Engine" />
		<input type="text" name="comments" placeholder="Comments" />

		<input type="submit" value="<fmt:message key="menu.add.request" bundle="${rb}" />">
		<p align="center">
			${Message1} <br>
		</p>
	</form>
	<br />

		<!--Запрос на поиск своих запросов   -->
	<form align="center" name="findAllREQForm" method="POST"
		action="http://localhost:8080/WEB/controller"">
		<input type="hidden" name="command" value="FINDREQUESTBYUSERID" />

		<input	type="submit" value="<fmt:message key="menu.find.request" bundle="${rb}" />">
	</form>
	<br />

	<!--Таблица дял отрисовки запросов   -->
	<table align="center">
		<tr>
			<th>namberRequest</th>
			<th>dataRequest</th>
			<th>dataDeparture</th>
			<th>type</th>
			<th>carrying</th>
			<th>amount</th>
			<th>engine</th>
			<th>status</th>

		</tr>

	<c:forEach var="elem" items="${userRequest}" varStatus="status">
			<tr align="center">
				<td><c:out value="${ elem.namberRequest }" /></td>
				<td><c:out value="${ elem.dataRequest }" /></td>
				<td><c:out value="${ elem.dataDeparture }" /></td>
				<td><c:out value="${ elem.type }" /></td>
				<td><c:out value="${ elem.carryingCar }" /></td>
				<td><c:out value="${ elem.amountCar }" /></td>
				<td><c:out value="${ elem.enginePower }" /></td>
				<td><c:out value="${ elem.status }" /></td>

			</tr>
		</c:forEach>

	</table>

	<!-- logout -->
	<p align="right">
		<a href="http://localhost:8080/WEB/controller?command=logout"><fmt:message key="label.logout" bundle="${rb}" /></a>
	</p>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>