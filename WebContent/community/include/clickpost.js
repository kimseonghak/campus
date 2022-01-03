var updateBtnFlag = false;
var boardData;
$('#updateBtn').click(function(){
	if(updateBtnFlag==false){
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


$('.coDeleteBtn').click(function(){
	var cFreeNo = $(this).next().val();
	if(window.confirm("삭제하시겠습니까?")){
		return true;
	}
	else{
		return false;
	}
});


$('.xi-share-alt-o').click(function() {
    var div = $('#modal');
    div.css("position", "absolute");
    div.css("top", "420px");
    div.css("right", "100px");
    div.css("z-index", "1");
    $('#modal').fadeIn();
});
$('#modal-close').click(function() {
    $('#modal').fadeOut();
});