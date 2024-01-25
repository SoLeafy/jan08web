<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>음료 주문</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/menu.css">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script> <!-- jQuery 넣은거 -->
<script type="text/javascript" src="./js/menu.js"></script>
<style type="text/css">
.page-link{
	font-size: large;
	font-weight: bolder;
}
.page-link:hover {
	cursor: pointer;
	text-decoration: underline;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	//페이지 로딩부터 라디오버튼 유무 검사...
	let studentsDone = ${studentsDone };
	console.log(studentsDone);
	for(let i = 0; i < studentsDone.length; i++) {
		//console.log($("#option" + studentsDone[i]).val());
		//console.log(studentsDone[i] == $("#option" + studentsDone[i]).val());
		if(studentsDone[i] == $("#option" + studentsDone[i]).val()){
			$("#option" + studentsDone[i]).attr("disabled", true);
		}
	}
	
	
});
</script>

</head>
<body>
	<div class="container1">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<hr>
				<h1>음료 주문</h1>
				<span class="page-link"
					onclick="url('https://composecoffee.com/menu/category/191')">Tea 메뉴 참고 링크 🌟</span>
				<h3>수정 불가 ~ 신중하게 고르세요</h3>
				<article>
					<form action="./cafe" method="post">
						<!-- 학생 번호 -->
						<div class="studentNo-form">
						
						<input type="radio" class="btn-check" name="studentNo" id="option1" value="1" autocomplete="off">
						<label class="btn btn-dark" for="option1">1</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option2" value="2" autocomplete="off">
						<label class="btn btn-dark" for="option2">2</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option3" value="3" autocomplete="off">
						<label class="btn btn-dark" for="option3">3</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option4" value="4" autocomplete="off">
						<label class="btn btn-dark" for="option4">4</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option5" value="5" autocomplete="off">
						<label class="btn btn-dark" for="option5">5</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option6" value="6" autocomplete="off">
						<label class="btn btn-dark" for="option6">6</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option7" value="7" autocomplete="off">
						<label class="btn btn-dark" for="option7">7</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option8" value="8" autocomplete="off">
						<label class="btn btn-dark" for="option8">8</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option9" value="9" autocomplete="off">
						<label class="btn btn-dark" for="option9">9</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option10" value="10" autocomplete="off">
						<label class="btn btn-dark" for="option10">10</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option11" value="11" autocomplete="off">
						<label class="btn btn-dark" for="option11">11</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option12" value="12" autocomplete="off">
						<label class="btn btn-dark" for="option12">12</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option13" value="13" autocomplete="off">
						<label class="btn btn-dark" for="option13">13</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option14" value="14" autocomplete="off">
						<label class="btn btn-dark" for="option14">14</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option15" value="15" autocomplete="off">
						<label class="btn btn-dark" for="option15">15</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option16" value="16" autocomplete="off">
						<label class="btn btn-dark" for="option16">16</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option17" value="17" autocomplete="off">
						<label class="btn btn-dark" for="option17">17</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option18" value="18" autocomplete="off">
						<label class="btn btn-dark" for="option18">18</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option19" value="19" autocomplete="off">
						<label class="btn btn-dark" for="option19">19</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option20" value="20" autocomplete="off">
						<label class="btn btn-dark" for="option20">20</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option21" value="21" autocomplete="off">
						<label class="btn btn-dark" for="option21">21</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option22" value="22" autocomplete="off">
						<label class="btn btn-dark" for="option22">22</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option23" value="23" autocomplete="off">
						<label class="btn btn-dark" for="option23">23</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option24" value="24" autocomplete="off">
						<label class="btn btn-dark" for="option24">24</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option25" value="25" autocomplete="off">
						<label class="btn btn-dark" for="option25">25</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option26" value="26" autocomplete="off">
						<label class="btn btn-dark" for="option26">26</label>
						
						<input type="radio" class="btn-check" name="studentNo" id="option27" value="27" autocomplete="off">
						<label class="btn btn-dark" for="option27">선생님</label>
						
						</div>
						<br>
						<br>
						<!-- 메뉴 -->
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="menu" id="flexRadioDefault1" value="ice-americano" checked> <label
								class="form-check-label" for="flexRadioDefault1">
								 아이스 아메리카노 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="menu" id="flexRadioDefault2" value="hot-americano"> <label
								class="form-check-label" for="flexRadioDefault2"> 핫 아메리카노 </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="menu" id="flexRadioDefault3" value="ice-tea"> <label
								class="form-check-label" for="flexRadioDefault3"> 아이스 Tea </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="menu" id="flexRadioDefault4" value="hot-tea"> <label
								class="form-check-label" for="flexRadioDefault4"> 핫 Tea </label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio"
								name="menu" id="flexRadioDefault5" value="no-drink"> <label
								class="form-check-label" for="flexRadioDefault5"> 안 먹기 </label>
						</div>
						<br>
						</p>
						원하는 티 메뉴 코멘트에 👇
						<br>
					    티 종류 : 자몽티 / 레몬티 / 유자티 / 복숭아티 / 자몽허니블랙티 / 페퍼민트티 / 캐모마일티 / 로즈마리티 / 얼그레이티 / 블랙퍼스트티
					<br>
					<br>
						<div class="input-group mb-3">
						  <span class="input-group-text" id="basic-addon1">의견</span>
						  <input type="text" name="menu-comment" class="form-control" placeholder="코멘트" aria-label="Username" aria-describedby="basic-addon1">
						</div>
						<button type="submit" class="btn btn-dark">투표하기</button>
					</form>
				</article>
				
				
				<article>
					<h1>결과</h1>
					<p>아이스 아메리카노(총 인원 ${iceAmeTotal }명) : 
					<c:forEach items="${iceAmeList }" var="a">
					${a },&ensp;
					</c:forEach>
					</p>
					<p>핫 아메리카노(총 인원 ${hotAmeTotal }명) : 
					<c:forEach items="${hotAmeList }" var="a">
					${a },&ensp;
					</c:forEach>
					</p>
					<p>아이스 Tea(총 인원 ${iceTeaTotal }명) : 
					<c:forEach items="${iceTeaList }" var="a">
					${a },&ensp;
					</c:forEach>
					</p>
					<p>핫 Tea(총 인원 ${hotTeaTotal }명) : 
					<c:forEach items="${hotTeaList }" var="a">
					${a },&ensp;
					</c:forEach>
					<p>안 마시는 사람(총 인원 ${nothingTotal }명) : 
					<c:forEach items="${nothingList }" var="a">
					${a },&ensp;
					</c:forEach>
					</p>
					<br>
					<p>투표 안 한 사람 : ${notYetList }
					<%-- <c:forEach items="${notYet }" var="h">
					${h },&ensp;
					</c:forEach> --%>
					</p>
				</article>
			</div>
		</div>
	</div>
</body>
</html>