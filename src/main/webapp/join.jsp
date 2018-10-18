<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/common/lib.jsp" %>

<!-- 주소 API를 위한 Lib -->
<!-- jquery ui css -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- 다음주소 API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<!-- jquery ui api -->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function(){
		//개발 과정에서 사용한 기본 값 설정
		$("#userId").val();
		$("#name").val("testUserId");
		$("#addr1").val("대전 중구 중앙로 76");
		$("#addr2").val("영민빌딩 2층 대덕인재개발원");
		$("#zipcd").val("34940");
		$("#birth").val("2018-08-08");
		$("#email").val("newUser@gmail.com");
		$("#tel").val("0422228202");
		
		$("#addrSearchBtn").click(function(){
			
			
			//생일 input date picker 적용
			$( "#birth" ).datepicker({
			   /*    showOn: "button",
			      buttonImage: "images/calendar.gif",
			      buttonImageOnly: true,
			      buttonText: "Select date", */
			      dateFormat:"yy-mm-dd"
			  });
			
			
			//주소 검색 버튼 클릭이벤트가 발생 했을때 실행
			  new daum.Postcode({
			        oncomplete: function(data) {
			            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
			            // 예제를 참고하여 다양한 활용법을 확인해 보세요.
			        	console.log(data);
			            //주소 : roadAddress
			            //상세주소 : ""
			            //우편번호 : zonecode
			        	
			            //input value 생성: data.roadAddress
			            //우편번호 input value 설정: data.zonecode
			         
			            $("#addr1").val(data.roadAddress);
			            $("#zipcd").val(data.zonecode);
			            
			        }
			    }).open();
			  
		})
		
	})
</script>
<body>
<%@ include file="/common/nav.jsp" %>
	
	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px">
				<form method="post" action="joinAction.jsp">
					<h3 style="text-align: center">회원가입 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" id="userId" name="userId" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" id="password" name="password" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="이름" id="name" name="name" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="date" class="form-control" placeholder="생년월일" id="birth" name="birth" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="tel" class="form-control" placeholder="전화번호" id="tel" name="tel" maxlength="20"/>
					</div>
					<div class="form-group">
						<input type="email" class="form-control" placeholder="이메일" id="email" name="email" maxlength="20"/>
					</div>
					<div class="form-group">
						<a id="addrSearchBtn" type="button" class="btn btn-primary">주소 검색</a>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="도로주소" id="addr1" name="addr1" readonly="readonly"/>
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="상세주소" id="addr2" name="addr2" />
					</div>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="우편번호" id="zipcd" name="zipcd" maxlength="20" readonly="readonly"/>
					</div>
					<input type="submit" class="btn btn-primary form-control" value="회원가입" />
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
</body>
</html>