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
	<c:choose>
		<c:when test="${isSuccess }">
			<script>
				alert("회원가입이 완료되었습니다.");
				location.href= "${pageContext.request.contextPath }";
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("회원가입에 실패했습니다.");
				location.href="${pageContext.request.contextPath }/user/insert_form.do";
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>