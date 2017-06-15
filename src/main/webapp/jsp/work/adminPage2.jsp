<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>

<!DOCTYPE html>
<html>
<head>
<title><fmt:message key="label.title.admin" bundle="${rb}" /></title>
</head>
<body>

	<%@ include file="/WEB-INF/jspf/head.jspf"%>
	<%@ include file="/WEB-INF/jspf/welcome.jspf"%>


	<!-- User registration form  -->
	<form align="center" name="loginForm" method="POST" action="/WEB/controller">
		<input type="hidden" name="command" value="REGISTERINGUSER" />
		<input type="text" name="login" pattern="[A-Za-zА-Яа-яёЁ0-9]{5,20}"	placeholder="<fmt:message key="placeholder.user.login" bundle="${rb}" />" required title="5 и более символов A-Za-zА-Яа-яёЁ0-9" />
		<input type="password" name="password" pattern="[A-Za-zА-Яа-яёЁ0-9]{3,20}" placeholder="<fmt:message key="placeholder.user.password" bundle="${rb}" />" required />
		<input type="email" name="email" placeholder="<fmt:message key="placeholder.user.email" bundle="${rb}" />" required />
		<input type="text" name="name"	pattern="[A-Za-zА-Яа-яёЁ0-9]{2,20}" placeholder="<fmt:message key="placeholder.user.name" bundle="${rb}" />" required />
		<select name="role" required >
			<option value="1">
				<fmt:message key="placeholder.user.type.admin" bundle="${rb}" />
			</option>
			<option value="2">
				<fmt:message key="placeholder.user.type.dispatcher" bundle="${rb}" />
			</option>
			<option selected value="3">
				<fmt:message key="placeholder.user.type.driver" bundle="${rb}" />
			</option>
		</select>
		<input type="submit" value="<fmt:message key="button.registration" bundle="${rb}" />">
		<p align="center"></p>
	</form>
	<br />


	<!-- The form of registration of new cars.  -->
	<form align="center" name="loginForm" method="POST" action="/WEB/controller">
		<input type="hidden" name="command" value="ADDCAR" />
		<input type="text" name="namber" pattern="[A-Z]{2}[0-9]{4}[A-Z]{2}"	placeholder="<fmt:message key="placeholder.car.number" bundle="${rb}" />" />
		<%@ include file="/WEB-INF/jspf/typeCar.jspf"%>
		<%@ include file="/WEB-INF/jspf/characteristicsCar.jspf"%>
		<select name="status" required >
			<option selected value="1">
				<fmt:message key="placeholder.car.status.FREE" bundle="${rb}" />
			</option>
			<option value="2">
				<fmt:message key="placeholder.car.status.USED" bundle="${rb}" />
			</option>
			<option value="3">
				<fmt:message key="placeholder.car.status.BROKEN" bundle="${rb}" />
			</option>
		</select>
		<input type="submit" value="<fmt:message key="button.registration" bundle="${rb}" />">
	</form>
	<br />


	<!--Search all users.-->
	<form align="center" name="findAllForm" method="POST" action="/WEB/controller">
		<input type="hidden" name="command" value="FINDALLUSERS" />
		<input type="submit" value="<fmt:message key="button.find.all.user" bundle="${rb}" />">
	</form>
	<br />


	<c:if test="${not empty users}">
		<div>
			<!--Close tables.-->
			<a href="/WEB/controller?command=CLOSE&table=users"> &times;</a>

			<!--Table for users -->
			<table align="center">
				<tr>
					<th>ID <a href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=users">&#11015</a></th>
					<th><fmt:message key="placeholder.user.login" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYLOGIN&object=users">&#11015</a></th>
					<th><fmt:message key="placeholder.user.name" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYNAME&object=users">&#11015</a></th>
					<th><fmt:message key="placeholder.user.email" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYEMAIL&object=users">&#11015</a></th>
					<th><fmt:message key="placeholder.user.role" bundle="${rb}" /><a href="/WEB/controller?command=SORT&typeSort=SORTBYROLE&object=users">&#11015</a></th>
				</tr>

				<c:forEach var="elem" items="${users}">
					<tr align="center">
						<form align="center" name="loginForm" method="POST" action="/WEB/controller">
							<input type="hidden" name="command" value="UPDATEUSER" />
							<td>
								<c:out value="${ elem.id }" />
								<input type="hidden" name="id" value="${ elem.id }" />
							</td>
							<td><c:out value="${ elem.login }" /></td>
							<td>
								<input type="text" name="name" pattern="[A-Za-zА-Яа-яёЁ0-9]{2,20}" value="${ elem.name }" disabled />
							</td>
							<td>
								<input type="email" name="email" value="${ elem.email }" disabled /></td>
							<td><c:out value="${ elem.type }" /></td>
							<td>
								<a href="/WEB/controller?command=DELETEUSER&id=${elem.id }">
									<fmt:message key="button.delete" bundle="${rb}" />
								</a>
							</td>
							<td>
								<input type="checkbox" name="agree" onclick="agreeForm(this.form)">
							</td>
							<td><input type="submit" name="change" value="<fmt:message key="button.change" bundle="${rb}" />" disabled></td>
						</form>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<br />


	<!--Search for all machines.-->
	<form align="center" name="findAllCarForm" method="POST" action="/WEB/controller">
		<input type="hidden" name="command" value="FINDALLCARS" />
		<input type="submit" value="<fmt:message key="button.find.all.car" bundle="${rb}" />">
	</form>
	<br />


	<c:if test="${not empty cars}">
		<div>
			<!--Close tables.-->
			<a href="/WEB/controller?command=CLOSE&table=cars"> &times;</a>
			<!--Table for machines.-->
			<table align="center">
				<tr>
					<th>ID <a href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=cars">&#11015</a></th>
					<th><fmt:message key="placeholder.car.number" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYNAMBER&object=cars">&#11015</a></th>
					<th><fmt:message key="placeholder.car.type" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYTYPE&object=cars">&#11015</a></th>
					<th><fmt:message key="placeholder.car.carrying" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYCARRYING&object=cars">&#11015</a></th>
					<th><fmt:message key="placeholder.car.amount" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYAMOUNT&object=cars">&#11015</a></th>
					<th><fmt:message key="placeholder.car.power" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYENGINE&object=cars">&#11015</a></th>
					<th><fmt:message key="placeholder.car.status" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=cars">&#11015</a></th>
					<th><fmt:message key="placeholder.comment" bundle="${rb}" /></th>
				</tr>

				<c:forEach var="elem" items="${cars}" >
					<tr align="center">
						<form align="center" name="loginForm" method="POST" action="/WEB/controller">
							<input type="hidden" name="command" value="UPDATECAR" />
							<td><c:out value="${ elem.id }" /> <input type="hidden" name="id" value="${ elem.id }" /></td>
							<td><input type="text" name="namber" pattern="[A-Z]{2}[0-9]{4}[A-Z]{2}" value="${ elem.namber }" disabled /></td>
							<td>
								<input type="text" name="oldtype" value="${ elem.type }" disabled />
								<select hidden name="type"required >
										<option selected value="1">
											<fmt:message key="placeholder.car.type.PLATFORM" bundle="${rb}" />
										</option>
										<option value="2">
											<fmt:message key="placeholder.car.type.VAN" bundle="${rb}" />
										</option>
										<option value="3">
											<fmt:message key="placeholder.car.type.TANK" bundle="${rb}" />
										</option>
										<option value="4">
											<fmt:message key="placeholder.car.type.TIPPER" bundle="${rb}" />
										</option>
										<option value="5">
											<fmt:message key="placeholder.car.type.SORTEMENT" bundle="${rb}" />
										</option>
										<option value="7">
											<fmt:message key="placeholder.car.type.BOARD" bundle="${rb}" />
										</option>
										<option value="6">
											<fmt:message key="placeholder.car.type.CONTAINER" bundle="${rb}" />
										</option>
										<option value="8">
											<fmt:message key="placeholder.car.type.GASOLINE" bundle="${rb}" />
										</option>
										<option value="9">
											<fmt:message key="placeholder.car.type.TENT" bundle="${rb}" />
										</option>
										<option value="10">
											<fmt:message key="placeholder.car.type.TRUCK" bundle="${rb}" />
										</option>
										<option value="11">
											<fmt:message key="placeholder.car.type.REFRIGERATOR" bundle="${rb}" />
										</option>
										<option value="12">
											<fmt:message key="placeholder.car.type.OTHERS" bundle="${rb}" />
										</option>

								</select>
							</td>
							<td><input type="number" name="carrying" step="0.1" value="${ elem.carryingCar }" disabled /></td>
							<td><input type="number" name="amount" step="0.1" value="${ elem.amountCar }" disabled /></td>
							<td><input type="number" name="engine" step="0.1" value="${ elem.enginePower }" disabled /></td>
							<td><input type="text" name="oldstatus" value="${ elem.statusCar }" disabled />
								<select hidden name="status"required ">
									<option selected value="1">
										<fmt:message key="placeholder.car.status.FREE" bundle="${rb}" />
									</option>
									<option value="2">
										<fmt:message key="placeholder.car.status.USED" bundle="${rb}" />
										</option>
									<option value="3">
										<fmt:message key="placeholder.car.status.BROKEN" bundle="${rb}" />
										</option>
								</select>
							</td>


							<td><input type="text" name="comments" value="${ elem.comments }" disabled /></td>
							<td>
								<a href="/WEB/controller?command=DELETECAR&id=${elem.id }">
									<fmt:message key="button.delete" bundle="${rb}" />
								</a>
							</td>
							<td><input type="checkbox" name="agree" onclick="updateForm(this.form)"></td>
							<td><input type="submit" name="change" value="<fmt:message key="button.change" bundle="${rb}" />" disabled></td>
						</form>
					</tr>
				</c:forEach>
			</table>
		</div>
	</c:if>
	<br />


	<%@ include file="/WEB-INF/jspf/logout.jspf"%>
	<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	<script src="/WEB/js/my.js"></script>
</body>
</html>