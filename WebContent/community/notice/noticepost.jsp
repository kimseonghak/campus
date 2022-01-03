<%@page import="com.campus.member.model.vo.Business"%>
<%@page import="com.campus.board.market.model.vo.MarketBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>CampUs-공지사항</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/community/include/post.css">
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
            
            <div id="where"><a href="/board/notice/listAll.do">&nbsp&nbsp&nbsp&nbsp 공지사항</a></div>
            <div id="post-wrap">
                <div id="post">
                    <br>
                    <div id="title" style="text-align:center">디스 이즈 제목</div>
                    <div id="postinfo">작성자: ${noticeBoard.businessName } &nbsp 추천수:</div>
                    <div id="postcontent-wrap">
                        <div id="postaction">
                            <span><i class="xi-thumbs-up xi-x"></i></span>
                            <span><i class="xi-thumbs-down xi-x"></i></span>
                            <span><i class="xi-bookmark-o xi-x"></i></span>
                            <span><i class="xi-share-alt-o xi-x"></i></span>
                        </div>
                        
                        <div id="modal">
                           	<div id="modal-close"><i class="xi-close-min xi-x"></i></div>
                           	<div>
                           		<a id="btnTwitter" class="link-icon twitter" href="javascript:shareTwitter();">
                           		<i class="xi-twitter xi-x"></i></a>
	                           	<a id="btnFacebook" class="link-icon facebook" href="javascript:shareFacebook();">
	                           	<i class="xi-facebook-official xi-x"></i></a>
								<a id="btnKakao" class="link-icon kakao" href="javascript:shareKakao();">
								<i class="xi-kakaotalk"></i></a>
								<a id="btnMail" class="link-icon email" href="mailto:?body=http://127.0.0.1/board/notice/selectOne.do?noticeNo=${noticeBoard.noticeNo}&currentPage=<%=request.getAttribute("currentPage") %>">
								<i class="xi-mail-o xi-x"></i></a>
                           	</div>
                        </div>
                        
                        <div id="postcontent">
                          <c:choose>
                        	<c:when test="${sessionScope.business.businessName eq '관리자'}">
								<form action="/board/notice/postUpdate.do?currentPage=<%=request.getAttribute("currentPage")%>" id="updateForm" method="post">
									<textarea id="postUpdate" name="content" cols="84" rows="20" disabled="true">${noticeBoard.noticeContent }</textarea>
									<br> <input type="hidden" name="boardNo" value="${noticeBoard.noticeNo }" />
								</form>

								<button id="deleteBtn">삭제</button>
								<button id="updateBtn">수정</button>
								<button id="cancleBtn" style="display: none;">취소</button>
							</c:when>
							<c:otherwise>
								<textarea cols="84" rows="20" disabled="true">${noticeBoard.noticeContent }</textarea>
							</c:otherwise>
                          </c:choose>
                        </div>
                    </div>
                </div>
                    <div id="back">
                    	<div>
							<a href="/NoticeBoardPrevPost.do?noticeNo=${noticeBoard.noticeNo}"><i class="xi-angle-left-min"></i>이전 글</a>
							<a href="/NoticeBoardNextPost.do?noticeNo=${noticeBoard.noticeNo}">다음 글<i class="xi-angle-right-min"></i></a>
						</div>
						<div>
							<a href="/board/notice/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>">
							<i class="xi-paper-o xi-x"></i>목록으로</a>
						</div>
                   </div>
			</div>
        </div>
        </div>   
        <!-- <a href="#" onclick="window.open(url_combine_mail, '', 'scrollbars=no, width=480, height=735'); return false;">
    <img src="https://cache.addthiscdn.com/icons/v3/ thumbs/32x32/email.png" title="이메일로 공유하기" alt="Email"></a> -->
        <div id="footer">
            <jsp:include page="/common/include/footer.jsp"/>
        </div>
    </div>

	<script src="/community/include/clickpost.js"></script>
	<script>
		/* 페이지를 메일로 보내기
		 var url_default_mail="https://api.addthis.com/oexchange/0.8/forward/email/offer?url=";
		 var url_this_page = location.href;
		 var title_this_page = document.title;
		 var url_combine_mail = url_default_mail + url_this_page + "&title=" + title_this_page ; */
		$('#deleteBtn').click(function(){
			var result = window.confirm("삭제하시겠습니까?");
			if(result==true) {
				location.replace("/board/notice/delete.do?noticeNo=${noticeBoard.noticeNo}");
			}
		});
		$('.xi-share-alt-o').click(function() {
		    var div = $('#modal');
		    div.css("position", "absolute");
		    div.css("top", "440px");
		    div.css("right", "100px");
		    $('#modal').fadeIn();
		});
	</script>
	<script type="text/javascript" src="/common/include/gnbWhite.js"></script>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
</body>
</html>