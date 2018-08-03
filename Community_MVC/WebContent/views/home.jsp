<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Community</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/nav.css" />
</head>
<body>
	<%@include file="include/header.jsp" %>
	<div class="container">
		<div class="content">
			<h3>Home</h3>
			<div class="card" style="width:18rem">
				<div class="card-header">
					<a href="${pageContext.request.contextPath }/board/free/list.do">자유게시판</a>
				</div>
				<ul class="list-group list-group-flush">
					<c:forEach var="tmp" items="${list }">
						<li class="list-group-item"><a href="${pageContext.request.contextPath }/board/free/detail.do?num=${tmp.num}">${tmp.title }</a></li>
					</c:forEach>
				</ul>
			</div>
			
		</div>
	</div>
	<%@include file="include/footer.jsp" %>
</body>
</html>