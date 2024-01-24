<%@page import="java.util.Map"%>
<%@page import="com.poseidon.dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>톺아보기</title>
</head>
<body>
	<%@ include file="./menu.jsp"%>
	<h1>톺아보기</h1>
	<%
	//http://localhost:8080/dec29web/detail.jsp?no=6&t=100&sub=&test=101
	//https://www.clien.net/service/board/news/18500330?od=T31&po=0&category=0&groupCd=
	//String no = request.getParameter("no"); // "3" 네트워크 상의 모든 데이터는 String

	//DAO에게 일 시키기
	//BoardDAO dao = new BoardDAO();
	Map<String, Object> detail = (Map<String, Object>) request.getAttribute("detail");
	%>

	<!-- 삭제 이미지를 누르면 삭제되는 기능을 넣겠습니다. -->
	<%-- <a href="./delete.jsp?no=<%=detail.get("board_no") %>">
	<img alt="삭제" src="./img/delete.png" title="버튼을 누르면 삭제합니다.">
	</a> --%>

	<img onclick="update()" alt="수정" src="./img/update.png"
		title="버튼을 누르면 글을 수정합니다.">
	<img onclick="del(<%=detail.get("board_no")%>)" alt="삭제"
		src="./img/delete.png" title="버튼을 누르면 삭제합니다.">

	<br> 제목 :
	<%=detail.get("board_title")%><br> 글쓴이 :
	<%=detail.get("board_write")%><br> 날짜 :
	<%=detail.get("board_date")%><br> 번호 :
	<%=detail.get("board_no")%><br> 조회 :
	<%=detail.get("board_count")%><br>
	<hr>
	<%=detail.get("board_content")%>
	<!-- 
	html문서에서 간혹 onclick, on* = 자바스크립트
	 -->
	<button onclick="location.href='./index.jsp'">돌아가기</button>

	<button onclick="what()">눌러보세요</button>

	<script type="text/javascript">
	function update() {
		//alert("버튼을 눌렀습니다.");
		let check = confirm("수정하시겠습니까?");
		if(check){
			//alert("글을 수정합니다.");
			location.href="./update?no=<%=detail.get("board_no")%>";
			//위 문장은 서버단에서      ---------------------------여기부터 계산해 숫자로 되돌려줌.
			//그 후 html/css/js단에서 처리함.
			//실행순서.
			//1. 자바 코드들 먼저 실행	= 서버 단에서 처리
			//2. html/css/js 실행		= 클라이언트 단에서 처리
		}
	}
	
	function what() {//함수
		for(let i = 0; i < 2; i++) {
			alert("메롱");
		}
	}
	function del(no) {
		if(confirm("정말 삭제할거야?")){ //confirm y/n 받는 거. t/f를 리턴.
			location.href="./delete?no=" + no; //삭제하기 no가 필요해.
			//.jsp -> servlet으로 변경
		}
	}
	</script>

	<!-- 프레임워크 / 라이브러리 
	C write 
	R list(글 목록), detail(1개 글)
	U update(detail과 write가 합쳐짐)
	D delete(where절 써야)
	
	+ 페이징, 로그인, 세션
	+ 값 검사
	+ 파일 업로드
	
	
	-->
</body>
</html>