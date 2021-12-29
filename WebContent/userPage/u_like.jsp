<%@page import="com.campus.userPage.model.vo.UserWish"%>
<%@page import="java.util.ArrayList"%>
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
            padding-top: 50px;
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
            margin: 0 10px 20px;
        }

        .like-add {
            color: grey;
            text-align: center;
            line-height: 400px;
        }

        .like-top {
            width: 292px;
            height: 50px;
            background-color: black;
            opacity: 0.4;


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

        
        #footer {
            width: 1250px;
            margin-top: 500px;
            /*background-color: #F6F6F6;
            margin: 20px auto 0;*/
        }

        #fixed-bar {
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

        #fixed-bar>a {
            width: 40px;
            height: 50px;
            color: gray;
            float: left;
            margin: 0 auto;
            text-align: center;
        }

        #fixed-bar>a:hover {
            color: #D5D5D5;
        }

        #fixed-bar-move {
            width: 55px;
            height: 20px;
            position: fixed;
            top: 540px;
            right: 0px;
            opacity: 0.5;
            padding-top: 2px;
        }

        #fixed-bar-move>a {
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

        #fixed-bar-move>a:hover {
            color: gray;
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
       	 			
       	 			<%}else if(ulist.size()>=4) {%>  
       	 			    <%for(int i=0; i < ulist.size(); i++) {%>
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
       	 		
       	 		<%}else{ %>
       	 		
       	 			<%for(int j = 0; j < 4; j++) {%>	
       	 					<div class="likebox">
                    			<div class="like-add">
                       			관심상품을 추가해주세요:-)
                    			</div>
                			</div>
       	 			<%} %>
       	 		<%} %>









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
