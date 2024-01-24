<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 얘는 코어태그 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/menu.css">
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
.login{
	margin: 0 auto;
	width: 440px;
	min-height: 300px;
	background-color: #7C9EB2;
	padding: 10px;
	box-sizing: border-box;
	text-align: center;
}

.login input{
	width: 100%;
	height: 30px;
	text-align: center;
	color: #52528C;
	margin-bottom: 10px;
	box-sizing: border-box;
}

.login button{
	width: 45%;
	height: 50px;
	color: #52528C;
	font-size: 20px;
	font-weight: bold;
}

</style>

</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp" %>
			<%-- <jsp:include page="menu.jsp"></jsp:include> --%>
			<!-- jsp:은 출력 결과만 화면에 나옴. -->
		</header>
		
		<div class="main">
			<div class="mainStyle">
				<article>
					로그아웃 하셨습니다.
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
	
</body>
</html>