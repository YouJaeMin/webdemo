<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list</title>
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
</head>
<body>
	<div class="wrap">
		<p>
			<a href="writeForm.do">글쓰기</a>
		</p>

		<table>
			<tr>
				<th>num</th>
				<th>subject</th>
				<th>writer</th>
				<th>readconunt</th>
				<th>file</th>
			</tr>
			<c:forEach var="data" items="${requestScope.aList}">
				<tr>
					<td>${data.num}</td>
					<td><c:if test="${data.re_level>0}">
							<img src="../guestview/images/level.gif" height="0"
								width="${15*data.re_level}" />
							<img src="../guestview/images/re.gif" />
						</c:if> <a href="view.do?num=${data.num}"> ${data.subject} </a></td>
					<td>${data.writer}</td>
					<td>${data.readcount}</td>
					<td>${data.upload}</td>
				</tr>
			</c:forEach>

		</table>

		<div>
			<c:if test="${pdto.startPage > 1}">
				<span><a href="list.do?pageNum=${pdto.startPage - pdto.blockPage}">이전</a></span>
			</c:if>

			<c:forEach var="num" begin="${pdto.startPage }" end="${pdto.endPage}">
				<span><a href="list.do?pageNum=${num}">${num }</a></span>
			</c:forEach>

			<c:if test="${pdto.endPage < pdto.totalPage }">
				<span><a
					href="list.do?pageNum=${pdto.startPage + pdto.blockPage}">다음</a></span>
			</c:if>

		</div>

	</div>

</body>
</html>