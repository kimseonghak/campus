<%@page import="com.campus.community.upload.model.vo.Image"%>
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
<title>CampUs-중고장터</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/community/include/post.css">
	<style>
    	#post{
            margin:auto;
        }
        #postcontent-wrap{
        }
        .postcontent{
            width:100%;
        }
        .postcontentimg{
        	position: relative;
        	left:70px;
        }
        .postcontentdata{
        	position: absolute;
        	width:250px;
        	right:180px;
        	top:455px;
        	border-radius:10px;
        	border:solid 2px rgba(0, 25, 51, 0.9);
        	padding: 10px;
        	text-align:left;
        }
        .xi-angle-left{
        	position: absolute;
        	left:-40px;
        	top:120px;
        }
        .xi-angle-right{
        	position: absolute;
        	right:420px;
        	top:120px;
        }
        #photo{
            width:270px;
            border:solid rgba(0, 25, 51, 0.9) 2px;
        }
        #ex{
            margin-top:15px;
        }
        textarea{
			margin-left:33px;
			color:rgba(0, 25, 51, 0.9);
		}
        #postaction{
        	padding-left:60%;
        }
        table{
        	width:80%;
        	margin:auto;
        }
        #back{
        	width:80%;
        	margin:auto;
        }
    </style>
</head>
<body>
<% 
	MarketBoard marketBoard = (MarketBoard)request.getAttribute("marketBoard");
	String imgPath = (String)request.getAttribute("imgPath");
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
            
            <div id="where">&nbsp&nbsp&nbsp&nbsp 중고장터</div>
            
            <div id="post-wrap">
                <div id="post">
                    <div id="title" style="text-align:center">
                        <%= marketBoard.getMarketTitle() %>
                    </div>
                    <div id="postinfo">
                        작성자: <%= marketBoard.getUserId() %> &nbsp 댓글수: &nbsp&nbsp <span>작성일: <%= marketBoard.getMarketDate() %></span></span>
                     </div>
                    <div id="postcontent-wrap">
                        <div id="postaction">
                            <span><i class="xi-thumbs-up xi-x"></i></span>
                            <span><i class="xi-thumbs-down xi-x"></i></span>
                            <span><i class="xi-bookmark-o xi-x"></i></span>
                            <a id="btnTwitter" href="javascript:shareTwitter();"><i class="xi-twitter xi-x"></i></a>
                            <a id="btnFacebook" href="javascript:shareFacebook();"><i class="xi-facebook-official xi-x"></i></a>
							<a id="btnKakao" href="javascript:shareKakao();"><i class="xi-kakaotalk xi-x"></i></a>
							<a id="btnMail" href=""><i class="xi-mail-o xi-x"></i></a>
                            <span><i class="xi-share-alt-o xi-x"></i></span>
                        </div>
                        <%if(m!=null && m.getUserId().equals(marketBoard.getUserId())){ %>
                        <form action="/board/market/postUpdate.do" id="updateForm" method="post">
                        <div class="postcontent">
                        	<div class="postcontentimg">
                            	<i class="xi-angle-left xi-2x"></i>
                            	<% 
                            		int imgNo=marketBoard.getImgNo();
                            	%>
                                <img id="photo" src="<%=imgPath%>"/>
                                <i class="xi-angle-right xi-2x"></i>
                       		</div>
                       		<div class="postcontentdata">
                       			<span>제품명 : <%= marketBoard.getMarketProduct() %></span><br><br>
                       			<span>가격 : <%= marketBoard.getMarketPrice() %></span><br><br>
                       			<span>상태 : <%= marketBoard.getMarketCondition() %></span><br><br>
                       			<span>지역 : <%= marketBoard.getMarketLocation() %></span><br><br>
                       			<span>거래방법 : <%= marketBoard.getMarketTrade() %></span>
                       		</div>
                         </div>
                            
                            <div id="ex">
                                <textarea rows="20" cols="50" cols="84" disabled="true"><%= marketBoard.getMarketContent() %></textarea>
                            </div>
                            <br>
                            <button id="deleteBtn">삭제</button>
							<button id="updateBtn">수정</button>
							<button id="cancleBtn" style="display: none;">취소</button>
                           </form>
                          <%}else{ %>
                          	<div class="postcontent">
                        	<div class="postcontentimg">
                            	<i class="xi-angle-left xi-2x"></i>
                                <img id="photo" src=<%=imgPath %>/>
                                <i class="xi-angle-right xi-2x"></i>
                       		</div>
                       		<div>
                       			<span>제품명 : <%= marketBoard.getMarketProduct() %></span><br><br>
                       			<span>가격 : <%= marketBoard.getMarketPrice() %></span><br><br>
                       			<span>상태 : <%= marketBoard.getMarketCondition() %></span><br><br>
                       			<span>지역 : <%= marketBoard.getMarketLocation() %></span><br><br>
                       			<span>거래방법 : <%= marketBoard.getMarketTrade() %></span>
                       		</div>
                            </div>
                            
                            <div id="ex">
                                <textarea rows="20" cols="50" cols="84" disabled="true"><%= marketBoard.getMarketContent() %></textarea>
                            </div>
                          <%} %>
                        </div>
                    </div>
                    <div id="comment">
<table>
	<tr class="tr1">
        <td class="writer">이롸롸: </td>
        <td class="comment">헤이 맘맘맘마 헤이 맘맘맘마!</td>
        <td class="button">
            <button class="coModifyBtn">수정</button> <button class="coDeleteBtn">삭제</button>
        </td>
    </tr>
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
                        <div><a href=""><i class="xi-angle-left-min"></i>이전 글</a><a href="/">다음 글<i class="xi-angle-right-min"></i></a></div>
                        <div><a href="/board/market/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>"><i class="xi-paper-o xi-x"></i>목록으로</a></div>
                    </div>
                </div>
            </div>
        </div>
        </div>   
        
        <div id="footer">
            
        </div>
    </div>
    
    
    <script>
	$('#deleteBtn').click(function(){
		var result = window.confirm("삭제하시겠습니까?");
		if(result==true)
		{
			location.replace("/board/market/delete.do?marketBoard=<%=marketBoard%>");
		}
						});
    function shareTwitter() {
	    var sendText = "신나는 캠핑~";
	    var sendUrl = "127.0.0.1/board/market/selectOne?/board/market/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&marketNo=<%=marketBoard.getMarketNo()%>"; // 전달할 URL
	    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
	}
	function shareFacebook() {
	    var sendUrl = "127.0.0.1/board/market/selectOne?/board/market/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&marketNo=<%=marketBoard.getMarketNo()%>"; // 전달할 URL
	    window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
	}
    
    </script>
</body>
</html>