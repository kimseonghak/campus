<%@page import="java.util.ArrayList"%>
<%@page import="com.campus.userPage.model.vo.UserWish"%>
<%@page import="com.campus.userPage.model.vo.UserReservation"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>CAMP : US</title>


    <!-- jQuery 라이브러리-->
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>

    <!-- xeicon -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">


    <style>
        * {
            box-sizing: border-box;
        }

        #wrap {
            width: 1250px;
            margin: 0px auto;
        }

        #header-wrap {
            z-index: 100;
        }

        #body {
            width: 1250px;
            margin: 0px auto;
        }

        /* 예약정보 */
        #reservation-area {
            width: 1000px;
            height: 400px;
            background-color: #F6F6F6;
            margin: 50px auto 20px;
            z-index: -100;
        }

        #img-area {
            width: 400px;
            height: 400px;
            float: left;
        }

        #img-circle {
            width: 300px;
            height: 300px;
            margin: 50px 50px;
            border-radius: 200px;
            text-align: center;
            overflow: hidden;
        }

        #img {
            width: 100%;
            height: 100%;
            object-fit: cover;
            /* 가로 세로는 100% 이미지 중앙으로 맞춰짐, 가로세로를 가득 채울때까지 확대*/
        }

        #reser-area {
            width: 600px;
            height: 400px;
            float: left;
        }

        #reservation {
            width: 500px;
            height: 350px;
            margin-top: 20px;
            margin-left: 50px;
            margin-right: 50px;

        }

        #name {
            width: 500px;
            height: 100px;
            text-align: center;
            padding-top: 20px;
        }

        #name>h3 {
            text-align: center;
            padding-bottom: 5px;
        }

        #res-infor {
            width: 500px;
            height: 250px;
            text-align: center;
            padding-top: 10px;
        }

        #res-infor>p {
            text-align: center;
        }

        #reservation-plus {
            width: 580px;
            height: 30px;
            text-align: right;
            margin: 0 auto;
        }


        /* 찜한 상품 */
        #like-area {
            width: 1250px;
            height: 400px;
        }

        .likebox {
            width: 292px;
            height: 400px;
            background-color: #F6F6F6;
            float: left;
            margin: 0 10px;
        }

        .like-add {
            color: grey;
            text-align: center;
            line-height: 400px;
        }

        .like-top:not(.opac) {
            width: 292px;
            height: 50px;
            background-color: black;
            opacity: 0.5!important;


        }

        .like-name {
            float: left;
            color: white;
            opacity: 1;
            line-height: 50px;
            padding-left: 20px;

        }

        .like-localname {
            float: left;
            color: white;
            opacity: 1;
            line-height: 50px;
            padding-left: 5px;
        }

        .like-areaname {
            float: right;
            color: white;
            opacity: 1;
            line-height: 50px;
            padding-right: 20px;
        }

        .like-body {
            width: 292px;
            height: 250px;
        }

        .like-bottom {
            width: 292px;
            height: 100px;
            background-color: black;
            opacity: 0.5;
        }

        .like-address {
            float: left;
            color: white;
            opacity: 1;
            padding-left: 20px;
            padding-top: 20px;

        }

        .like-price {
            float: right;
            color: white;
            opacity: 1;
            padding-right: 20px;
            padding-bottom: 20px;
        }

        

        /* 쪽지, 회원정보 */
        #information-area {
            width: 1000px;
            height: 200px;
            margin: 20px auto 0;
        }

        #note-area {
            width: 490px;
            height: 200px;
            float: left;
            margin: 0 10px 0 0;
            background-color: #F6F6F6;
            padding-top: 45px;
        }

        #note-area>a {
            text-decoration: none;
        }

        #note-icon {
            width: 60px;
            height: 60px;
            margin: 0 auto;

        }

        #note-icon>i {
            color: grey;
        }

        #note {
            width: 100px;
            height: 20px;
            font-size: 15px;
            color: grey;
            text-align: center;
            margin: 20px auto 0;
        }

        #infor-area {
            width: 490px;
            height: 200px;
            float: left;
            margin: 0 0 0 10px;
            background-color: #F6F6F6;
            padding-top: 45px;
        }

        #infor-area>a {
            text-decoration: none;
        }

        #infor-icon {
            width: 60px;
            height: 60px;
            margin: 0 auto;
        }

        #infor-icon>i {
            color: grey;
        }

        #infor {
            width: 200px;
            height: 20px;
            font-size: 15px;
            color: grey;
            text-align: center;
            margin: 20px auto 0;
        }
		#no-reser{
        	margin: 0 auto;
        	text-align: center;
        	width: 500px;
        	height: 400px;
        	line-height: 400px;
        	color: grey;
        }


        #footer {
            width: 1250px;
            margin-top: 20px;
            /*background-color: #F6F6F6;
            margin: 20px auto 0;*/
        }

        #fixed-bar{
             width: 55px;
            height: 290px;
            position: fixed;
            top: 250px;
            right: 0px;
            border-radius: 10px;
            padding: 25px 2.5px 25px 2.5px;
            opacity: 0.5;
            border: 5px solid #D5D5D5;
        }
        #fixed-bar>a{
            width: 40px;
            height: 50px;
            color: gray;
            float: left;
            margin: 0 auto;
            text-align: center;
        }
        #fixed-bar>a:hover{
            color: #D5D5D5;
        }
         #fixed-bar-move{
            width: 55px;
            height: 20px;
            position: fixed; 
            top:540px; 
            right:0px;
            opacity: 0.5;
            padding-top: 2px;
        }
        #fixed-bar-move>a{
            width: 25px;
            height: 25px;
            background-color: #D5D5D5;
            color: white;
            float: left;
            text-align: center;
            border-radius: 5px;
            margin: 1px;
            padding-top: 5px;
        }
        #fixed-bar-move>a:hover{
            color: gray;
        }
        
        #plus{
        	float:right;
        	padding: 0 20px 20px 0;
        	font-size:13px;
        	text-decoration: none;
        	color: grey;
        }

    </style>
</head>

<body>
    <div id="wrap">
        <a name="top"></a>
        <div id="header-wrap">
            <%@ include file="include/u_gnb.jsp" %>
        </div>
		
		
		<c:choose>
			<c:when test="${member==null }">
				<script>
					location.replace('/main/login/M_Login.jsp');
				</script>
			</c:when>
			
			<c:otherwise>
			
		
        <div id="body">
        
        	<%
        		UserReservation uReser = (UserReservation)request.getAttribute("UserReservation");
       	 		//System.out.println(uReser.getPath()+uReser.getFileName());
       	 		//System.out.println(uReser.getFileName());
       	 	%>
        	<%if(uReser!=null){ %>
        	
        	
            <div id="reservation-area">
                <div id="img-area">
                    <div id="img-circle">
                        <img src="<%=uReser.getPath() %><%=uReser.getFileName() %>" id="img">
                    </div>
                </div>
                <div id="reser-area">
                    <div id="reservation">
                        <div id="name">
                            <h3> <%=uReser.getBusinessName() %> | <%=uReser.getCampNo() %> (<%=uReser.getCampType() %>)</h3>
                            <hr>
                        </div>
                        <div id="res-infor">
                            <p><%=uReser.getReservSta() %> - <%=uReser.getReservEnd() %></p>
                            <p>체크인 <%=uReser.getCheckin() %> | 체크아웃 <%=uReser.getCheckout() %> </p>
                            <p>예약자 id : <%=uReser.getUserId() %></p>
                            <p>예약 번호 : <%=uReser.getReservNo() %></p>
                            <p><%=uReser.getBusinessAddress() %></p>
                        </div>

                    </div>
                    <a href="/userPage/ReservationList.do" id="plus"> 예약 내역 확인 > </a>
                </div>

            </div>
        	
        	
        	
        	<%}else{ %>
        	
        	
            <div id="reservation-area">
                <div id="no-reser">
                    아직 예약내역이 없습니다.
                    우리 사이트를 이용해주세요 : )
                </div>
            </div>
             
        	
        	<%} %>
        
        	<%-- ㅇㅖ약 샘플
            <div id="reservation-area">
                <div id="img-area">
                    <div id="img-circle">
                        <img src="image/test/캠핑카.jpg" id="img">
                    </div>
                </div>
                <div id="reser-area">
                    <div id="reservation">
                        <div id="name">
                            <h3> 내손자바 캠핑장 | A 구역 (캠핑)</h3>
                            <hr>
                        </div>
                        <div id="res-infor">
                            <p>2021.12.25(토) ~ 2021.12.26(일)</p>
                            <p>체크인 15:00 | 체크아웃 13:00 </p>
                            <p>예약자 명 : 홍길동</p>
                            <p>예약 번호 : 123454657</p>
                            <p>강원도 어쩌구 저쩌구 abc</p>
                        </div>

                    </div>
                </div>

            </div>
             --%>
            
            
            
            

            <div id="like-area">
            
            	<%
            	ArrayList<UserWish> ulist = (ArrayList<UserWish>)request.getAttribute("UserWishList");
       	 		%>
       	 		<%if(!ulist.isEmpty()) {%>
       	 		
       	 			<%if(ulist.size()<4){ %>
       	 				<%for(int i=0; i <ulist.size(); i++) { %>
       	 					<style>
        						#likebox<%=i%> {							
        							background: url("<%=ulist.get(i).getPath()%><%=ulist.get(i).getFileName()%>");
            						background-repeat: no-repeat;
           							background-size: cover;
            						background-position: center;
        						}
        					</style>
       	 					<div class="likebox" id="likebox<%=i%>">
       	 						
                    			<div class="like-top">
                        			<div class="like-name opac"><%=ulist.get(i).getBusinessName() %></div>
                       			 	
                       			 	<div class="like-areaname opac">| <%=ulist.get(i).getCmapNo() %> 구역</div>
                    			</div>
                    			<div class="like-body"></div>
                    			<div class="like-bottom">
                        			<div class="like-address"><%=ulist.get(i).getBusinessAddress() %> </div>
                        			<div class="like-price"><%=ulist.get(i).getCampPrice() %>원</div>
                    			</div>
                			</div>
       	 				<%} %>
       	 				<%for(int j = 0; j< 4-ulist.size(); j++) {%>	
       	 					<div class="likebox">
                    			<div class="like-add">
                       			관심상품을 추가해주세요:-)
                    			</div>

                			</div>
       	 				<%} %>
       	 			
       	 			<%}else {%>  
       	 			    <%for(int i=0; i < 4; i++) {%>
       	 					<style>
        						#likebox<%=i%> {							
        							background: url("<%=ulist.get(i).getPath()%><%=ulist.get(i).getFileName()%>");
            						background-repeat: no-repeat;
           							background-size: cover;
            						background-position: center;
        						}
        					</style>
       	 			    
       	 					<div class="likebox" id="likebox<%=i%>">
                    			<div class="like-top">
                        			<div class="like-name"><%=ulist.get(i).getBusinessName() %></div>
                       			 	<div class="like-areaname">| <%=ulist.get(i).getCmapNo() %> 구역</div>
                    			</div>
                    			<div class="like-body"></div>
                    			<div class="like-bottom">
                        			<div class="like-address"><%=ulist.get(i).getBusinessAddress() %> </div>
                        			<div class="like-price"><%=ulist.get(i).getCampPrice() %>원</div>
                    			</div>
                			</div>
       	 				<%} %>	 		
       	 			<%} %>
       	 		<%-- 
       	 		<%for(UserWish uWish : ulist) {%>
                <div class="likebox likebox1">
                    <div class="like-top">
                        <div class="like-name"><%=uWish.getBusinessName() %></div>
                        <div class="like-localname">[<%=uWish.getBusinessAddr() %>]</div>
                        <div class="like-areaname">| <%=uWish.getCmapNo() %> 구역</div>
                    </div>
                    <div class="like-body"></div>
                    <div class="like-bottom">
                        <div class="like-address"><%=uWish.getBusinessAddress() %> </div>
                        <div class="like-price"><%=uWish.getCampPrice() %>원</div>
                    </div>
                </div>
       	 		<%} %>--%>
       	 		<%}else{ %>
       	 		
       	 			<%for(int j = 0; j < 4; j++) {%>	
       	 					<div class="likebox">
                    			<div class="like-add">
                       			관심상품을 추가해주세요:-)
                    			</div>
                			</div>
       	 				<%} %>
       	 		<%} %>
            
            
           
            <%-- 관심상품 샘플
                <div class="likebox" id="likebox1">
                    <div class="like-top">
                        <div class="like-name">abc캠핑장</div>
                        <div class="like-localname">[춘천]</div>
                        <div class="like-areaname">| A 구역</div>
                    </div>
                    <div class="like-body"></div>
                    <div class="like-bottom">
                        <div class="like-address">강원도 춘천시 어쩌구 저쩌구 </div>
                        <div class="like-price">40,000원</div>
                    </div>
                </div>
                 

                
                <div class="likebox">
                    <div class="like-add">
                        관심상품을 추가해주세요:-)
                    </div>
                </div>
			--%>
			
			
			
            </div>
            <div id="information-area">
                <div id="note-area">
                    <a href="" onclick=notes()>
                        <div id="note-icon"><i class="xi-note-o xi-4x"></i></div>
                        <div id="note">쪽지함</div>
                    </a>
                </div>
                <div id="infor-area">
                    <a href="u_pwdCheck.jsp">
                        <div id="infor-icon"><i class="xi-user-o xi-4x"></i></div>
                        <div id="infor">내 정보 보기</div>
                    </a>
                </div>
            </div>

        </div>
        <div id="footer">
			<%@include file="/common/include/footer.jsp" %>
        </div>


        <div id="fixed-bar">
            <a href="/userPage/userPage.do"><i class="xi-bars xi-2x"></i></a>
            <a href="/userPage/ReservationList.do"><i class="xi-calendar-check xi-2x"></i></a>
            <a href="/userPage/userWishList.do"><i class="xi-heart-o xi-2x"></i></a>
            <a href="" onclick=notes()><i class="xi-note-o xi-2x"></i></a>
            <a href="u_pwdCheck.jsp"><i class="xi-user-o xi-2x"></i></a>
        </div>
        <div id="fixed-bar-move">
        	<a href="#top"><i class="xi-angle-up"></i></a>
        	<a href="#bottom"><i class="xi-angle-down"></i></a>
        </div>
        <a name="bottom"></a>


    </div>

	</c:otherwise>
			
			
	</c:choose>
		<script type="text/javascript">
        function notes(){
            var userId = "<c:out value='${member.userId}'/>"
                if(userId!=""){
                    window.open("/board/msg/listAll.do?userId="+userId,"_blank","width=700px, height=400px");
                }else{
                    alert('로그인이 필요한 서비스입니다.\n로그인 후에 이용해주세요.')
                }
        };
    </script>

</body>

</html>
