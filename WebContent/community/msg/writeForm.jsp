<%@page import="com.campus.board.msg.model.vo.MsgBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	MsgBoard msgboard=(MsgBoard)request.getAttribute("msgboard");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CampUs-쪽지함 쪽지 작성</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/community/include/post.css">
	<style>
        textarea{
			resize: none;
			font-size: 20px;
		}
    </style>
</head>
<body>
	<div id="wrap">
		<div id="where">&nbsp&nbsp&nbsp&nbsp 쪽지함</div>
	            
	    <div id="post">
	    
		    <form action="/board/msg/write.do" method="post">
		    	<textarea rows="1" cols="100" placeholder="보내는 사람"  name="sendId"></textarea><br><br>
		        <textarea rows="1" cols="100" placeholder="받는 사람"  name="receiveId"></textarea><br><br>
			    <textarea rows="1" cols="100" placeholder="제목을 작성하세요"  name="msgTitle"></textarea><br><br>
				<textarea rows="20" cols="100" placeholder="내용을 작성하세요"  name="msgContent"></textarea><br><br>
				<input type="submit" value="전송"> <button type="button" id="backBtn">목록</button> <input type="reset" value="취소" > 
			</form>
        </div>
    </div>
<script>
	$(function(){
		$('#backBtn').click(function(){
				window.location.href="/board/msg/listAll.do";
		});
	});
</script>
</body>
</html>