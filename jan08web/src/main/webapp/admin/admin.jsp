<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css" rel="stylesheet"/> <!-- 파일위치 ..으로 최상단으로 올라가기 -->
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
		<div class="menu">
		<nav>
			<ul>
				<li onclick="url('./members')"><i class="xi-users"></i> 회원 관리</li>
				<li onclick="url('./board')"><i class="xi-document"></i> 게시글 관리</li>
				<li onclick="url('./comments')"><i class="xi-comment"></i> 댓글 관리</li>
				<li onclick="url('./info')"><i class="xi-lock-o"></i> hachu님</li>
			</ul>
		</nav>
		</div>
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