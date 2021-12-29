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
    	#postaction{
            padding-left:63%;
        }
        #marketTitle{
			resize: none;
			font-size: 19px;
		}
		#marketContent{
			position:relative;
			resize: none;
			margin-top:250px;
			font-size: 17px;
		}
		#img{
			position: absolute;
			left:200px;
			top:380px;
			width:220px;
			height:240px;
			border:2px solid rgba(0, 25, 51, 0.3);
		}
		#add-img{
			position: absolute;
			left:200px;
			top:380px;
			width:220px;
			height:240px;
			text-align:center;
			border:2px solid rgba(0, 25, 51, 0.3);
			padding-top:25px;
		}
		#add-img>label{
			color:rgba(0, 25, 51, 0.6);
		}
		#add-img>input{
			margin-left:20px;
		}
		#marketForm{
			position: absolute;
        	right:200px;
        	top:380px;
			text-align:left;
			width:300px;
			margin:auto;
			padding:20px;
			border:2px solid rgba(0, 25, 51, 0.7);
			border-radius:5px;
		}
		#btn{
			margin-top:10px;
		}
		i{
			color: rgba(0, 25, 51, 0.8);
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
                        <%if(request.getAttribute("imgPath")!=null){ %>
							<img id="img" src="<%=request.getAttribute("imgPath")%>"/>
						<%}else{ %>
                        <form action="/board/market/upload.do?userId=<%=request.getParameter("userId") %>" method="post" enctype="multipart/form-data" id="add-img">
							<label for="chooseFile">클릭하여 이미지를 <br><br>(.jpg/jpeg/png/bmp)<br><br>추가해주세요 :-)</label><br><br>
							<input type="file" class="add-content" id="chooseFile" name="file" accept="image/*" onchange="loadFile(this)">
							<div id="btn">
								<i class="xi-check-circle-o xi-2x" id="submitBtn"></i>
							</div>
						</form>
						<%} %>
                        <form action="/board/market/write.do?currentPage=<%=request.getParameter("currentPage") %>&imgPath=<%=request.getAttribute("imgPath")%>" method="post">
			                <div><textarea id="marketTitle" rows="1" cols="100" placeholder="제목을 작성하세요" name="marketTitle"></textarea></div>
					        <br>
					        <div id="marketForm">     
					              제품명 : <input type="text" name="marketProduct" placeholder="제품명을 입력하세요"/><br>
							가격 : <input type="text" name="marketPrice" placeholder="가격을 입력하세요"/><br>
							상태 : <input type="radio" name="marketCondition" value="상"/>상<input type="radio" name="marketCondition" value="중"/>중<input type="radio" name="marketCondition" value="하"/>하<br>
							지역 : <input type="text" name="marketLocation" placeholder="지역을 입력하세요"/><br>
							거래방식 : <br>
								  <input type="checkbox" name="marketTrade" value="직거래"/>직거래
								  <input type="checkbox" name="marketTrade" value="우체국택배"/>우체국 택배<br>
								  <input type="checkbox" name="marketTrade" value="편의점택배"/>편의점 택배
								  <input type="checkbox" name="marketTrade" value="퀵"/>퀵 이용
								  <input type="checkbox" name="marketTrade" value="기타"/>기타
								  <br><br>
							<input type="reset" value="다시 입력하기"/>
							</div>
							<div>
							<textarea id="marketContent" rows="20" cols="100" placeholder="내용을 작성하세요"  name="marketContent"></textarea><br><br>
							</div>
							<input type="submit" value="완료"> <button type="button" id="backBtn">목록</button> <input type="reset" value="취소" > <br><br>
						</form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>   
    </div>
    
<!-- <script type="text/javascript">
	var img_file;
	
	$(document).ready(function(){
		$('#chooseFile').on("change",imgView)
	});
	
	function imgView(e){
		var files=e.target.files;
		var filesArr=Array.prototype.slice.call(files);
		
		var name=/(.*?)\/(jpg|jpeg|png|bmp)$/;
		
		filesArr.forEach(function(f){
			if (!f.type.match(name)) {
                alert("확장자는 이미지 확장자만 가능합니다.");
                return;
            }
			
			img_file=f;
			
			var reader=new FileReader();
			reader.onload=function(e){
				$("#img").attr("src", e.target.result);
			}
			reader.readAsDataURL(f);
		});
	}
</script> -->
<!-- 	$(function(){
		$('#submitBtn').click(function(){
			 $("#chooseFile").ajaxSubmit({
				type:'post',
				url: '/board/market/upload.do',
				dataType:'json',
				success:function(data){
					if(data.status=="success"){
						$('#img').attr('src',data); 
			            alert("이미지 업로드 성공!");
					}
					else{
						alert("이미지 업로드 실패. 다시 시도해주세요!");
					}
				},
				error:function(xhr){
					alert("이미지 업로드 실패. 다시 시도해주세요!");
				}
			}); */
			
			/* var form = new FormData();
	        form.append(inputName, data.file, data.name);
	        
	        var xhr=new XMLHttpRequest();
	        xhr.open("POST","/board/market/upload.do");
	        xhr.send(form);
	        
	        jQuery.a
	        xhr.onreadystatechange = function(){
	        	if(xhr.readyState===4){
	        		if(xhr.status===200){
	        			$('#img').attr('src',data); 
			            alert("이미지 업로드 성공!");
	        		}
	        		else{
	        			alert("이미지 업로드 실패. 다시 시도해주세요!");
	        		}
	        	}
	        }; 
		}); -->
<script>
	$('#btn').click(function() {
	    var file = document.getElementById('chooseFile');
	    var fileSize = file.files[0].size;
	    if (fileSize > (50 * 1024 * 1024)) {
	        alert('업로드 가능한 최대 사이즈는 50MB 입니다.');
	    }
	    else {
	        $('#add-img').submit();
	    }
	});
	$(function(){
		$('#backBtn').click(function(){
			window.location.href="/board/market/listAll.do?currentPage=<%=request.getParameter("currentPage") %>";
		});
   	});
</script>
</body>
</html>