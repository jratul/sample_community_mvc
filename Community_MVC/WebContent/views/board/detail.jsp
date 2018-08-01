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
					<c:forEach var="tmp" items="${commentList }">
						<div class="comment"
							<c:if test="${tmp.num ne tmp.comment_group }">style="margin-left:50px;"</c:if>>
							<c:if test="${tmp.num ne tmp.comment_group }">
								<img class="reply_icon"
									src="${pageContext.request.contextPath }/resources/images/re.gif" />
							</c:if>
		
							<img
								src="${pageContext.request.contextPath }/resources/images/user_image.gif" />
							<span><strong>${tmp.writer }</strong></span> <span>${tmp.regdate }</span> <a
								href="javascript:" class="reply_link">답글</a> | <a href="">신고</a>
							<c:if test="${tmp.num ne tmp.comment_group }"><br/>
								<i class="muted"><span class="text-primary">${tmp.target_id }</span></i>
							</c:if>
							<p><pre>${tmp.content }</pre></p>
							<form action="comment_insert.do" method="post" class="form-inline">
								<!-- 덧글 작성자 -->
								<input type="hidden" name="writer" value="${id }" />
								<!-- 덧글 그룹 -->
								<input type="hidden" name="ref_group" value="${dto.num }" />
								<!-- 덧글 대상 -->
								<input type="hidden" name="target_id" value="${tmp.writer }" /> <input
									type="hidden" name="comment_group" value="${tmp.comment_group }" />
								<textarea name="content" class="form-control" cols=30 rows=1></textarea>
								<button type="submit" class="btn btn-primary">등록</button>
							</form>
							<br />
						</div>
						<hr />
					</c:forEach>
					<br />
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
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
</body>
</html>