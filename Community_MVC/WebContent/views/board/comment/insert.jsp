<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Community</title>
</head>
<body>
<c:if test="${not isSuccess }">
<script>
	alert("댓글 작성에 실패했습니다.")
</script>
</c:if>
<script>
	location.href = "${pageContext.request.contextPath }/board/${boardName}/detail.do?num=${postNum}";
</script>
</body>
</html>