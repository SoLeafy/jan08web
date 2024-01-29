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
/* $(document).ready(function(){
	let del = ${row.del };
	$(".deleted").css('background-color', 'gray');
}); */
</script>
</head>
<body>
	<div class="wrap">
		<!-- container, app, wrap 등... 쓴다. -->
		<!-- menu -->
		<%@ include file="menu.jsp"%>
		<!-- 본문내용 -->
		<div class="main">
			<article>
				<h1>게시글 관리</h1>
				<table>
					<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>날짜</th>
						<th>방문자</th>
						<th>삭제</th>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
						<tr class="row${row.del }">
							<td>${row.no }</td>
							<td>${row.title }
							<c:if test="${row.comment ne 0 }">&ensp;<span>${row.comment }</span></c:if>
							</td>
							<td>${row.write }</td>
							<td>${row.date }</td>
							<td>${row.count }</td>
							<td>${row.del }</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
				totalCount : ${totalCount } /
				totalPage 수 : <c:set var="totalPage" value="${totalCount / 10 }"/>
				
				<fmt:parseNumber integerOnly="true" value="${totalPage }" var="totalPage"/>
				<c:if test="${totalCount % 10 gt 0 }">
					<c:set var="totalPage" value="${totalPage + 1 }"/>
				</c:if>
				<c:out value="${totalPage }"/>
				<!-- jstl로 변수 만들기 -->
				/ startPage : <c:set var="startPage" value="1"/>
				<c:if test="${page gt 5 }">
					<c:set var="startPage" value="${page - 5 }"/>
				</c:if>
				${startpage }
				/ endPage : <c:set var="endPage" value="${startPage + 9 }" /> ${endPage }
				<c:if test="${endPage gt totalPage }">
					<c:set var="startpage" value="${totalpage -10 }"/>
					<c:set var="endPage" value="${totalPage }"/>
				</c:if>
				/ page : ${page }
			</article>

			<div class="paging">
				<button onclick="paging(1)">〈〈</button>
				<button
				<c:if test="${page - 10 lt 1 }">disabled="disabled"</c:if>
				>〈</button>
				
				<c:forEach begin="${startPage }" end="${endPage }" var="p">
					<button
					<c:if test="${page eq p }">id="currentBtn"</c:if> 
					onclick="paging(${p})">${p }</button>
				</c:forEach>
				<button
				<c:if test="${page + 10 gt totalPage }">disabled="disabled"</c:if>
				onclick="paging(${page + 10})">〉</button>
				<button onclick="paging(${totalPage})">〉〉</button>
			</div>
		
		</div>
	</div>

	<script type="text/javascript">
		function paging(no){
			location.href="./board?page=" + no;
		}
	</script>
</body>
</html>