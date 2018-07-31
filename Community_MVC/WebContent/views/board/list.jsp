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
				<c:when test="${boardName eq 'free' }"><a href="${pageContext.request.contextPath }/board/free/list.do">자유게시판</a></c:when>
			</c:choose>
			</h3>
			<br /> 
			<c:if test="${not empty id }">
				<a href="${pageContext.request.contextPath }/board/private/${boardName }/insert_form.do" class="btn btn-info">글쓰기</a>
			</c:if>
			<table class="table table-striped">
				<thead>
					<tr>
						<th style="width:10%">번호</th>
						<th style="width:10%">작성자</th>
						<th style="width:50%">제목</th>
						<th style="width:10%">조회수</th>
						<th style="width:20%">등록일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="tmp" items="${list }">
						<tr>
							<td>${tmp.num }</td>
							<td>${tmp.writer }</td>
							<td><a href="detail.do?num=${tmp.num }&condition=${condition}&keyword=${keyword}">${tmp.title }</a></td>
							<td>${tmp.viewCount }</td>
							<td>${tmp.regdate }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<nav aria-label="Page navigation">
				<ul class="pagination">
					<c:choose>
						<c:when test="${ startPageNum ne 1}">
							<li><a href="${pageContext.request.contextPath }/board/free/list.do?pageNum=${startPageNum-1 }&condition=${condition}&keyword=${keyword}"
								aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a href="javascript:"
								aria-label="Previous"><span aria-hidden="true">&laquo;</span></a>
							</li>
						</c:otherwise>
					</c:choose>
					<c:forEach var="i" begin="${startPageNum }"
						end="${endPageNum<totalPageCount ? endPageNum : totalPageCount }">
						<li <c:if test="${pageNum eq i}">class="active"</c:if>><a
							href="${pageContext.request.contextPath }/board/free/list.do?pageNum=${i}&condition=${condition}&keyword=${keyword}">${i }</a></li>
					</c:forEach>
	
					<c:choose>
						<c:when test="${ endPageNum lt totalPageCount}">
							<li>
								<a href="${pageContext.request.contextPath }/board/free/list.do?pageNum=${endPageNum+1 }&condition=${condition}&keyword=${keyword}" aria-label="Next">
									<span aria-hidden="true">&raquo;</span>
								</a></li>
						</c:when>
						<c:otherwise>
							<li class="disabled"><a class="disabled" href="javascript:"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</nav>
			<!-- keyword 검색어 form -->
			<form action="${pageContext.request.contextPath }/board/free/list.do" method="post" class="form-inline">
				<label for="condition">검색조건</label>
				<select name="condition" id="condition" class="form-control">
					<option value="titlecontent" <c:if test="${condition eq 'titlecontent'}">selected</c:if>>제목+검색</option>
					<option value="title" <c:if test="${condition eq 'title'}">selected</c:if>>제목</option>
					<option value="writer" <c:if test="${condition eq 'writer'}">selected</c:if>>작성자</option>
				</select>
				<input type="text" class="form-control" name="keyword" placeholder ="검색어.." value="${keyword }"/>
				<button class="btn btn-info" type="submit">검색</button>
			</form>
			<c:choose>
				<c:when test="${not empty keyword }">
					<p><strong>${keyword }</strong> 검색어로 검색된 <strong>${totalRow }</strong>개의 글이 있습니다.</p>
				</c:when>
				<c:otherwise>
					<p><strong>${totalRow }</strong>개의 글이 있습니다.</p>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
	<script>
		function reloadList() {
			console.log("reload");
			setTimeout(function() {
				window.location.reload();
			}, 100);
		}
	</script>
</body>
</html>