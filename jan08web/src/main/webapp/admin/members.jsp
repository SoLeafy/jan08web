<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css" rel="stylesheet"/> <!-- 파일위치 ..으로 최상단으로 올라가기 -->
<link href="../css/member.css" rel="stylesheet"/> <!-- 파일위치 ..으로 최상단으로 올라가기 -->
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script src="../js/menu.js" type="text/javascript"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
//$(document).ready(function (){ // 얘 다이어트 시키기
$(function(){
	//alert("!");
	$('select[name=grade]').on("change", function(){
		//let val = $(this).val(); // this는 name이 grade인 select
		//let val = $(this).parent().prev().prev().prev().prev().text(); // mno 가져오기.
		let val = $(this).val(); // 변경시킬 등급
		let mno = $(this).closest("tr").children().first().text();
		//alert(val + " : " + mno);
		//두 값을 잡았으면 가상 form에 담아서 전송함.
		let form = $('<form></form>');
		form.attr('method', 'post');
		form.attr('action', './members');
		form.append($('<input/>', {type:'hidden', name:'mno', value: mno})); //json타입임
		form.append($('<input/>', {type:'hidden', name:'grade', value: val}));
		// null 잡고, 서블릿도 없다면~
		<c:if test="${param.grade ne null}">
		form.append($('<input/>', {type:'hidden', name:'currentgrade', value: ${param.grade }}));
		</c:if>
		form.appendTo('body');
		form.submit();
	});
	
});
</script>
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
			<article>
			<h2>회원관리</h2>
				<!-- 이 페이지에 오는 모든 사람은 관리자인지 검사를 합니다.
				관리자인 경우 보여주고, 로그인하지 않았거나 일반 사용자는 로그인 페이지로 이동시킴. -->
				<div class="member-lists">
					<ul>
						<li onclick="url('./members')"><i class="xi-close-circle-o"></i> 전체보기</li>
						<li onclick="url('./members?grade=0')"><i class="xi-close-circle-o"></i> 탈퇴</li>
						<li onclick="url('./members?grade=1')"><i class="xi-minus-circle-o"></i> 강퇴</li>
						<li onclick="url('./members?grade=2')"><i class="xi-check-circle-o"></i> 정지</li>
						<li onclick="url('./members?grade=5')"><i class="xi-label-o"></i> 정상</li>
						<li onclick="url('./members?grade=8')"><i class="xi-label"></i> 천재</li>
						<li onclick="url('./members?grade=9')"><i class="xi-plus-square-o"></i> 관리자</li>
					</ul>
				</div>
				<table>
					<thead>
					<tr>
						<td>번호</td>
						<td>아이디</td>
						<td>이름</td>
						<td>가입일</td>
						<td>등급</td>
					</tr>
					</thead>
					<tbody>
						<c:forEach items="${list }" var="row">
						<tr class="row${row.mgrade }"> <!-- choose 보다 간단한것 같아서 편법 쓴 거 -->
							<td class="d1">${row.mno }</td>
							<td class="title">${row.mid }</td>
							<td class="d1">${row.mname }</td>
							<td class="d2">${row.mdate }</td>
							<td class="d1">
								<select name="grade">
									<optgroup label="정지">
										<option <c:if test="${row.mgrade eq 0}">selected="selected"</c:if> value="0">0 강퇴</option>
										<option <c:if test="${row.mgrade eq 1}">selected="selected"</c:if> value="1">1 탈퇴</option>
										<option <c:if test="${row.mgrade eq 2}">selected="selected"</c:if> value="2">2 정지</option>
									</optgroup>
									<optgroup label="정상">
										<option <c:if test="${row.mgrade eq 5}">selected="selected"</c:if> value="5">5 평민</option>
										<option <c:if test="${row.mgrade eq 8}">selected="selected"</c:if> value="8">8 천재</option>
									</optgroup>
									<optgroup label="관리자">
										<option <c:if test="${row.mgrade eq 9}">selected="selected"</c:if> value="9">9 관리자</option>
									</optgroup>
								</select>
							${row.mgrade }
							
							</td>
						</tr>
						</c:forEach>
					</tbody>
						
				</table>
				
			</article>
			
		</div>
	</div>
</body>
</html>