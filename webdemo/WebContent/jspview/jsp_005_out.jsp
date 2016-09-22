<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp_005_out</title>
</head>
<body>

	<%
		int x = Integer.parseInt(request.getParameter("x"));

		int y = Integer.parseInt(request.getParameter("y"));

		String ope = request.getParameter("ope");

		int sum = 0;

		switch (ope) {
		case "+":
			sum = x + y;
			break;
		case "-":
			sum = x - y;
			break;
		case "*":
			sum = x * y;
			break;
		case "/":
			sum = x / y;
			break;
		}

		out.print(x + ope + y + "=" + sum);
	%>

</body>
</html>