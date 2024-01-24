<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 얘는 코어태그 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/menu.css">
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
.login{
	margin: 0 auto;
	width: 440px;
	min-height: 300px;
	background-color: #F79672;
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
	color: #F47243;
	background-color: white;
	font-size: 20px;
	font-weight: bold;
	border: dotted;
}

.login img{
	width: 100%;
}

.login h2{
	color: white;
	text-align: center;
}

#id_input, #pw_input{
	width: 420px;
	height: 30px;
	text-align: left;
	border-collapse: collapse;
	border-bottom: 1px solid #7DB53A;
	border-left-style: none;
	border-right-style: none;
	border-top-style: none;
	margin: 0 autopx;
	padding-top: 10px;
	display: flex;
	align-items: center;
}

</style>
<script type="text/javascript">
function err() {
	let errBox = document.querySelector("#errorMSG");
	errBox.innerHTML = "<marquee>올바른 id와 pw를 입력하세요.</marquee>";
	errBox.style.color = 'black';
	errBox.style.fontSize = "10pt";
}
</script>
</head>
<body>
	<div class="container">
		<header>
			<%-- <%@ include file="menu.jsp" %> --%>
			<jsp:include page="menu.jsp"></jsp:include>
			<!-- jsp:은 출력 결과만 화면에 나옴. -->
		</header>
		
		<div class="main">
			<div class="mainStyle">
				<article>
					<h1>login</h1>
					<c:if test="${param.error ne null}"> <!-- param에 error라는 글자가 있느냐 -->
						<script type="text/javascript">
							//alert("올바른 암호와 아이디를 입력하세요.");
						</script>
					</c:if>
					<!-- ${param.error }의 param은 el태그의 문법, url의 error를 갖다줌 -->
					<div class="login">
						<form action="./login" method="post">
							<img alt="login" src="./img/치이카와.jpg">
							<h2>귀여워지세요</h2>
							<input type="text" id="id_input" name="id" placeholder="아이디를 입력하세요">
							<input type="password" id="pw_input" name="pw" placeholder="비밀번호를 입력하세요">
							<button type="reset">지우기</button>
							<button type="submit">로그인</button>
							<div id="errorMSG"></div>
						</form>
						<a href="./join">성인큐트파티가입</a>
					</div>
					
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
	<c:if test="${param.error ne null }">
		<script type="text/javascript">
			err();
		</script>
	</c:if>
	
</body>
</html>