<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ page import="java.io.PrintWriter"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Error Page</title>
</head>
<body>
	<div>


		<c:if test="${not empty message}">
			<h3>${message}</h3>
		</c:if>




		Ошибка ${errorMessage}; <a href="/WEB"> Назад</a>
	</div>
</body>
</html>