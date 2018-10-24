<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/common/lib.jsp" %>
<body>
<%@ include file="/common/nav.jsp" %>
<%@ include file="/common/left.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
	 <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	 	  <div class="container">
		      <div class="row">
			        <div class="col-sm-8 blog-main">
				          <div class="blog-post">
				            <h2 class="blog-post-title">${postVo.postName}</h2>
				            <p class="blog-post-meta">
				            	<fmt:parseDate var="date" value="${postVo.postDate}" pattern="yyyy-MM-dd"/> 
				            	<fmt:formatDate value="${date}" pattern="yyyy.MM.dd"/>
				            	by <a href="#">${postVo.userId}</a>
				            </p>
							<hr>
				            <p>${postVo.postCon}</p>
				            <hr>
				            <blockquote>
				            	<c:choose>
				            		<c:when test="${fn:length(fileList) == 0}">
				            			해당 게시물은 파일이 없습니다.
				            		</c:when>
				            		<c:when test="${fileList != null}">
				            			<c:forEach items="${fileList}" var="file">
				            				<p><a href="/fileS?file=${file.fileName}" >${file.fileName}<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span></a><p>
				            			</c:forEach>
				            		</c:when>
				            		<c:otherwise></c:otherwise>
				            	</c:choose>
				            </blockquote>
				          </div><!-- /.blog-post -->
				          <hr>
					          <a href="/boardPageList?boardCode=1&pageNumber=1" class="btn btn-primary btn-xs">목록</a>
					          <c:choose>
					          	<c:when test="${userVo.userId != null && userVo.userId == postVo.userId}">
					          		<a href="/postUpdateSend?postCode=${postVo.postCode}" class="btn btn-primary pull-right btn-xs">수정</a>
					          	 	<a onclick="return confirm('정말로 삭제하시겠습니까?')" href="/postDelete?postCode=${postVo.postCode}&boardCode=${postVo.boardCode}" class="btn btn-primary pull-right btn-xs">삭제</a>
					          	 	<a href="/postAnswerSend?postRefer=${postVo.postCode}" class="btn btn-primary pull-right btn-xs">답글</a>
					          	</c:when>
					          	<c:when test="${userVo.userId != null && userVo.userId != postVo.userId}">
					          		<a href="/postDetail" class="btn btn-primary pull-right btn-xs">답글</a>
					          	</c:when>
					          </c:choose>
				          <hr>
				            <h3>댓글</h3>
				            <c:choose>
					          	<c:when test="${userVo.userId != null}">
							 		<form class="form-horizontal" role="form" action="/postCommentInsert?postCode=${postVo.postCode}" method="post">
										<div class="test">
											<div class="col-sm-10">
												<textarea class="form-control" placeholder="댓글 내용" name="commentCon" maxlength="2048" style="height: 50px;"></textarea>
												<input type="submit" class="btn btn-primary pull-right btn-block" value="등록">
											</div>
										</div>
									</form>
					          	</c:when>
					          </c:choose>
				           
			         </div>
				          <hr>
				          
				 	</div>
				 	 <hr>
				 	 <c:choose>
				 	 	<c:when test="${fn:length(commentList) == 0}">
					 	 		<div class="commentCon">
						             <div><p>해당 게시물에는 댓글이 존재하지 않습니다</p></div>
						         </div>
					 	</c:when>
				 	 	<c:when test="${commentList != null}">
				 	 		<c:forEach items="${commentList}" var="commentVo">
				 	 			<div class="commentCon">
					 	 			<fmt:parseDate var="commentDate" value="${commentVo.commentDate}" pattern="yyyy-MM-dd"/> 
							 		<p style="text-align:left; color:#848484;">${commentVo.userId}- - - <fmt:formatDate value="${commentDate}" pattern="yyyy.MM.dd"/></p>
							 		<c:if test="${userVo.userId == commentVo.userId}">
							 			<a class="btn btn-primary pull-right btn-xs" href="/commentS?commentCode=${commentVo.commentCode}">삭제</a>
							 		</c:if>
						         	 <hr>
						             <div><p>${commentVo.title}</p></div>
						         </div>
				 			</c:forEach>
				 	 	</c:when>
				 	 	<c:otherwise></c:otherwise>
				 	 </c:choose>
			 	</div>
		 	</div>
	 </div>
</body>
</html>