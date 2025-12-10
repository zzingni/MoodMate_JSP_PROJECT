<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 헤더 공통 네비게이션 -->
	<!-- include 하는 곳에 css 링크 포함되면 됨! header 파일에는 css 링크 없어도 괜찮음 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm mb-4">
	    <div class="container">
	
	        <!-- 로고/홈 링크 -->
	        <a class="navbar-brand fw-bold" href="/">MoodMate</a>
	
	        <!-- 메뉴 (우측) -->
	        <div class="ms-auto">
	
	            <!-- 로그인 안 했을 때 로그인 버튼만 노출 -->
	            <c:if test="${empty sessionScope.loginUser}">
	                <a class="btn btn-outline-primary" href="/login">로그인</a>
	                <a class="btn btn-outline-primary" href="/join">회원가입</a>
	            </c:if>
	
	            <!-- 로그인 했을 때 마이페이지 / 모임 만들기 / 로그아웃 노출 -->
	            <c:if test="${not empty sessionScope.loginUser}">
	            	<!-- 로그인 했을 때 닉네임 표시 영역 -->
				    <span class="me-3">
				        <strong>${sessionScope.loginUser.nickname}</strong> 님, 안녕하세요 😊
				    </span>
	                <a class="btn btn-outline-secondary me-2" href="/mypage">마이페이지</a>
	                <a class="btn btn-outline-primary me-2" href="/meeting/create">+ 모임 만들기</a>
	                <a class="btn btn-danger" href="/logout">로그아웃</a>
	            </c:if>
	        </div>
	    </div>
	</nav>
</body>
</html>