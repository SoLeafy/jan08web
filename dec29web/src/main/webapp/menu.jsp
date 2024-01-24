<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<style>
body {
	margin: 0;
	padding: 0;
}
.navi {
	width: 100%;
	height: 50px;
	text-align: center;
	background-color: black;
}

.menu {
	margin: 0 auto;
	width: auto;
	height: 50px;
	text-align: center;
}

.menu li {
	width: 100px;
	height: 50px;
	display: inline-block;
	text-align: center;
	color: white;
	line-height: 50px;
}

.menu li:hover {
	background-color: gray;
	cursor: pointer;
}
</style>

<div>
	<nav class="navi">
		<ul class="menu">
			<li onclick="url('./')"><i class="xi-apple"></i></li>
			<li onclick="url('./board')"><i class="xi-shop"></i> 게시판</li>
			<li onclick="url('./notice')"><i class="xi-compass"></i> 문의사항</li>
			<li onclick="url('./login')"><i class="xi-gamepad"></i> 로그인</li>
		</ul>
	</nav>
</div>
<script>
	function url(url) {location.href=url;}
</script>