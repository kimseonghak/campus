<%@page import="com.campus.member.model.vo.Member"%>
<%@page import="com.campus.board.free.model.vo.FreeBoard"%>
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
<script src="https://code.jquery.com/jquery-3.6.0.js"
	integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/community/include/post.css">
</head>
<body>
	<%
		FreeBoard freeBoard = (FreeBoard) request.getAttribute("freeBoard");
		Member m = (Member) session.getAttribute("member");
	%>
	<div id="wrap">

		<div id="header-wrap">
			<%@ include file="/common/include/gnb.jsp"%>
		</div>

		<div id="contents-wrap">
			<%@ include file="/community/include/sideNavi.jsp"%>
			<div id="contents">

				<%@ include file="/community/include/upimg.jsp"%>

				<div id="where"><a href="/board/free/listAll.do">&nbsp&nbsp&nbsp&nbsp 자유게시판</a></div>

				<div id="post-wrap">
					<div id="post">
						<div id="title" style="text-align: center">
							<%=freeBoard.getFreeTitle()%>
						</div>
						<div id="postinfo">
							<span>작성자: <%=freeBoard.getUserId()%></span>
							<span>댓글수:</span>
							<span>추천수: <%=freeBoard.getFreeLike()%></span>
							<span>비추천수: <%=freeBoard.getFreeHate()%></span>
							<span>작성일: <%=freeBoard.getFreeDate()%></span>
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
									<a id="btnMail" class="link-icon email" href="mailto:?body=http://127.0.0.1/board/free/selectOne.do?freeNo=${freeBoard.freeNo}&currentPage=<%=request.getAttribute("currentPage") %>">
									<i class="xi-mail-o xi-x"></i></a>
	                           	</div>
	                        </div>
							
							<div id="postcontent">
								<%
									if (m != null && m.getUserId().equals(freeBoard.getUserId())) {
								%>
								<form action="/board/free/postUpdate.do" id="updateForm"
									method="post">
									<textarea id="postUpdate" name="content" cols="84" rows="20" disabled="true"><%=freeBoard.getFreeContent()%></textarea>
									<br> <input type="hidden" name="boardNo" value="<%=freeBoard.getFreeNo()%>" />
									<br> <input type="hidden" name="currentPage" value="<%=request.getAttribute("currentPage")%>" />
								</form>

								<button id="deleteBtn">삭제</button>
								<button id="updateBtn">수정</button>
								<button id="cancleBtn" style="display: none;">취소</button>

								<%
									} else {
								%>
								<textarea cols="84" rows="20" disabled="true"><%=freeBoard.getFreeContent()%></textarea>
								<br>
								<%
									}
								%>
							</div>
						</div>
						<div id="comment">
							<table>
								<thead>
									<tr>
										<th style="width:18%">작성자</th>
										<th style="width:60%">댓글 내용</th>
										<th style="width:15%">댓글 작성 일시</th>
										<th style="width:7%">삭제</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${map.list }" var="cf" varStatus="i">
										<tr class="tbodyTr">
											<td class="contentTd" style="border-bottom:1px solid black" align="center">${cf.userId }</td>
											<td class="contentTd" style="border-bottom:1px solid black">${cf.cFreeConents }</td>
											<td class="contentTd" style="border-bottom:1px solid black" align="center">${cf.cFreeDate }</td>
											<c:choose>
												<c:when test="${cf.userId eq sessionScope.member.userId }">
													<td style="border-bottom:1px solid black" align="center">
													<form action="/board/free/commentDelete.do" method="get">
														<input type="submit" class="coDeleteBtn" value="X">
														<input type="hidden" value="${cf.cFreeNo }" name="cFreeNo">
														<input type="hidden" name="currentPage" value=${currentPage }>
														<input type="hidden" name="freeNo" value=<%=freeBoard.getFreeNo() %>>
														<input type="hidden" name="commentPage" value=${commentPage }>
													</form>
													</td>
												</c:when>
												<c:otherwise>
													<td style="border-bottom:1px solid black"></td>
												</c:otherwise>
											</c:choose>
										</tr>
									</c:forEach>
									<c:if test="${member!=null }">
									<form action="/board/free/commentWrite.do" method="post">
										<tr>
											<td colspan="2">
											댓글 작성 : <input type="text" size="60" name="commentContent">
											<input type="hidden" name="currentPage" value=${currentPage }>
											<input type="hidden" name="freeNo" value=<%=freeBoard.getFreeNo() %>>
											<input type="hidden" name="commentPage" value=${commentPage }>
											</td>
											<td align="center"><button>작성</button></td>
										</tr>
									</form>
									</c:if>
								</tbody>
								<tbody id="naviBody">
									<tr id="tfootTr">
										<td colspan="3" align="center">${map.CpageNavi }</td>
									</tr>
							</table>
						</div>
						<div id="back">
							<div>
								<a href="/FreeBoardPrevPost.do?freeNo=${freeBoard.freeNo}"><i class="xi-angle-left-min"></i>이전 글</a>
								<a href="/FreeBoardNextPost.do?freeNo=${freeBoard.freeNo}">다음 글<i class="xi-angle-right-min"></i>
								</a>
							</div>
							<div>
								<a href="/board/free/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>">
								<i class="xi-paper-o xi-x"></i>목록으로</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="footer"><jsp:include page="/common/include/footer.jsp"/></div>
	</div>

	<script>
		$('#deleteBtn').click(function(){
			var result = window.confirm("삭제하시겠습니까?");
			if(result==true) {
				location.replace("/board/free/delete.do?freeNo=<%=freeBoard.getFreeNo()%>");
			}
		});
	</script>
	<script>
	    function shareTwitter() {
		    var sendText = "신나는 캠핑~";
		    var sendUrl = "http://127.0.0.1/board/free/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&freeNo=<%=freeBoard.getFreeNo()%>"; // 전달할 URL
		    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
		}
		function shareFacebook() {
		    var sendUrl = "http://127.0.0.1/board/free/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&freeNo=<%=freeBoard.getFreeNo()%>"; // 전달할 URL
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
			    		webUrl: "http://127.0.0.1/board/free/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&freeNo=<%=freeBoard.getFreeNo()%>"
			    	}
				}
			});
		}
    </script>
	<script>
		$('.navi').css("color",'black');
		$('.navi').css('border','1px solid black');
		$('.navi').css('display', 'inline-block');
		$('.navi').css('width','25px');
		$('.navi').css('margin','0px -2px');
		$('.navi').hover(
			function(){
				$(this).css('background-color','rgba(220,220,220,0.5)');
			},
			function(){
				$(this).css('background-color','white');
			});
	</script>
	<script type="text/javascript" src="/common/include/gnbWhite.js"></script>
	<script src="/community/include/clickpost.js"></script>
	<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
</body>
</html>