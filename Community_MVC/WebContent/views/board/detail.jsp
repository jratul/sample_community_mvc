<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Community - ${boardName }</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/nav.css" />
<script
	src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.js"></script>
</head>
<body>
	<%@include file="../include/header.jsp" %>
	<div class="container">
		<div class="content">
			<h3>
				<c:choose>
					<c:when test="${boardName eq 'free' }">자유게시판</c:when>
				</c:choose>
			</h3>
				<c:if test="${not empty keyword }">
					<p>
						검색어 : <strong>${keyword }</strong>에 대한 자세히 보기
					</p>
				</c:if>
				<a href="list.do?condition=${condition}&keyword=${keyword}"
					class="btn btn-success">글 목록</a>
				<br />
				<div>
					<c:if test="${dto.prevNum ne 0 }">
						<a
							href="detail.do?num=${dto.prevNum }&condition=${condition}&keyword=${keyword}">이전글</a>
					</c:if>
					<c:if test="${dto.nextNum ne 0 }">
						<a
							href="detail.do?num=${dto.nextNum }&condition=${condition}&keyword=${keyword}">다음글</a>
					</c:if>
				</div>
				<br />
				<c:if test="${id eq dto.writer }">
					<a href="private/update_form.do?num=${dto.num }" class="btn btn-info">수정</a>
					<a href="javascript:deleteConfirm()" class="btn btn-danger">삭제</a>
				</c:if>
				<script>
					function deleteConfirm() {
						var isDelete = confirm("정말로 글을 삭제하시겠습니까?");
						if (isDelete) {
							location.href = "private/delete.do?num=${dto.num}";
						}
					}
				</script>
				<br />
				<table class="table table-bordered table-striped">
					<tr>
						<td style="width: 10%"><strong>번호</strong></td>
						<td>${dto.num }</td>
					</tr>
					<tr>
						<td><strong>작성자</strong></td>
						<td>${dto.writer }</td>
					</tr>
					<tr>
						<td><strong>제목</strong></td>
						<td>${dto.title }</td>
					</tr>
					<tr>
						<td><strong>조회수</strong></td>
						<td>${dto.viewCount }</td>
					</tr>
					<tr>
						<td><strong>내용</strong></td>
						<td>${dto.content }</td>
					</tr>
					<tr>
						<td><strong>작성 시간</strong></td>
						<td>${dto.regdate }</td>
					</tr>
				</table>
				<!-- 댓글에 관련된 UI -->
				<div class="comments">
				</div>
			</div>
			<!-- 원글에 댓글을 작성할수 있는 폼 -->
			<div class="comment_form">
				<form action="comment_insert.do" method="post" class="form-inline">
					<input type="hidden" name="writer" value="${id }" /> <input
						type="hidden" name="ref_group" value="${dto.num }" /> <input
						type="hidden" name="target_id" value="${dto.writer }" />
					<textarea name="content" class="form-control" cols=100></textarea>
					<button type="submit" class="btn btn-primary">등록</button>
				</form>
			</div>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
	<script>
		function appendComment(item) {
			var newComment = $("<div class='comment'/>");
			newComment
			.html("<img src='${pageContext.request.contextPath }"+item.pic+"' style='width: 50px; height:50px;'/>" +" " + item.num + " " + item.writer + " " + item.content + " " + item.likeCnt + " " + item.dislikeCnt + " " + item.regdate);
			
			newComment.append("<hr/>").appendTo(".comments");
		}
		
		var postNum = ${postNum};
		var startPageNum;
		var endPageNum;

		$.ajax({
			url: "${pageContext.request.contextPath }/board/free/comment/list.do",
			data: {
				"postNum" : postNum,
				"pageNum" : 1
			},
			success: function(responseData) {
				startPageNum = responseData.startPageNum;
				endPageNum = responseData.endPageNum;
				
				console.log(responseData);
				responseData.list.forEach(function(value, index) {
					appendComment(value);
				});
				
				$(".comments").append("<br/>");
				$(".comments").append("<<");
				for(var i=startPageNum; i<=endPageNum;i++) {
					$(".comments").append(i + " ");
				}
				$(".comments").append(">>");
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
		   }
		});
	</script>
</body>
</html>