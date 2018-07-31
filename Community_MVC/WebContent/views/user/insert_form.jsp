<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sample Community</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/nav.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/insert-form.css" />
</head>
<body>
	<%@include file="../include/header.jsp"%>
	<div class="container">
		<div class="content">
			<h3>회원가입</h3>
			 <span class="form-essential">*</span> 표시는 필수 입력 항목입니다.
			<form
				action="${pageContext.request.contextPath }/user/insert.do"
				method="post" enctype="multipart/form-data"
				id="userInsertForm">
				<div class="form-row">
					<div class="form-group col-sm-6">
						<label for="id">아이디 <span class="form-essential">*</span></label> <span id="checkIdResult"></span><input type="text"
							class="form-control" name="id" id="id" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm-6">
						<label for="pwd">비밀번호(4~20자) <span class="form-essential">*</span></label> <span id="checkPwdResult"></span><input type="password"
							class="form-control" name="pwd" id="pwd" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm-6">
						<label for="pwd-re">비밀번호 확인 <span class="form-essential">*</span></label> <span id="checkPwdReResult"></span><input type="password"
							class="form-control" name="pwd-re" id="pwd-re" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm-6">
						<label for="nickname">닉네임 <span class="form-essential">*</span></label><span id="checkNicknameResult"></span> <input type="text"
							class="form-control" name="nickname" id="nickname" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm-6">
						<label for="email">이메일</label> <span class="form-essential">*</span><span id="checkEmailResult"></span><input type="text"
							class="form-control" name="email" id="email" />
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-sm-6">
						<label for="image">프로필 사진(선택)</label> <input type="file"
							class="form-control-file" name="image" id="image" />
						<br />
						<div id="holder"></div>
					</div>
				</div>
				<button class="btn btn-primary" type="submit">회원 가입</button>
			</form>
			<br /><br /><br /><br />
		</div>
	</div>
	<%@include file="../include/footer.jsp"%>
</body>
<script src="${pageContext.request.contextPath }/resources/js/jquery-3.3.1.js"></script>
<script>
	var imageUpload = document.getElementById("image");
	var holder = document.getElementById("holder");
	
	var domEleArray = [$('#image').clone()];
	
	var formIdValid = false;
	var formPwdValid = false;
	var formPwdReValid = false;
	var formNicknameValid = false;
	var formEmailValid = false;

	imageUpload.onchange = function(e) {
		e.preventDefault();

		var file = imageUpload.files[0];
		var reader = new FileReader();
		
		var fileName = file.name;
		var ext = getExtensionOfFilename(fileName);
		
		if(ext!=".jpg" && ext!= ".jpeg" && ext!=".png" && ext!=".bmp" && ext!=".gif") {
			alert("사진 파일만 업로드 가능합니다.");
			 domEleArray[1] = domEleArray[0].clone(true);
			 $('#image').replaceWith(domEleArray[1]);
			return false;
		}
	
		reader.onload = function(event) {
			var img = new Image();
			
			img.src = event.target.result;
			
			console.log(img.width);

			if (img.width > 50) {
				img.width = 50;
			}

			holder.innerHTML = '';
			holder.appendChild(img);
		};
		reader.readAsDataURL(file);

		return false;
	}
	
	$("#userInsertForm").submit(function() {
		if(!formIdValid || !formPwdValid || !formPwdReValid || !formNicknameValid || !formEmailValid) {
			alert("양식을 올바르게 작성해 주세요.");
			return false;
		}
	});
	
	$("#id").on("input", function() {
		var inputId = $(this).val();
		
		$.ajax({
			url: "canUseId.do",
			method: "post",
			data: {inputId: inputId},
			success: function(responseData) {
				if(responseData.canUse && $("#id").val() != "") {
					formIdValid = true;
					$("#checkIdResult")
					.text("사용가능")
					.css("color", "#009900");
				} else {
					formIdValid = false;
					$("#checkIdResult")
					.text("사용불가")
					.css("color", "#cc0000");
				}
			}
		});
	});
	
	$("#pwd").on("input", function() {
		var pwd = $("#pwd").val();
		var pwdLen = pwd.length;
		
		if(pwdLen < 4 || pwdLen > 20) {
			formPwdValid = false;
			$("#checkPwdResult")
			.text("비밀번호는 4자 이상 20자 이하로 입력해 주세요.")
			.css("color", "#cc0000");
		} else {
			formPwdValid = true;
			$("#checkPwdResult").text("");
		}
	});
	
	
	$("#pwd-re").on("input", function() {
		var pwd = $("#pwd").val();
		var pwdRe = $(this).val();
		
		if(pwd === pwdRe) {
			formPwdReValid = true;
			$("#checkPwdReResult").text("");
		} else {
			formPwdReValid = false;
			$("#checkPwdReResult")
			.text("비밀번호와 동일하게 입력해주세요.")
			.css("color", "#cc0000");
		}
	});
	
	$("#nickname").on("input", function() {
		var inputNickname = $(this).val();
		
		$.ajax({
			url: "canUseNickname.do",
			method: "post",
			data: {inputNickname: inputNickname},
			success: function(responseData) {
				if(responseData.canUse && $("#nickname").val() != "") {
					formNicknameValid = true;
					$("#checkNicknameResult")
					.text("사용가능")
					.css("color", "#009900");
				} else {
					formNicknameValid = false;
					$("#checkNicknameResult")
					.text("사용불가")
					.css("color", "#cc0000");
				}
			}
		});
	});
	
	$("#email").on("input", function() {
		var inputEmail = $(this).val();
		
		var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		
		if(inputEmail.match(regExp) != null) {
			$.ajax({
				url: "canUseEmail.do",
				method: "post",
				data: {inputEmail: inputEmail},
				success: function(responseData) {
					if(responseData.canUse && $("#email").val() != "") {
						formEmailValid = true;
						$("#checkEmailResult")
						.text("사용가능")
						.css("color", "#009900");
					} else {
						formEmailValid = false;
						$("#checkEmailResult")
						.text("사용불가")
						.css("color", "#cc0000");
					}
				}
			});
		} else {
			formEmailValid = false;
			$("#checkEmailResult")
			.text("이메일 형식에 맞게 입력해 주세요.")
			.css("color", "#cc0000");
		}
	});
	
	function getExtensionOfFilename(filename) {
		 
	    var _fileLen = filename.length;
	 
	    /** 
	     * lastIndexOf('.') 
	     * 뒤에서부터 '.'의 위치를 찾기위한 함수
	     * 검색 문자의 위치를 반환한다.
	     * 파일 이름에 '.'이 포함되는 경우가 있기 때문에 lastIndexOf() 사용
	     */
	    var _lastDot = filename.lastIndexOf('.');
	 
	    // 확장자 명만 추출한 후 소문자로 변경
	    var _fileExt = filename.substring(_lastDot, _fileLen).toLowerCase();
	 
	    return _fileExt;
	}
</script>
</html>