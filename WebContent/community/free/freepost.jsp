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
<style>
#postaction {
	
}
</style>
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
			<style>
	            .subBar>li>a {color: white;}
			</style>

		<div id="contents-wrap">
			<%@ include file="/community/include/sideNavi.jsp"%>
			<div id="contents">

				<%@ include file="/community/include/upimg.jsp"%>

				<div id="where">&nbsp&nbsp&nbsp&nbsp 자유게시판</div>

				<div id="post-wrap">
					<div id="post">
						<div id="title" style="text-align: center">
							<%=freeBoard.getFreeTitle()%>
						</div>
						<div id="postinfo">
							작성자:
							<%=freeBoard.getUserId()%>
							&nbsp 댓글수: &nbsp&nbsp 추천수:
							<%=freeBoard.getFreeLike()%>
							&nbsp&nbsp&nbsp비추천수:
							<%=freeBoard.getFreeHate()%>
							<span>작성일: <%=freeBoard.getFreeDate()%></span></span>
						</div>
						<div id="postcontent-wrap">
							<div id="postaction">
								<span><i class="xi-thumbs-up xi-x"></i></span> <span><i
									class="xi-thumbs-down xi-x"></i></span> <span><i
									class="xi-bookmark-o xi-x"></i></span> <a id="btnTwitter"
									class="link-icon twitter" href="javascript:shareTwitter();"><i
									class="xi-twitter xi-x"></i></a> <a id="btnFacebook"
									class="link-icon facebook" href="javascript:shareFacebook();"><i
									class="xi-facebook-official xi-x"></i></a> <a id="btnKakao"
									class="link-icon kakao" href="javascript:shareKakao();"><i
									class="xi-kakaotalk xi-x"></i></a> <a id="btnMail"
									class="link-icon email" href=""><i class="xi-mail-o xi-x"></i></a>
								<span><i class="xi-share-alt-o xi-x"></i></span>
							</div>
							<div id="postcontent">
								<%
									if (m != null && m.getUserId().equals(freeBoard.getUserId())) {
								%>
								<form action="/board/free/postUpdate.do" id="updateForm"
									method="post">
									<textarea id="postUpdate" cols="84" disabled="true"><%=freeBoard.getFreeContent()%></textarea>
									<br> <input type="hidden" name="boardNo"
										value="<%=freeBoard.getFreeNo()%>" />
								</form>

								<button id="deleteBtn">삭제</button>
								<button id="updateBtn">수정</button>
								<button id="cancleBtn" style="display: none;">취소</button>

								<%
									} else {
								%>
								<textarea cols="84" disabled="true"><%=freeBoard.getFreeContent()%></textarea>
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
											<td class="contentTd" style="border-bottom:1px solid black" align="center">${cf.userId }:</td>
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
<<<<<<< HEAD
										<tr style="height:35px">
											<td style="width:18%" align="center">
											댓글 작성 : 
											</td>
											<td colspan=2>
											<input type="text" size="70" name="commentContent">
=======
										<tr>
											<td colspan="2">
											댓글 작성 : <input type="text" size="60" name="commentContent">
>>>>>>> 9b9eef1082b3ab3f3ae05944430866e658a46ae2
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
									<tr id="tfootTr" align="center">
										<td colspan="3" >${map.CpageNavi }</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="back">
							<div>
<<<<<<< HEAD
								<a href="/board/free/selectOne.do?freeNo=<%=request.getAttribute("prevPost")%>&currentPage=<%=request.getAttribute("currentPage")%>">
								<i class="xi-angle-left-min"></i>이전 글</a>
								<a href="/board/free/selectOne.do?freeNo=<%=request.getAttribute("prevPost")%>&currentPage=<%=request.getAttribute("currentPage")%>">
								다음글<i class="xi-angle-right-min"></i>
=======
								<a href="/FreeBoardPrevPost.do?freeNo=${freeBoard.freeNo}"><i class="xi-angle-left-min"></i>이전 글</a>
								<a href="/FreeBoardNextPost.do?freeNo=${freeBoard.freeNo}">다음 글<i class="xi-angle-right-min"></i>
>>>>>>> 9b9eef1082b3ab3f3ae05944430866e658a46ae2
								</a>
							</div>
							<div>
								<a
									href="/board/free/listAll.do?currentPage=<%=request.getAttribute("currentPage")%>"><i
									class="xi-paper-o xi-x"></i>목록으로</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div id="footer"></div>
	</div>

	<script>
	var updateBtnFlag = false;
	var boardData;
	$('#updateBtn').click(function(){
		if(updateBtnFlag==false)
			{
				$('#postUpdate').prop('disabled',false);
				$('#updateBtn').text('완료');
				$('#cancleBtn').css('display','inline');	
				updateBtnFlag=true;
				boardData = $('#content').html();
			}
		else if(updateBtnFlag==true){
			$('#updateForm').submit();
		}
	});
	
	$('#cancleBtn').click(function(){
		$('#postUpdate').prop('disabled',true);
		$('#updateBtn').text('삭제');
		$('#cancleBtn').css('display','none');	
	});
	
	$('#deleteBtn').click(function(){
		var result = window.confirm("삭제하시겠습니까?");
		if(result==true)
		{
			location.replace("/board/free/delete.do?freeNo=<%=freeBoard.getFreeNo()%>");
		}
						});
	$('.coDeleteBtn').click(function(){
		var cFreeNo = $(this).next().val();
		if(window.confirm("삭제하시겠습니까?")){
			return true;
		}else{
			return false;
		}
	});
	function shareTwitter() {
	    var sendText = "신나는 캠핑~";
	    var sendUrl = "127.0.0.1/board/free/selectOne?/board/free/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&freeNo=<%=freeBoard.getFreeNo()%>"; // 전달할 URL
	    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
	}
	function shareFacebook() {
	    var sendUrl = "127.0.0.1/board/free/selectOne?/board/free/selectOne.do?currentPage=<%=request.getAttribute("currentPage") %>&freeNo=<%=freeBoard.getFreeNo()%>"; // 전달할 URL
	    window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
	}
	</script>
	<script>
		$('.navi').css("color",'black');
		$('.navi').css('border','1px solid black');
		$('.navi').css('display', 'inline-block');
		$('.navi').css('width','25px');
		$('.navi').css('margin','0px -2px');
		$('.navi').hover(function(){
			$(this).css('background-color','rgba(220,220,220,0.5)');
		},function(){
			$(this).css('background-color','white');
		});
	</script>
	<script type="text/javascript" src="/common/include/gnbWhite.js"></script>
</body>
</html>