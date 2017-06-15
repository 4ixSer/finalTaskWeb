<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.title.driver" bundle="${rb}" /></title>
</head>
<body>

	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<%@ include file="/WEB-INF/jspf/welcome.jspf"%>


	<!--Take an Request.-->
	<form align="center" name="findRequest" method="POST" action="/WEB/controller">
		<input type="hidden" name="command" value="FINDREQUEST" />
		<input	type="submit" value="<fmt:message key="button.get.request" bundle="${rb}" />">
	</form>

	<c:if test="${not empty requestUser}">
		<table align="center">
				<tr>
					<th><fmt:message key="placeholder.id" bundle="${rb}" /></th>
					<th><fmt:message key="placeholder.request.date.request" bundle="${rb}" /> </th>
					<th><fmt:message key="placeholder.request.date.departure" bundle="${rb}" /> </th>
					<th><fmt:message key="placeholder.car.type" bundle="${rb}" /></th>
					<th><fmt:message key="placeholder.car.carrying" bundle="${rb}" /> </th>
					<th><fmt:message key="placeholder.car.amount" bundle="${rb}" /> </th>
					<th><fmt:message key="placeholder.car.power" bundle="${rb}" /> </th>
					<th><fmt:message key="placeholder.comment" bundle="${rb}" /> </th>
				</tr>
				<tr align="center">
						<td><c:out value="${ requestUser.namberRequest }" /></td>
						<td><c:out value="${ requestUser.dateRequest }" />   </td>
						<td><c:out value="${ requestUser.dateDeparture }" />   </td>
						<td><c:out value="${ requestUser.type }" />   </td>
						<td><c:out value="${ requestUser.carryingCar }" />   </td>
						<td><c:out value="${ requestUser.amountCar }" />   </td>
						<td><c:out value="${ requestUser.enginePower }" />   </td>
						<td><c:out value="${ requestUser.note }" />   </td>
						<td><a  href="/WEB/controller?command=DENYREQUEST"><fmt:message key="button.request.reject" bundle="${rb}" /></a></td>
				</tr>
			</table>
				<c:if test="${not empty requestCar}">
					<dib align="center">
						<form align="center" name="formRequest" method="POST" action="/WEB/controller" >
						<input type="hidden" name="command" value="ADDFLIGHT" />
							<select name="selectedCar" required >
								<c:forEach var="elem" items="${requestCar}">
									<option value="${elem.id}">${elem.type}_____carrying_____${elem.carryingCar}_____amount_____${elem.amountCar}_____engine_____${elem.enginePower} </option>
								</c:forEach>
							</select>
							<input type="text" name="comments" placeholder="<fmt:message key="placeholder.comment" bundle="${rb}" />"/>
							<input type="submit" value="<fmt:message key="button.add.flight" bundle="${rb}" />">
						</form>
					</div>
				</c:if>
	</c:if>
	<br />


	<!--Search all flights.-->
	<form align="center" name="findFlight" method="POST" action="/WEB/controller">
		<input type="hidden" name="command" value="FINDALLFLIGHT" />
		<input	type="submit" value="<fmt:message key="button.find.flight" bundle="${rb}" />">
	</form>

	<c:if test="${not empty allFlight}">
		<table align="center">
			<a  href="/WEB/controller?command=CLOSE&table=allFlight">	&times;</a>
				<tr>
					<th><fmt:message key="placeholder.id" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=allFlight">&#11015</a></th>
					<th><fmt:message key="placeholder.request.date.departure" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=allFlight">&#11015</a></th>
					<th><fmt:message key="placeholder.request.status" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYDATE&object=allFlight">&#11015</a></th>
					<th><fmt:message key="placeholder.driver" bundle="${rb}" /></th>
					<th><fmt:message key="placeholder.dispatcher" bundle="${rb}" /></th>
					<th><fmt:message key="placeholder.car" bundle="${rb}" /></th>
					<th><fmt:message key="placeholder.comment" bundle="${rb}" /></th>
				</tr>
				<c:forEach var="elem" items="${allFlight}" >
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

	<%@ include file="/WEB-INF/jspf/logout.jspf"%>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>