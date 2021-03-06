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
		var postNum = ${postNum};
		var startPageNum;
		var endPageNum;
		var commentList = [];
		function appendComment(item) {
			var marginLeft = 50 * item.depth;
			var newComment = $("<div class='comment' style='margin-left:"+ marginLeft + "px'/>");
			var commentModify = " <a href=\"javascript:openUpdateComment(" + item.num+ ", " + item.postNum + ", \'"+ item.content + "\')\">수정</a> <a href='javascript:commentDeleteConfirm("+item.num+")'>삭제</a> "
			var commentBase = "<img src='${pageContext.request.contextPath }"+item.pic+"' style='width: 50px; height:50px;'/>" +" " + item.num + " <strong>" + item.writer + "</strong> " + item.content + " <font color = 'blue'>" + item.likeCnt + "</font> <font color='red'>" + item.dislikeCnt + "</font> " + item.regdate;
			var commentLike =  "<div class= 'commentRightBtn'><font color='blue'><a href='javascript: doLike("+ item.num + ")'>추천</a></font>  <font color='red'><a href='javascript: doDislike("+ item.num + ")'>비추천</a></font>  <a href='javascript:openSubComment("+ item.num + ", " + item.postNum + ", " + item.depth + ")'>답글</a></div>"

			if(${not empty nickname} && '${nickname}' == item.writer && item.isDelete == 0) {
				commentBase += commentModify;
			}
			
			if(item.isDelete == 0) {
				commentBase += commentLike;
			}
			newComment
			.html(commentBase);
			
			if(item.isDelete == 1) {
				newComment.css("background-color", "#cccccc");
			}
			
			newComment.append("<hr/>").appendTo(".comments");
			commentList.push({
				"div" : newComment,
				"num" : item.num,
				"postNum" : item.postNum, 
				"depth" : item.depth
			});
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
					commentList = [];
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
		
		function openSubComment(parentNum, postNum, parentDepth) {
			var targetDiv;
			
			if(${empty nickname}) {
				alert("로그인이 필요합니다.");
				return false;
			}
			
			commentList.forEach(function(value, idx) {
				if(parentNum == value.num) {
					targetDiv = value.div;
				}
			});
			
			$(".subComment").remove();
			
			targetDiv.append(
				"<div class='subComment'>"
				+	"<form action='${pageContext.request.contextPath }/board/${boardName }/comment/insert.do' method='post' class='form-inline subCommentForm'>"
				+		"<input type='hidden' name='writer' value='${nickname }' />"
				+		"<input type='hidden' name='postNum' value='" + postNum + "' />"
				+		"<input type='hidden' name='parentNum' value='" + parentNum + "' />"
				+		"<input type='hidden' name='depth' value='"+ (parentDepth + 1) + "' />"
				+		"<textarea name='content' class='form-control' cols=100></textarea>"
				+		"<button type='submit' class='btn btn-primary'>등록</button>"
				+	"</form>"
				+"</div><br/>"
			
			);
		}
		
		function openUpdateComment(num, postNum, content) {
			var targetDiv;
			commentList.forEach(function(value, idx) {
				if(num == value.num) {
					targetDiv = value.div;
				}
			});
			
			$(".subComment").remove();
			
			targetDiv.append(
					"<div class='subComment'>"
					+	"<form action='${pageContext.request.contextPath }/board/${boardName }/comment/update.do' method='post' class='form-inline subCommentForm'>"
					+		"<input type='hidden' name='num' value='"+ num + "'/>"
					+		"<input type='hidden' name='postNum' value='" + postNum + "'/>"
					+		"<textarea name='content' class='form-control' cols=100>"+ content + "</textarea>"
					+		"<button type='submit' class='btn btn-primary'>등록</button>"
					+	"</form>"
					+"</div><br/>"
				);
		}

		loadComments(postNum, 1);
		
		$("#newCommentForm").on("submit", function() {
			if(${empty nickname}) {
				alert("로그인이 필요합니다.");
				return false;
			}
				
			$(this).submit();
		});
		
		function doLike(num) {
			if(${empty nickname}) {
				alert("로그인이 필요합니다.");
				return false;
			}
			var height = $(document).scrollTop();
			$.ajax({
				url : "${pageContext.request.contextPath }/board/free/comment/like.do",
				data: {"num" : num, "likeOrDislike": "like"},
				success: function(responseData) {
					if(responseData.canLike) {
						location.reload();
						$(document).scrollTop(height);
					} else {
						alert("추천, 비추천할 수 없습니다.");
					}
				}
			})
		}
		
		function doDislike(num) {
			if(${empty nickname}) {
				alert("로그인이 필요합니다.");
				return false;
			}
			var height = $(document).scrollTop();
			$.ajax({
				url : "${pageContext.request.contextPath }/board/free/comment/like.do",
				data: {"num" : num, "likeOrDislike": "dislike"},
				success: function(responseData) {
					if(responseData.canLike) {
						location.reload();
						$(document).scrollTop(height);
					} else {
						alert("추천, 비추천할 수 없습니다.");
					}
				}
			})
		}
		
		function commentDeleteConfirm(num) {
			var height = $(document).scrollTop();
			var isDelete = confirm("정말로 댓글을 삭제하시겠습니까?");
			if(!isDelete) return;
			$.ajax({
				url: "${pageContext.request.contextPath }/board/free/comment/delete.do",
				data: {"num" : num},
				success: function(responseData) {
					if(responseData.isSuccess) {
						location.reload();
						$(document).scrollTop(height);
					} else {
						alert("댓글 삭제에 실패했습니다.")
					}
				}
			})
		}
		
	</script>
</body>
</html>