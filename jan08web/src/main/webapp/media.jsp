<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 미디어쿼리 -->
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>미디어쿼리연습</title>
<style type="text/css">
/* 모든 기기 공통 CSS */
body{
	background-color: #cdb4db;
}

/* desktop 규격 */
@media screen and (min-width : 1024px){ /*우리껀 2048일수도 있대.. 해상도 얘기*/
	body {
		background-color: #f9c74f;
	}
}

/* tablet 규격 */
@media screen and (max-width : 1023px){
	body {
		background-color: #90be6d;
	}
}

/* mobile 규격 */
@media screen and (max-width : 540px){
	body {
		background-color: #277da1;
	}
}

</style>
<script>
//console.log(window.innerWidth);
window.onresize = function(event){
	document.getElementById("size").textContent = window.innerWidth + " x " + window.innerHeight;
}
</script>
</head>
<body>
	<h1 id="size">너비 x 높이 : </h1>
</body>
</html>