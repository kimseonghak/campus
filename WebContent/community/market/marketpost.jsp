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
        </div>
        
        <div id="contents-wrap">
        <%@ include file="/community/include/sideNavi.jsp" %>
        <div id="contents">
            
            <%@ include file="/community/include/upimg.jsp" %>
            
            <div id="where"><a href="/board/market/listAll.do">&nbsp&nbsp&nbsp&nbsp 중고장터</a></div>
            
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
									<i class="xi-kakaotalk xi-x"></i></a>
									<a id="btnMail" class="link-icon email" href="mailto:?body=http://127.0.0.1/board/market/selectOne.do?marketNo=${marketBoard.marketNo}&currentPage=<%=request.getAttribute("currentPage") %>">
									<i class="xi-mail-o xi-x"></i></a>
                            	</div>
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
                       			제품명 : <input type="text" name="marketProduct" placeholder="제품명을 입력하세요" value="${marketBoard.marketProduct }"/><br>
								가격 : <input type="text" name="marketPrice" placeholder="가격을 입력하세요" value="${marketBoard.marketPrice }"/><br>
								
								상태 :
								<c:set var = "marketCondition" scope = "session" value = "${marketBoard.marketCondition}"/> 
								<c:choose>
									<c:when test="${marketCondition=='상' }">
										<input type="radio" name="marketCondition" value="상" checked/>상
										<input type="radio" name="marketCondition" value="중"/>중
										<input type="radio" name="marketCondition" value="하"/>하<br>
									</c:when>
									<c:when test="${marketCondition=='중'}">
										<input type="radio" name="marketCondition" value="상"/>상
										<input type="radio" name="marketCondition" value="중" checked/>중
										<input type="radio" name="marketCondition" value="하"/>하<br>
									</c:when>
									<c:when test="${marketCondition=='하'}">
										<input type="radio" name="marketCondition" value="상"/>상
										<input type="radio" name="marketCondition" value="중"/>중
										<input type="radio" name="marketCondition" value="하" checked/>하<br>
									</c:when>
									<c:otherwise>
										<input type="radio" name="marketCondition" value="상"/>상
										<input type="radio" name="marketCondition" value="중"/>중
										<input type="radio" name="marketCondition" value="하"/>하<br>
									</c:otherwise>
								</c:choose>
								
								지역 : <input type="text" name="marketLocation" placeholder="지역을 입력하세요" value="${marketBoard.marketLocation }"/><br>
								거래방식 : ${marketBoard.marketTrade}<br>
								
									<!-- <input type="checkbox" name="marketTrade" value="직거래"/>직거래
									<input type="checkbox" name="marketTrade" value="우체국택배"/>우체국 택배<br>
									<input type="checkbox" name="marketTrade" value="편의점택배"/>편의점 택배
									<input type="checkbox" name="marketTrade" value="퀵"/>퀵 이용<br>
									<input type="checkbox" name="marketTrade" value="기타"/>기타<br><br> -->
								
                       		</div>
                        </div>
                            
                        <div id="ex">
                            <textarea name="content" rows="20" cols="50" cols="84" disabled="true"><%= marketBoard.getMarketContent() %></textarea>
                            <br> <input type="hidden" name="boardNo" value="<%=marketBoard.getMarketNo()%>" />
							<br> <input type="hidden" name="currentPage" value="<%=request.getAttribute("currentPage")%>" />
                        </div>
                        <br>
                        <button type="button" id="deleteBtn">삭제</button>
						<button id="updateBtn">수정</button>
						<button id="cancleBtn" style="display: none;">취소</button>
                        </form>
                          <%}else{ %>
                          	<div class="postcontent">
                        	<div class="postcontentimg">
                            	<i class="xi-angle-left xi-2x"></i>
                                <img id="photo" src="<%=imgPath %>"/>
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
                        <div>
							<a href="/MarketBoardPrevPost.do?marketNo=${marketBoard.marketNo}"><i class="xi-angle-left-min"></i>이전 글</a>
							<a href="/MarketBoardNextPost.do?marketNo=${marketBoard.marketNo}">다음 글<i class="xi-angle-right-min"></i></a>
						</div>
						<div>
							<a href="/board/market/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>">
							<i class="xi-paper-o xi-x"></i>목록으로</a>
						</div>
                    </div>
                </div>
            </div>
        </div>
        
        <div id="footer">
            <jsp:include page="/common/include/footer.jsp"/>
        </div>
    </div>
    
    <script>
		function shareTwitter() {
		    var sendText = "신나는 캠핑~";
		    var sendUrl = "http://127.0.0.1/board/market/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&marketNo=<%=marketBoard.getMarketNo()%>"; // 전달할 URL
		    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
		}
		function shareFacebook() {
		    var sendUrl = "http://127.0.0.1/board/market/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&marketNo=<%=marketBoard.getMarketNo()%>"; // 전달할 URL
		    window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
		}
		function shareKakao() {
			Kakao.init('7e924b2e9ad57bbcb7a285a9f6793057');
			 
			Kakao.Link.createDefaultButton({
				container: '#btnKakao', 
			    objectType: 'feed',
			    content: {
			    	title: "즐거운 캠핑, CampUs",
			    	description: "CampUs에서 즐겨요",
			    	imageUrl: "127.0.0.1${img.imgPath}",
			    	link: {
			    		webUrl: "http://127.0.0.1/board/market/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&marketNo=<%=marketBoard.getMarketNo()%>"
			    	}
				}
			});
		}
    </script>
    
    
    
    <script>
		$('#deleteBtn').click(function(){
			var result = window.confirm("삭제하시겠습니까?");
			if(result==true) {
				location.replace("/board/market/delete.do?marketNo=${marketBoard.marketNo}"); 
			}
		});
	</script>
	
    <script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
    <script type="text/javascript" src="/common/include/gnbWhite.js"></script>
	<script src="/community/include/clickpost.js"></script>
</body>
</html>