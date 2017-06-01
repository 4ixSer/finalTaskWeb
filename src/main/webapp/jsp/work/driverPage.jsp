<%@ page language="java" contentType="text/html"
	pageEncoding="UTF-8" isELIgnored="false"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="pagecontent" var="rb" />
<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.title.driver" bundle="${rb}" /></title>
<script>

function agreeForm(f) {
    // Если поставлен флажок, снимаем блокирование кнопки
   if (f.agree.checked) {

	   f.comments.hidden = 0;
	   f.status.hidden = 0;
	   f.change.disabled = 0;

   }
    // В противном случае вновь блокируем кнопку
    else {
    	f.comments.hidden = 1;
 	   	f.status.hidden = 1;
 	   	f.change.disabled = 1;
    }

}
</script>
</head>
<body>
	<%@ include file="/jsp/head.jspf"%>
	<h2>
		<p align="center">
			<fmt:message key="label.welcome" bundle="${rb}" />, ${name}.
		</p>
	</h2>
	${Message} <br><br>
		<!-- регистрация заявки   -->
	<form align="center" name="formRequest" method="GET" action="/WEB/controller" >
		<input type="hidden" name="command" value="ADDREQUEST" />
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

		<input type="number" name="carrying" step="0.1" pattern="[a-z]" placeholder="Carrying" />
		<input type="number" name="amount" step="0.1"	pattern="[0-9]{1,5},[0-9]{2}" placeholder="Amount" />
		<input type="number" name="engine" step="0.1" pattern="[0-9]{1,5},[0-9]{2}" placeholder="Engine" />
		<input type="text" name="comments" placeholder="Comments" />

		<input type="submit" value="<fmt:message key="menu.add.request" bundle="${rb}" />">
		<p align="center">
			${Message1} <br>
		</p>
	</form>
	<br />

		<!--Запрос на поиск своих запросов   -->
	<form align="center" name="findAllREQForm" method="POST"
		action="/WEB/controller"">
		<input type="hidden" name="command" value="FINDREQUESTBYUSERID" />

		<input	type="submit" value="<fmt:message key="menu.find.request" bundle="${rb}" />">
	</form>
	<br />
	<c:if test="${not empty allRequest}">
		<div>
			<a  href="/WEB/controller?command=CLOSE&table=allRequest">	&times;</a>
			<!--Таблица дял отрисовки запросов   -->
			<table align="center">
				<tr>
					<th>№ <a href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=userRequest">&#11015</a> </th>
					<th>dataRequest <a href="/WEB/controller?command=SORT&typeSort=SORTBYDATAREQUEST&object=userRequest">&#11015</a></th>
					<th>dataDeparture <a href="/WEB/controller?command=SORT&typeSort=SORTBYDATADEPARTURE&object=userRequest">&#11015</a></th>
					<th>type <a href="/WEB/controller?command=SORT&typeSort=SORTBYTYPE&object=userRequest">&#11015</a> </th>
					<th>carrying <a href="/WEB/controller?command=SORT&typeSort=SORTBYCARRYING&object=userRequest">&#11015</a></th>
					<th>amount <a href="/WEB/controller?command=SORT&typeSort=SORTBYAMOUNT&object=userRequest">&#11015</a>	</th>
					<th>engine <a href="/WEB/controller?command=SORT&typeSort=SORTBYENGINEPOWER&object=userRequest">&#11015</a> </th>
					<th>status <a href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=userRequest">&#11015</a> </th>

				</tr>

			<c:forEach var="elem" items="${allRequest}" varStatus="status">
					<tr align="center">
						<td><c:out value="${ elem.namberRequest }" /></td>
						<td><c:out value="${ elem.dataRequest }" /></td>
						<td><c:out value="${ elem.dataDeparture }" /></td>
						<td><c:out value="${ elem.type }" /></td>
						<td><c:out value="${ elem.carryingCar }" /></td>
						<td><c:out value="${ elem.amountCar }" /></td>
						<td><c:out value="${ elem.enginePower }" /></td>
						<td><c:out value="${ elem.status }" /><c:set var = "status" value = "${ elem.status }"/></td>
						<c:if  test = "${not fn:containsIgnoreCase(status, 'PROCESSED')}">
							<td><a  href="/WEB/controller?command=CANCELREQUEST&id=${elem.namberRequest }">Отменить</a></td>
					</c:if>
					</tr>
				</c:forEach>

			</table>
		</div>
	</c:if>

	<!--Запрос на просмотр всех рейсов   -->
	<form align="center" name="findFlight" method="POST"
		action="/WEB/controller">
		<input type="hidden" name="command" value="FINDUSERFLIGHT" />

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
						<form align="center" name="loginForm" method="POST" action="/WEB/controller" >
							<input type="hidden" name="command"  value="UPDATEFLIGHT" />
							<input type="hidden" name="id"  value="${ elem.namberFlight }" />
							<td><c:out value="${ elem.namberFlight }" /></td>
							<td><c:out value="${ elem.date }" /></td>
							<td><c:out value="${ elem.status }" /> <c:set var = "status" value = "${ elem.status }"/></td>
							<td><c:out value="${ elem.driver }" /></td>
							<td><c:out value="${ elem.dispatcher }" /></td>
							<td><c:out value="${ elem.car }" /></td>
							<td><c:out value="${ elem.note }" /></td>
							 <c:if  test = "${not fn:containsIgnoreCase(status, 'CLOSED')}">
								<td><input type="checkbox" name="agree" onclick="agreeForm(this.form)" /></td>
								<td><input type="text" name="comments" placeholder="Comments" hidden required/> </td>
								<td><select	name="status" hidden required>
									<option selected  value="1"> <fmt:message key="car.status.FREE" bundle="${rb}"  /> </option>
									<option value="3"> <fmt:message key="car.status.BROKEN" bundle="${rb}" /></option>
								</select>
								</td>
							</c:if>
							<td><input type="submit" name="change" value="Изменить" disabled /></td>
						</form>
					</tr>

				</c:forEach>
			</table>
	</c:if>


	<!-- logout -->
	<p align="right">
		<a href="/WEB/controller?command=LOGOUT"><fmt:message key="label.logout" bundle="${rb}" /></a>
	</p>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>