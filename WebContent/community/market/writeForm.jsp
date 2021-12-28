<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CampUs-중고장터 게시글 작성</title>
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
            
            <div id="where">&nbsp&nbsp&nbsp&nbsp 중고장터</div>
            
            <div id="post-wrap">
                <div id="post">
                    <div id="postcontent-wrap">
                        <div id="postcontent">
                        <form action="/board/market/write.do" method="post">
			                <textarea rows="1" cols="100" placeholder="제목을 작성하세요"  name="marketTitle"></textarea><br><br>
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
							<textarea rows="20" cols="100" placeholder="내용을 작성하세요"  name="marketContent"></textarea><br><br>
							<input type="submit" value="완료"> <button type="button" id="backBtn">목록</button> <input type="reset" value="취소" > 
						</form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>   
    </div>
<script>
	$(function(){
		$('#backBtn').click(function(){
				window.location.href="/board/market/listAll.do?currentPage=<%=request.getParameter("currentPage") %>";
		});
	});
</script>
</body>
</html>