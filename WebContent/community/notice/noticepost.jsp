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
                    <div id="postinfo">작성자: 김뫄뫄 &nbsp 추천수:</div>
                    <div id="postcontent-wrap">
                        <div id="postaction">
                            <button><i class="xi-thumbs-up xi-x"></i></button>
                            <button><i class="xi-thumbs-down xi-x"></i></button>
                            <button><i class="xi-bookmark-o xi-x"></i></button>
                            <a id="btnTwitter" class="link-icon twitter" href="javascript:shareTwitter();"></a>
                            <a id="btnFacebook" class="link-icon facebook" href="javascript:shareFacebook();"></a>
							<a id="btnKakao" class="link-icon kakao" href="javascript:shareKakao();"></a>
							<a id="btnMail" class="link-icon email" href="javascript:shareKakao();"><i class="xi-mail-o xi-x"></i></a>
                            <button><i class="xi-share-alt-o xi-x"></i></button>
                        </div>
                        <div id="postcontent">
                            헤이 모두들 안녕 내가 누군지 아늬?<br>
                            이하!늬다! 이하!늬다!<br>
                            어쩌고 저쩌고<br>
                            힛띵 마 드럼스 라잌<br>
                            덤디디데이~<br>
                            아 랔 더 덜띠 릐듬 유 플레이~<br>
                        </div>
                    </div>
                </div>
                    <div id="back">
                        <a href=""><img src="../image/common/back.png"/>이전 글</a>&nbsp&nbsp&nbsp<a src="">다음 글<img  src="../image/common/front.png"/></a><br>
                        <a href="/board/notice/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>">목록으로</a><br><br>
                    </div>
            </div>
        </div>
        </div>   
        
        <div id="footer">
            
        </div>
    </div>

<script>
    $('#msg').click(function(){
        $(this).children('ul').children('li').slideToggle(300);
    });
    function shareTwitter() {
    var sendText = "개발새발"; // 전달할 텍스트
    var sendUrl = "/"; // 전달할 URL
    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
}
    function shareFacebook() {
    var sendUrl = "/"; // 전달할 URL
    window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
}
</script>
</body>
</html>