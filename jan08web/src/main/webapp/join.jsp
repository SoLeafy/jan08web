<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>성인큐트파티가입</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<link href="./css/index.css" rel="stylesheet"/>
<link href="./css/menu.css" rel="stylesheet"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script type="text/javascript" src="./js/menu.js"></script>
<script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.7.1.min.js"></script> <!-- jQuery 넣은거 -->
<style type="text/css">
.id-alert, .name-alert, .pw-alert{
	background-color: #fb8500;
	width: 500px;
	height: 50px;
}
.alert{
	color: white;
}
</style>
<script type="text/javascript">
	//$(document).ready(function (){}); -> 다른 모양. 축약형. //문서 읽어들일 때
	//글로벌 변수(전역 변수) - 우리 알고 있는 클래스 필드라고 생각하면 된다.
	let idCheckBool = false;
	
	$(function (){//제이쿼리 시작문 = 제이쿼리 시작합니다.
		$('.id-alert, .name-alert, .pw-alert').hide(); //나열하면 여러 개 속성을 일괄 적용
		/* $('.name-alert').hide();
		$('.pw-alert').hide(); */
		
		//onchange()
		//$("#id").change(function (){ //onchange말고 change는 input창 밖에 클릭해야 alert 뜸.
		//	alert("아이디 입력창 값이 변경되었습니다.");
		//});
		$('#id').on("change keyup paste", function (){ // 변화 감지. keyup을 감지.
			//alert("아이디 입력창 값이 변경되었습니다.");
			$('.id-alert').show();
			$('.id-alert').html('<p class="alert">당신이 입력한 ID는 ' + $('#id').val() +'</p>'); //텍스트만 바꿀거면 .val()도 될 것.. 이건 html 직접 다 바꿈.
			//$('.id-alert').text('당신이 입력한 ID는' + $("#id").val());
			if($('#id').val().length > 5){
				idCheck();
			}
		});
		
	});
	

	function check() {
		//alert("제이쿼리가 동작합니다.");
		// $(선택자).할일();
		let id = $("#id").val(); //선택자가 들어간다. ''도 괜찮다!
		//alert(id + " : " + id.length); //Not a Number 숫자 아닌지 확인 (영문 섞여있으면 true)
		if(id.length < 3 || id == ""){
			$('.id-alert').show();
			$("id").focus();
			return false;
		} else {
			//완료되면 다시 지우기. else로 적어도 된다.
			$('.id-alert').hide();
		}
		if(!idCheckBool) {
			alert("ID 검사를 먼저 실행해주세요.");
			return false;
		}
		
		let name = $('#name').val();
		if(name.length < 2 || name.length > 6){
			$('.name-alert').show();
			$('#name').focus();
			return false;
		} else {
			$('.name-alert').hide();
		}
		let pw1 = $('#pw1').val();
		let pw2 = $('#pw2').val();
		if(pw1.length < 5) {
			$('.pw-alert').show();
			$('#pw1').focus();
			return false;
		}
		if(pw2.length < 5) {
			//alert("비밀번호는 5자리 이상 적도록 하세요.");
			$('.pw-alert').show();
			$('#pw2').focus();
			return false;
		}
		if(pw1 != pw2){ //일치하지 않으면 둘다 지워버릴 것.
			$('.pw-alert').show();
			//alert("비밀번호가 일치하지 않아요.");
			$('#pw2').val("");
			$('#pw2').focus();
			return false;
		}
		$('.pw-alert').hide();
	}
	
	function idCheck(){
		//alert('id 중복 검사를 눌렀습니다.');
		let id = $('#id').val();
		//const regExp = const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"\sㄱ-ㅎㅏ-ㅣ가-힣]/g;//한글+공백
		//alert(regExp.test(id)); //한글, 공백 포함 여부 검사하는 정규식
	    //나중에는 영어 소문자, 숫자만 들어오게 해주면 감사하겠습니다.
	    const regExp = /^[a-z0-9]{5,15}$/;
		if(id.length < 5 || !regExp.test(id)) {
			//alert("아이디는 영문자 5글자 이상이고 특수문자가 없어야 합니다.");
			$('.id-alert').html('<p class="alert">아이디는 영문자 5글자 이상이고 특수문자가 없어야 합니다.</p>');
			$('#id').focus();
		} else {
			//AJAX = 1페이징, 2AJAX, 3파일업로드
			$.ajax({
				url : './idCheck',			//이동할 주소
				type : 'post',				//post / get
				dataType : 'text',			//수신 타입
				data : {'id' : id},			//보낼 값
				success : function(result){	//성공 시
					//alert("통신에 성공했습니다.");
					if(result == 1) {
						//alert("이미 가입된 아이디입니다.");
						$('.id-alert').append('<p class="alert">이미 가입된 아이디입니다.</p>')
						idCheckBool = false;
						$('#joinBtn').attr("disabled", "disabled"); //비활성화 시키기
						$('#id').focus();
					} else {
						//alert("가입할 수 있습니다. 계속 진행하세요.");
						//$('.id-alert').html('<p class="alert">가입할 수 있습니다. 계속 진행하세요.</p>')
						$('.id-alert').append('<p class="alert">가입할 수 있습니다.</p>');
						$('.id-alert .alert').css("color", "black");
						idCheckBool = true; //?이건가
						$('#joinBtn').removeAttr("disabled"); //비활성화 제거하기 = 활성화 시키기
						//$('#name').focus();
					}
				},
				error : function(request, status, error){ //접속불가, 문제발생 등
					alert("문제가 발생했습니다.");
				}
			});
			
		}
		return false;
	}
</script>
</head>
<body>
<%
//자바 영역... 이런 것 때문에 코드가 많이 길어진다.
//그리고 열었다 닫았다 하기가 귀찮아짐.
//model1 때 많이 썼다. model2때도 썼다.
//사이사이가 무의미하게 길어지고 공백도 많고 해서 가독성이 떨어짐. -> 이거 치기 싫다고 개발자들이 많이 도망갔다.
//php가 이런 느낌.

//그래서 나온 게 jstl
//html 태그를 쓰도록 하자! 하고 나온 것.
int num = 100;
%>

<%=num %>
<!-- container1에 둘러싸여서 안보이긴 할 것. 소스보기로는 나옴. -->
	<c:forEach items="집합" begin="1" end="5" var="h">
		${h }
	</c:forEach>

	<div class="container1">
		<header>
			<%@ include file="menu.jsp"%>
		</header>
		<div class="main">
			<div class="mainStyle">
				<article style="display:flex; flex-direction:column; justify-content:center;">
					<div class="join-form">
						<h1>머찐계란되기</h1>
						<!-- <h3>(현재 가능)</h3> -->
						<div class="mx-5 p-5 bg-warning rounded-pill">
							<form action="./join" method="post" onsubmit="return check()">
								<div class="input-group mb-2">
									<label class="input-group-text">아&ensp;이&ensp;디</label>
									<input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력하세요">
									<button class="input-group-text btn btn-dark" onclick="return idCheck()">중복 검사</button>
								</div>
								<div class="input-group mb-2 id-alert">
									<p class="alert">올바른 아이디를 입력하세요</p>
								</div>
								<div class="input-group mb-2">
									<label class="input-group-text">&ensp;이&ensp;&ensp;름&ensp;</label>
									<input type="text" id="name" name="name" class="form-control" placeholder="이름을 입력하세요">
								</div>
								<div class="input-group mb-2 name-alert">
									<p class="alert">올바른 이름을 입력하세요(이름은 2글자에서 5글자까지 가능)</p>
								</div>
								<div class="input-group mb-2">
									<label class="input-group-text">비밀번호</label>
									<input type="password" id="pw1" name="pw1" class="form-control" placeholder="비밀번호를 입력하세요">
									<label class="input-group-text">비번확인</label>
									<input type="password" id="pw2" name="pw2" class="form-control" placeholder="비밀번호를 다시 한 번 입력하세요">
								</div>
								<div class="input-group mb-2 pw-alert">
									<p class="alert">올바른 암호를 입력하세요</p>
								</div>
								<div class="input-group mb-2">
									<button type="reset" class="btn btn-warning">지우기</button>
									<button id="joinBtn" type="submit" disabled="disabled" class="btn btn-dark" onclick="return pwCheck()">가입하기</button>
								</div>
							</form>
						</div>
					</div>
					<div style="margin:50px;">
						<button class="btn btn-light" onclick="location.href='https://smore.im/quiz/5xjJqtXtZ1?tm'">티니핑 테스트</button>
					</div>
				</article>
			</div>
		</div>
		<footer>
		<c:import url="footer.jsp"/>
		</footer>
	</div>
	
	<script>
		/* function pwCheck() {
			/* let pw1 = document.querySelector("#pw1");
			let pw2 = document.querySelector("#pw2"); */
			/* if(pw1.value != pw2.value) {
				alert("비밀번호가 일치하지 않아요.");
				return false;
			} */
			
			/* let memId = document.querySelector("#id"); */
			/* let memName = document.querySelector("#name"); */
			
			/* if(memId.value.length < 1) {
				alert("아이디를 입력하세요.");
				return false;
			} */
			/* if(memName.value.length < 1) {
				alert("이름을 입력하세요.");
				return false;
			} */
			/* if(pw1.value.length < 1) {
				alert("비밀번호를 입력하세요.");
				return false;
			} */
		} */
	</script>
</body>
</html>