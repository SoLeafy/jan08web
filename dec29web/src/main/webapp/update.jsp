<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
<!-- include libraries(jQuery, bootstrap) -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<link rel="stylesheet" type="text/css" href="./css/write.css">
<script type="text/javascript">
	function check() {
		//alert('!');
		var title = document.getElementsByName("title"); // title은 object Nodelist로 나옴. => title[0].value로 접근
		title = document.getElementById("title"); //title은 object HTMLInputElement로 나옴. => title.value로 접근
		//alert(title.value.length);
		if (title.value.length < 5) {
			alert("제목은 5글자 이상이어야 합니다.");
			title.focus();
			return false;
		}

		var content = document.getElementsByClassName("content"); // Elements <- 배열 요소
		//alert(content[0].value.length);
		if (content[0].value.length < 3) {
			alert("내용은 3글자 이상으로 적어주세요.");
			content[0].focus();
			return false;
		}

	}
</script>
</head>
<body>
<%@ include file="./menu.jsp" %>
	<%
	Map<String, Object> detail = (Map<String, Object>) request.getAttribute("detail");
	%>
	<h1>글 수정</h1>
	<!-- form : 글 제목, 글 내용-->
	<form action="./update" method="post">
		<!-- 나중에 또 servlet이 된다. 원래 writeAction.jsp였다 -->
		<input type="text" name="title" id="title"
			value="<%=detail.get("board_title")%>">
		<textarea id="summernote" name="content" class="content"><%=detail.get("board_content")%></textarea>
		<button type="submit" onclick="return check()">글쓰기</button>
		<input type="text" name="no" value="<%=detail.get("board_no")%>">
	</form>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#summernote').summernote({
				height: 600
			});
		});
	</script>
</body>
</html>