<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<!-- css 적용 안되니까 강제로 올리기 ?ver=0.12 바꿀 때마다 ?ver=0.13, ?ver=0.14, ... 이런 식으로 가면 된다? 마음대로 쓰라고..? -->
<link href="../css/admin.css?ver=0.13" rel="stylesheet"/> <!-- 파일위치 ..으로 최상단으로 올라가기 -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script src="../js/menu.js" type="text/javascript"></script>
</head>
<body>
<h1>관리자 페이지</h1>
	<!-- 2024-01-26 관리자 페이지 만들기 -->
	<!-- 어플 같은 것도 만들면, 관리자 페이지도 만들어줘야. -->
	<!-- 틀 -->
	<div class="wrap"> <!-- container, app, wrap 등... 쓴다. -->
		<!-- menu -->
		<%@ include file="menu.jsp" %>
		<!-- 본문내용 -->
		<div class="main">
			<!-- 이 페이지에 오는 모든 사람은 관리자인지 검사를 합니다.
			관리자인 경우 보여주고, 로그인하지 않았거나 일반 사용자는 로그인 페이지로 이동시킴. -->
			<article>
				
				<div class="info">
					왼쪽 메뉴를 선택하세요.
				</div>
				
			</article>
			
		</div>
	</div>
</body>
</html>