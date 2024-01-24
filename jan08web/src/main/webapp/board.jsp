<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    <%
    // 자바 여는 곳 위에 여기여도 상관 없다.
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>당.샐.</title>
<link href="./css/index.css" rel="stylesheet"/>
<link href="./css/menu.css" rel="stylesheet"/>
<link href="./css/board.css" rel="stylesheet"/>
<script type="text/javascript" src="./js/menu.js"></script>
</head>
<body>
	<!-- jstl 쓰려고 자바 관련된 거 삭제 -->
	<%-- <%
	// 서블릿이 던져준 값을 잡기만 하는 것. 서블릿을 통과하는 게 아니라.
	List<BoardDTO> lists = (List<BoardDTO>) request.getAttribute("list"); // servlet get에서 이렇게 보내서.
	%> --%>
	
	<div class="container">
	<!-- 시멘틱 태그 -->
		<header>
			<%@ include file="menu.jsp" %>
		</header>
		<div class="main">
			<div  class="mainStyle">
				<!-- article에 안 적으면 메뉴(fixed) 밑으로 들어가서 안보임. -->
				<article>
					<%-- for문 연습해보기<br>
					<!-- list에 들어있으니 index가 0부터 시작함. -->
					<c:forEach items="${list }" var="e" varStatus="s">
						${e.no } / ${s.first } / ${s.last } / ${s.index } / ${s.count } / ${s.step }<br>
					</c:forEach> --%>
				</article>
			
				<article><c:choose><c:when test="${fn:length(list) gt 0}">
							<table>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>날짜</th>
									<th>읽음</th>
								</tr><!-- 서블릿이 주는 변수명: list --><c:forEach items="${list }" var="row">
								<tr>
									<td class="w1">${row.no }</td>
									<td class="title">
									<a href="./detail?page=${page }&no=${row.no }">${row.title }
									<c:if test="${row.comment ne 0 }">
									 &ensp;<span>${row.comment }</span>
									 </c:if></a>
									</td>
									<td class="w1">${row.write }</td>
									<td class="w2">${row.date }</td>
									<td class="w1">${row.count }</td>
								</tr></c:forEach>
							</table>
							<!-- 페이징. 원리를 알자. -->
							totalCount : ${totalCount } / 
							totalPage 수 : <c:set var="totalPage" value="${totalCount / 10 }"></c:set> <!-- (실수) -->
							
							<fmt:parseNumber integerOnly="true" value="${totalPage }" var="totalPage"/> <!-- 정수로 -->
							<c:if test="${totalCount % 10 gt 0 }">
								<c:set var="totalPage" value="${totalPage + 1 }"></c:set>
							</c:if>
							<c:out value="${totalPage }"/>
							<!-- jstl로 변수 만드는 중. 자바로 해도 된다. -->
							/ startPage : <c:set var="startPage" value="1"/> 
							<c:if test="${page gt 5 }">
								<c:set var="startPage" value="${page - 5 }" />
							</c:if>
							${startPage }
							/ endPage : <c:set var="endPage" value="${startPage + 9 }" /> ${endPage }
							<c:if test="${endPage gt totalPage }">
								<c:set var="startPage" value="${totalPage - 10 }"/>
								<c:set var="endPage" value="${totalPage }"/>
							</c:if>
							/ page : ${page }
							
							<div class="paging">
								<button onclick="paging(1)">‍🙋‍♂️</button>
								<button 
								<c:if test="${page - 10 lt 1 }">disabled="disabled"</c:if> 
								onclick="paging(${page - 10})">🥬</button>
								
								<c:forEach begin="${startPage }" end="${endPage }" var="p">
									<button 
									<c:if test="${page eq p }">id="currentBtn"</c:if> 
									onclick="paging(${p})">${p }</button>
								</c:forEach>
								<button 
								<c:if test="${page + 10 gt totalPage }">disabled="disabled"</c:if>
								onclick="paging(${page + 10})">🥕</button>
								<button onclick="paging(${totalPage})">💁‍♂️</button>
								
							</div>
							</c:when><c:otherwise>
							<h1>출력할 값이 없습니다.</h1></c:otherwise></c:choose>
							
							<c:if test="${sessionScope.mname ne null }">
								<button onclick="url('./write')">글쓰기</button>
							</c:if>
					<%-- ${sessionScope.mname }님 반갑습니다. --%> <!-- mname이라고 한다. 얘도 외우기. -->
				</article>
				<article>
					<%-- <fmt:requestEncoding value="UTF-8"/>
					<fmt:setLocale value="ko_kr"/>
					<fmt:formatNumber value="3.14" type="number"/>
					<fmt:parseNumber value="3.14" integerOnly="true"/>
					
					<c:set var="nowDate" value="<%=new Date() %>"/>
					${nowDate }
					<br>
					<fmt:formatDate type="time" value="${nowDate }"/><br>
					<fmt:formatDate type="date" value="${nowDate }"/><br>
					<fmt:formatDate type="both" value="${nowDate }"/><br>
					<fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${nowDate }"/><br>
					<fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${nowDate }"/><br>
					<fmt:formatDate type="both" dateStyle="long" timeStyle="long" value="${nowDate }"/><br>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${nowDate }" /> --%>
				</article>
				
				<!-- fn이랑 choose 많이 쓴다. -->
				<article>
					<%-- fn 이용해서 자료형 데이터 길이 뽑아내기
					${fn:length(list) }<br> --%>
				</article>
				
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
		
	</div>
	<script type="text/javascript">
		function paging(no) {
			location.href="./board?page=" + no; //혹시 몰라서 ./ 넣은 것.
			//location.href="board?page=" + no; <- /jan08web/ 처럼 프로젝트 이름이 껴있으면 이거 안됨.
		}
	</script>
	
</body>
</html>