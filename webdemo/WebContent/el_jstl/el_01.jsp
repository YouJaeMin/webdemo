<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>el_01</title>
</head>
<body>
<%-- 
	EL(Expression Language) : 표현 언어
	1. jsp스크립트를 대신해서 속성값들을 편리하게 출력할 수 있도록 제공되는 언어이다.
	2. ${}
 --%>
 	<%
		// page영역에 p1이름으로 page값이 저장된다.
		pageContext.setAttribute("p1", "page");
		// request영역에 p2이름으로 request값이 저장된다.
		request.setAttribute("p2", "request");
		// session영역에 p3이름으로 session값이 저장된다.
		session.setAttribute("p3", "session");
		// application영역에 p4이름으로 application값이 저장된다.
		application.setAttribute("p4", "application");
	%>
	
	<p>${pageScope.p1} / ${requestScope.p2} / ${sessionScope.p3} / ${applicationScope.p4}</p>
	
	<!-- 속성값을 가져올떄 영역을 명시하지 않으면
		page > request > session > application 순으로 검색을 한다. -->
	<p>${p3}</p>
	
	
	
</body>
</html>