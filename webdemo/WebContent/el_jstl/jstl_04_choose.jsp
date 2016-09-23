<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String color = new String("red");
		if (color.equals("red"))
			out.print("ff0000");
		else if (color.equals("green"))
			out.print("00ff00");
		else if (color.equals("blue"))
			out.print("0000ff");
		else
			out.print("other");
	%>
	<hr />
	
	<c:set var="color" value="red"/>
	
	<c:choose>
		<c:when test="${color=='red'}">
			<c:out value="ff0000"></c:out>
		</c:when>
		<c:when test="${color=='green'}">
			<c:out value="00ff00"></c:out>
		</c:when>
		<c:when test="${color=='blue'}">
			<c:out value="0000ff"></c:out>
		</c:when>
		
		<c:otherwise>
			<c:out value="other"></c:out>
		</c:otherwise>
		
	</c:choose>

	
</body>
</html>