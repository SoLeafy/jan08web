<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css?ver=0.13" rel="stylesheet" />
<link href="../css/board.css" rel="stylesheet" />
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script src="../js/menu.js" type="text/javascript"></script>
<script 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
$(function(){
	
});
</script>
<style type="text/css">
.xi-eye-o, .xi-eye-off-o:hover {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="wrap">
		<!-- container, app, wrap 등... 쓴다. -->
		<!-- menu -->
		<%@ include file="menu.jsp"%>
		<!-- 본문내용 -->
		<div class="main">
			<article>
				<h1>댓글 관리</h1>
			</article>
			<div class="search">
					<input type="text" id="search"><button id="searchBtn">🔍</button>
				</div>
				<table>
					<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
						<th>좋아요</th>
						<th>삭제</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
						<tr class="row">
							<td class="d1">${row.cno }</td>
							<td class="title">
							<a href="../detail?no=${row.board_no }">
							${row.comment }
							</a>
							</td>
							<td class="d2">${row.mname }</td>
							<td class="d2">${row.cdate }</td>
							<td class="d1">${row.clike }</td>
							<td class="d1"></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
		</div>
	</div>
</body>
</html>