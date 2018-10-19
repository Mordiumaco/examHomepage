<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="/common/lib.jsp" %>
<body>
<%@ include file="/common/nav.jsp" %>
<%@ include file="/common/left.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript">
	$(document).ready(function(){
		
		console.log("document.ready");
		//tr에 select( class = "userClick")
		$(".userClick").click(function(){
			console.log("userClick")
			var postCode = $(this).children()[0].innerText;
			var userId = $(this).children()[2].innerText;
			
			location.href='/boardDetail?postCode='+postCode;
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
                </tr>
              </thead>
              <tbody>
	            <c:choose>
		          	<c:when test="${postList!=null}">
						<c:forEach items="${postList}" var="post">
				            <tr class="userClick">
			                  <td>${post.postCode}</td>
			                  <td class="trlength">${post.title}</td>
			                  <td>${post.userId}</td>
			                  <fmt:parseDate var="date" value="${post.postDate}" pattern="yyyy-MM-dd"/>
			                  <td><fmt:formatDate value="${date}" pattern="yyyy-MM-dd"/></td>
			                  <td>${post.postInquiry}</td>
			                </tr>
						</c:forEach>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose> 
              </tbody>
            </table>
          </div>
        </div>
</body>
</html>