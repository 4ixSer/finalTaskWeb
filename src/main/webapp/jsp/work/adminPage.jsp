<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="pagecontent" var="rb" />
<!DOCTYPE html>
<head>
<title><fmt:message key="label.title.admin" bundle="${rb}" /></title>
<script>
function agreeForm(f) {
    // Если поставлен флажок, снимаем блокирование кнопки
   if (f.agree.checked) {

	   f.email.disabled = 0;
	   f.name.disabled = 0;
	   f.change.disabled = 0;

   }
    // В противном случае вновь блокируем кнопку
    else {
  	   f.email.disabled = 1;
 	   f.name.disabled = 1;
 	  f.change.disabled = 1;
    }

}


function updateForm(f) {
    // Если поставлен флажок, снимаем блокирование кнопки
   if (f.agree.checked) {
	   f.namber.disabled = 0;
	   f.carrying.disabled = 0;
	   f.amount.disabled = 0;
	   f.engine.disabled = 0;
	   f.comments.disabled = 0;
	   f.oldtype.hidden = 1;
	   f.newtype.hidden = 0;
	   f.oldstatus.hidden = 1;
	   f.newstatus.hidden = 0;
	   f.change.disabled = 0;
   }
    // В противном случае вновь блокируем кнопку
    else {
    	f.namber.disabled = 1;
		f.carrying.disabled = 1;
  	   	f.amount.disabled = 1;
  	   	f.engine.disabled = 1;
  	   	f.comments.disabled = 1;
  	   	f.oldtype.hidden = 0;
  	 	f.newtype.hidden = 1;
  	 	f.oldstatus.hidden = 0;
  	 	f.newstatus.hidden = 1;
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
	${Message} <br>
		<!-- регистрация пользователей  -->
	<form align="center" name="loginForm" method="POST" action="/WEB/controller" >
		<input type="hidden" name="command"  value="REGISTERINGUSER" />
		<input type="text" name="login" pattern="[A-Za-zА-Яа-яёЁ0-9]{5,20}" placeholder="login" required title="5 и более символов A-Za-zА-Яа-яёЁ0-9" />
		<input type="password" name="password" pattern="[A-Za-zА-Яа-яёЁ0-9]{3,20}" placeholder="password" required />
		<input type="email" name="email"	placeholder="Email" required />
		<input type="text" name="name"  pattern="[A-Za-zА-Яа-яёЁ0-9]{2,20}" placeholder="name" required />
		<select	name="role" required ">
			<option value="1"> <fmt:message key="label.admin" bundle="${rb}" /> </option>
			<option value="2"> <fmt:message key="label.dispatcher" bundle="${rb}" /></option>
			<option selected value="3"> <fmt:message key="label.driver" bundle="${rb}" /></option>
		</select> <input type="submit" value="<fmt:message key="menu.register.user" bundle="${rb}" />">
		<p align="center">

		</p>
	</form>
	<br />


	<!-- регистарция новых автомобилей  -->
	<form align="center" name="loginForm" method="POST" action="/WEB/controller" >
		<input type="hidden" name="command" value="ADDCAR" />
		<input type="text" name="namber"  pattern="[A-Z]{2}[0-9]{4}[A-Z]{2}" placeholder="namber" />

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

		<input type="number"  name="carrying" step="0.1" placeholder="Carrying" />
		<input type="number" name="amount" step="0.1"	placeholder="Amount" />
		<input type="number" name="engine" step="0.1" placeholder="Engine" />
		<input type="text" name="comments" placeholder="Comments" />

		<select
			name="statusCar" required ">
			<option selected  value="1"> <fmt:message key="car.status.FREE" bundle="${rb}" /> </option>
			<option value="2"> <fmt:message key="car.status.USED" bundle="${rb}" /></option>
			<option value="3"> <fmt:message key="car.status.BROKEN" bundle="${rb}" /></option>
		</select>

		<input type="submit" value="<fmt:message key="menu.register.car" bundle="${rb}" />">
		<p align="center">
			${Message2} <br>
		</p>
	</form>
	<br />

		<!--Запрос на поиск всех юзеров   -->
	<form align="center" name="findAllForm" method="POST"
		action="/WEB/controller"">
		<input type="hidden" name="command" value="FINDALLUSERS" />

		<input	type="submit" value="<fmt:message key="menu.find.user" bundle="${rb}" />">
	</form>

	<br />
	<c:if test="${not empty users}">
		<div>
			<a  href="/WEB/controller?command=CLOSE&table=users">	&times;</a>
				<!--Таблица дял отрисовки юзеров   -->
			<table align="center">
				<tr>
					<th>ID <a href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=users">&#11015</a></th>
					<th>Login <a href="/WEB/controller?command=SORT&typeSort=SORTBYLOGIN&object=users">&#11015</a></th>
					<th>Name <a href="/WEB/controller?command=SORT&typeSort=SORTBYNAME&object=users">&#11015</a></th>
					<th>Email <a href="/WEB/controller?command=SORT&typeSort=SORTBYEMAIL&object=users">&#11015</a></th>
					<th>Role<a href="/WEB/controller?command=SORT&typeSort=SORTBYROLE&object=users">&#11015</a></th>
				</tr>

				<c:forEach var="elem" items="${users}" varStatus="status">
					<tr align="center">
						<form align="center" name="loginForm" method="POST" action="/WEB/controller" >
							<input type="hidden" name="command"  value="UPDATEUSER" />
							<td><c:out value="${ elem.id }" /> <input type="hidden" name="id"  value="${ elem.id }" /></td>
							<td><c:out value="${ elem.login }" />   </td>
							<td><input type="text" name="name"  pattern="[A-Za-zА-Яа-яёЁ0-9]{2,20}"  value="${ elem.name }" disabled /> </td>
							<td><input type="email" name="email"	  value="${ elem.email }" disabled /> </td>
							<td><c:out value="${ elem.type }" />  </td>
							<td><a href="/WEB/controller?command=DELETEUSER&id=${elem.id }"><fmt:message key="label.delete" bundle="${rb}" /></a></td>
							<td><input type="checkbox" name="agree" onclick="agreeForm(this.form)"></td>
							<td><input type="submit" name="change" value="Изменить" disabled></td>
						</form>
					</tr>
				</c:forEach>

			</table>
		</div>
	</c:if>
	<br />

		<!--Запрос на поиск машин   -->
	<form align="center" name="findAllCarForm" method="POST" action="/WEB/controller">
		<input type="hidden" name="command" value="FINDALLCARS" />
		<input type="submit" value="<fmt:message key="menu.find.car" bundle="${rb}" />">
	</form>
	<br />
	<c:if test="${not empty cars}">
		<div>
			<a  href="/WEB/controller?command=CLOSE&table=cars">	&times;</a>
			<!--Таблица дял отрисовки машин   -->
			<table align="center">
				<tr>
					<th>ID <a href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=cars">&#11015</a></th>
					<th><fmt:message key="car.Namber" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYNAMBER&object=cars">&#11015</a></th>
					<th><fmt:message key="car.Type" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYTYPE&object=cars">&#11015</a></th>
					<th><fmt:message key="car.Carrying" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYCARRYING&object=cars">&#11015</a></th>
					<th><fmt:message key="car.Amount" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYAMOUNT&object=cars">&#11015</a></th>
					<th><fmt:message key="car.EnginePower" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYENGINE&object=cars">&#11015</a></th>
					<th><fmt:message key="car.Statuc" bundle="${rb}" /> <a href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=cars">&#11015</a></th>
					<th><fmt:message key="car.Comments" bundle="${rb}" /></th>
				</tr>

				<c:forEach var="elem" items="${cars}" varStatus="status">
					<tr align="center">
						<form align="center" name="loginForm" method="POST" action="/WEB/controller" >
							<input type="hidden" name="command"  value="UPDATECAR" />

							<td><c:out value="${ elem.id }" /> <input type="hidden" name="id"  value="${ elem.id }" /></td>
							<td><input type="text" name="namber"  pattern="[A-Z]{2}[0-9]{4}[A-Z]{2}"  value="${ elem.namber }"  disabled /> </td>
							<td > <input type="text"  name="oldtype"  value="${ elem.type }"  disabled />
								<select hidden name="newtype"  required ">
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
							</td>
							<td><input type="number"  name="carrying" step="0.1" value="${ elem.carryingCar }"  disabled /></td>
							<td><input type="number" name="amount" step="0.1"	value="${ elem.amountCar }"  disabled/></td>
							<td><input type="number" name="engine" step="0.1" value="${ elem.enginePower }" disabled /></td>
							<td > <input type="text"  name="oldstatus"  value="${ elem.statusCar }"  disabled />
								<select hidden name="newstatus" required ">
									<option selected  value="1"> <fmt:message key="car.status.FREE" bundle="${rb}" /> </option>
									<option value="2"> <fmt:message key="car.status.USED" bundle="${rb}" /></option>
									<option value="3"> <fmt:message key="car.status.BROKEN" bundle="${rb}" /></option>
								</select>
							</td>


							<td><input type="text" name="comments"  value="${ elem.comments }" disabled /></td>
							<td><a href="/WEB/controller?command=DELETECAR&id=${elem.id }"><fmt:message key="label.delete" bundle="${rb}" /></a></td>
							<td><input type="checkbox" name="agree" onclick="updateForm(this.form)"></td>
							<td><input type="submit" name="change" value="Изменить" disabled></td>
						</form>
					</tr>
				</c:forEach>

			</table>
		</div>
	</c:if>
	<br />

	<!-- logout -->
	<p align="right">
		<a href="/WEB/controller?command=LOGOUT"><fmt:message key="label.logout" bundle="${rb}" /></a>
	</p>
	<%@ include file="/jsp/footer.jspf"%>
</body>
</html>