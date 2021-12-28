<%@page import="com.campus.board.notice.model.vo.NoticePage"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.campus.board.notice.model.vo.NoticeBoard"%>
<%@page import="com.campus.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	NoticePage noticepage = (NoticePage)request.getAttribute("noticepage");
	ArrayList<NoticeBoard> list = noticepage.getPageList();
	Member m = (Member) session.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>CampUs-공지게시판</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/community/include/board.css">
<link rel="stylesheet" href="/community/include/table.css">
	<style>
		#arrange {
            padding-left:80%;
        }
		th{
		    height:35px;
		    color:rgb(0, 0, 40);
		}
		td{
    		height:28px;
    		border-top:1px solid black;
		}
		.title{
    		width:60%;
		}
		table a{
    	text-decoration:none;
    	color:black;
		}
		.title:hover>a{
    		text-decoration:underline;
		}
		.like{width:60px;}
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
            <div id="where">&nbsp&nbsp&nbsp&nbsp 공지사항</div>
            <form id="arrange" action="/board/boardSearch.do" method="get">
               <button type="button" id="myfind" class="btn2"><img src=""/>즐겨찾기</button>
            </form>
            <div id="board">
<table id="table1" style="text-align: center;">
	<tr>
		<th>번호</th>
        <th>작성자</th>
		<th>제목</th>
		<th>추천</th>
		<th>작성일</th>
	</tr>

	<%for(NoticeBoard noticeboard : list){%>
    <tr>	
        <td class="no"><%=noticeboard.getNoticeNo()%></td>
        <td class="writer"><%=noticeboard.getBusinessName()%></td>
		<td class="title"><a href="/board/notice/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&noticeNo=<%=noticeboard.getNoticeNo()%>" > <%=noticeboard.getNoticeTitle()%> </a></td>
        <td class="like"><%=noticeboard.getNoticeLike()%></td>
		<td class="date"><%=noticeboard.getNoticeDate()%></td>
	</tr>
	<%}%>
</table>

            </div>
            <div id="page" style="text-align: center;"><%=noticepage.getPageNavi()%></div>
    <div id="search" action="/board/notice/search.do" method="get">
        <select id="select" name="noticeTitle">
            <option value="userId">작성자</option>
        </select>

        <%String keyword = request.getParameter("noticeTitle");%>
		<%if(keyword!=null){ %>
			<input type="text" name="keyword" value="<%=keyword%>"> <input type="submit" value="검색">
		<%}else{%>
			<input type="text" name="keyword"> <input type="submit" value="검색">
		<%} %>
    </div>
    <form action="/community/notice/writeForm.jsp?currentPage=<%=request.getAttribute("currentPage") %>" method="post">
		
		
		<%if (m!=null && m.getUserId().toUpperCase().equals(list.get(0).getBusinessId())) {%>
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