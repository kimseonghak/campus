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

	// modal
      $(function() {
          $('#add').click(function() {
              $('body').css('overflow', 'hidden'); // 스크롤 제어
              
              var div = $('#modal');
              div.css("position", "absolute");
              // 모달창 열리는 위치 현재 화면의 위치로 지정
              div.css("top", Math.max(0, (($(window).height() - div.outerHeight()) / 2) + $(window).scrollTop()) + "px");
              div.css("left", Math.max(0, (($(window).width() - div.outerWidth()) / 2) + $(window).scrollLeft()) + "px");
              // 모달창 띄우기
              $('#modal').fadeIn();
          });
          $('#modal-close').click(function() {
              // 모달창 닫기
              $('#modal').fadeOut();
              // 스크롤 제어 해제
              $('body').css('overflow', 'auto');
          });

      });
