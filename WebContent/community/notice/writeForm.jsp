<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CampUs-공지게시판 게시글 작성</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="../include/post.css">
	<style>
        #postaction{
            padding-left:63%;
        }
        textarea{
		resize: none;
		font-size: 20px;
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
	            
	            <div id="where">&nbsp&nbsp&nbsp&nbsp 공지게시판</div>
	            
	            <div id="post-wrap">
	                <div id="post">
		                <form action="/board/notice/write.do" method="post">
			                <textarea rows="1" cols="100" placeholder="제목을 작성하세요"  name="noticeTitle"></textarea><br><br>
							<textarea rows="20" cols="100" placeholder="내용을 작성하세요"  name="noticeContent"></textarea><br><br>
							<input type="submit" value="완료"> <button type="button" id="backBtn">목록</button> <input type="reset" value="취소" > 
						</form>
                    </div>
            	</div>
        	</div>
    	</div>
	</div>
<script>
	$(function(){
		$('#backBtn').click(function(){
				window.location.href="/board/notice/listAll.do?currentPage=<%=request.getParameter("currentPage") %>";
		});
	});
</script>
</body>
</html>