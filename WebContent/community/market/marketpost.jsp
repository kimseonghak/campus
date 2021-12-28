<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>CampUs-중고장터</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/community/include/post.css">
	<style>
    	#post{
            float:left;
        }
        #postcontent-wrap{
            float:left;
        }
        #postcontent{
            width:100%;
            float:left;
        }
        #photo{
            margin-left:14px;
            width:270px;
            border:solid rgb(0, 0, 40) 2px;
            float:left;
        }
        form{
            width:300px;
            height:;
            float:left;
            margin-left:20px;
            border: solid rgb(0, 0, 40) 1px;
            border-radius: 40px;
            padding:25px;
            font-size:14px;
        }
        #ex{
            margin-top:15px;
            float:left;
        }
        #postaction{
            padding-left:72%;
        }
        .phototurn{
            margin-top:18%;
            margin-left:10px;
            width:20px;
            height:20px;
            float:left;
        }
        #product{
            float:left;
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
            
            <div id="where">&nbsp&nbsp&nbsp&nbsp 중고장터</div>
            
            <div id="post-wrap">
                <div id="post">
                    <div id="title" style="text-align:center">
                        디스 이즈 제목
                    </div>
                    <div id="postinfo">
                        작성자: 김뫄뫄 &nbsp 댓글수: &nbsp 추천수:
                    </div>
                    <div id="postcontent-wrap">
                        <div id="postaction">
                            <button><i class="xi-thumbs-up xi-x"></i></button>
                            <button><i class="xi-thumbs-down xi-x"></i></button>
                            <button><i class="xi-bookmark-o xi-x"></i></button>
                            <a id="btnTwitter" class="link-icon twitter" href="javascript:shareTwitter();"></a>
                            <a id="btnFacebook" class="link-icon facebook" href="javascript:shareFacebook();"></a>
							<a id="btnKakao" class="link-icon kakao" href="javascript:shareKakao();"></a>
							<a id="btnMail" class="link-icon email" href=""><i class="xi-mail-o xi-x"></i></a>
                            <button><i class="xi-share-alt-o xi-x"></i></button>
                        </div>
                        <div id="postcontent">
                            <div id="product">
                                <img class="phototurn" id="left" src="../image/common/left.png">
                                <img id="photo" src="../image/merch/latan.png"/>
                                <img class="phototurn" id="right" src="../image/common/next.png">
                                <form>
                                    제품명 : <input type="text" name="userId" placeholder="제품명을 입력하세요"/><br>
		가격 : <input type="text" name="userPwd" placeholder="가격을 입력하세요"/><br>
		상태 : <input type="radio" name="userPwd_re" value="양호"/>양호<input type="radio" name="userPwd_re" value="중고"/>중고<input type="radio" name="userPwd_re" value="나쁨"/>나쁨<br>
		지역 : <input type="text" name="userName" placeholder="이름를 입력하세요"/><br>
		거래방식 : <br>
			  <input type="checkbox" name="hobby" value="운동"/>직거래
			  <input type="checkbox" name="hobby" value="등산"/>우체국택배
			  <input type="checkbox" name="hobby" value="독서"/>편의점택배<br>
			  <input type="checkbox" name="hobby" value="노래"/>퀵 이용
			  <br><br>
		<input type="submit" value="저장"/><input type="reset" value="다시 입력하기"/><br><br>
                                </form>
                            </div>
                            <div id="ex">
                                헤이 모두들 안녕 내가 누군지 아늬?<br>
                                이하!늬다! 이하!늬다!<br>
                                어쩌고 저쩌고<br>
                                힛띵 마 드럼스 라잌<br>
                                덤디디데이~<br>
                                아 랔 더 덜띠 릐듬 유 플레이~<br>
                            </div>
                        </div>
                    </div>
                    <div id="comment">
<table>
<!--<tr><th>작성자</th><th>댓글</th><th>날짜</th></tr>-->
	<tr class="tr1">
        <td class="writer">이롸롸: </td>
        <td class="button">
            <button class="coModifyBtn">수정</button> <button class="coDeleteBtn">삭제</button>
        </td>
    </tr>
    <tr>
        <td class="comment">헤이 맘맘맘마 헤이 맘맘맘마!</td>
        <td></td>
	</tr>
    <tr class="tr1">
        <td class="writer">이롸롸: </td>
        <td class="button">
            <button class="coModifyBtn">수정</button> <button class="coDeleteBtn">삭제</button>
        </td>
    </tr>
    <tr>
        <td class="comment">헤이 맘맘맘마 헤이 맘맘맘마!</td>
        <td></td>
	</tr>
</table>
                    </div>
                    <div id="back">
                        <a href=""><img src="../image/common/back.png"/>이전 글</a>&nbsp&nbsp&nbsp<a src="">다음 글<img  src="../image/common/front.png"/></a><br>
                        <a href="/board/market/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>">목록으로</a>
                    </div>
                </div>
            </div>
        </div>
        </div>   
        
        <div id="footer">
            
        </div>
    </div>
</body>
</html>