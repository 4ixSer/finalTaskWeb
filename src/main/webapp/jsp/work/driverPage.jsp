<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ include file="/WEB-INF/jspf/taglib.jspf"%>

<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.title.driver" bundle="${rb}" /></title>
</head>
<body>

	<%@ include file="/jsp/head.jspf"%>
	<%@ include file="/WEB-INF/jspf/welcome.jspf"%>


	<!--Registration of a new Request.-->
	<form align="center" name="formRequest" method="GET" action="/WEB/controller">
		<input type="hidden" name="command" value="ADDREQUEST" />
		<input type="datetime-local" name="date" value="2017-04-10T20:37:40" />
		<%@ include file="/WEB-INF/jspf/typeCar.jspf"%>
		<%@ include file="/WEB-INF/jspf/characteristicsCar.jspf"%>
		<input type="submit" value="<fmt:message key="button.add.request" bundle="${rb}" />">
	</form>
	<br />

	<!--Search for your Request.-->
	<form align="center" name="findAllREQForm" method="POST" action="/WEB/controller">
		<input type="hidden" name="command" value="FINDREQUESTBYUSERID" />
		<input type="submit" value="<fmt:message key="button.find.user.request" bundle="${rb}" />">
	</form>
	<br />

	<c:if test="${not empty allRequest}">
		<div>
			<a href="/WEB/controller?command=CLOSE&table=allRequest"> &times;</a>
			<!--Request table-->
			<table align="center">
				<tr>
					<th><fmt:message key="placeholder.id" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=allRequest">&#11015</a> </th>
					<th><fmt:message key="placeholder.request.date.request" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYDATEREQUEST&object=allRequest">&#11015</a></th>
					<th><fmt:message key="placeholder.request.date.departure" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYDATEDEPARTURE&object=allRequest">&#11015</a></th>
					<th><fmt:message key="placeholder.car.type" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYTYPE&object=allRequest">&#11015</a> </th>
					<th><fmt:message key="placeholder.car.carrying" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYCARRYING&object=allRequest">&#11015</a></th>
					<th><fmt:message key="placeholder.car.amount" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYAMOUNT&object=allRequest">&#11015</a> </th>
					<th><fmt:message key="placeholder.car.power" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYENGINEPOWER&object=allRequest">&#11015</a> </th>
					<th><fmt:message key="placeholder.car.status" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=allRequest">&#11015</a> </th>
				</tr>

				<c:forEach var="elem" items="${allRequest}">
					<tr align="center">
						<td><c:out value="${ elem.namberRequest }" /></td>
						<td><c:out value="${ elem.dateRequest }" /></td>
						<td><c:out value="${ elem.dateDeparture }" /></td>
						<td><c:out value="${ elem.type }" /></td>
						<td><c:out value="${ elem.carryingCar }" /></td>
						<td><c:out value="${ elem.amountCar }" /></td>
						<td><c:out value="${ elem.enginePower }" /></td>
						<td><c:out value="${ elem.status }" /> <c:set var="status"
								value="${ elem.status }" /></td>
						<c:if test="${not fn:containsIgnoreCase(status, 'PROCESSED')}">
							<td><a
								href="/WEB/controller?command=CANCELREQUEST&id=${elem.namberRequest }"><fmt:message key="button.cancel" bundle="${rb}" /></a></td>
						</c:if>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>

	<!--Search all flights.-->
	<form align="center" name="findFlight" method="POST" action="/WEB/controller">
		<input type="hidden" name="command" value="FINDUSERFLIGHT" />
		<input type="submit" value="<fmt:message key="button.find.user.flight" bundle="${rb}" />">
	</form>

	<c:if test="${not empty allFlight}">
		<table align="center">
			<a href="/WEB/controller?command=CLOSE&table=allFlight"> &times;</a>
			<tr>
				<th><fmt:message key="placeholder.id" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=allFlight">&#11015</a></th>
				<th><fmt:message key="placeholder.request.date.departure" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYDATE&object=allFlight">&#11015</a></th>
				<th><fmt:message key="placeholder.car.status" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=allFlight">&#11015</a></th>
				<th><fmt:message key="placeholder.dispatcher" bundle="${rb}" /></th>
				<th><fmt:message key="placeholder.car" bundle="${rb}" /></th>
				<th><fmt:message key="placeholder.comment" bundle="${rb}" /></th>
			</tr>

			<c:forEach var="elem" items="${allFlight}">
				<tr align="center">
					<form align="center" name="loginForm" method="POST" action="/WEB/controller">
						<input type="hidden" name="command" value="UPDATEFLIGHT" />
						<input type="hidden" name="id" value="${ elem.namberFlight }" />
						<td><c:out value="${ elem.namberFlight }" /></td>
						<td><c:out value="${ elem.date }" /></td>
						<td><c:out value="${ elem.status }" /> <c:set var="status" value="${ elem.status }" /></td>
						<td><c:out value="${ elem.dispatcher }" /></td>
						<td><c:out value="${ elem.car }" /></td>
						<td><c:out value="${ elem.note }" /></td>
						<c:if test="${not fn:containsIgnoreCase(status, 'CLOSED')}">
							<td><input type="checkbox" name="agree"
								onclick="formDriver(this.form)" /></td>
							<td><input type="text" name="comments"
								placeholder="<fmt:message key="placeholder.comment" bundle="${rb}" />" hidden required /></td>
							<td><select name="status" hidden required>
									<option selected value="1">
										<fmt:message key="placeholder.car.status.FREE" bundle="${rb}" />
									</option>
									<option value="3">
										<fmt:message key="placeholder.car.status.BROKEN" bundle="${rb}" /></option>
							</select></td>
						</c:if>
						<td><input type="submit" name="change" value="<fmt:message key="button.flight.complete" bundle="${rb}" />" disabled /></td>
					</form>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<%@ include file="/WEB-INF/jspf/logout.jspf"%>
	<%@ include file="/jsp/footer.jspf"%>
	<script src="/WEB/js/my.js"></script>
</body>
</html>