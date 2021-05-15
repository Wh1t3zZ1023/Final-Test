<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	/* 图片每3秒循环换 */
	var curIndex = 0;
	var timeInterval = 3000;
	var arr = new Array();
	arr[0] = "${pageContext.request.contextPath}/images/1.jpg";
	arr[1] = "${pageContext.request.contextPath}/images/2.jpg";
	arr[2] = "${pageContext.request.contextPath}/images/3.jpg";
	setInterval(changeImg, timeInterval);
	function changeImg() {
		var obj = document.getElementById("obj");
		if (curIndex == arr.length - 1) {
			curIndex = 0;
		} else {
			curIndex += 1;
		}
		obj.src = arr[curIndex];
	};
	window.onload = function() {
		document.getElementById("obj").onclick = function() {
			var obj = document.getElementById("obj");
			if (curIndex == arr.length - 1) {
				curIndex = 0;
			} else {
				curIndex += 1;
			}
			obj.src = arr[curIndex];
		}
	}
</script>
</head>
<body bgcolor="#e5eecc">

	<h3>新闻浏览</h3>

	<div align="center">
		<h4>校内近期新闻</h4>
	</div>
	<div align="center">	    	   
		<img id="obj" src="${pageContext.request.contextPath}/images/1.jpg"
			width="700">
		
	</div>
	
	<p>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图一：第六届中国国际“互联网+”大学生创新创业大赛总决赛,广工获得2金1银。图二：如何全面贯彻党的教育方针，为党育人、为国育才？在接受采访时，广东工业大学党委书记胡钦太表示，习近平总书记的重要讲话为新时代办好教育指明了方向，今后将以高质量党建引领学校高质量发展，坚持立德树人根本任务，坚持扎根南粤大地办大学，打好关键核心技术攻坚战，不断增强教育服务创新发展能力。图三：广工图书馆</p>
</body>
</html>