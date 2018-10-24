<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <div class="col-sm-3 col-md-2 sidebar">

	<ul class="nav nav-sidebar">
		<li class="active"><a href="/main.jsp">Main <span
				class="sr-only">(current)</span></a></li>
		<li><a href="userAllList">사용자 리스트</a></li>
		<li><a href="/userPageList?page=1&pageSize=10">사용자 페이지 리스트</a></li>
		<li><a href="/userDetail">사용자 정보조회</a></li>
		<li><a href="user/userEdit.jsp">사용자 정보조회</a></li>
		<li><a href="/prod/prod_list.jsp?page=1&pageSize=10">제품 리스트</a></li>
		사용자 리스트 클릭시: jspuser 전체 정보를 조회하여 화면에 출력
					
					0. 요청을 처리할 서블릿 생성: UserServlet
					1. jspuser 전체정보를 조회 : userService.selectUserAll();
					2. 사용자 정보를 화면에 출력할 jsp : userAllList.jsp
					
	</ul>
	
</div>
 -->
  <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
           <c:choose>
				<c:when test="${boardList!=null}">
					<c:forEach items="${boardList}" var="board">
						<c:choose>
							<c:when test="${userVo.right == 1}">
								<li><a href="boardPageList?boardCode=${board.boardCode}&pageNumber=1">${board.boardName}</a></li>
							</c:when>
							<c:when test="${userVo.right == 3}">
								<li><a href="boardPageList?boardCode=${board.boardCode}&pageNumber=1">${board.boardName}</a></li>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
          </ul>
        </div>

