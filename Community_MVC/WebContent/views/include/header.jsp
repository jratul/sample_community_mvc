<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<li class="nav-item active"><a class="nav-link" href="home.do">Home</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Notice</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Board</a></li>
				<li class="nav-item active"><a class="nav-link" href="#">Gallery</a></li>
				</li>
			</ul>
			<ul class="nav justify-content-end">
				<li class="nav-item"><a href="" class="nav-right">Login</a></li>
				&nbsp;<span class="nav-right">|</span>&nbsp;
				<li class="nav-item"><a href="user/insert_form.do" class="nav-right">Join</a></li>
			</ul>
		</div>
	</div>
</nav>