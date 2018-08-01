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
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/board.css" />
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
				<!-- 원글에 댓글을 작성할수 있는 폼 -->
				<div class="comment_form">
					<form action="${pageContext.request.contextPath }/board/${boardName }/comment/insert.do" method="post" class="form-inline" id="newCommentForm">
						<input type="hidden" name="writer" value="${nickname }" /> 
						<input type="hidden" name="postNum" value="${postNum }" />
						<input type="hidden" name="parentNum" value="0" />
						<input type="hidden" name="depth" value="0" />
						<textarea name="content" class="form-control" cols=100></textarea>
						<button type="submit" class="btn btn-primary">등록</button>
					</form>
				</div>
			</div>
			<br /><br /><br /><br />
		</div>
	</div>
	<%@include file="../include/footer.jsp" %>
	<script>
		function appendComment(item) {
			var marginLeft = 50 * item.depth;
			var newComment = $("<div class='comment' style='margin-left:"+ marginLeft + "px'/>");
			newComment
			.html("<img src='${pageContext.request.contextPath }"+item.pic+"' style='width: 50px; height:50px;'/>" +" " + item.num + " <strong>" + item.writer + "</strong> " + item.content + " <font color = 'blue'>" + item.likeCnt + "</font> <font color='red'>" + item.dislikeCnt + "</font> " + item.regdate
				+ "<div class= 'commentRightBtn'><font color='blue'>추천</font>  <font color='red'>비추천</font>  수정  삭제  답글</div>"
			);
			
			newComment.append("<hr/>").appendTo(".comments");
		}
		
		function loadComments(postNum, pageNum) {
			$.ajax({
				url: "${pageContext.request.contextPath }/board/${boardName}/comment/list.do",
				data: {
					"postNum" : postNum,
					"pageNum" : pageNum
				},
				success: function(responseData) {
					startPageNum = responseData.startPageNum;
					endPageNum = responseData.endPageNum;
					
					$(".comments").html("");
					responseData.list.forEach(function(value, index) {
						appendComment(value);
					});
					
					$(".comments").append("<br/>");
					$(".comments").append("<<");
					for(var i=startPageNum; i<=endPageNum;i++) {
						var eleStrPre = "<a href='javascript: loadComments("+postNum+", "+ i +")'><span style='margin-left:5px; margin-right:5px;'>";
						var eleStrPost
						
						if(i==pageNum) {
							eleStrPost = "<strong><u>" + i + "</u></strong></span></a>";
						} else {
							eleStrPost = i + "</span></a>";
						}
						var newPageBtn = $(eleStrPre + eleStrPost + "  ");
						$(".comments").append(newPageBtn);
					}
					$(".comments").append(">>");
				},
				error:function(request,status,error){
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error)
			   }
			});
		}
		
		var postNum = ${postNum};
		var startPageNum;
		var endPageNum;

		loadComments(postNum, 1);
		
		$("#newCommentForm").on("submit", function() {
			if(${empty nickname}) {
				alert("로그인이 필요합니다.");
				return false;
			}
				
			$(this).submit();
		});
		
	</script>
</body>
</html>