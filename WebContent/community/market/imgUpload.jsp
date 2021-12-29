<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Campus-중고장터 파일 업로드</title>
<link rel="stylesheet"
	href="//cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	
	<style>
		form{
			width:200px;
			height:250px;
			border:2px solid rgba(0, 25, 51, 0.3);
			border-radius:10px;
			text-align:center;
			margin:auto;
			padding:20px;
		}
		form>label{
			margin-top:60px;
			color:rgba(0, 25, 51, 0.6);
		}
		form>input{
			margin-left:20px;
		}
		#btn{
			margin-top:30px;
		}
		i{
			color: rgba(0, 25, 51, 0.8);
		}
	</style>
</head>
<body>
<form action="/board/market/upload.do" method="post" enctype="multipart/form-data" id="addForm">
	<label for="chooseFile">클릭하여 이미지를 <br><br>(.jpg/jpeg/png/bmp)<br><br>추가해주세요 :-)</label><br><br>
	<input type="file" class="add-content" id="chooseFile" name="file" accept="image/*" onchange="loadFile(this)">
	<div id="btn">
		<i class="xi-check-circle-o xi-2x" id="submitBtn"></i><i class="xi-close-circle-o xi-2x" id="cancelBtn"></i>
	</div>
</form>

<script>
	$('#submitBtn').click(function() {
	    var file = document.getElementById('chooseFile');
	    var fileSize = file.files[0].size;
	    if (fileSize > (50 * 1024 * 1024)) {
	        alert('업로드 가능한 최대 사이즈는 50MB 입니다.');
	    }
	    else {
	        $('#addForm').submit();
	        alert('이미지 업로드 성공!');
	        window.close();
	    }
	});
	$('#cancelBtn').click(function(){
		window.close();
	});
</script>	

</body>
</html>