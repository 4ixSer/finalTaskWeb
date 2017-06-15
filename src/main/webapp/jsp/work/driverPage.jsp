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
					<!--Registration of a new Request.-->
					<form align="center" name="formRequest" method="GET"
						action="/WEB/controller">
						<input type="hidden" name="command" value="ADDREQUEST" />
						<div class="form-group row">
							<%@ include file="/WEB-INF/jspf/typeCar.jspf"%>
							<%@ include file="/WEB-INF/jspf/characteristicsCar.jspf"%>
							<div class="col-xs-2">
								<button type="submit" class="btn btn-primary	">
									<i class="fa fa-plus" aria-hidden="true"> </i>
									<fmt:message key="button.add.request" bundle="${rb}" />
								</button>
							</div>
						</div>
						<div class="form-group row">
							<div class="col-xs-4"></div>
							<div class="col-xs-4">
								<input class="form-control" type="datetime-local" name="date"
									value="2017-04-10T20:37:40" />
							</div>
							<div class="col-xs-4"></div>

						</div>

					</form>
				</div>
			</div>
			<hr>




			<div class="container">
				<div class="row">
					<div>
						<!--Search for your Request.-->
						<form align="center" name="findAllREQForm" method="POST"
							action="/WEB/controller">
							<input type="hidden" name="command" value="FINDREQUESTBYUSERID" />
							<button type="submit" class="btn btn-primary">
								<i class="fa fa-search" aria-hidden="true"></i>
								<fmt:message key="button.find.user.request" bundle="${rb}" />
							</button>
						</form>
					</div>

					<c:if test="${not empty allRequest}">
						<div>

							<div align="right">
								<a class="btn btn-warning"
									href="/WEB/controller?command=CLOSE&table=allRequest"> <i
									class="fa fa-times" aria-hidden="true"> </i></a>
							</div>

							<!--Request table-->
							<table class="table table-bordered">
								<tr align="center">
									<th><fmt:message key="placeholder.id" bundle="${rb}" /> <a
										href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=allRequest">&#11015</a>
									</th>
									<th><fmt:message key="placeholder.request.date.request"
											bundle="${rb}" /><a
										href="/WEB/controller?command=SORT&typeSort=SORTBYDATEREQUEST&object=allRequest">&#11015</a></th>
									<th><fmt:message key="placeholder.request.date.departure"
											bundle="${rb}" /><a
										href="/WEB/controller?command=SORT&typeSort=SORTBYDATEDEPARTURE&object=allRequest">&#11015</a></th>
									<th><fmt:message key="placeholder.car.type" bundle="${rb}" /><a
										href="/WEB/controller?command=SORT&typeSort=SORTBYTYPE&object=allRequest">&#11015</a>
									</th>
									<th><fmt:message key="placeholder.car.carrying"
											bundle="${rb}" /><a
										href="/WEB/controller?command=SORT&typeSort=SORTBYCARRYING&object=allRequest">&#11015</a></th>
									<th><fmt:message key="placeholder.car.amount"
											bundle="${rb}" /><a
										href="/WEB/controller?command=SORT&typeSort=SORTBYAMOUNT&object=allRequest">&#11015</a>
									</th>
									<th><fmt:message key="placeholder.car.power"
											bundle="${rb}" /><a
										href="/WEB/controller?command=SORT&typeSort=SORTBYENGINEPOWER&object=allRequest">&#11015</a>
									</th>
									<th><fmt:message key="placeholder.car.status"
											bundle="${rb}" /><a
										href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=allRequest">&#11015</a>
									</th>
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
										<td><c:out value="${ elem.status }" /> <c:set
												var="status" value="${ elem.status }" /></td>
										<c:if test="${not fn:containsIgnoreCase(status, 'PROCESSED')}">


											<td><a class="btn btn-warning"
												href="/WEB/controller?command=CANCELREQUEST&id=${elem.namberRequest }"><i
													class="fa fa-minus" aria-hidden="true"></i> <fmt:message
														key="button.cancel" bundle="${rb}" /></a></td>
										</c:if>
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
						<!--Search all flights.-->
						<form align="center" name="findFlight" method="POST"
							action="/WEB/controller">
							<input type="hidden" name="command" value="FINDUSERFLIGHT" />
							<button type="submit" class="btn btn-primary">
								<i class="fa fa-search" aria-hidden="true"></i>
								<fmt:message key="button.find.user.flight" bundle="${rb}" />
							</button>
						</form>
					</div>

					<c:if test="${not empty allFlight}">
						<table class="table">

							<div align="right">
								<a class="btn btn-warning"
									href="/WEB/controller?command=CLOSE&table=allFlight"> <i
									class="fa fa-times" aria-hidden="true"> </i>
								</a>
							</div>
							<tr>
								<th><fmt:message key="placeholder.id" bundle="${rb}" /><a
									href="/WEB/controller?command=SORT&typeSort=SORTBYID&object=allFlight">&#11015</a></th>
								<th><fmt:message key="placeholder.request.date.departure"
										bundle="${rb}" /><a
									href="/WEB/controller?command=SORT&typeSort=SORTBYDATE&object=allFlight">&#11015</a></th>
								<th><fmt:message key="placeholder.car.status"
										bundle="${rb}" /><a
									href="/WEB/controller?command=SORT&typeSort=SORTBYSTATUS&object=allFlight">&#11015</a></th>
								<th><fmt:message key="placeholder.dispatcher"
										bundle="${rb}" /></th>
								<th><fmt:message key="placeholder.car" bundle="${rb}" /></th>
								<th><fmt:message key="placeholder.comment" bundle="${rb}" /></th>
							</tr>

							<c:forEach var="elem" items="${allFlight}">
								<tr align="center">
									<form align="center" name="loginForm" method="POST"
										action="/WEB/controller">
										<input type="hidden" name="command" value="UPDATEFLIGHT" /> <input
											type="hidden" name="id" value="${ elem.namberFlight }" />
										<td><c:out value="${ elem.namberFlight }" /></td>
										<td><c:out value="${ elem.date }" /></td>
										<td><c:out value="${ elem.status }" /> <c:set
												var="status" value="${ elem.status }" /></td>
										<td><c:out value="${ elem.dispatcher }" /></td>
										<td><c:out value="${ elem.car }" /></td>
										<td><c:out value="${ elem.note }" /></td>
										<c:if test="${not fn:containsIgnoreCase(status, 'CLOSED')}">
											<td><input type="checkbox" name="agree"
												onclick="formDriver(this.form)" /></td>
											<td><input type="text" name="comments"
												placeholder="<fmt:message key="placeholder.comment" bundle="${rb}" />"
												hidden required /></td>
											<td><select name="status" hidden required>
													<option selected value="1">
														<fmt:message key="placeholder.car.status.FREE"
															bundle="${rb}" />
													</option>
													<option value="3">
														<fmt:message key="placeholder.car.status.BROKEN"
															bundle="${rb}" /></option>
											</select></td>
											<td><button class="btn btn-primary" type="submit"
												name="change" disabled>
												<fmt:message key="button.flight.complete" bundle="${rb}" />
											</button></td>
										</c:if>

									</form>
								</tr>
							</c:forEach>
						</table>
					</c:if>
				</div>
			</div>
			<hr />

			<%@ include file="/WEB-INF/jspf/logout.jspf"%>
			<%@ include file="/WEB-INF/jspf/footer.jspf"%>
		</div>
	</div>
	<script src="/WEB/js/my.js"></script>
</body>
</html>