<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/common/lib.jsp" %>
<body>
<%@ include file="/common/nav.jsp" %>
<%@ include file="/common/left.jsp" %>
	 <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
     	 <div class="container">
		      <div class="header">
		        <h3 class="text-muted">Board World</h3>
		      </div>
		
		      <div class="jumbotron">
		        <h1>Welcome to Board World!</h1>
		        <p class="lead">안녕하세요 Board World에 오신걸 환영합니다. </p>
		        <p> 간단한 게시판 생성에서 부터 글쓰기 파일 업로드 등이 가능합니다. 필요한 기능은 앞으로 추가될 예정입니다.</p>
		        <p><a class="btn btn-lg btn-success" href="/boardPageList?boardCode=1&pageNumber=1" role="button">게시물 조회하기</a></p>
		      </div>
		
		      <div class="row marketing">
		        <div class="col-lg-6">
		          <h4>자유 게시판</h4>
		          <p>다양한 주제로 다양한 글들을 써주세요! 언제 재미있고 신박한 글들은 환영합니다.</p>
		
		          <h4>QnA게시판</h4>
		          <p>궁금하신게 있으면 언제든지 물어봐주세요. 다양한 지식을 가진 유저들이 당신을 도와줄겁니다.</p>
				  <p></p>
		        </div>
		
		        <div class="col-lg-6">
		          <h4>경조사 게시판</h4>
		          <p>축하할 일이 있으면 여기에 글을 써주세요. 다같이 축하해 줍시다.</p>
		
		          <h4>공지 사항</h4>
		          <p>앞으로 그게 공지할 일이 있으면 여기에 글을 쓰겠습니다. 큰일이 있으시다면 유저들도 이용해도 상관없습니다.</p>
		
		        </div>
		      </div>
		
		      <footer class="footer">
		      	<p></p>
		        <p>&copy; Board World 2018 for JSP EXAM</p>
		      </footer>

    </div> <!-- /container -->     
     </div>
</body>
</html>