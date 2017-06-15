<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/title.jspf"%>
<body>

	<%@ include file="/WEB-INF/jspf/head.jspf"%>

	<div class="container">
		<div class="row">

			<%@ include file="/WEB-INF/jspf/welcome.jspf"%>


			<div class="container">
				<div class="row">
					<!-- User registration form  -->
					<form align="center" name="loginForm" method="POST"
						action="/WEB/controller">
						<input type="hidden" name="command" value="REGISTERINGUSER" />
						<div class="form-group row">
							<div class="col-xs-2">
								<input class="form-control" type="text" name="login"
									pattern="[A-Za-zА-Яа-яёЁ0-9]{5,20}"
									placeholder="<fmt:message key="placeholder.user.login" bundle="${rb}" />"
									required title="5 и более символов A-Za-zА-Яа-яёЁ0-9" />
							</div>
							<div class="col-xs-2">
								<input class="form-control" type="password" name="password"
									pattern="[A-Za-zА-Яа-яёЁ0-9]{3,20}"
									placeholder="<fmt:message key="placeholder.user.password" bundle="${rb}" />"
									required />
							</div>
							<div class="col-xs-2">
								<input class="form-control" type="email" name="email"
									placeholder="<fmt:message key="placeholder.user.email" bundle="${rb}" />"
									required />
							</div>
							<div class="col-xs-2">
								<input class="form-control" type="text" name="name"
									pattern="[A-Za-zА-Яа-яёЁ0-9]{2,20}"
									placeholder="<fmt:message key="placeholder.user.name" bundle="${rb}" />"
									required />
							</div>
							<div class="col-xs-2">
								<select class="form-control" name="role" required>
									<option value="1">
										<fmt:message key="placeholder.user.type.admin" bundle="${rb}" />
									</option>
									<option value="2">
										<fmt:message key="placeholder.user.type.dispatcher"
											bundle="${rb}" />
									</option>
									<option selected value="3">
										<fmt:message key="placeholder.user.type.driver" bundle="${rb}" />
									</option>
								</select>
							</div>
							<button type="submit" class="btn btn-primary">
								<fmt:message key="button.registration.user" bundle="${rb}" />
							</button>
						</div>
					</form>
					<br />
				</div>
			</div>
			<hr>

			<div class="container">
				<div class="row">
					<!-- The form of registration of new cars.  -->
					<form align="center" name="loginForm" method="POST"
						action="/WEB/controller">
						<input type="hidden" name="command" value="ADDCAR" />
						<div class="form-group row">
							<div class="col-xs-2">
								<input class="form-control" type="text" name="namber"
									pattern="[A-Z]{2}[0-9]{4}[A-Z]{2}" required
									placeholder="<fmt:message key="placeholder.car.number" bundle="${rb}" />" />
							</div>

							<%@ include file="/WEB-INF/jspf/typeCar.jspf"%>
							<%@ include file="/WEB-INF/jspf/characteristicsCar.jspf"%>
						</div>
						<div class="form-group row">
							<div class="col-xs-2"></div>
							<div class="col-xs-2">
								<select class="form-control" name="status" required>
									<option selected value="1">
										<fmt:message key="placeholder.car.status.FREE" bundle="${rb}" />
									</option>
									<option value="2">
										<fmt:message key="placeholder.car.status.USED" bundle="${rb}" />
									</option>
									<option value="3">
										<fmt:message key="placeholder.car.status.BROKEN"
											bundle="${rb}" />
									</option>
								</select>
							</div>
							<div class="col-xs-4"></div>
							<div class="col-xs-2">
								<button type="submit" class="btn btn-primary">
									<fmt:message key="button.registration.car" bundle="${rb}" />
								</button>
							</div>
						</div>

					</form>
				</div>
			</div>
		</div>
		<hr>
		<div class="container">
			<div class="row">
				<div>
					<!--Search all users.-->
					<form align="center" name="findAllForm" method="POST"
						action="/WEB/controller">
						<input type="hidden" name="command" value="FINDALLUSERS" />
						<button type="submit" class="btn btn-primary">
							<fmt:message key="button.find.all.user" bundle="${rb}" />
						</button>

					</form>
				</div>


				<c:if test="${not empty users}">
					<!--Close tables.-->
					<div align="right">
						<a class="btn btn-warning"
							href="/WEB/controller?command=CLOSE&table=users"> <i
							class="fa fa-times" aria-hidden="true"> </i></a>
					</div>


					<div class="container">
						<!--Table for users -->
						<table class="table table-bordered">
							<tr>
								<th></th>
								<th>ID<a
									href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=users">&#11015</a></th>
								<th><fmt:message key="placeholder.user.login"
										bundle="${rb}" /><a
									href="/WEB/controller?command=SORT&typeSort=SORTBYLOGIN&object=users">&#11015</a></th>
								<th><fmt:message key="placeholder.user.name" bundle="${rb}" />
									<a
									href="/WEB/controller?command=SORT&typeSort=SORTBYNAME&object=users">&#11015</a></th>
								<th><fmt:message key="placeholder.user.email"
										bundle="${rb}" /><a
									href="/WEB/controller?command=SORT&typeSort=SORTBYEMAIL&object=users">&#11015</a></th>
								<th><fmt:message key="placeholder.user.role" bundle="${rb}" /><a
									href="/WEB/controller?command=SORT&typeSort=SORTBYROLE&object=users">&#11015</a></th>

								<th></th>
							</tr>
							<c:forEach var="elem" items="${users}">
								<tr>
									<form name="loginForm" method="POST" action="/WEB/controller">
										<input type="hidden" name="command" value="UPDATEUSER" />
										<td><input type="checkbox" name="agree"
											onclick="agreeForm(this.form)" /></td>
										<td><c:out value="${ elem.id }" /> <input type="hidden"
											name="id" value="${ elem.id }" /></td>
										<td><c:out value="${ elem.login }" /></td>
										<td><input class="form-control" type="text" name="name"
											pattern="[A-Za-zА-Яа-яёЁ0-9]{2,20}" value="${ elem.name }"
											disabled /></td>
										<td><input class="form-control" type="email" name="email"
											value="${ elem.email }" disabled /></td>
										<td><c:out value="${ elem.type }" /></td>


										<td>
											<button class="btn btn-primary" type="submit" name="change"
												disabled>
												<fmt:message key="button.change" bundle="${rb}" />
											</button>
										</td>
										<td><a class="btn btn-warning"
											href="/WEB/controller?command=DELETEUSER&id=${elem.id }">
												<i class="fa fa-trash-o" aria-hidden="true"></i> <fmt:message key="button.delete" bundle="${rb}" />
										</a></td>
									</form>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:if>
			</div>
		</div>
		<hr>

		<div class="container">
			<div class="row">
				<div>
					<!--Search for all machines.-->
					<form align="center" name="findAllCarForm" method="POST"
						action="/WEB/controller">
						<input type="hidden" name="command" value="FINDALLCARS" />
						<button type="submit" class="btn btn-primary">
							<fmt:message key="button.find.all.car" bundle="${rb}" />
						</button>
					</form>

				</div>

				<div class="container">
					<c:if test="${not empty cars}">

						<div align="right">
							<a class="btn btn-warning"
								href="/WEB/controller?command=CLOSE&table=cars"> <i
								class="fa fa-times" aria-hidden="true"> </i></a>
						</div>
						<!--Table for machines.-->
						<table class="table table-bordered">
							<tr>
								<th></th>
								<th>ID<a
									href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=cars">&#11015</a></th>
								<th><fmt:message key="placeholder.car.number"
										bundle="${rb}" /> <a
									href="/WEB/controller?command=SORT&typeSort=SORTBYNAMBER&object=cars">&#11015</a></th>
								<th><fmt:message key="placeholder.car.type" bundle="${rb}" />
									<a
									href="/WEB/controller?command=SORT&typeSort=SORTBYTYPE&object=cars">&#11015</a></th>
								<th><fmt:message key="placeholder.car.carrying"
										bundle="${rb}" /> <a
									href="/WEB/controller?command=SORT&typeSort=SORTBYCARRYING&object=cars">&#11015</a></th>
								<th><fmt:message key="placeholder.car.amount"
										bundle="${rb}" /> <a
									href="/WEB/controller?command=SORT&typeSort=SORTBYAMOUNT&object=cars">&#11015</a></th>
								<th><fmt:message key="placeholder.car.power" bundle="${rb}" />
									<a
									href="/WEB/controller?command=SORT&typeSort=SORTBYENGINE&object=cars">&#11015</a></th>
								<th><fmt:message key="placeholder.car.status"
										bundle="${rb}" /> <a
									href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=cars">&#11015</a></th>
								<th><fmt:message key="placeholder.comment" bundle="${rb}" /></th>
							</tr>

							<c:forEach var="elem" items="${cars}">
								<tr align="center">
									<form align="center" name="loginForm" method="POST" action="/WEB/controller">
										<input type="hidden" name="command" value="UPDATECAR" />
										<td>
											<input type="checkbox" name="agree" onclick="updateForm(this.form)">
										</td>
										<td>
											<c:out value="${ elem.id }" />
											 <input type="hidden" name="id" value="${ elem.id }" />
										</td>
										<td>
											<input class="form-control" type="text" name="namber" pattern="[A-Z]{2}[0-9]{4}[A-Z]{2}" value="${ elem.namber }" disabled />
										</td>
										<td>
											<input type="text" name="oldtype" value="${ elem.type }" disabled size=8/>
											<select  hidden name="type" required>
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
										<td>
											<input class="form-control" type="number" name="carrying" step="0.1" value="${ elem.carryingCar }" disabled />
										</td>
										<td>
											<input class="form-control" type="number" name="amount" step="0.1" value="${ elem.amountCar }" disabled />
										</td>
										<td>
											<input class="form-control" type="number" name="engine" step="0.1" value="${ elem.enginePower }" disabled />
										</td>
										<td>

											<input type="text" name="oldstatus" value="${ elem.statusCar }" disabled size=4/>
											<select hidden name="status" required>
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
									<td>
										<input class="form-control" type="text" name="comments" value="${ elem.comments }" disabled />
									</td>


									<td>
										<button class="btn btn-primary" type="submit" name="change" disabled> <fmt:message key="button.change" bundle="${rb}" /></button>
									</td>
									<td>
										<a class="btn btn-warning" href="/WEB/controller?command=DELETECAR&id=${elem.id }">
											<i class="fa fa-trash-o" aria-hidden="true"></i> <fmt:message key="button.delete" bundle="${rb}" />
										</a>
									</td>
								</form>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
				<hr />
				<%@ include file="/WEB-INF/jspf/administrator.jspf"%>
			</div>


			<div>
					<!--Search for all machines.-->
					<form align="center" name="findAllCarForm" method="POST"
						action="/WEB/controller">
						<input type="hidden" name="command" value="AVG" />
						<button type="submit" class="btn btn-primary">
							Найти среднее значение объема сломанных машин
						</button>
					</form>


	</div>
	<div>
		<c:if test="${not empty AVG}">

		<p>Cреднее значение объема сломанных машин =  <c:out value=" ${AVG}" /></p>

		</c:if>
	</div>
	</hr>
			<%@ include file="/WEB-INF/jspf/logout.jspf"%>
			<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		</div>
	</div>





	<script src="/WEB/js/my.js"></script>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.js"></script>
</body>
</html>