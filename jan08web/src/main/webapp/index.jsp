<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당근카페</title>
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/menu.css">
<script type="text/javascript" src="./js/menu.js"></script>
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
			<hr>
			<h1>당근카페</h1>
			<img id="homeImage" alt="홈" src="./img/chiikawa.jpg">
			
			<article>
				<h1>index</h1>
				<!-- DTO와 달리 map은 자바가 만듦. 싹 다 보여줄 수 있음. DTO는 이런 식으로 하면 주솟값만 보여줌. -->
				//서버에서 보내준 map : ${map }<br>
				
				<c:forEach items="${map }" var="i"> <!-- 변수명은 잡기 나름이지만, row라고 많이 돼있다. -->
					이름 : ${i.name }<br>
					나이 : ${i.age }<br>
					주소 : ${i.addr }<br>
				</c:forEach>
				<!-- 서버가 보내준 map이라는 걸 m이라는 변수에 저장. 편법이긴 하지만 쓸 일 있을 것? -->
				<c:set value="${map }" var="m" scope="page"></c:set> <!-- scope는 변수 page, session, request(응답 받은 객체. url에 쓴거) 형태로 저장. 쓸 수 있는데 안 적으면 page로. 많이 안 적는다. -->
				<c:out value="${m }"></c:out>
				<c:out value="${m[1]['addr'] }"></c:out>
				
				<%-- ${map } 이거는 서블릿에서 보내준거라 안되지만 --%>
				${m } <!-- 이거는 c:set으로 했기 때문에 써진다. -->
				
				<%-- ${sessionScope.m } --%> <!-- scope에 session이라고 쓰면 session 타입으로 쓸 수 있다... 다른 page로 넘어갈 수 있다. 로그인 안 한 session. 그거랑 별개... 이거는 쓰면 좀 위험할 것. -->
				
				<%-- <c:set value="포세이돈" var="mname" scope="session"/>
				<c:set value="poseidon" var="mid" scope="session"/> --%>
				<!-- 이런 거 쓰면 로그인 된 취급이 된다고 한다,,, 위험! -->
				
				<!-- 나중엔 session -> jwt (토큰이 날아옴. 가상의 토큰 값을 아이디로 쓰고 그 값으로 서로 주고받음) -->
				
				<c:if test="">
				
				</c:if>
				
				if() {
					참일 때 실행
				}
				
				<!-- else까지 있는 버전. when이 늘어나서 else if가 되는 것 같다. -->
				<c:choose>
					<c:when test=""></c:when>
					<c:when test=""></c:when>
					<c:when test=""></c:when>
					<c:when test=""></c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				
				스프링 --> 스프링 부트......(여기까지 jsp쓰고) 스프링부트 타임리프(이때 타임리프)
				Controller-servlet
				Service----로직
				Model -----DAO DTO
				XML --- 확장 html (컴퓨터랑 나랑 협약해서 쓰는 거로 바뀜.)
				
			</article>
				<%-- <article>
					
					<c:set var="number" value="105"/>
					<c:out value="${number }"/> <!-- el태그 적는 게 빠를 수도 있다 -->
					<br>
					<c:forEach begin="1" end="9" var="row" step="2">
						2 x ${row } = ${row * 2 }<br>
					</c:forEach>
					
					<c:if test="${number eq 105 }">
						number는 105입니다.<br>
						eq 11 == 5 같은 값?	false<br>
						ne 11 != 5 달라?	true<br>
						lt 11 ＜ 5 			false<br>
						gt 11 ＞ 5			true<br>
						le 11 ≤ 5			false<br>
						ge 11 ≥ 5			true<br>
						&&<br>
						||<br>
						empty<br>
					</c:if>
					<hr>
					1~45까지 짝수만 출력하세요.<br>
					
				</article>
				<article>
					<c:forEach begin="1" end="45" var="row">
						<c:if test="${row % 2 eq 0 }">
							${row }<br>
						</c:if>
					</c:forEach>
				</article>
				<article>
					<!-- 얘도 jsp 끌어오는 거. jstl -->
					<c:import url="menu.jsp"/>
					<!-- 바로 저 페이지로 보내버림. if문이라도 걸고 해야 -->
					<c:redirect url="./board"/>
					<br>
					<c:forEach begin="1" end="10" var="row" varStatus="s">
						${s.begin } /
						${s.end } / 스텝은 ${s.step }
					</c:forEach>
				</article> --%>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/> <!-- jstl이 있어야 쓸 수 있다. -->
		</footer>
	</div>
</body>
</html>