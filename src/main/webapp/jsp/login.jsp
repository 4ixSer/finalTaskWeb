<%@ page language="java" contentType='text/html; charset=UTF-8'
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/jspf/taglib.jspf"%>
<%@ include file="/WEB-INF/jspf/title.jspf"%>
<body>
	<%@ include file="/WEB-INF/jspf/head.jspf"%>

	<c:set var="role" value="${userType }" />
	<c:choose>
		<c:when test="${fn:containsIgnoreCase(role, 'DRIVER')}">
			<jsp:forward page="/jsp/work/driverPage.jsp" />
		</c:when>
		<c:when test="${fn:containsIgnoreCase(role, 'DISPATCHER')}">
			<jsp:forward page="/jsp/work/dispatcherPage.jsp" />
		</c:when>
		<c:when test="${fn:containsIgnoreCase(role, 'ADMINISTRATOR')}">
			<jsp:forward page="/jsp/work/adminPage.jsp" />
		</c:when>
		<c:otherwise>

		</c:otherwise>
	</c:choose>

	<br />
	<br />
	<br />
	<br />
	<br />
	<br />
	<div class="container">
		<div class="row">

			<div class="col-md-offset-3 col-md-6">
				<c:if test="${not empty errorMessage}">
					<div class="alert alert-warning alert-dismissable">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true">&times;</button>
						${errorMessage}
					</div>
				</c:if>
				<form class="form-horizontal" name="loginForm" method="POST"
					action="/WEB/controller">
					<input type="hidden" name="command" value="LOGIN" />
					<div class="form-group">
						<input type="text" pattern="[A-Za-zА-Яа-яёЁ0-9]{5,20}"
							name="login" required class="form-control"
							placeholder="<fmt:message key="label.login" bundle="${rb}" />">
						<i class="fa fa-user"></i>
					</div>
					<div class="form-group help">
						<input type="password" class="form-control"
							placeholder="<fmt:message key="label.password"  bundle="${rb}"  />"
							name="password" pattern="[A-Za-zА-Яа-яёЁ0-9]{3,20}" required>
						<i class="fa fa-lock"></i>

					</div>

					<button type="submit" class="btn btn-default">
						<fmt:message key="button.login" bundle="${rb}" />
					</button>


				</form>
			</div>
		</div>
		<br />
		<br />
		<br />
		<br />
		<br />
		<br />
		<%@ include file="/WEB-INF/jspf/footer.jspf"%>
	</div>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.js"></script>
</body>
</html>