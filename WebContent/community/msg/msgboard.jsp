<%@page import="com.campus.board.msg.model.vo.MsgPage"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.campus.board.msg.model.vo.MsgBoard"%>
<%@page import="com.campus.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	MsgPage msgpage=(MsgPage)request.getAttribute("msgpage");
	ArrayList<MsgBoard> list = msgpage.getPageList();
	Member m = (Member) session.getAttribute("member");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>CampUs-쪽지함</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<style>
        *{box-sizing: border-box;}
        #wrap{
            width:100%;
            margin: 0px auto;
        }
        #where{
            margin:0px auto;
            width:100%;
            font-size: 20px;
            color:rgb(0, 0, 40);
            font-weight:700;
            float:left;
        }
        #board{
            width:100%;
            margin:0px auto;
        }
        table{
            margin-top:5px;
            width:100%;
            border-top:1px solid black;
            border-bottom:1px solid black;
            border-spacing: 0px;
            font-size: 14px;
        }
        th{
            height:35px;
            color:rgb(0, 0, 40);
        }
        th:first-of-type{
            border-right: solid 1px rgb(0, 0, 40);
        }
        td{
            height:28px;
            border-top:1px solid black;
        }
        td:first-of-type{
            border-right: solid 1px rgb(0, 0, 40);
        }
        .no{
        	width:8%;
        }
        .writer{
        	width:10%;
        }
        .title{
        	width:60%;
        }
        a{
            text-decoration:none;
            color:black;
        }
        .writer:hover>a{
            text-decoration:underline;
        }
        #page{
        	margin-top:30px;
        }
        #search{
            width:100%;
            margin-top:30px;
            padding-left:20%;
        }
    </style>
</head>
<body>
	<div id="wrap">
            <div id="where">&nbsp&nbsp&nbsp&nbsp 쪽지함</div>
            <div id="search">
            <form action="/board/msg/search.do" method="get">
				<select id="select" name="type">
			    	<option value="msgTitle">제목</option>
			        <option value="userId">ID</option>
			        <option value="all">제목+ID</option>
			    </select>
			        <%String keyword = request.getParameter("keyword");%>
			        <%if(keyword!=null){ %>
			            <input type="text" name="keyword" value="<%=keyword%>"> <input type="submit" value="검색">
			        <%}else{%>
			            <input type="text" name="keyword"> <input type="submit" value="검색">
			        <%} %>
			        </form>
            </div>
            <div id="board">
                
<table id="table1" style="text-align: center;">
	<tr>
		<th class="no">번호</th>
        <th class="writer">보낸 이</th>
        <th class="writer">받는 이</th>
        <th class="date">제목</th>
	</tr>
    <%for(MsgBoard msgboard : list){%>
    <tr style="text-align: center;">	
        <td class="no"><%=msgboard.getMsgNo()%></td>
        <td class="writer"><%=msgboard.getSendId()%></td>
        <td class="writer"><%=msgboard.getReceiveId()%></td>
        <td class="title"><a href="/board/msg/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&msgNo=<%=msgboard.getMsgNo()%>"><%=msgboard.getMsgTitle()%></a></td>
        <td class="date"><%=msgboard.getMsgDate()%></td>
	</tr>
	<%}%>
</table>
            <div id="page" style="text-align: center;"><%=msgpage.getPageNavi()%></div>
            	<form action="/community/msg/writeForm.jsp" method="post">
					<%if (m != null) {%>
						<input type="submit" value="글쓰기">
					<%}%>
				</form>
            </div>
    </div>
</body>
</html>