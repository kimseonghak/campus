<%@page import="com.campus.board.free.model.vo.FreeBoard"%>
<%@page import="com.campus.board.free.model.vo.FreePage"%>
<%@page import="com.campus.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	FreePage freepage=(FreePage)request.getAttribute("freepage");
	ArrayList<FreeBoard> list = freepage.getPageList();
	Member m = (Member) session.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>CampUs-자유게시판</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<style>
		<link rel="stylesheet" href="../include/board.css">
		<link rel="stylesheet" href="../include/table.css">
        #arrange{
            padding-left:60%;
        }
    </style>
</head>
<body>
    <div id="wrap">
        
        <div id="header-wrap">
            <%@ include file="/common/include/gnb.jsp" %>
        </div>
        
        <div id="contents-wrap">
        	<%@ include file="/community/include/sideNavi.jsp" %>
	        <div id="contents">
	            
	            <%@ include file="/community/include/upimg.jsp" %>
	            
	            <div id="where">&nbsp&nbsp&nbsp&nbsp 자유게시판</div>
	            
	            <form id="arrange">
	                <button type="button" id="list1" class="btn1"><i id="titlelist" class="xi-list-dot xi-x"></i></button><button type="button" id="list2" class="btn1"><i id="titlelist" class="xi-apps xi-x"></i></button>
	                <button type="button" id="mypost" class="btn2">내 글</button><button type="button" id="mycmt" class="btn2">내 댓글</button><button type="button" id="myfind" class="btn2"><img src=""/>즐겨찾기</button>
	            </form>
	            
	            <div id="board">
	            
<table id="table1" style="text-align: center;">
	<tr>
		<th>번호</th>
        <th>작성자</th>
		<th>제목</th>
        <th>댓글</th>
		<th>추천</th>
		<th>작성일</th>
	</tr>
    <%for(FreeBoard freeboard : list){%>
    <tr>	
        <td class="no"><%=freeboard.getFreeNo()%></td>
        <td class="writer"><%=freeboard.getUserId()%></td>
		<td class="title"><a href="/board/free/selectOne.do?freeNo=<%=freeboard.getFreeNo()%>"><%=freeboard.getFreeTitle()%>[댓글]</a></td>
        <td class="hit"><%=freeboard.getFreeHit()%></td>
        <td class="like"><%=freeboard.getFreeLike()%></td>
		<td class="date"><%=freeboard.getFreeDate()%></td>
	</tr>
	<%}%>
</table>

<ul id="table2" style="text-align: center;">
	<%for(FreeBoard freeboard : list){%>
    <li>
        <a href="/board/free/selectOne.do?freeNo=<%=freeboard.getFreeNo()%>"><img src="../image/merch/griddle.png"/></a>
        <dl>
            <dt><a href="/board/free/selectOne.do?freeNo=<%=freeboard.getFreeNo()%>"><%=freeboard.getFreeTitle()%></a>[댓글]</dt>
            <dd><div><%=freeboard.getUserId()%></div></dd>
            <dd><span><%=freeboard.getFreeDate()%></span>&nbsp&nbsp&nbsp<span><%=freeboard.getFreeHit()%></span>&nbsp&nbsp&nbsp<span><%=freeboard.getFreeLike()%></span></dd>
        </dl>
    </li>
    <%}%>
</ul>
            	</div>
				<%=freepage.getPageNavi()%>
			    <div id="search">
			    	<form action="/board/free/search.do" method="get">
			        <select id="select" name="type">
			            <option value="freeTitle">제목</option>
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
			    <form action="/community/free/writeForm.jsp" method="post">
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