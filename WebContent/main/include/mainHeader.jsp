<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- xeicon -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>Insert title here</title>
<style>
#header-wrap {
	width: 100%;
	height: 663px;
}

#header-1 {
	width: 100%;
	height: 100px;
}

#logoWrap {
	width: 15%;
	height: 100%;
	float: left;
}

#logo {
	margin: 0 auto;
}

#GNB {
	position:relative;
	width: 70%;
	height: 100%;
	float: left;
	z-index:1;
}

#GNB>ul {
	padding: 0px;
	margin: 0px;
	width: 100%;
	height: 100%;
	text-align: center;
}

#GNB>ul>li {
	list-style: none;
	display: block;
	float: left;
	color: black;
	line-height: 100px;
	width: 25%;
	font-size: 24px;
	font-weight: bolder;
}

#GNB>ul>li:hover>a {
	color: skyblue;
}

#GNB>ul>li>a {
	display: block;
	width: 100%;
	height: 100%;
	text-decoration: none;
	color: black;
}

.subBar {
	padding: 0px;
	margin: 0px;
	width: 500px;
	text-align: center;
}

#firstSub {
	margin: 0 50px;
}

#secondSub {
	margin: 0 30px;
}

#thirdSub {
	margin: 0 50px;
}

#fourthSub {
	margin: 0 50px;
}

.subBar>li {
	list-style: none;
	width: 20%;
	height: 50px;
	line-height: 50px;
	font-size: 16px;
	font-weight: bold;
	float: left;
	display: none;
}

.subBar>li>a {
	color: white;
	width: 100%;
	height: 100%;
	text-decoration: none;
	border-radius: 50px;
	display: block;
}

.subBar>li>a:hover {
	background-color: skyblue;
}

#loginWrap {
	width: 15%;
	height: 100%;
	float: right;
}

#loginWrap2 {
	width: 15%;
	height: 100%;
	float: right;
	font-size: 18px;
	padding: 10px;
}

#loginWrap3 {
	width: 15%;
	height: 100%;
	float: right;
	font-size: 18px;
	padding: 10px;
}

#loginWrap2>a {
	color: black;
	text-decoration: none;
}
#loginWrap3>a {
	color: black;
	text-decoration: none;
}

.userIcon:hover>.xi-user-o {
	color: gray;
}

.userIcon:hover {
	color: gray;
}

.notes:hover>.xi-note-o {
	color: gray;
}

.xi-user-o {
	color: black;
}

.xi-note-o {
	color: black;
}

.logout:hover {
	color: gray;
}

#loginWrap>a {
	display: block;
	width: 70%;
	height: 100%;
	font-size: 20px;
	text-align: end;
	color: black;
	text-decoration: none;
	margin-left: 60px;
}

/* ?????? ?????? */
#mainLogoWrap {
	width: 100%;
	height: 563px;
}

#mainLogoImg {
	position: absolute;
	left: 0;
	width: 100%;
	height: 563px;
}

/* ?????? ?????? ?????? ?????? */
#mainReservation {
	position:relative;
	width: 100%;
	height: 150px;
	background-image: url(/main/image/mainlogo/stars.png);
	border-radius: 70px;
	margin: -65px 0px;
	padding:50px 0px;
	z-index:1;
}

#mainReservationSearch {
	width: 60%;
	height: 60px;
	float:left;
	text-align: center;
}

/* ????????? css */
#mainSelectInfo{
	width: 40%;
	height:60px;
	text-align: right;
	bolder:1px solid blue;
	float:left;
	font-size: 30px;
	font-weight:bolder;
	line-height:60px;
	color:white;
}
#selectSearch{
	margin-top:7px;
	width:300px;
	height:40px;
	font-size:30px;
	text-align: center;
}
/* btn????????? css */
#imageBtn{
	position:relative;
	left:200px;
	top:-50px;
	cursor: pointer;
}

</style>
</head>
<body>
<body>
	<div id="header-wrap">
		<div id="header-1">
			<div id="logoWrap">
				<a href=""><img src="/main/image/logo/logo2.png" id="logo"
					alt=""></a>
			</div>
			<div id="GNB">
				<ul>
					<li><a href="/board/notice/listAll.do">Board</a>
						<ul class="subBar" id="firstSub">
							<li><a href="/board/notice/listAll.do">????????????</a></li>
							<li><a href="/board/free/listAll.do">?????? ?????????</a></li>
							<li><a href="/board/info/listAll.do">????????????</a></li>
							<li><a href="/board/market/listAll.do">????????????</a></li>
							<li><a href="">?????? ??????</a></li>
						</ul></li>
					<li><a href="/reservation/SelectCampingList.do">Reservation</a>
						<ul class="subBar" id="secondSub">
							<li><a href="/reservation/SelectCampingList.do">?????? ??????</a></li>
						</ul></li>
					<li><a href="/diary/allView.do">MyPage</a>
						<ul class="subBar" id="thirdSub">
							<li><a href="/userPage/userPage.do">??? ?????? ??????</a></li>
							<li><a href="/diary/allView.do">????????????</a></li>
						</ul></li>
					<li><a href="/main/service/M_service.jsp">Service</a>
						<ul class="subBar" id="fourthSub">
							<li><a href="/main/service/M_service.jsp">FAQ</a></li>
						</ul></li>
				</ul>
			</div>
		<c:choose>
			<c:when test="${business!=null }">
				<div id="loginWrap3">
					<a href="/admin/dashboard/index.jsp" class="userIcon"><i class="xi-user-o xi-2x"></i>
						${business.businessName }</a>???<br> <a href="/admin/dashboard/index.jsp" class="notes"><i
						class="xi-note-o xi-2x"></i></a> <a href="/main/logout.do" class="logout">logout</a>
				</div>
			</c:when>
			<c:when test="${member!=null }">
				<div id="loginWrap2">
					<a href="/userPage/userPage.do" class="userIcon"><i class="xi-user-o xi-2x"></i>
						${member.userName }</a>???<br> <a onclick=notes() class="notes" style="cursor:pointer"><i
						class="xi-note-o xi-2x"></i></a> <a href="/main/logout.do" class="logout">logout</a>
				</div>
			</c:when>
			<c:otherwise>
				<div id="loginWrap">
					<a href="/main/login/M_Login.jsp">login<img
						src="/main/image/logo/2lock.jpg" alt=""></a>
				</div>
			</c:otherwise>
		</c:choose>
		</div>
		<div id="mainLogoWrap">
			<a href="/"><img src="/main/image/mainlogo/campUs.png" alt=""
				id="mainLogoImg"></a>
		</div>
		<div id="mainReservation">
			<div id="mainSelectInfo">
				?????? ?????? ????????? ???????????????.
			</div>
			<div id="mainReservationSearch">
				<form action="/reservation/SelectCampingList.do" method="get" id="mainSearchForm">
					<select id="selectSearch" name="location">
						<option value="">??????</option>
						<option value="??????">??????</option>
						<option value="??????">??????</option>
						<option value="??????">??????</option>
						<option value="??????">??????</option>
						<option value="??????">??????</option>
						<option value="??????">??????</option>
						<option value="??????">??????</option>
						<option value="??????">??????</option>
					</select>
				</form>
				<img alt="" src="/main/image/search/searchLeaf.png" id="imageBtn">
			</div>
		</div>
	</div>
	<script>
		$('#GNB>ul>li').hover(function() {
			$(this).children().children().slideToggle(300);
		});
		$('#imageBtn').click(function(){
			$('#mainSearchForm').submit();
		});
	</script>
</body>
</html>