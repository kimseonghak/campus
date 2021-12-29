<%@page import="com.campus.reservation.model.vo.CampingArea"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--Jquery 라이브러리 & JqueryUI라이브러리-->
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<!--jQuery UI CSS -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" />
<!--부트스트랩 CSS파일 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

<!--아이콘CSS -->
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CampUS reservation</title>

<link rel="stylesheet" type="text/css"
	href="/reservation/css/reservation.css">




</head>
<!--캠핑장 검색  달력 start-->
<script type="text/javascript">
	$(function() {
		weather('Seoul');

		$("#from").datepicker(
				{
					changeMonth : true,
					changeYear : true,
					nextText : '다음 달',
					prevText : '이전 달',
					dateFormat : 'yy-mm-dd',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ]
				});
		$("#to").datepicker(
				{
					changeMonth : true,
					changeYear : true,
					nextText : '다음 달',
					prevText : '이전 달',
					dateFormat : 'yy-mm-dd',
					monthNames : [ '1월', '2월', '3월', '4월', '5월', '6월', '7월',
							'8월', '9월', '10월', '11월', '12월' ],
					monthNamesShort : [ '1월', '2월', '3월', '4월', '5월', '6월',
							'7월', '8월', '9월', '10월', '11월', '12월' ],
					dayNames : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesShort : [ '일', '월', '화', '수', '목', '금', '토' ],
					dayNamesMin : [ '일', '월', '화', '수', '목', '금', '토' ]
				});
	});

	/* 지도 */

	function showPopup() {
		window.open("/reservation/views/mapSearch.jsp", "map",
				"width=700px, height=600px, left=100, top=100");
	};
	
	function searchList(){
		if($("#from").val() > $("#to").val()){
			
			alert("날짜를 확인하세요");
			$("#from").focus();

			return;
		} 
		document.serachPanel.submit();
	}
</script>
<!--캠핑장 검색  달력 end -->
<body>

	<%
		String from = "";

		if (request.getParameter("from") == null) {
			from = "";
		} else {
			from = request.getParameter("from");
		}

		String to = "";

		if (request.getParameter("to") == null) {
			to = "";
		} else {
			to = request.getParameter("to");
		}

		//페이징 처리되어 넘어온 데이터를 가져와야 함
		HashMap<String, Object> pageDataMap = (HashMap<String, Object>) request.getAttribute("pageDataMap");

		ArrayList<CampingArea> list = (ArrayList<CampingArea>) pageDataMap.get("list");
		String pageNavi = (String) pageDataMap.get("pageNavi");
		int currentPage = (int) request.getAttribute("currentPage");
		//	String keyword = (String)request.getAttribute("keyword");
		//String cpNm = "";
	%>


	<div id="wrapper">
		<div id="header">
			<%@ include file="/common/include/gnb.jsp"%>
		</div>
		<div id="body">
			<div class="searchArea">
				<form id="serachPanel" mehtod="get" action="/reservation/SelectCampingList.do" onSubmit="searchList();return false">
					<div class="search">
						<label for="form">어디로 가나요?</label><br>
						<select name="location" class="form" style="width: 150px; height: 40px; font-size: 20px;">
							<option value="">전체</option>
							<option value="가평">가평</option>
							<option value="경기">경기</option>
							<option value="인천">인천</option>
							<option value="강원">강원</option>
							<option value="충청">충청</option>
							<option value="경상">경상</option>
							<option value="전라">전라</option>
							<option value="제주">제주</option>
						</select>
					</div>
					<div class="search">
						<label for="form">일정이 언제에요?</label><br> <input type="text"
							id="from" style="text-align: center" name="from" size="12"
							readonly />

					</div>
					<div class="search">
						<label for="form">언제까지에요?</label><br> <input type="text"
							id="to" style="text-align: center" name="to" size="12" readonly />
					</div>
					<br>
					<div id="searchBTN">
						<a href="/reservation/SelectCampingList.do">
							<button type="submit" class="btn btn-outline-light"
								style="width: 120px; , height: 30px; font-size: 20px;">
								<i class="xi-search"></i> 검색
							</button>
						</a>
					</div>
				</form>

			</div>

			<div id="contentArea">
				<div id="leftArea">
					<div class="resultArea">
						<br>
						<div style="display: inline-block; font-size: 25pt;">
							<img class="searchlogo"
								src='../../reservation/image/main/select2.png'> 캠핑장 정보
						</div>
						<div
							style="display: inline-block; font-size: 15pt; float: right; padding-right: 20px; padding-top: 5px;">
							<input class='mapbtn' type="button" value="지도에서 검색" onclick="showPopup();" />
						</div>

						<table class="table table-striped" style="width: 880px;">
							<%
								if (!list.isEmpty()) {
									for (CampingArea campingArea : list) {
							%>

							<tr style="border: 1px solid gary;">
								<td rowspan="4" style="border: 1px solid gray; width: 300px;">
									<a
									href="/reservation/SelectCampingAreaList.do?bsnNo=<%=campingArea.getBusinessNo()%>&from=<%=from%>&to=<%=to%>">
										<img src="<%=campingArea.getFilename()%>"
										onerror="this.src='../../reservation/image/main/sample_camping/default_300_200.jpg'" />
								</a>
								</td>
							</tr>
							<tr style="border: 1px solid gray; height: 15px;">
								<td
									style="display: inline-block; margin: 4px; padding: 5px 7px; line-height: 1; border-radius: 6px; background: #FFC946; border: 1px solid #FFC946; color: #fff; text-align: center; font-size: 20px;">
									<%=campingArea.getCampType()%></td>
							</tr>
							<tr
								style="border: 1px solid gray; font-size: 20pt; height: 40px;">
								<td style="border: 1px solid gray; font-weight: bold;"><%=campingArea.getBusinessName()%></td>
							</tr>
							<tr
								style="border: 1px solid gray; font-size: 18pt; height: 40px;">
								<td style="border: 1px solid gray;">주소 : <%=campingArea.getBusinessAddress()%></td>
								<%
									}
									}
								%>
							
						</table>
						<div style='text-align: center; font-size: 18pt;'>
							<%=pageNavi%>
						</div>
					</div>
				</div>

				<div id="rightArea">
					<br>

					<!-- 날씨정보 START -->
					<h2>
						<img class="searchlogo"
							src='../../reservation/image/main/select5.png'> 날씨정보
					</h2>
					</a>
					<%@ include file="/reservation/views/weather.jsp"%>
					<!-- 날씨정보 END -->

					<div id="mapArea">

						<br>
						<!-- 지도정보 START -->
						<div>



							<!-- 	<%@ include file="/reservation/views/map.jsp" %> -->
						</div>
						<!-- 지도정보 END -->
					</div>
				</div>
				<div id="footer">
					<%@include file="/common/include/footer.jsp" %>
				</div>
			</div>
</body>
</html>