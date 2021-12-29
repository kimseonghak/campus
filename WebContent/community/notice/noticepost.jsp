<%@page import="com.campus.board.notice.model.vo.NoticeBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>CampUs-자유게시판</title>
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
	NoticeBoard noticeBoard = (NoticeBoard)request.getAttribute("noticeBoard");
	Member m = (Member)session.getAttribute("member");
%>
	<div id="wrap">
        
        <div id="header-wrap">
            <%@ include file="../../common/include/gnb.jsp" %>
        </div>
        
        <div id="contents-wrap">
        <%@ include file="../include/sideNavi.jsp" %>
        <div id="contents">
            
            <%@ include file="../include/upimg.jsp" %>
            
            <div id="where">&nbsp&nbsp&nbsp&nbsp 공지사항</div>
            
            <div id="post-wrap">
                <div id="post">
                    <br>
                    <div id="title" style="text-align:center">디스 이즈 제목</div>
<<<<<<< HEAD
                    <div id="postnotice">작성자: <%= noticeBoard.getBusinessName() %> &nbsp 추천수: <%= noticeBoard.getNoticeLike() %> &nbsp&nbsp&nbsp비추천수: <%= noticeBoard.getNoticeHate() %> <span>작성일: <%= noticeBoard.getNoticeDate() %></span>
                    </div>
=======
                    <div id="postinfo">작성자: ${noticeBoard.businessId } &nbsp 추천수:</div>
>>>>>>> b66ff1efed68650d098dd370d78e5e73684d5957
                    <div id="postcontent-wrap">
                        <div id="postaction">
                            <span><i class="xi-thumbs-up xi-x"></i></span>
                            <span><i class="xi-thumbs-down xi-x"></i></span>
                            <span><i class="xi-bookmark-o xi-x"></i></span>
                            <a id="btnTwitter" class="link-icon twitter" href="javascript:shareTwitter();"><i class="xi-twitter xi-x"></i></a>
                            <a id="btnFacebook" class="link-icon facebook" href="javascript:shareFacebook();"><i class="xi-facebook-official xi-x"></i></a>
							<a id="btnKakao" class="link-icon kakao" href="javascript:shareKakao();"><i class="xi-kakaotalk xi-x"></i></a>
							<a id="btnMail" class="link-icon email" href=""><i class="xi-mail-o xi-x"></i></a>
                            <span><i class="xi-share-alt-o xi-x"></i></span>
                        </div>
                        <div id="postcontent">
<<<<<<< HEAD
                            <%if(m!=null && m.getUserId().equals(noticeBoard.getBusinessId())){ %>
							<form action="/board/notice/postUpdate.do" id="updateForm" method="post">
								<textarea rows="20" cols="50" id="postUpdate" cols="84" disabled="true"><%= noticeBoard.getNoticeContent() %></textarea><br>
								<input type="hidden" name="boardNo" value="<%=noticeBoard.getNoticeNo()%>"/>
							</form>
	
							<button id="deleteBtn">삭제</button>
							<button id="updateBtn">수정</button>
							<button id="cancleBtn" style="display: none;">취소</button>
							
							<%}else{ %>
								<textarea rows="20" cols="50" cols="84" disabled="true"><%= noticeBoard.getNoticeContent() %></textarea><br>
							<%} %>
=======
                            ${noticeBoard.noticeContent }
>>>>>>> b66ff1efed68650d098dd370d78e5e73684d5957
                        </div>
                    </div>
                </div>
                    <div id="back">
<<<<<<< HEAD
                        <div><a href=""><i class="xi-angle-left-min"></i>이전 글</a><a href="">다음 글<i class="xi-angle-right-min"></i></a></div>
                        <div><a href="/board/notice/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>"><i class="xi-paper-o xi-x"></i>목록으로</a></div>
=======
                        <a href="">이전 글</a>&nbsp&nbsp&nbsp<a src="">다음 글</a><br>
                        <a href="/board/notice/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>">목록으로</a><br><br>
>>>>>>> b66ff1efed68650d098dd370d78e5e73684d5957
                    </div>
            </div>
        </div>
        </div>   
        
        <div id="footer">
            
        </div>
    </div>

<script src="/community/include/clickpost.js"></script>
</body>
</html>