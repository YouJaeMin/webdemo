<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>jsp_008_directive_include_res</title>
</head>
<body>

	<%@ include file="jsp_008_directive_include.jsp" %>

	<%
		int x = 20;
		out.print(x+y);
	%>

</body>
</html>