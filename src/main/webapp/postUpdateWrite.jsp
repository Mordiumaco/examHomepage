<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Smart Editor</title>

<!-- Favicon -->
<link rel="shortcut icon" href="favicon.ico" />
<meta name="viewport" content="width=device-width", initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css"/>
<link rel="stylesheet" href="css/dashboard.css"/>
<link rel="stylesheet" href="css/custom.css"/>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="js/bootstrap.js"></script>
<script src="/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">

var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.

$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#savebutton").click(function(){
		if(confirm("수정을 완료하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(validation()) {
				$("#frm").submit();
			}
		}
	})
});

// 필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}

	return true;
}

</script>
</head>
<body>
<%@ include file="/common/nav.jsp" %>
<%@ include file="/common/left.jsp" %>
 <div class="container-fluid">
 <div class="col-sm-12 col-sm-offset-3 col-md-12 col-md-offset-2 main">
		      <div class="row">
			        <div class="col-md-12 blog-main">
			        
<form action="/postUpdate?boardCode=${postVo.boardCode}&postCode=${postVo.postCode}" method="post" id="frm" class="form-horizontal">
	<div class="form-group">
		<div class="col-md-6">
			 <input type="text" class="form-control" id="postName" name="postName" value="${postVo.postName}"
				placeholder="게시물 제목">
		</div>
	</div>
	<textarea name="postCon" id="smarteditor" rows="10" cols="100" style="width:766px; height:412px;">${postVo.postCon}</textarea>
	<input type="button" class="btn btn-primary" id="savebutton" value="수정" /> 
</form>

</div>
</div>
</div>

</div>
</div>
</body>
</html>