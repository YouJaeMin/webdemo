<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>usecookie</title>
</head>
<body>

	<p>${requestScope.str}</p>

	<p>
		<a href="deleteCookie">쿠키삭제</a>
	</p>


</body>
</html>