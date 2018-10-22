<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/common/lib.jsp" %>
<body>
<%@ include file="/common/nav.jsp" %>
<%@ include file="/common/left.jsp" %>
	 <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h1 class="page-header">게시판 만들기</h1>
        <hr>
		<form class="form-inline" action="/boardInsert" method="post">
		  <div class="form-group">
		    <label class="sr-only">게시판 제목</label>
		    <p class="form-control-static">게시판 제목</p>
		    <input type="text" class="form-control" id="boardName" name="boardName" placeholder="게시판 제목">
		  </div>
		  <button type="submit" class="btn btn-primary">게시판 등록</button>
		</form>
		<hr>
		<h1 class="page-header">게시판 수정</h1>
		<hr>
		 <c:forEach items="${boardListForBoardMaker}" var="boardVo">
		 	<c:choose>
		 		<c:when test="${boardListForBoardMaker != null}">
		 			<form class="form-inline" action="/boardUpdate" method="post">
					 <div class="form-group">
					    <label class="sr-only">게시판 번호</label>
					    <p class="form-control-static">게시판 번호</p>
					    <input type="text" class="form-control" id="boardCode" name="boardCode" placeholder="게시판 번호" value="${boardVo.boardCode}" readonly="readonly">
					 </div>
					 <div class="form-group">
					    <label class="sr-only">게시판 제목</label>
					    <p class="form-control-static">게시판 제목</p>
					    <input type="text" class="form-control" id="boardName" name="boardName" value="${boardVo.boardName}" placeholder="게시판 제목">
					    <label class="sr-only">사용여부</label>
					    <p class="form-control-static">사용여부</p>
					    <select class="form-control" name="boardAvailable">
					      <c:if test="${boardVo.boardAvailable == 1}">
					      	<option value="1" selected="selected">Y</option>
						  	<option value="2">N</option>
					      </c:if>
					      <c:if test="${boardVo.boardAvailable == 2}">
					      	<option value="1">Y</option>
						  	<option value="2" selected="selected">N</option>
					      </c:if>
						</select>
					  </div>
					  <button type="submit" class="btn btn-primary">게시판 수정</button>
					</form>	
		 		</c:when>
		 		<c:otherwise>
		 		</c:otherwise>
		 	</c:choose>
		 </c:forEach>
     </div>
</body>
</html>