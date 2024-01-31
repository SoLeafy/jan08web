<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 페이지</title>
<link href="../css/admin.css?ver=0.13" rel="stylesheet" />
<link href="../css/board.css" rel="stylesheet" />
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script src="../js/menu.js" type="text/javascript"></script>
<script 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js"
integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g=="
crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {

  var data = google.visualization.arrayToDataTable([
    ['Ip', 'Access'],
    <c:forEach items="${topIpList }" var="rank">
	['${rank.ip }', ${rank.count }],
	</c:forEach>
  ]);

  var options = {
    title: '최다 접속 아이피'
  };

  var chart = new google.visualization.PieChart(document.getElementById('piechart'));

  chart.draw(data, options);
}

</script>
<script type="text/javascript">
    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);
    function drawChart() {
      var data = google.visualization.arrayToDataTable([
        ["ip", "접속수", { role: "style" } ],
        <c:forEach items="${topIpList }" var="rank">
    	['${rank.ip }', ${rank.count }, "#b87333"],
    	</c:forEach>
      ]);

      var view = new google.visualization.DataView(data);
      view.setColumns([0, 1,
                       { calc: "stringify",
                         sourceColumn: 1,
                         type: "string",
                         role: "annotation" },
                       2]);

      var options = {
        title: "최다 접속핑",
        width: 600,
        height: 400,
        bar: {groupWidth: "95%"},
        legend: { position: "none" },
      };
      var chart = new google.visualization.BarChart(document.getElementById("barchart_values"));
      chart.draw(view, options);
  }
  </script>
<style type="text/css">
.nav-lists-item, .xi-eye-o, .xi-eye-off-o:hover {
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="wrap">
		<!-- container, app, wrap 등... 쓴다. -->
		<!-- menu -->
		<%@ include file="menu.jsp"%>
		<!-- 본문내용 -->
		<div class="main">
			<article>
				<h1>IP 관리</h1>
				0. 중복없이 ip리스트 뽑기는?<br>
				1. 최다 접속 ip 5개 출력.<br>
				2. 그래프 그리기 - 구글차트 - ip당 접속 건수 - 10개?<br>
				<div class="nav-lists">
					<ul class="nav-lists-group">
						<li class="nav-lists-item" onclick="url('./ip?del=1')"><i class="xi-close-circle-o"></i></li>
						<li class="nav-lists-item" onclick="url('./ip?del=0')"><i class="xi-close-circle-o"></i></li>
					</ul>
					<div class="search">
						<input type="text" id="search">
						<button id="searchBtn">🔍</button>
					</div>
					<button onclick="location.href='./ip'">초기화</button>
				</div>
				<h3>🌟 최다 접속핑 🌟</h3>
				<c:forEach items="${topIpList }" var="rank">
					${rank.ip } / ${rank.count }회<br>
				</c:forEach>
				<h3>🎀 최신 접속핑 🎀</h3>
				<c:forEach items="${latestAccessIP10 }" var="latest">
					${latest.ip } / ${latest.date }<br>
				</c:forEach>
				<!-- 그래프로 그리기 -->
				<div id="piechart" style="width: 900px; height: 500px;"></div>
				<!-- 막대 그래프 -->
				<div id="barchart_values" style="width: 900px; height: 300px;"></div>
				<table>
				<thead>
					<tr>
						<th>ino</th>
						<th>ip</th>
						<th>날짜</th>
						<th>url</th>
						<th>data</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach items="${list }" var="row">
					<tr class="row">
						<td class="d1">${row.ino }</td>
						<td class="d1"><a href="./ip?ip=${row.iip }">${row.iip }</a></td>
						<td class="d2">${row.idate }</td>
						<td class="d1">${row.iurl }</td>
						<td class="title">${row.idata }</td>
					</tr>
				</c:forEach>
				</tbody>
				</table>
				
				
			</article>

		
		</div>
	</div>
</body>
</html>