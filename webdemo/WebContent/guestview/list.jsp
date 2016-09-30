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
<script type="text/javascript">
	$(function() {
		$("[name=searchBtn]").bind("click", function() {
			$("form").attr("action", "list.do");
			$("form").submit();
		})

		var searchKey = "${pdto.searchKey}";
		var searchWord = "${pdto.searchWord}";

		switch (searchKey) {
		case "all":
			$("option[value=all]").prop("selected", "selected");
			searchWord = "";
			break;
		case "subject":
			$("option[value=subject]").prop("selected", "selected");
			break;
		case "writer":
			$("option[value=writer]").prop("selected", "selected");
			break;
		}

		$("input[name=searchWord]").val(searchWord);
	});
</script>
</head>
<body>
	<div class="wrap">
		<p>
			<a href="writeForm.do">글쓰기</a>
		</p>

		<form name="frm">
			<select name="searchKey">
				<option value="all">전체</option>
				<option value="subject">제목</option>
				<option value="writer">글쓴이</option>
			</select> <input type="text" name="searchWord" /> <input type="button"
				name="searchBtn" value="검색" />
		</form>

		<%-- <form name="frm">
			<select name="searchKey">
				<c:choose>
					<c:when test="${empty pdto.searchKey || pdto.searchKey=='all'} ">
						<option value="all" selected="selected">전체</option>
					</c:when>
					<c:otherwise>
						<option value="all">전체</option>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${pdto.searchKey=='subject'}">
						<option value="subject" selected="selected">제목</option>
					</c:when>
					<c:otherwise>
						<option value="subject">제목</option>
					</c:otherwise>
				</c:choose>

				<c:choose>
					<c:when test="${pdto.searchKey=='writer'}">
						<option value="writer" selected="selected">글쓴이</option>
					</c:when>
					<c:otherwise>
						<option value="writer">글쓴이</option>
					</c:otherwise>
				</c:choose>
			</select>

			<c:choose>
				<c:when test="${empty pdto.searchKey || pdto.searchKey=='all'}">
					<input type="text" name="searchWord" value="" />
				</c:when>
				<c:otherwise>
					<input type="text" name="searchWord" value="${pdto.searchWord}" />
				</c:otherwise>
			</c:choose>
			<input type="button" name="searchBtn" value="검색" />
		</form> --%>

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
						</c:if> 
						
<%-- 						<a href="view.do?num=${data.num}&pageNum=${pdto.currentPage}&searchKey=${pdto.searchKey}&searchWord=${pdto.searchWord}"> ${data.subject}</a> --%>
						<c:url var="link" value="view.do">
							<c:param name="num" value="${data.num}"/>
							<c:param name="pageNum" value="${pdto.currentPage}"/>
							<c:param name="searchKey" value="${pdto.searchKey}"/>
							<c:param name="searchWord" value="${pdto.searchWord}"/>
						</c:url>
						<a href="${link }">${data.subject}</a>

						</td>
						
						
						
					<td>${data.writer}</td>
					<td>${data.readcount}</td>


					<td><c:if test="${!empty data.upload}">
							<img src="../guestview/images/save.gif" />
						</c:if></td>
				</tr>
			</c:forEach>

		</table>

		<div>
			<c:if test="${pdto.startPage > 1}">
				<span><a
					href="list.do?pageNum=${pdto.startPage - pdto.blockPage}&searchKey=${pdto.searchKey}&searchWord=${pdto.searchWord}">이전</a></span>
			</c:if>

			<c:forEach var="num" begin="${pdto.startPage }" end="${pdto.endPage}">
				<span><a
					href="list.do?pageNum=${num}&searchKey=${pdto.searchKey}&searchWord=${pdto.searchWord}">${num }</a></span>
			</c:forEach>

			<c:if test="${pdto.endPage < pdto.totalPage }">
				<span><a
					href="list.do?pageNum=${pdto.startPage + pdto.blockPage}&searchKey=${pdto.searchKey}&searchWord=${pdto.searchWord}">다음</a></span>
			</c:if>

		</div>

	</div>

</body>
</html>