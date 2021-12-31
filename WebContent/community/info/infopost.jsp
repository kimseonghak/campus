<%@page import="com.campus.board.info.model.vo.InfoBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>CampUs-정보공유게시판</title>
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
	InfoBoard infoBoard = (InfoBoard)request.getAttribute("infoBoard");
	Member m = (Member)session.getAttribute("member");
%>
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
            <div id="where">&nbsp&nbsp&nbsp&nbsp 정보공유게시판</div>
            
            <div id="post-wrap">
                <div id="post">
                    <div id="title" style="text-align:center"><%= infoBoard.getInfoTitle() %></div>
                    <div id="postinfo">
                        작성자: <%= infoBoard.getUserId() %> &nbsp 댓글수: &nbsp&nbsp 추천수: <%= infoBoard.getInfoLike() %> &nbsp&nbsp&nbsp비추천수: <%= infoBoard.getInfoHate() %> <span>작성일: <%= infoBoard.getInfoDate() %></span></span>
                    </div>
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
                           <%if(m!=null && m.getUserId().equals(infoBoard.getUserId())){ %>
							<form action="/board/info/postUpdate.do" id="updateForm" method="post">
								<textarea rows="20" cols="50" id="postUpdate" cols="84" disabled="true"><%= infoBoard.getInfoContent() %></textarea><br>
								<input type="hidden" name="boardNo" value="<%=infoBoard.getInfoNo()%>"/>
							</form>
	
							<button id="deleteBtn">삭제</button>
							<button id="updateBtn">수정</button>
							<button id="cancleBtn" style="display: none;">취소</button>
							
							<%}else{ %>
								<textarea rows="20" cols="50" cols="84" disabled="true"><%= infoBoard.getInfoContent() %></textarea><br>
							<%} %>
                        </div>
                    </div>
                    <div id="comment">
<table>
<!--<tr><th>작성자</th><th>댓글</th><th>날짜</th></tr>-->
	<tr class="tr1">
        <td class="writer">이롸롸: </td>
        <td class="comment">헤이 맘맘맘마 헤이 맘맘맘마!</td>
        <td class="button">
            <button class="coModifyBtn">수정</button> <button class="coDeleteBtn">삭제</button>
        </td>
    </tr>
</table>
                    </div>
                    <div id="back">
                        <div><a href=""><i class="xi-angle-left-min"></i>이전 글</a><a href="">다음 글<i class="xi-angle-right-min"></i></a></div>
                        <div><a href="/board/info/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>"><i class="xi-paper-o xi-x"></i>목록으로</a></div>
                    </div>
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