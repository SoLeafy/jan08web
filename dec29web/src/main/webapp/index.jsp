<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.poseidon.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index</title>

<style type="text/css">
table {
	width: 600px;
	border-collapse: collapse;
}

td {
	text-align: center;
	border-bottom: #c0c0c0 1px solid;
}

tbody tr:hover {
	background-color: silver;
}

.w1 {
	width: 10%; /* table(부모??)이 600이라 600의 몇 퍼센트 */
}

.w2 {
	width: 20%;
}

.w5 {
	width: 50%;
	text-align: left;
}

tr {
	height: 30px;
}
</style>

<script src="./js/write.js" charset="UTF-8"></script>

</head>
<body>
	<!-- 메뉴 jsp 연결... java 명령으로 file 추가 -->
	<%@ include file="./menu.jsp" %>
	<h1>index</h1>
	<!-- 옛날옛날에 이렇게 쳤다. DAO DBConn으로 파일 빼서 html에 다 안보이게 -->
	<!-- 게시판 내용 보기 -->
	<%
	BoardDAO dao = new BoardDAO();
	//DTO 대신에 쓴 것
	//int랑 String 담을 거라 최상위 부모클래스인 Object 쓴 것. (Object, Object는 뜯는 데 오래 걸린다)
	List<Map<String, Object>> list = dao.boardList();
	%>

	<%-- 나온 결과 : <%=list %> --%>
	<!-- 
	자바 코드를 여러 줄 쓸 때는 < % % >
	자바의 값 하나를 출력할 때는 < % = % > 
	-->

	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>글쓴이</th>
				<th>쓴 날짜</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<%
			//servlet 서블릿 : 자바 코드 속에 html이 있어요.
			//jsp java server page : html 코드 속에 java가 있어요.
			for (Map<String, Object> map : list) {
			%>
			<tr>
				<td class="w1"><%=map.get("board_no")%></td>
				<td class="w5"><a
					href="./detail?no=<%=map.get("board_no")%>"> <%=map.get("board_title")%>
				</a></td>
				<td class="w1"><%=map.get("board_write")%></td>
				<td class="w2"><%=map.get("board_date")%></td>
				<td class="w1"><%=map.get("board_count")%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>

	<button onclick="write1()">글쓰기</button>

	연습
	<a href="./test">여기</a>를 눌러주세요.
	<br>
	<!-- ./test.jsp가 아님!! -->
	연습
	<a href="./main.jsp">main</a>를 눌러주세요.

</body>
</html>