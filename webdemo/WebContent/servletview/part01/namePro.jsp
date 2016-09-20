<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>namePro</title>
</head>
<body>
<!-- 스크립트릿(scriptlet) : 자바소스 -->

<%

	String fname  = (String) request.getAttribute("fname");
%>

<!-- expression(표현식) : 변수나 메소드를 호출해서 출력한다. -->
<p><%= fname %>님 회원입니다.</p>
</body>
</html>