<%@page import="com.campus.board.msg.model.vo.MsgBoard"%>
<%@page import="com.campus.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>CampUs-쪽지함</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/community/include/post.css">
	<style>
        #postaction{
            padding-left:63%;
        }
    </style>
</head>
<body>
<% 
	MsgBoard msgBoard = (MsgBoard)request.getAttribute("msgBoard");
	Member m = (Member)session.getAttribute("member");
%>
	<div id="wrap">
        
        <div id="header-wrap">
            <%@ include file="/common/include/gnb.jsp" %>
        </div>
        
        <div id="contents-wrap">
        <%@ include file="/community/include/sideNavi.jsp" %>
        <div id="contents">
            
            <%@ include file="/community/include/upimg.jsp" %>
            
            <div id="where">&nbsp&nbsp&nbsp&nbsp 쪽지함</div>
            
            <div id="post-wrap">
                <div id="post">
                    <div id="title" style="text-align:center">
                    	<%= msgBoard.getMsgTitle() %>
                    </div>
                    <div id="postinfo">
                        작성자: <%= msgBoard.getSendId()%> 작성일: <%= msgBoard.getMsgDate() %>
                    </div>
                    <div id="postcontent-wrap">
                        <div id="postcontent">
	                        <%if(m!=null && m.getUserId().equals(msgBoard.getSendId())){ %>
								<button id="deleteBtn">삭제</button>
								<button id="cancleBtn" style="display: none;">취소</button>
							<%}else{ %>
								<textarea cols="50" disabled="true"><%= msgBoard.getMsgContent() %></textarea><br>
							<%} %>
                        </div>
                    </div>
                    <div id="back">
                        <a href="/board/msg/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>">목록으로</a>
                    </div>
                </div>
            </div>
        </div>
        </div>   
        
        <div id="footer">
            
        </div>
    </div>

<script>
	$('#cancleBtn').click(function(){
		location.replace('/board/msg/selectOne.do?currentPage=<%=request.getAttribute("currentPage")%>&msgNo=<%=msgBoard.getMsgNo()%>');
	});
	
	$('#deleteBtn').click(function(){
		var result = window.confirm("삭제하시겠습니까?");
		if(result==true) {
			location.replace("/board/msg/delete.do?msgNo=<%=msgBoard.getMsgNo()%>");	
		}
	});
</script>
</body>
</html>