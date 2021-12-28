<%@page import="com.campus.board.market.model.vo.MarketBoard"%>
<%@page import="com.campus.board.market.model.vo.MarketPage"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.campus.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	MarketPage marketpage=(MarketPage)request.getAttribute("marketpage");
	ArrayList<MarketBoard> list = marketpage.getPageList();
	Member m = (Member) session.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>CampUs-중고장터</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/community/include/board.css">
<link rel="stylesheet" href="/community/include/table.css">
	<style>
        #arrange {
            padding-left:60%;
        }
    </style>
</head>
<body>
	<div id="wrap">
        
        <div id="header-wrap">
            <%@ include file="/common/include/gnb.jsp" %>
            <style>
	            .subBar>li>a {color: white;}
			</style>
        </div>
        
        <div id="contents-wrap">
        <%@ include file="/community/include/sideNavi.jsp" %>
        <div id="contents">
            
            <%@ include file="/community/include/upimg.jsp" %>
            
            <div id="where">&nbsp&nbsp&nbsp&nbsp 중고장터</div>
            
            <form id="arrange">
                <button type="button" id="list1" class="btn1"><i id="titlelist" class="xi-list-dot"></i></button><button type="button" id="list2" class="btn1"><i id="piclist" class="xi-apps"></i></button>
                <button type="button" id="mypost" class="btn2">내 글</button><button type="button" id="mycmt" class="btn2">내 댓글</button><button type="button" id="myfind" class="btn2"><img src=""/>즐겨찾기</button>
            </form>
            
            <div id="board">

<table id="table1" style="text-align: center;">
	<tr>
		<th style="width:7%;">번호</th>
        <th style="width:10%;">작성자</th>
        <th style="width:9%;">마감</th>
		<th style="width:46%;">제목</th>
		<th style="width:8%;">가격</th>
		<th style="width:8%;">조회수</th>
		<th style="width:12%;">작성일</th>
	</tr>
    <%for(MarketBoard marketboard : list){%>
    <tr>	
        <td class="no"><%=marketboard.getMarketNo()%></td>
        <td class="writer"><%=marketboard.getUserId()%></td>
        <td class="del_yn"><%=marketboard.getMarketWithdrawal()%></td>
		<td class="title"><a href="/board/market/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&marketNo=<%=marketboard.getMarketNo()%>"><%=marketboard.getMarketTitle()%>[댓글]</a></td>
        <td class="price"><%=marketboard.getMarketPrice()%></td>
        <td class="hit"><%=marketboard.getMarketHit()%></td>
		<td class="date"><%=marketboard.getMarketDate()%></td>
	</tr>
	<%}%>
</table>

<ul id="table2" style="text-align: center;">
    <%for(MarketBoard marketboard : list){%>
    <li>
        <a href="/board/market/selectOne.do?marketNo=<%=marketboard.getMarketNo()%>"><img src="../image/merch/griddle.png"/></a>
        <dl>
            <dt><a href="/board/market/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&marketNo=<%=marketboard.getMarketNo()%>"><%=marketboard.getMarketTitle()%></a>[댓글]</dt>
            <dd><div><%=marketboard.getUserId()%><%=marketboard.getMarketPrice()%></div></dd>
            <dd><span><%=marketboard.getMarketDate()%></span>&nbsp&nbsp&nbsp<span><%=marketboard.getMarketHit()%></span></dd>
        </dl>
    </li>
    <%}%>
</ul>
            </div>
            <div id="page" style="text-align: center;"><%=marketpage.getPageNavi()%></div>
    <div id="search">
    <form action="/board/market/search.do" method="get">
        <select id="select" name="type">
            <option value="marketTitle">제목</option>
			<option value="userId">작성자</option>
			<option value="all">제목+작성자</option>
        </select>
        <%String keyword = request.getParameter("keyword");%>
		<%if(keyword!=null){ %>
			<input type="text" name="keyword" value="<%=keyword%>"> <input type="submit" value="검색">
		<%}else{%>
			<input type="text" name="keyword"> <input type="submit" value="검색">
		<%} %>
    </form>
    </div>
    	<form action="/community/market/writeForm.jsp?currentPage=<%=request.getAttribute("currentPage") %>" method="post">
			<%if (m != null) {%>
				<input type="submit" value="글쓰기">
			<%}%>
		</form>
        </div>
        </div>   
        
        <div id="footer">
            
        </div>
        
    </div>
    
<script src="/community/include/click.js"></script>
</body>
</html>