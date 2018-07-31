<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.js"></script>
<nav class="navbar navbar-expand-sm navbar-dark bg-primary">
	<div class="container">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath }/home.do">Home</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Notice</a></li>
				<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath }/board/free/list.do">Board</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Gallery</a></li>
				</li>
			</ul>
			<ul class="nav justify-content-end">
				<c:choose>
					<c:when test="${empty id }">
						<li class="nav-item"><a href="" class="nav-right">Login</a></li>
						&nbsp;<span class="nav-right">|</span>&nbsp;
						<li class="nav-item"><a href="${pageContext.request.contextPath }/user/insert_form.do" class="nav-right">Join</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a href="${pageContext.request.contextPath }/user/logout.do" class="nav-right">Logout</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>
<div class="container-fluid">
	<div class="user-box">
	<c:choose>
		<c:when test="${not empty id }">
			<img id="picInfo"/><br />
			<p id="nicknameInfo"></p>
			<p id="pointInfo"></p>
			<script>
				var id = "${id}";
				$.ajax({
					url: "${pageContext.request.contextPath }/user/getInfo.do",
					method: "post",
					data : {"id" : id},
					success : function(responseData) {
						$("#picInfo").attr("src", "${pageContext.request.contextPath }" + responseData.pic);
						$("#nicknameInfo").text(responseData.nickname + "님 환영합니다.");
						$("#pointInfo").text("포인트 : " + responseData.point);
					}
				});
			</script>
		</c:when>
		<c:otherwise>
			<h3>로그인</h3>
			<form action="${pageContext.request.contextPath }/user/login.do" method="post" id="loginForm">
				<div class="form-row">
					<div class="form-group">
						<label for="id">아이디</label>
						<input type="text" class="form-control" id="id" name="id"/>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group">
						<label for="pwd">비밀번호</label>
						<input type="password" class="form-control" id="pwd" name="pwd"/>
					</div>
				</div>
				<button class="btn btn-primary" type="submit">로그인</button>
			</form>
			<script>
				$("#loginForm").on("submit", function() {
					var id = $("#id").val();
					var pwd = $("#pwd").val();
					
					if(id=="" || pwd == "") {
						alert("아이디와 비밀번호를 바르게 입력해 주세요.");
						
					} else {
						$.ajax({
							url: "${pageContext.request.contextPath }/user/login.do",
							method: "post",
							data: {
								id : id,
								pwd : pwd
							},
							success: function(responseData) {
								console.log(responseData);
								if(responseData.isSuccess) {
									location.reload();
								} else {
									alert("아이디와 비밀번호를 확인해 주세요.");
								}
							}
						});
					}
					
					return false;
				});
			</script>
		</c:otherwise>
	</c:choose>
	</div>