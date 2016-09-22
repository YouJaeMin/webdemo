<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8">
<title>jsp_009_template.jsp</title>
<link rel="stylesheet" href="css/common.css" />
</head>
<body>
	<div class="wrap">
		<header> 
			<jsp:include page="jsp_009_top.jsp"></jsp:include>
		</header>
		<section class="main_left">

			<nav>
				<jsp:include page="jsp_009_menu.jsp"></jsp:include>
			</nav>
		</section>
		<section class="main_right">
			<%
				String pathName = request.getParameter("pathName");
				if(pathName == null){
					pathName = "jsp_009_red";
				}
				
				pathName += ".jsp";
				
			%>
			<jsp:include page="<%= pathName %>"></jsp:include>
		</section>
		
		<footer>
			<jsp:include page="jsp_009_bottom.jsp"></jsp:include>
		</footer>
		
	</div>

</body>
</html>