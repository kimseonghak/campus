<%@page import="com.campus.diary.model.vo.Background"%>
<%@page import="com.campus.diary.model.vo.Title"%>
<%@page import="com.campus.diary.model.vo.Frame"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <style>
        * {
            box-sizing: border-box;
        }

        #wrap {
            width: 100%;
            height: 100%;
            /*background: url("./image/camper-1845590.jpg");*/
            background-repeat: no-repeat;
            background-size: 100% 100%;
            background-position: center;
            opacity: 0.8;
            position: fixed;
            z-index: -1;
            filter: blur(2px);

        }

        #body {
            width: 100%;
            margin-top: 50px;
        }

        #pol-title {
            width: 85%;
            height: 80px;
            font-size: 25px;
            color: white;
            margin: 0 auto 10px;
            padding-left: 20px;
            padding-top: 20px;
            line-height: 70px;
        }

        #pol-area {
            width: 85%;
            height: 100%;
            margin: 0 auto;
            text-align: center;
        }


        .polaroid {
            width: 250px;
            height: 350px;
            background-color: white;
            border-radius: 5px;
            float: left;
            box-shadow: 1px 1px 5px gray;
            margin: 20px;
        }

        .delete {
            display: none;
            position: relative;
            left: 125px;
            top: -6px;
            z-index: 10;
            color: gray;
        }

        .img-area {
            width: 90%;
            height: 70%;
            margin: 15px auto;
            overflow: hidden;
            border: 1px solid #EAEAEA;
        }

        .img-area>img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        .title-area {
            width: 90%;
            height: 17%;
            margin: 15px auto;
            text-align: center;
            font-size: 25px
        }

        .title {
            width: 100%;
            height: 100%;
            float: left;
            font-size: 15px;
            text-align: center;
            margin: auto;
            line-height: 50px;
        }



        .date {
            position: relative;
            left: -80px;
            top: 0px;
            z-index: 10;
            color: white;
            /*text-shadow: 0px 1px 2px grey;*/
        }

        #pol-add-area {
            width: 250px;
            height: 350px;
            float: left;
            margin: 15px;
            float: left;
        }

        #pol-add {
            margin: 120px auto;

        }

        #add {
            color: gray;
            cursor: pointer;
        }


        #modal {
            /*
            position: absolute;
            top: 0;
            left: 0;
            */
            width: 100%;
            height: 100%;

            display: none;

            background-color: rgba(0, 0, 0, 0.4);
        }


        #modal-area {
            position: absolute;
            top: 50%;
            left: 50%;

            width: 400px;
            height: 600px;


            text-align: center;


            transform: translateX(-50%) translateY(-50%);
        }

        #modal-pol {

            width: 375px;
            height: 525px;
            background-color: white;
            margin: 0 auto;
            padding-top: 5px;
            border-radius: 5px;
            box-shadow: 0 2px 3px 0 rgba(34, 36, 38, 0.15);
        }

        #add-img {
            width: 337.5px;
            height: 367.5px;
            margin: 22.5px auto;
            border-radius: 5px;
            background-color: #F6F6F6;
            text-align: center;
            line-height: 337.5px;
            overflow: hidden;
            border: 1px solid #F6F6F6;
        }

        #add-title>label {
            cursor: pointer;
            font-size: 15px;
            color: gray;
        }

        #add-img>label {
            cursor: pointer;
            font-size: 15px;
            color: gray;
        }

        #chooseFile {
            visibility: hidden;
        }

        #add-title {
            width: 337.5px;
            height: 89.25px;
            margin: 22.5px auto;
            text-align: center;
            background-color: #F6F6F6;
            padding-top: 10px;
        }

        #addTitle {
            border: none;
            background-color: #F6F6F6;

            width: 300px;
            height: 50px;
        }

        #addTitle:focus {
            outline: none;
            font-size: 15px;
        }


        #add-date {
            width: 375px;
            height: 30px;
            margin: 0 auto;
        }

        #add-date>input {
            float: left;
        }

        #modal-btn {
            width: 100px;
            height: 50px;
            margin: 0 auto;
        }

        #add-pol {
            float: left;
            cursor: pointer;
            color: white;
            text-shadow: 0px 1px 2px grey;
        }

        #modal-close {
            float: left;
            cursor: pointer;
            color: white;
            text-shadow: 0px 1px 2px grey;
        }


        /*update button*/
        #titleUpdate {
            position: absolute;
            top: 70px;
            right: 10px;

            width: 35px;
            height: 40px;

        }

        #titleUpdate>i {
            color: white;
            opacity: 0.5;
        }

        #titleUpdate:hover {
            cursor: pointer;
        }

        #backgroundUpdate {
            position: absolute;
            top: 120px;
            right: 10px;

            width: 35px;
            height: 40px;

        }

        #backgroundUpdate>i {
            color: white;
            opacity: 0.5;
        }

        #backgroundUpdate:hover {
            cursor: pointer;
        }


        /*title update modal*/
        #t_u_modal {

            position: absolute;
            top: 0;
            left: 0;

            width: 100%;
            height: 100%;

            display: none;

            background-color: rgba(0, 0, 0, 0.4);
        }

        #t_u_modal_area {
            position: absolute;
            top: 50%;
            left: 50%;

            width: 600px;
            height: 400px;

            text-align: center;


            transform: translateX(-50%) translateY(-50%);
        }

        #t_u_input_area {
            width: 500px;
            height: 200px;
            margin: 50px auto 20px;
            border-radius: 20px;
            padding: 20px 10px 10px 0;
            background-color: white;
            opacity: 0.9;
        }

        #t_u_input_area>label {
            cursor: pointer;
            font-size: 20px;
            color: gray;
        }

        #t_u_input {
            width: 460px;
            height: 80px;
            font-size: 25px;
            border: none;
            background-color: none;
        }

        #t_u_input:focus {
            outline-color: grey;
        }

        #t_u_btn {
            width: 100px;
            height: 50px;
            margin: 0 auto;
        }

        #t_u_add {
            float: left;
            cursor: pointer;
            color: white;
            text-shadow: 0px 1px 2px grey;
        }

        #t_u_close {
            float: left;
            cursor: pointer;
            color: white;
            text-shadow: 0px 1px 2px grey;
        }




        /*background update modal*/
        #b_u_modal {

            position: absolute;
            top: 0;
            left: 0;

            width: 100%;
            height: 100%;

            display: none;

            background-color: rgba(0, 0, 0, 0.4);
        }

        #b_u_modal_area {
            position: absolute;
            top: 50%;
            left: 50%;

            width: 600px;
            height: 400px;

            text-align: center;


            transform: translateX(-50%) translateY(-50%);
        }

        #b_u_input_area {
            width: 500px;
            height: 300px;
            margin: 50px auto 20px;
            border-radius: 20px;
            padding: 20px 10px 10px 10px;
            background-color: white;
            opacity: 0.9;
        }

        #b_u_text1 {
            width: 400px;
            height: 20px;
            color: gray;
            font-size: 20px;
            text-align: center;
            margin: 0 auto 15px;
        }

        #b_u_text2 {
            width: 400px;
            height: 20px;
            color: gray;
            font-size: 13px;
            text-align: center;
            margin: 0 auto 10px;
        }

        #b_u_input_area>label {
            color: black;
            cursor: pointer;
        }

        #b_u_file {
            visibility: hidden;
        }

        #b_u_name {
            width: 350px;
            height: 30px;
            border-bottom: solid 2px black;
            text-align: center;
            margin: 0 auto;
        }

        #b_u_btn {
            width: 100px;
            height: 50px;
            margin: 0 auto;
        }

        #b_u_add {
            float: left;
            cursor: pointer;
            color: white;
            text-shadow: 0px 1px 2px grey;
        }

        #b_u_close {
            float: left;
            cursor: pointer;
            color: white;
            text-shadow: 0px 1px 2px grey;
        }

    </style>
</head>

<body>

    <c:choose>
        <c:when test="${member==null }">
            <script>
                location.replace('/main/login/M_Login.jsp');

            </script>
        </c:when>

        <c:otherwise>


            <div id="wrap">
            </div>
            <%
            	Background back =  (Background)request.getAttribute("background");
            %>
            <%if(back!=null){ %>
            	<style>
            		#wrap{
            			width: 100%;
            			height: 100%;
            			background: url("<%=back.getDiaryBackPath()%>");
            			background-repeat: no-repeat;
            			background-size: 100% 100%;
            			background-position: center;
            			opacity: 0.8;
            			position: fixed;
            			z-index: -1;
            			filter: blur(2px);
            		}
            	</style>
            	<%
            	
            	%>
            <%}else{ %>
            	<style>
            		#wrap {
            			width: 100%;
            			height: 100%;
           			 	background: url("./image/camper-1845590.jpg");
            			background-repeat: no-repeat;
            			background-size: 100% 100%;
            			background-position: center;
            			opacity: 0.8;
            			position: fixed;
            			z-index: -1;
            			filter: blur(2px);

        			}
            	</style>
            <%} %>
            
            


            <%
					Title t = (Title)request.getAttribute("title");
			%>




            <div id="header-wrap">
                <%@ include file="include/d_gnb.jsp" %>
            </div>
            <div id="body">

                <div id="titleUpdate">
                    <i class="xi-pen xi-2x"></i>
                </div>
                <div id="backgroundUpdate">
                    <i class="xi-library-add xi-2x"></i>
                </div>
                <div id="t_u_modal" class="u_modal">
                    <div id="t_u_modal_area">
                        <form action="/diary/updateTitle.do" method="post" id="t_u_form">
                            <div id="t_u_input_area">
                                <label for="t_u_input">???????????? ????????? ????????? ??? ????????????.</label>
                                <%if(t!=null){ %>
                                <input type="text" value="<%=t.getDiaryTitle() %>" id="t_u_input" name="title">
                                <%}else{ %>
                                <input type="text" value="USER'S DIARY:)" id="t_u_input" name="title">
                                <%} %>
                            </div>
                            <div id="t_u_btn">

                                <div id="t_u_add">
                                    <i class="xi-plus-square-o xi-3x" id="t_u_submitBtn"></i>
                                </div>
                        </form>
                        <div id="t_u_close">
                            <i class="xi-close-square-o xi-3x"></i>
                        </div>

                    </div>
                </div>
            </div>
            <div id="b_u_modal">
                <div id="b_u_modal_area">
                    <form action="/diary/updateBack.do" method="post" id="b_u_form" enctype="multipart/form-data" >
                        <div id="b_u_input_area">
                            <div id="b_u_text1">????????? ???????????? ????????? ??? ????????????.</div>
                            <div id="b_u_text2">(???????????? ????????? ??????????????? ????????? ????????? ????????? ???????????????.)</div>
                            <hr>
                            <br>
                            <br>
                            <label for="b_u_file" class="add-content" id="label">???????????? ???????????? ?????????????????? :-)</label>
                            <input type="file" class="add-content" id="b_u_file" name="backImage" accept="image/*" onchange="b_u_loadFile(this)">
                            <div id="b_u_name">????????????</div>
                        </div>
                        <div id="b_u_btn">

                            <div id="b_u_add">
                                <i class="xi-plus-square-o xi-3x" id="b_u_submitBtn"></i>
                            </div>
                    </form>
                    <div id="b_u_close">
                        <i class="xi-close-square-o xi-3x"></i>
                    </div>

                </div>
            </div>
            </div>








            <div id="pol-title">
                <%if(t!=null){ %>
                <%=t.getDiaryTitle() %>
                <%}else{ %>
                USER'S DIARY : )
                <%} %>
            </div>

            <div id="pol-area">
                <div id="polaroid-wrap">


                    <%
							ArrayList<Frame> list = (ArrayList<Frame>)request.getAttribute("list");
						%>


                    <%if(!list.isEmpty()){%>

                    <%for(Frame fr : list) {%>
                    <div class="polaroid">
                        <a href="/diary/frameDeletedo?frameNo=<%=fr.getFrameNo() %>&imageName=<%=fr.getImageName() %>" class="delete"><i class="xi-close-circle-o"></i></a>
                        <div class="img-area">
                            <img src="<%=fr.getImagePath() %>">
                        </div>
                        <div class="title-area">
                            <div class="title">
                                <%if(fr.getFrameTitle()==null) {%>

                                <%}else{ %>
                                <%=fr.getFrameTitle() %>
                                <%} %>
                            </div>
                        </div>
                        <div class="date">
                            <%=fr.getFrameDate() %>
                        </div>
                    </div>
                    <%} %>

                    <%} %>




                    <%-- ??????????????? ??????
                <div class="polaroid">
                    <a href="" class="delete" ><i class="xi-close-circle-o"></i></a>
                    <div class="img-area">
                        <img src="image/test/nature-2588685.jpg" alt="">
                    </div>
                    <div class="title-area">
                        <div class="title">
                            ?????? ?????????
                        </div>
                        <div class="mood"></div>
                        <div class="weather"></div>
                    </div>
                    <div class="date">
                        2021-12-12
                    </div>
                </div>
             --%>




                    <div id="pol-add-area">
                        <div id="pol-add">
                            <i class="xi-plus-circle xi-4x" id="add"></i>
                        </div>

                    </div>

                    <div id="modal">
                        <div id="modal-area">
                            <form action="/diary/frameUpload.do" method="post" enctype="multipart/form-data" id="addForm">
                                <div id="modal-pol">
                                    <div id="add-img">

                                        <label for="chooseFile" class="add-content" id="label">???????????? ???????????? ?????????????????? :-)</label>
                                        <input type="file" class="add-content" id="chooseFile" name="file" accept="image/*" onchange="loadFile(this)">

                                    </div>
                                    <div id="add-title">
                                        <label for="addTitle">???????????? ????????? ?????????????????? :-)</label>
                                        <input type="text" id="addTitle" name="title" maxlength="15">

                                    </div>

                                </div>
                                <div id="add-date">
                                    <input type="date" name="date" id="date">
                                </div>
                                <div id="modal-btn">
                                    <div id="add-pol">
                                        <i class="xi-plus-square-o xi-3x" id="submitBtn"></i>
                                    </div>
                            </form>
                            <div id="modal-close">
                                <i class="xi-close-square-o xi-3x"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <script>
                    // ????????? ?????? ????????? ????????? submit ??????
                    $('#addTitle').keydown(function() {
                        if (event.keyCode == 13) {
                            event.preventDefault();
                        };
                    });


                    // ??????????????? ?????? ????????? ?????? ?????? ?????? ??? ?????? ????????? ???????????? ???????????????
                    $('#submitBtn').click(function() {
                        //console.log(document.getElementById('chooseFile').files[0]);

                        var file = document.getElementById('chooseFile');
                        var fileSize = file.files[0].size;
                        //console.log(fileSize);
                        if (fileSize > (50 * 1024 * 1024)) {
                            alert('????????? ????????? ?????? ???????????? 50MB ?????????.');
                        } else if ($('#date').val() == '') {
                            alert('????????? ??????????????????');

                        } else {
                            $('#addForm').submit();
                            //console.log('submit');
                        }
                    });

                    //???????????? ????????? ????????? ?????? ?????? ?????? ??? ????????? ???????????? ?????????
                    $('#t_u_submitBtn').click(function() {
                        if ($('#t_u_input').val() == '') {
                            alert('????????? ??????????????????');
                        } else {
                            $('#t_u_form').submit();
                        }

                    });
                    // ??????????????? ????????? ?????? ?????? ?????? ??? ?????? ????????? ???????????? ???????????????
                    $('#b_u_submitBtn').click(function() {
                        //console.log(document.getElementById('chooseFile').files[0]);

                        var file = document.getElementById('b_u_file');
                        var fileSize = file.files[0].size;
                        //console.log(fileSize);
                        if (fileSize > (50 * 1024 * 1024)) {
                            alert('????????? ????????? ?????? ???????????? 50MB ?????????.');
                        }else {
                            $('#b_u_form').submit();
                            //console.log('submit');
                        }
                    });

                </script>

                <script>
                    // ??????????????? ?????? ??????
                    $(function() {
                        $('#add').click(function() {
                            $('body').css('overflow', 'hidden'); // ????????? ??????

                            var div = $('#modal');
                            div.css("position", "absolute");
                            // ????????? ????????? ?????? ?????? ????????? ????????? ??????
                            div.css("top", Math.max(0, (($(window).height() - div.outerHeight()) / 2) + $(window).scrollTop()) + "px");
                            div.css("left", Math.max(0, (($(window).width() - div.outerWidth()) / 2) + $(window).scrollLeft()) + "px");
                            // ????????? ?????????
                            $('#modal').fadeIn();
                        });
                        $('#modal-close').click(function() {
                            // ????????? ??????
                            $('#modal').fadeOut();
                            // ????????? ?????? ??????
                            $('body').css('overflow', 'auto');
                        });

                    });


                    // ????????? ???????????? ??????
                    $(function() {
                        $('#titleUpdate').click(function() {
                            $('body').css('overflow', 'hidden'); // ????????? ??????

                            var div = $('#t_u_modal');
                            div.css("position", "absolute");
                            // ????????? ????????? ?????? ?????? ????????? ????????? ??????
                            div.css("top", Math.max(0, (($(window).height() - div.outerHeight()) / 2) + $(window).scrollTop()) + "px");
                            div.css("left", Math.max(0, (($(window).width() - div.outerWidth()) / 2) + $(window).scrollLeft()) + "px");
                            // ????????? ?????????
                            $('#t_u_modal').fadeIn();
                        });
                        $('#t_u_close').click(function() {
                            // ????????? ??????
                            $('#t_u_modal').fadeOut();
                            // ????????? ?????? ??????
                            $('body').css('overflow', 'auto');
                        });

                    });
                    // ?????? ???????????? ??????
                    $(function() {
                        $('#backgroundUpdate').click(function() {
                            $('body').css('overflow', 'hidden'); // ????????? ??????

                            var div = $('#b_u_modal');
                            div.css("position", "absolute");
                            // ????????? ????????? ?????? ?????? ????????? ????????? ??????
                            div.css("top", Math.max(0, (($(window).height() - div.outerHeight()) / 2) + $(window).scrollTop()) + "px");
                            div.css("left", Math.max(0, (($(window).width() - div.outerWidth()) / 2) + $(window).scrollLeft()) + "px");
                            // ????????? ?????????
                            $('#b_u_modal').fadeIn();
                        });
                        $('#b_u_close').click(function() {
                            // ????????? ??????
                            $('#b_u_modal').fadeOut();
                            // ????????? ?????? ??????
                            $('body').css('overflow', 'auto');
                        });

                    });

                </script>

            </div>
            </div>
            <script>
                // ?????? ?????? ????????????
                $('.polaroid').hover(function() {
                    $(this).children('.delete').css('display', 'block');
                    //$(this).children('.img-area').css('margin','-1px 17.5px 14px 12.5px'); 
                    $(this).children('.img-area').css('margin', '-1px 17.5px 14px 12.5px');
                }, function() {
                    $(this).children('.delete').css('display', 'none');
                    $(this).children('.img-area').css('margin', '15px auto');
                });
                $('.delete').click(function() {
                    if (window.confirm('????????? ?????????????????? ????????? ??? ????????????. ????????????????????????? ')) {
                        return true;
                    } else {
                        return false;
                    }
                });

            </script>

            <script>
                // ?????????????????? ?????? ???????????? ????????????
                function loadFile(input) {


                    // ????????? ?????? ????????????
                    var file = input.files[0];


                    // ????????? ????????? div ??????
                    var newImage = document.createElement("img");
                    newImage.setAttribute("class", 'img');

                    // ????????? source ????????????
                    newImage.src = URL.createObjectURL(file);

                    newImage.style.width = "100%";
                    newImage.style.height = "100%";
                    newImage.style.objectFit = "cover";


                    var container = document.getElementById('add-img');
                    $('.add-content').css('display', 'none');
                    container.appendChild(newImage);
                };


                // ?????? ?????? ?????? ???????????? ????????????
                function b_u_loadFile(input) {


                    // ????????? ?????? ????????????
                    var file = input.files[0];

                    //?????? ????????? ?????? div??? text(?????? ??????) ??????
                    var name = document.getElementById('b_u_name');
                    name.textContent = file.name;




                };

            </script>



            </div>

        </c:otherwise>


    </c:choose>



</body>

</html>
