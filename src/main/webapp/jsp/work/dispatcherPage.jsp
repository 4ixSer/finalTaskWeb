<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"
	isELIgnored="false"%>
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
			<fmt:message key="label.welcome" bundle="${rb}" />
			, ${name}.
		</p>
	</h2>
	${Message} <br><br>
	<!--Запрос на поиск зявок   -->
	<form align="center" name="findRequest" method="POST"
		action="/WEB/controller">
		<input type="hidden" name="command" value="FINDREQUEST" />

		<input	type="submit" value="Взять заявку">
	</form>

	<!--  результат поиска заявок -->
	<c:if test="${not empty requestUser}">

		<table align="center">

				<tr>
					<th>namber </th>
					<th>data Request </th>
					<th>data Departure </th>
					<th>type </th>
					<th>carrying </th>
					<th>amount </th>
					<th>enginePower </th>

					<th>note </th>
				</tr>


				<tr align="center">


						<td><c:out value="${ requestUser.namberRequest }" /></td>

						<td><c:out value="${ requestUser.dataRequest }" />   </td>
						<td><c:out value="${ requestUser.dataDeparture }" />   </td>
						<td><c:out value="${ requestUser.type }" />   </td>
						<td><c:out value="${ requestUser.carryingCar }" />   </td>
						<td><c:out value="${ requestUser.amountCar }" />   </td>
						<td><c:out value="${ requestUser.enginePower }" />   </td>
						<td><c:out value="${ requestUser.note }" />   </td>
						<!-- <td><a href="/WEB/controller?command=FINDCARBYCHARACTERISTICS">Найти машину</a>   </td> -->
						<td><a  href="/WEB/controller?command=DENYREQUEST">Отказать</a></td>


				</tr>
			</table>
				<c:if test="${not empty requestCar}">
					<dib align="center">
						<form align="center" name="formRequest" method="POST" action="/WEB/controller" >
						<input type="hidden" name="command" value="ADDFLIGHT" />

							<select name="selectedCar" required ">
								<c:forEach var="elem" items="${requestCar}" varStatus="status">
									<option value="${elem.id}">${elem.type}_____carrying_____${elem.carryingCar}_____amount_____${elem.amountCar}_____engine_____${elem.enginePower} </option>
								</c:forEach>
							</select>
							<input type="text" name="comments" placeholder="Comments"/>
							<input type="submit" value="Назначить">

						</form>
					</div>
				</c:if>


	</c:if>
	<br />


	<!--Запрос на просмотр всех рейсов   -->
	<form align="center" name="findFlight" method="POST"
		action="/WEB/controller">
		<input type="hidden" name="command" value="FINDALLFLIGHT" />

		<input	type="submit" value="Найти все рейсы">
	</form>

	<!--  Показать все рейсы -->
	<c:if test="${not empty allFlight}">

		<table align="center">
			<a  href="/WEB/controller?command=CLOSE&table=allFlight">	&times;</a>
				<tr>
					<th>namber <a href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=allFlight">&#11015</a></th>
					<th>date  <a href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=allFlight">&#11015</a></th>
					<th>status <a href="/WEB/controller?command=SORT&typeSort=SORTBYDATE&object=allFlight">&#11015</a></th>
					<th>driver</th>
					<th>dispatcher</th>
					<th>car</th>
					<th>note </th>
				</tr>


				<c:forEach var="elem" items="${allFlight}" varStatus="status">
					<tr align="center">
						<td><c:out value="${ elem.namberFlight }" /></td>
						<td><c:out value="${ elem.date }" /></td>
						<td><c:out value="${ elem.status }" /></td>
						<td><c:out value="${ elem.driver }" /></td>
						<td><c:out value="${ elem.dispatcher }" /></td>
						<td><c:out value="${ elem.car }" /></td>
						<td><c:out value="${ elem.note }" /></td>
					</tr>
				</c:forEach>
			</table>
	</c:if>
	<br />


	<!-- logout -->
	<p align="right">
		<a href="/WEB/controller?command=LOGOUT"><fmt:message
				key="label.logout" bundle="${rb}" /></a>
	</p>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>