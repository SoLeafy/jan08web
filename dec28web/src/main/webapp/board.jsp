<%@page import="java.util.ArrayList"%>
<%@page import="com.poseidon.dto.BoardDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.poseidon.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 만들기</title>
<style type="text/css">
	/* 주석 */
	/* 스타일 시트 입니다. */
	body {
		background-color: yellow;
	}
	h1, h2 { /* <- 선택자 */
		color: red;
		font-weight: bold;
		font-family: "D2coding";
	}
	#head3 { /* id는 # class는 . */
		color: #c0c0c0;
		text-decoration: underline;
	}
	.head3 {
		background-color: gray;
		text-align: center;
	}
	#board {
		width: 900px;
		height: 500px;
		background-color: white;
		border-collapse: collapse;
	}
	td {
		border-bottom: 1px dotted gray; /* solid 실선, px적으면 pixel이 늘어남 */
		text-align: center;
	}
	
	/*tr:nth-child(even){ 
		background-color: gray;
	} */
	
	.w1 {
		width: 50px;
	}
	
	.w2 {
		width: 100px;
	}
	
	.w5 {;
		width: 400px;
		text-align: left;
	}
	th {
		background-color: #c0c0c0;
	}
	tr:hover {
		background-color: #c0c0c0;
		font-weight: bold;
	}
</style>

</head>
<body>
	<!-- 데이터베이스에서 가져오기 -->
	<%
	BoardDAO dao = new BoardDAO();
	List<BoardDTO> list = dao.boardList();
	%>
	<!-- 10개만 글만 가져오기 -->
	<!-- 번호, 제목, 글쓴이, 쓴 날짜, 조회수 -->
	<h1>게시판</h1>
	<h2 class="head3">게시판2</h2>
	<h3 id="head3">게시판3</h3>
	<h3>게시판3</h3>
	<table id = "board">
	<thead>
		<tr>
			<th class="w1">번호</th>
			<th>제목</th>
			<th class="w1">글쓴이</th>
			<th class="w2">쓴 날짜</th>
			<th class="w1">읽음</th>
		</tr>
	</thead>
	<tbody>
	<% for (BoardDTO dto : list) { %>
		<tr>
			<td class="w1"><%=dto.getBoard_no() %></td>
			<td class="w5"><%=dto.getBoard_title() %></td>
			<td class="w1"><%=dto.getBoard_write() %></td>
			<td class="w2"><%=dto.getBoard_date() %></td>
			<td class="w1"><%=dto.getBoard_count() %></td>
		</tr>
	</tbody>
	<%} %>
	</table>

</body>
</html>