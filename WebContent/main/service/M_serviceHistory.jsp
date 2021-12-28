<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
    <style>
        * {
            box-sizing: border-box;
        }

        #wrap {
            width: 1250px;
            height: 1920px;

            box-sizing: border-box;
            margin: 0px auto;
        }

        #header-wrap {
            width: 100%;
            height: 100px;

        }

        #bodyWrap {
            width: 100%;
            height: 1000px;
        }

/* contentWrap */
        #ContentWrap {
            width: 100%;
            height: 450px;
        }
/* table css */
        #serviceTbl{
        	margin:0 auto;
        	width:100%;
        	overflow: hidden;
        	text-align:center;
        	table-layout: fixed;
        	border-spacing: 0px;
        }
        #serviceNo{
        	width:10%;
        }
		#serviceSubject{
			width:50%;
		}
		#serviceDate{
			width:20%;
		}
		#serviceCondition{
			width:20%;
		}
/* head css */
		#theadTr{
			height:40px;
		}
		.headTh{
			border-top:1px solid black;
			border-bottom:1px solid black;
		}
/* content css */
		.tbodyTr{
			height:60px;
		}
		.tbodyTr:hover{
			background-color: rgba(220,220,220,0.3);
		}
		.contentTd{
			border-bottom:1px solid rgba(220,220,220,0.7);
		}
		.contentTd>a{
			text-decoration: none;
			color:black;
		}
		#tfootTr{
			height:60px;
		}
		.answerCondition{
			padding:0px 70px;
		}
		#serviceRegister{
			border:1px solid #ff5000;
			height:40px;
			line-height: 40px;
			color:#ff5000;
		}
		#serviceEnd{
			border:1px solid #999;
			height:40px;
			line-height: 40px;
			color:#999
		}
		.navi{
			text-decoration: none;
			color:black;
			border:1px solid black;
			display:inline-block;
			width:25px;
			margin:0px -1px;;
		}
		#focusNavi{
			border-color:#ff5000;
		}
		.navi:hover{
			background-color: rgba(220,220,220,0.5);
		}
    </style>
</head>
<body>
<c:if test="${member==null }">
	<script>
		location.replace("/main/login/M_Login.jsp");
	</script>
</c:if>
    <div id="wrap">
        <div id="header-wrap">
            <%@include file="/common/include/gnb.jsp"%>
        </div>
        <div id="bodyWrap">
            <%@include file="/main/include/serviceBodyLogo.jsp" %>
            <div id="ContentWrap">
				<table id="serviceTbl">
					<thead id="tblHead">
						<tr id="theadTr">
							<th id="serviceNo" class="headTh">문의 번호</th>
							<th id="serviceSubject" class="headTh">문의 제목</th>
							<th id="serviceDate" class="headTh">문의 날짜</th>
							<th id="serviceCondtion" class="headTh">문의 상황</th>
						</tr>
					</thead>
					<tbody id="contentBody">
						<c:forEach items="${map.list }" var="s" varStatus="i">
							<tr class="tbodyTr">
								<td class="contentTd">${s.serviceNo }</td>
								<td class="contentTd"><a href="/main/serviceSelectContent.do?serviceNo=${s.serviceNo }&currentPage=${currentPage}">${s.serviceSubject }</a></td>
								<td class="contentTd">${s.serviceDate }</td>
								<td class="contentTd answerCondition">
									<c:choose>
										<c:when test="${String.valueOf(s.serviceAnswer) eq 'N' }">
										<div id="serviceRegister">접수</div>
										</c:when>
										<c:otherwise>
										<div id="serviceEnd">문의종료</div>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
					<tbody id="naviBody">
					<tr id="tfootTr">
						<td colspan="4" align="center">${map.pageNavi }</td>
					</tr>
					</tbody>
				</table>
            </div>
        </div>
    </div>
</body>
</html>