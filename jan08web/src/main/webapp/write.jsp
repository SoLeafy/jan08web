<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/menu.css">
<script type="text/javascript" src="./js/menu.js"></script>
<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<style type="text/css">
#title{
	width: 100%;
	height: 30px;
	margin-bottom: 10px;
}
</style>
</head>
<body>
	<div class="container1">
		<header>
			<%@ include file="menu.jsp" %>
			<%-- <jsp:include page="menu.jsp"></jsp:include> --%>
			<!-- jsp:은 출력 결과만 화면에 나옴. -->
		</header>
		
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>글쓰기</h1>
					<div class="writeFORM">
					<!-- post는 숨김 방식 -->
						<form action="./write" method="post">
						<!-- 자바는 name만 갖다 쓸 수 있다! 자바스크립트는 id까지 양쪽다 -->
							<input type="text" id="title" name="title">
							<textarea id="summernote" name="content"></textarea>
							<button type="submit" onclick="return check()">저장하기</button>
						</form>
					</div>
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
		  $('#summernote').summernote({
			  height:500
		  });
		});
		
		function check() {
			let title = document.querySelector("#title");
			let content = document.querySelector("#summernote");
			if(title.value.length < 2) {
				alert("제목은 2글자 이상 써주세요");
				return false;
			}
			
			if(content.value.length < 1) {
				alert("내용을 써주세요");
				return false;
			}
		}
	</script>
</body>
</html>