<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>톺아보기</title>
<link rel="stylesheet" href="./css/index.css">
<link rel="stylesheet" href="./css/menu.css">
<link rel="stylesheet" href="./css/detail.css">
<link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script type="text/javascript" src="./js/menu.js"></script>
<!-- 제이쿼리 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
	integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
	crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript">
	function del() {
		/* alert("삭제를 눌렀습니다."); */
		var ch = confirm("글을 삭제하시겠습니까?");
		if (ch) {
			//이거 string 안되고 되는 이유... jstl 자바 계열은 먼저 바뀐다.
			location.href = "./delete?no=${detail.no }";
		}
	}
	
	function update() {
		if (confirm("글을 수정하시겠습니까?")) {
			location.href = "./update?no=${detail.no }";
		}
	}
	/* function commentDel(cno){
		if(confirm("댓글을 삭제하시겠습니까?")) {
			location.href = './commentDel?no=${detail.no}&cno='+cno;
		}
	} */
	function commentUpdate(){
		
	}

	$(document).ready(function() {
		//댓글쓰기 버튼을 누르면 댓글창 나오게 하기 2024-01-24
		$(".comment-write").hide();//원래는 ready 바로 아래 두는 것을 추천...
		//$(".comment-write").show(5000); //지연시간 (.. 킹받게 나타나네)
		
		$(".xi-comment-o").click(function(){
			$(".xi-comment-o").hide();
			//$(".comment-write").show();
			$(".comment-write").slideToggle('slow'); //킹받아..
		});
		
		$(".commentEdit").click(function(){
			if(confirm("수정하시겠습니까?")){
				//필요한 값 cno잡기 / 수정한 내용 + 로그인 ==== 서블릿에서 정리
				let cno = $(this).siblings(".cno").val();
				//let content = $(this).parents(".comment").children(".ccomment").text();
				let comment = $(this).parents(".chead").next(); //나중에 변경. //되네.. 아 부모의 다음,,
				$(this).prev().hide();
				$(this).hide();
				comment.css('height', '110px');
				comment.css('padding-top', '10px');
				//comment.css('backgroundColor', '#eaf2d7');
				//comment.css('resize', none);
				let recommentBox = '<div class="recommentBox">';
				//let commentChange = comment.html().replaceAll('<br>', '\r\n');
				//alert(cno + " : " + comment.html());
				recommentBox+= '<div class="recommentBox-form">'; //form 없어져서 css땜에 div추가
				//recommentBox += '<form class="recommentBox-form" action="./cedit" method="post">';
				recommentBox += '<textarea class="commentcontent" name="comment">' + comment.html().replaceAll(/<br>/g,'\r\n') + '</textarea>';
				recommentBox += '<input type="hidden" name="cno" value="' + cno + '">'; //다 String으로 인지해서
				recommentBox += '<button class="comment-btn" type="button">댓글 수정</button>';
				//recommentBox += '</form>';
				recommentBox += '</div>';
				recommentBox += '</div>';
				
				comment.html(recommentBox); // html 바꿔치기
				
				
			}
		});
		
		//댓글 수정		.comment-btn버튼 눌렀을 때 .cno값, .commentcontent값 가져오는 명령 만들기 
		//(다 클래스 클래스해서 값 가져오는 명령. jquery로 만들면 됨.)
		// 2024-01-25
		$(document).on('click', ".comment-btn", function(){ //동적 생성된 녀석들! 얘네는 $(document).on('click', 클래스이름, 함수)
			//alert("!");
			if(confirm('수정하시겠습니까?')){
				//cno값과 commentcontent 내용값 잡아오기.
				let cno = $(this).prev().val();
				//let content = $(this).siblings(".commentcontent").text();
				let recomment = $('.commentcontent').val();
				let comment = $(this).parents(".ccomment");//댓글 위치
				alert(comment);
				//alert("cno: " + cno + " content: " + content);
				//잡았으면 ajax로 보내주자.
				$.ajax({
					url: './recomment', //if문장으로 분리시켜서 하면 기존 commentwrite에 보내도 되지만 까다로우니 새로운 거.
					type: 'post',
					dataType: 'text',
					data: {'cno' : cno, 'comment' : recomment}, //변수 안 만들고 그대로 넣어도 된다.
					success: function(result){
						//alert('통신 성공 : ' + result);
						if(result == 1){
							//수정된 데이터를 화면에 보여주면 됨.
							$(this).parent(".recommentBox").remove();
							comment.css('backgroundColor', '#ffffff');
							comment.css('min-height', '100px');
							comment.css('height', 'auto'); //css도 바꿈..
							//recomment = recomment.replaceAll(/<br>/g, '\r\n');
							comment.html(recomment.replace(/(?:\r\n|\r|\n)/g, "<br>")); //replaceAll아니고..?
							$(".commentDelete").show();
							$(".commentEdit").show();
						} else {
							//실패 화면 재로드.
							alert("문제가 발생했습니다. 화면을 갱신합니다.");
							//location.href='./detail?page=${param.page}&no=${param.no}'; //이 방법도 된다.
							location.href='./detail?page=${param.page}&no=${detail.no}'; //param은 url에서 가져오는 거.
						}
					},
					error: function(error){
						alert('문제가 발생했습니다 : ' + error);
					}
				});
			}
		});
		
		
		
		
		//댓글 삭제 버튼을 눌렀습니다.
		
		
		$(".commentDelete").click(function(){
			//alert("삭제 버튼을 눌렀습니다.");
			//부모객체(html꺼) 찾아가기 -> 삭제할 번호는? 어디있냐. 서버에서 하고 소멸된 건 안 찾음. java, jstl, el태그 같은
			//여기서도 this가 쓰임. ==== 자바는 this() -> 내꺼 생성자. super() -> 부모꺼생성자.
			//클릭한주체(.commentDelete).부모 ,,,, s는 그 위 부모 모두(?) 부모 조부모...
			//$(this).parent(".cname").css('color', 'green');
			//let text = $(this).parent(".cname").text(); //text() html()... val()은->input, text창만 value 지정되니까 div에 없다.
			//부모 요소 아래 자식요소 찾기 children()
			//let cno = $(this).parent(".cname").children(".cno").val();
			//let no = $(this).parent().children(".cno").val(); -> 이거 부모 이름 지정 안해도 된다.
			//★문서 구조를 알아야 원하는 걸 뜯어올 수 있다.
			//형제 요소 찾기 .siblings() .prev() 바로 이전 .next() 바로 다음
			//let cno = $(this).siblings(".cno").val();
			
			if(confirm("삭제 하시겠습니까?")){
				let cno = $(this).prev().val();
				//alert(cno);
				//가상 form 만들어서 post로 전송하기.
				//ajax
				let point = $(this).closest(".comment");
				$.ajax({//순서 없다. 딕셔너리같은 거라 그런가?
					url : './commentDel',	//주소
					type : 'post',			//get, post
					dataType: 'text',		//수신타입 json
					data: {no: cno},			//보낼 값 ('no'는 왜 아닐까..? -> json은 : 앞에껀 알아서 key로 인식)
					success:function(result){//0, 1
						//alert("서버에서 온 값 : " + result);
						//나중에 여기에 if문. 일단 여기까지.
						if(result == 1){
							//정상 삭제 : this의 부모(.comment)를 찾아서 remove한다. (화면에서 지우기)
							//$(this).closest(".comment").hide();//이름 없으면 못가져오니까 문서 구조 꼭 확인.
							point.remove(); //hide는 show로 살릴 수 있지만 remove는 안된다.
						} else {
							alert("삭제할 수 없습니다. 관리자에게 문의하세요.");
						}
					}, 
					error:function(request, status, error){
						alert("문제가 발생했습니다.");
					}
				});//end ajax
			}
			
		});
		
		//alert("준비되었습니다.");
		$("#comment-btn").click(function() {
			let content = $("#commentcontent").val();
			let bno = ${detail.no };//여기는 글번호
			//alert("content : " + content + " no : " + no);
			//가상 form 만들기 = 동적 생성
			//전송 ---> content가 2글자 이상인 경우 실행하겠습니다.
			if (content.length < 2) {
				alert("댓글은 두 글자 이상 적어주세요.");
				content.focus();
				//return false;
			} else {
				let form = $('<form></form>');
				form.attr('name', 'form');
				form.attr('method', 'post');
				form.attr('action', './comment');

				form.append($('<input/>', {type : 'hidden', name : 'commentcontent', value : content}));//json을 쓴 것. (map이라고 생각하자)
				form.append($('<input/>', {type : 'hidden', name : 'bno', value : bno}));

				form.appendTo("body");
				form.submit();

				/* let form = document.createElement('form');
				form.name='form';
				form.method='post';
				form.action='./comment';
				//붙이기
				let text = document.createElement('input');
				text.setAttribute("type", "hidden");
				text.setAttribute("name", "commentcontent");
				text.setAttribute("value", content);
				//붙이기
				let no = document.createElement('input');
				no.setAttribute("type", "hidden");
				no.setAttribute("name", "bno");
				no.setAttribute("value", ${detail.no }); // 305 도 사용 가능 (??)
				//form에다가 붙이기
				form.appendChild(text);
				form.appendChild(no);
				//전송하기
				document.body.appendChild(form);
				form.submit(); */
			}
		});
		//댓글작성 100자 넘기기 안되게
		$("#commentcontent").keyup(function(){
			let text = $(this).val();
			if(text.length > 100){
				alert("100자 넘었어요.");
				$(this).val(text.substr(0, 100));
			}
			$("#comment-btn").text("글쓰기 " + text.length + "/100");
		});
		/* //댓글수정도 되나해서 -> 안되겠다!!
		$(".commentcontent").keyup(function(){
			let text = $(this).val();
			if(text.length > 100){
				alert("100자 넘었어요.");
				$(this).val(text.substr(0, 100));
			}
			$(".comment-btn").text("글쓰기 " + text.length + "/100");
		}); */
	});
	
	
</script>
<style type="text/css">
.xi-comment-o:hover{
	cursor:pointer;
}
</style>
</head>
<body>
	<div class="container">
		<header>
			<%@ include file="menu.jsp"%>
			<%-- <jsp:include page="menu.jsp"></jsp:include> --%>
			<!-- jsp:은 출력 결과만 화면에 나옴. -->
		</header>

		<div class="main">
			<div class="mainStyle">
				<article>
					<div class="detailDIV">
						<div class="detailTITLE">${detail.title }</div>
						<div class="detailWRITECOUNT">
							<div class="detailWRITE">
								${detail.write }
								<c:if test="${detail.mid eq 'test1' }">
									<img class="profilePic" alt="test1프로필" src="./img/미첼.png">
								</c:if>
								<c:if test="${detail.mid eq 'hachu' }">
									<img class="profilePic" alt="하츄핑프로필" src="./img/하츄핑.jpg">
								</c:if>
								<c:if test="${detail.mid eq 'apple' }">
									<img class="profilePic" alt="애플이프로필" src="./img/애플이.png">
								</c:if>
								<c:if test="${detail.mid eq 'glumin' }">
									<img class="profilePic" alt="글루민프로필" src="./img/글루민.png">
								</c:if>
								<c:if test="${detail.mid eq 'test6' }">
									<img class="profilePic" alt="방글핑프로필" src="./img/방글핑.png">
								</c:if>
								<c:if test="${detail.mid eq '페이커' }">
									<img class="profilePic" alt="지훈님프로필" src="./img/지훈님.png">
								</c:if>
								<c:if test="${detail.mid eq 'test8' }">
									<img class="profilePic" alt="라라핑프로필" src="./img/라라핑.jpg">
								</c:if>
								<c:if test="${detail.mid eq 'test9' }">
									<img class="profilePic" alt="시러핑프로필" src="./img/시러핑.png">
								</c:if>
								<c:if test="${detail.mid eq 'peter' }">
									<img class="profilePic" alt="피터프로필" src="./img/피터.png">
								</c:if>
								<c:if test="${detail.mid eq 'jjuni' }">
									<img class="profilePic" alt="쭈니프로필" src="./img/쭈니.png">
								</c:if>
								<c:if test="${detail.mid eq 'test10' }">
									<img class="profilePic" alt="성인큐트파티프로필" src="./img/악동핑.png">
								</c:if>
								<c:if test="${detail.mid eq 'takotako' }">
									<img class="profilePic" alt="탁호프로필" src="./img/탁호.png">
								</c:if>
								<%-- <c:if test="${sessionScope.mname ne detail.mid eq sessionScope.mid}"> --%>
								<c:if
									test="${sessionScope.mname ne null && detail.mid eq sessionScope.mid}">
									<img id="deleteImg" alt="delete" src="./img/delete.png"
										onclick="del()">
									<img id="editImg" alt="edit" src="./img/write_pencil.png"
										onclick="update()">
								</c:if>
							</div>
							<div class="detailCOUNT">${ip } / ${detail.count }</div>
						</div>
						<div class="detailCONTENT">${detail.content }</div>
					</div>
					<c:if test="${sessionScope.mid ne null }">
					<p class="xi-comment-o">댓글창 열기</p>
						<!-- 댓글 쓰는 창을 여기다가 만들어줄 것. 댓글내용, 누가, 어느, 2024-01-22 -->
						<div class="comment-write">
							<div class="comment-form">
								<textarea id="commentcontent" name="commentcontent"></textarea>
								<button id="comment-btn">댓글쓰기</button>
							</div>
						</div>
					</c:if>
					<!-- 댓글 출력창 -->
					<div class="comments">
						<c:forEach items="${commentList }" var="co">
							<div class="comment">
								<div class="chead">
									<div class="cname">${co.mname }
									<c:if test="${co.mid eq 'test1' }">
										<img class="profilePic" alt="test1프로필" src="./img/미첼.png">
									</c:if>
									<c:if test="${co.mid eq 'hachu' }">
										<img class="profilePic" alt="하츄핑프로필" src="./img/하츄핑.jpg">
									</c:if>
									<c:if test="${co.mid eq 'apple' }">
										<img class="profilePic" alt="애플이프로필" src="./img/애플이.png">
									</c:if>
									<c:if test="${co.mid eq 'glumin' }">
										<img class="profilePic" alt="글루민프로필" src="./img/글루민.png">
									</c:if>
									<c:if test="${co.mid eq 'test6' }">
										<img class="profilePic" alt="방글핑프로필" src="./img/방글핑.png">
									</c:if>
									<c:if test="${co.mid eq '페이커' }">
										<img class="profilePic" alt="지훈님프로필" src="./img/지훈님.png">
									</c:if>
									<c:if test="${co.mid eq 'test8' }">
										<img class="profilePic" alt="라라핑프로필" src="./img/라라핑.jpg">
									</c:if>
									<c:if test="${co.mid eq 'test9' }">
										<img class="profilePic" alt="시러핑프로필" src="./img/시러핑.png">
									</c:if>
									<c:if test="${co.mid eq 'peter' }">
										<img class="profilePic" alt="피터프로필" src="./img/피터.png">
									</c:if>
									<c:if test="${co.mid eq 'jjuni' }">
										<img class="profilePic" alt="쭈니프로필" src="./img/쭈니.png">
									</c:if>
									<c:if test="${co.mid eq 'test10' }">
										<img class="profilePic" alt="성인큐트파티프로필" src="./img/악동핑.png">
									</c:if>
									<c:if test="${co.mid eq 'takotako' }">
										<img class="profilePic" alt="탁호프로필" src="./img/탁호.png">
									</c:if>
						<!-- 지금하는중 jan24 -->
									<input type="hidden" class="cno" value="${co.cno }">
									<c:if
									test="${sessionScope.mname ne null && co.mid eq sessionScope.mid}">
									<img id="deleteImg" alt="삭제" src="./img/delete.png"
										class="commentDelete">
									<img id="editImg" alt="수정" src="./img/write_pencil.png"
										class="commentEdit">
									</c:if>
									</div>
									
									<div class="cdate">${co.ip} / ${co.cdate }</div>
								</div>
								<div class="ccomment">${co.comment }</div>
							</div>
						</c:forEach>
					</div>
					<article>하단 foot 때문에 안 보인다면 추가</article>
					<button onclick="url('./board?page=${param.page }')">게시판으로</button>
				</article>
			</div>
		</div>
		<footer>
			<c:import url="footer.jsp" />
		</footer>
	</div>

	<script type="text/javascript">
		
	</script>
</body>
</html>