<%@page import="com.campus.reservation.model.vo.CampingArea" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--Jquery 라이브러리 & JqueryUI라이브러리-->
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<!--jQuery UI CSS -->
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<!--부트스트랩 CSS파일 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CampUS reservation</title>

<style>
	div{
		box-sizing: border-box;
	}
	#wrapper{
		width: 1250px;
		height: 1350px;
		margin: 0px auto;
	}
	#header{
		width: 1250px;
		height: 100px;
		margin: 0px auto;
		
	}
	#body{
		width: 1250px;
		height: 1600px;
		margin: 0px auto;
		
	}
	#footer{
		width: 1250px;
		height: 100px;
		margin: 0px auto;
    }
	#upSideArea{
		width: 1250px;
		height: 300px;
		margin: 0px auto;
    }
	#downSideArea{
		width: 1250px;
		height: 1200px;
		margin: 0px auto;
    }
	#campingArea{
		width: 950px;
		height: 1200px;
		margin: 0px auto;
        float: left;
    }
	#campingInfo{
		width: 300px;
		height: 1200px;
		margin: 0px auto;
        float: left;
    }
	#campingTitle{
		width: 950px;
		height: 50px;
		margin: 0px auto;
        float: left;
        margin-top: 1px;
    }
	#campingAreaList{
		width: 950px;
		height: 1000px;
		margin: 0px auto;
        float: left;
        z-index: 0;
    }
    table.table{
    	border:1px solid gray;
		width: 920px;
		height:220px;
    }
	#reservationSelect{
		width: 300px;
		height: 200px;
        float: left;
        
    }
	#campingdetail{
		width: 300px;
		height: 700px;
        float: left;
    }
    .title{
    	width: 920px;
		height: 45px;
		margin: 0px auto;
        float: left;
        text-align: left;
     	background-color: white;
    }
    #serachPanel{
    	width: 300px;
		height: 150px;
		margin: 0px auto;
        float: left;
        text-align : center;
    }
	#reSearch{
	background:#3e4a56;
	color:#fff;
	border:1px solid white;
	position:relative;
	width: 300px;
	height:50px;
	font-size:1.6em;
	padding:0 2em;
	cursor:pointer;
	transition:800ms ease all;
	outline:none;
	}
	#reSearch:hover{
	  background:#fff;
	  color:#3e4a56;
	}
	#reSearch:before,#reSearch:after{
	  content:'';
	  position:absolute;
	  top:0;
	  right:0;
	  height:2px;
	  width:0;
	  background: #3e4a56;
	  transition:400ms ease all;
	}
	#reSearch:after{
	  right:inherit;
	  top:inherit;
	  left:0;
	  bottom:0;
	}
	#reSearch:hover:before,#reSearch:hover:after{
	  width:100%;
	  transition:800ms ease all;
	}
	#detailArea{
	width : 300px;
	height : 700px;
	float: left;
	border: 1px solid black;	
	}
    .btn btn-success btn-sm{
    width : 20px;
    height : 20px;
    }
    #likeBtnImg{
    width : 20px;
    height : 20px;
    }
    .prevnextIcon{
	width:20px;
	height:20px;	
}
.modal{ 
  position:absolute; width:100%; height:100%; background: rgba(255,255,255,0.6); top:0; left:0; display:none;
}

.modal_content{
  width:500px; height:900px;
  background:#fff; border-radius:10px;
  position:relative; top:20%; left:50%;
  margin-top:-100px; margin-left:-200px;
  text-align:center;
  box-sizing:border-box; padding:74px 0;
  line-height:23px; cursor:pointer;
}

.mUpside{
	width : 500px;
	height : 650px;
	background-color: #3e4a56;
	
}
.mImageArea{
	width : 500px;
	height : 250px;
	float: left;
	
}
.mimage{
	width : 500px;
	height : 220px;
	float: left;
	padding-top: 20px;
	
}
.mAreaInfo{
	width : 500px;
	height : 500px;
	float: left;
	background-color:#3e4a56;
}
.mTitle{
	width : 500px;
	height : 50px;
	float: left;
	
}
.summary{
	width : 500px;
	height : 500px;
	background-color: white;
	text-align: left;
}
.confirm{
	width : 500px;
	height : 50px;
	color: white;
	font-weight: bold;	
	background-color: #3e4a56;
}

.reservBtn{
	width : 500px;
	height : 50px;
	background-color: #3e4a56;
	color: white;
	font-weight: bold;
	font-size: 14pt;
	
}
.modal_content .layerpop_close {
    width: 30px;
    height: 30px;
    display: block;
    position: absolute;
    top: 16px;
    right: 16px;
    background: transparent url('./image/btn_exit.png') no-repeat;
}
.modal_content .layerpop_close:hover {
    background: transparent url('./image/btn_exit.png') no-repeat;
    cursor: pointer;
}

#modalCpNm{
	font-size: 32px;
}
#cpNm{
	font-size: 32px;
}
.searchlogo{
	width:40px;
	height:40px;
	margin-bottom: 10px;
}
</style>

<script type="text/javascript">
	 $(function() {
		  $( "#from" ).datepicker({
		         changeMonth: true, 
		         changeYear: true,
		         nextText: '다음 달',
		         prevText: '이전 달',
		         dateFormat: 'yy-mm-dd',
		         monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		         monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
		         dayNames: ['일', '월', '화', '수', '목', '금', '토'],
		         dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
		         dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],

		         
		  });
		  $( "#to" ).datepicker({
		         changeMonth: true, 
		         changeYear: true,
		         nextText: '다음 달',
		         prevText: '이전 달',
		         dateFormat: 'yy-mm-dd',
			     monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			     monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			     dayNames: ['일', '월', '화', '수', '목', '금', '토'],
			     dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
			     dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],

		  });		  	  
		}); 

	 function rsvInfo(rsvSeq){
			
			if($("#from").val()==null ||$("#from").val() == ""){
				$("#from").focus();
				return;
			}
			if($("#to").val()==null ||$("#to").val() == ""){
				$("#to").focus();
				return;
			}
			if($("#from").val() > $("#to").val()){
				alert("날짜를 확인하세요");
				$("#from").focus();

				return;
			} 
		 
			$.ajax({
				type : "GET", 
				url : "/reservation/ajax.do",
				data: {"rsvSeq":rsvSeq},
				dataType : "json",
				success : function(data){
					var keys = Object.keys(data);	
						
					$.each(keys, function(index, item){
						
						var html='	<div class="summary" id="summary">'
								+'  	<form style="font-size : 16pt; fon">'
								+'		숙소이름 : '+ data[item].campName +'<br><br>'
								+'		체크인 : '+ data[item].checkin +'<br><br>'
								+'		체크아웃 : '+ data[item].checkout +'<br><br>'
								+'		결제금액 : '+ data[item].campPrice +'<br><br>'
								+'		예약안내 : '+ data[item].reservInfo +'<br><br><br>'
								+'		<input type="checkbox" style="zoom:1.5;"/> 숙소이용규칙 및 취소/환불규정 동의 (필수)<br>'
								+'		<input type="checkbox" style="zoom:1.5;"/> 개인정보 수집 및 이용 동의 (필수)<br>'
								+'		<input type="checkbox" style="zoom:1.5;"/> 개인정보 제 3자 제공 동의 (필수)<br>'
								+'		<input type="checkbox" style="zoom:1.5;"/> 만 14세 이상 확인 (필수)<br>'
								+'	</form>'
								+'</div>';
								
						$("#mimage").attr("src",data[item].filename);
						$("#modalCpNm").text(data[item].businessName);
						$("#summary").html(html);
						
						//일정
						var startdate = $("#from").val().replaceAll("-", ""); 
						var enddate = $("#to").val().replaceAll("-", ""); 
						$("#reservSta").val(startdate);
						$("#reservEnd").val(enddate);
						
						$(".modal").fadeIn();
						return; 
					});
				
				}, error : function(e){
					alert("지속적인 문제발생시 고객센터에 문의바랍니다.");
				}
			});
	 }

</script> 
   
   
   
<body>
<%
String from="";

if(request.getParameter("from")==null)
{
	from="";
}else {
	from = request.getParameter("from");
}

String to="";

if(request.getParameter("to")==null)
{
	to="";
}else {
	to = request.getParameter("to");
}


	//페이징 처리되어 넘어온 데이터를 가져와야 함
	HashMap<String, Object> pageDataMap = (HashMap<String, Object>)request.getAttribute("pageDataMap");
	
	ArrayList<CampingArea> list = (ArrayList<CampingArea>)pageDataMap.get("list");
	String pageNavi = (String)pageDataMap.get("pageNavi");
	int currentPage = (int)request.getAttribute("currentPage");
	String cpNm = "";
	if(!list.isEmpty()) cpNm = list.get(0).getBusinessName();
%>
	<div id="wrapper">
        <div id="header">
		<%@ include file="/common/include/gnb.jsp" %></div>
		<div id="body">
		    <div id="upSideArea" style="background: url('../../reservation/image/main/2maintop.jpg') no-repeat 50%;"></div>
		    <div id="downSideArea">
		        <div id="campingArea">
		        
		            <div id="campingTitle">
		            	<div class="title" style="padding-top: 10px;">
		            	<img class="searchlogo" src='../../reservation/image/main/select3.png'><span id="cpNm"> <%=cpNm %></span>
		            	</div>
		            </div>
					<br>
		            <div id="campingAreaList">
		        	<br>
		        		<table class="table table-striped">
<%					
						if(!list.isEmpty()) {
							for(CampingArea campingArea:list){
%>
							
						
							<tr style="border:1px solid gary;">
								<td rowspan="5" style="border:1px solid gray; width: 300px;">						
									<img src="<%=campingArea.getFilename()%>"onerror="this.src='../../reservation/image/main/sample_camping/default_300_200.jpg'"/>
								</td>
								<td style="border:1px solid gray;">
									<input type="button" class="reservBtn" value="예약하기" onclick="javascript:rsvInfo('<%=campingArea.getCampSeq()%>'); return false"/>
									
									<c:if test="${member!=null }">
									
										
											
											<i class="xi-heart-o xi-2x" id="heartBtn"></i>
											</a>
											
											
											
											
											
											
										
										
									</c:if>
									
									
								</td>
							</tr>
							<tr style="border:1px solid gray; height: 37px;">
								
								<td style="display: inline-block; margin: 4px; padding: 5px 7px; line-height: 1; border-radius: 6px; background: #FFC946;  border: 1px solid #FFC946; color: #fff; text-align: center;  font-size: 13px;">
								<%=campingArea.getCampType() %></td>
								
							</tr>
							<tr style="border:1px solid gray;">
								<td style="border:1px solid gray; font-weight: bold;"><%=campingArea.getCampName() %></td>
							</tr>													
							<tr style="border:1px solid gray;">
								<td style="border:1px solid gray;">기준인원 : <%=campingArea.getCampPo() %>인 (최대인원: <%=campingArea.getCampMaxpo() %>인)</td>
							</tr>
							<tr style="border:1px solid gray;">
								<td style="border:1px solid gray;">가격(1박) : <%=campingArea.getCampPrice() %></td>
							</tr>
<%							}
						}
%>
						</table>
						<div style='text-align: center; font-size: 18pt;'>	<%=pageNavi%> </div>
		            	</div>
		            
		        </div>
		        <div id="campingInfo">

                    <div id="reservationSelect">
                   	<form id="serachPanel" mehtod="get">
						<br><br>
						<div class="search">
						     <label for="form">일정이 언제에요?</label><br>
						     <input type="text" style="text-align:center" size= 30 id="from" name = "from" value="<%=from%>" readonly/>
						</div>
						<div class="search">
						     <label for="form">언제까지에요?</label><br>
						     <input type="text" style="text-align:center" size= 30 id="to" name = "to" value="<%=to%>" readonly />
						</div>
					</form>
 				 </div>


		            	
                    <div id="campingdetail">
                    	<div id="detailArea">
                    	</div>
                    </div>
		        </div>
		        
		        
		    </div>
		</div>
		<div id="footer">
				<%@include file="/common/include/footer.jsp" %>
		</div>
	</div>
</body>

<div class="modal">
	  <div class="modal_content">
  		<div class="mUpside">
  			<a href="javascript:void(0);" class="layerpop_close" id="layerbox_close"></a> 
			<div class="mImageArea">
				<div class="mimage">
					<img src="" id="mimage"/>
				</div>
			</div>
			<div class="mAreaInfo">
				<div class="mTitle">
		            <span id="modalCpNm" style="background-color: white; color:#3e4a56;"></span>
				</div>
				<form action="/reservation/reservationJoin.do" method="post" style="font-size : 16pt;">
					<!-- 모달창 입력내용 ajax -->
					<div class="summary" id="summary"></div>
					<input type="hidden" id="reservSta" name="reservSta" />
					<input type="hidden" id="reservEnd" name="reservEnd" />
					<input type="submit" class="confirm" value="예약"></input>
						
				</form>
			</div>
		</div>
  </div>
<script>
$(function(){ 
  $("#layerbox_close").click(function(){
	$(".modal").fadeOut();
  });

});

</script>
</div>



</html>