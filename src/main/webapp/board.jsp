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
<script type="text/javascript">
	$(document).ready(function(){
		
		console.log("document.ready");
		//tr에 select( class = "userClick")
		$(".userClick").click(function(){
			console.log("userClick");
			var postCode = $(this).children()[5].innerText;
			var userId = $(this).children()[2].innerText;
			
			location.href='/postDetail?postCode='+postCode;
		});
		
		/* var ev = "click";
		$(".userClick").on("click", function(){
			
		});
		 */
	});
</script>
	 <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">
          	<%-- <c:choose>
	          	<c:when test="${boardList!=null}">
					<c:forEach items="${boardList}" var="board">
						<c:if test="${board.boardCode == boardCode}">
							${board.boardName}
						</c:if>
					</c:forEach>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose> --%>
			${boardName}
          </h1>
          
          <h2 class="sub-header">게시판</h2>
          <div class="table-responsive">
            <table class="table table-striped table-hover">
              <thead>
                <tr>
                  <th>번호</th>
                  <th class="trlength">제목</th>
                  <th>글쓴이</th>
                  <th>날짜</th>
                  <th>조회수</th>
                  <th>고유코드</th>
                </tr>
              </thead>
              <tbody>
	            <c:choose>
		            <c:when test="${fn:length(postList) == 0}">
			            <tr class="userClick">
		                  <td colspan="6">해당 게시판에 게시물이 존재하지 않습니다.</td>
		                </tr>
					</c:when>
		          	<c:when test="${postList!=null}">
						<c:forEach items="${postList}" var="post">
				            <tr class="userClick">
			                  <td>${post.rnum}</td>
			                  <td class="trlength">${post.title}</td>
			                  <td>${post.userId}</td>
			                  <fmt:parseDate var="date" value="${post.postDate}" pattern="yyyy-MM-dd"/>
			                  <td><fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></td>
			                  <td>${post.postInquiry}</td>
			                  <td><b>${post.postCode}</b></td>
			                </tr>
						</c:forEach>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose> 
              </tbody>
            </table>
          </div>
	          <div class="text-center">
				<ul class="pagination">
					<li>
				      
				    </li>
				    <c:choose>
				    	<c:when test="${pageNumber == 1}">
				    		<li class="disabled">
					    		 <a class="disabled" href="#" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							     </a>
							</li>
				    	</c:when>
				    	<c:when test="${pageNumber > 1}">
				    		 <li class>
					    		 <a href="/boardPageList?boardCode=${boardCode}&pageNumber=1" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							     </a>
							</li>
				    	</c:when>
				    </c:choose>
				    <c:choose>
				    	<c:when test="${pageNumber == 1}">
				    		 <li class="disabled"><a class="disabled" href="#"><</a></li>
				    	</c:when>
				    	<c:when test="${pageNumber > 1}">
				    		 <li><a href="/boardPageList?boardCode=${boardCode}&pageNumber=${pageNumber-1}"><</a></li>
				    	</c:when>
				    </c:choose>
				    <c:forEach begin="1" end="${totalPage}" var="i">
						<li><a href="/boardPageList?boardCode=${boardCode}&pageNumber=${i}">${i}</a></li>
					</c:forEach>
					<c:choose>
				    	<c:when test="${pageNumber == totalPage}">
				    		 <li class="disabled"><a class="disabled" href="#">></a></li>
				    	</c:when>
				    	<c:when test="${pageNumber < totalPage}">
				    		 <li><a href="/boardPageList?boardCode=${boardCode}&pageNumber=${pageNumber+1}">></a></li>
				    	</c:when>
				    </c:choose>
				    <c:choose>
				    	<c:when test="${pageNumber == totalPage}">
				    		 <li class="disabled">
				    		 	 <a class="disabled" href="#" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							     </a>
				    		 </li>
				    	</c:when>
				    	<c:when test="${pageNumber < totalPage}">
				    		 <li>
				    		 	 <a href="/boardPageList?boardCode=${boardCode}&pageNumber=${totalPage}" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							     </a>
				    		 </li>
				    	</c:when>
				    </c:choose>
				</ul>
			</div>
          <a href="/postSend?boardCode=${boardCode}&postRefer=${postRefer}" class="btn btn-primary pull-right">글쓰기</a>
        </div>
        
</body>
</html>