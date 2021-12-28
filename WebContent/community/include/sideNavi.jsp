<%@page import="com.campus.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<style>
		#mylist{
            height:100%;
            padding-right:30px;
            position:fixed;
            border: 5px solid rgb(0, 0, 40);
            border-bottom-right-radius:50px;
            border-top-right-radius:50px;
            background-color:rgb(0, 0, 20);
        }
        #listground{
            padding-top:10%;
            margin-left:10%;
            height:100%;
            color:white;
        }
        #listground>div{
            font-size: 25px;
        }
        #listground>ul{
            width:120%;
            font-size:17px;
        }
        #listground>ul>li:hover{
            background-color:skyblue;
            font-weight: 1000;
            color:rgb(0, 0, 40);
        }
        #listground>ul>li{
            list-style:none;
            height:50px;
            padding-top:12px;
        }
        #listground a{
            text-decoration: none;
            color:white;
        }
        #msg{
        	color:white;
        }
        .xi-note-o {
    		color: white;
		}
	</style>
</head>
<body>
	<div id="mylist">
    	<div id="listground">
	        <div>커뮤니티</div>
            <ul>
                <li><a href="/board/notice/listAll.do">공지사항</a></li>
   	            <li><a href="/board/free/listAll.do">자유게시판</a></li>
       	        <li><a href="/board/info/listAll.do">정보공유게시판</a></li>
           	    <li><a href="/board/market/listAll.do">중고장터</a></li>
           	    <li><a href="\">통합채팅</a></li>
           	    <%if((Member)session.getAttribute("member")!=null){ %>
               	<li id="msg">쪽지함<i class="xi-note-o xi-x"></i></li>
               	<%} %>
           	</ul>
    	</div>
	</div>
	<%if((Member)session.getAttribute("member")!=null){ %>
	<script>
		document.getElementById('msg').onclick=function popup(){
			var newWin = window.open("/board/msg/listAll.do?userId=<%=((Member)session.getAttribute("member")).getUserId()%>","_blank","width=700px, height=400px");
		}
	</script>
	<%} %>
</body>
</html>