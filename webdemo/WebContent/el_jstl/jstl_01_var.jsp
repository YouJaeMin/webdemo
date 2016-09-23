<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>jstl_01_var</title>
</head>
<body>

<!--  
	커스텀 태그 (customer tag)
	1. jsp 에서 사용할 수 있도록 사용자가 정의한 태그를 의미한다.
	2. jstl(JSP Standard Tag Library)은 jsp페이지에서 많이 사용되는 논리적인 판단,
	 반복문의 처리, 데이터 베이스 등의 처리를 위한 표준 커스텀 태그이다.
-->

<!-- 변수 선언 -->
<%-- int data = 10; --%>

<c:set var="data" value="10" />
<p>${data}</p>
<p><c:out value="${data}" /></p>

<!-- 변수 제거 -->
<c:remove var="data"/>
<p>data: ${data}</p>

<%
	int num = 20;
%>
<!-- 스크립트에서 선언된 변수를 표현언어로 출력할 수 없다. -->
<p>num: ${num}</p>
<p>num:<c:out value="<%=num%>"/></p>


<%
	request.setAttribute("st", "request");
	int age=5;
%>

<%-- 스크립트요소에 정의된 변수, 메소드를 out태그를 이용해서 출력 할때는 value속성에 <%= %>을 사용한다. --%>
<p>age : <c:out value="<%=age %>"/></p>
<p>st : <c:out value='<%=request.getAttribute("st") %>'/></p>


















</body>
</html>