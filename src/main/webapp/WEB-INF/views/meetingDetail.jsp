<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모임 상세</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<style>
	body { background-color: #f2f6fc; }
	
	.meeting-card {
	    background: #fff;
	    border-radius: 20px;
	    padding: 0;
	    max-width: 800px;
	    margin: 50px auto;
	    box-shadow: 0 6px 20px rgba(0,0,0,0.15);
	    overflow: hidden;
	}
	
	.meeting-card {
	    background: #fff;
	    border-radius: 20px;
	    padding: 0;
	    max-width: 650px;          /* 가로폭 ↓ (세로 강조) */
	    margin: 60px auto;
	    box-shadow: 0 6px 20px rgba(0,0,0,0.15);
	    overflow: hidden;
	    min-height: 700px;        /* 세로 길게 */
	}
	
	/* 카드 이미지 높이 늘림 */
	.meeting-card img {
	    width: 100%;
	    height: 380px;
	    object-fit: cover;
	}
	
	.meeting-body {
	    padding: 35px;
	}
	
	.meeting-title {
	    font-weight: 700;
	    font-size: 1.9rem;
	    margin-bottom: 18px;
	}
	
	.meeting-content {
	    color: #495057;
	    margin-bottom: 15px;
	}
	
	.meeting-info {
	    font-size: 0.95rem;
	    margin-bottom: 14px;
	    line-height: 1.6;
	}
	
	.badge-category {
	    font-size: 0.82rem;
	    margin-bottom: 14px;      
	    display: inline-block; 
	}
	
	.btn-apply {
	    width: 100%;
	    font-weight: 600;
	    padding: 10px 0;
	}
	
	.alert-warning {
	    margin-top: 20px;
	}
</style>
</head>
<body>
<!-- 공통 헤더 부분 -->
<%@ include file="/WEB-INF/views/header.jsp" %>


<div class="meeting-card">
    <img src="${meeting.imageUrl}" class="card-img-top" alt="모임 이미지">

	<div class="meeting-body">
	    <div class="d-flex justify-content-between align-items-center mb-2">
		    <h3 class="meeting-title m-0">${meeting.title}</h3>
		
		    <c:if test="${not empty loginUser and loginUser.userId == meeting.user.userId}">
		        <div class="d-flex align-items-center">
		            <a href="/meeting/edit/${meeting.meetingId}"
		               class="text-primary fw-semibold me-3"
		               style="cursor:pointer; text-decoration:none;">
		               수정
		            </a>
		
		            <a href="/meeting/delete/${meeting.meetingId}"
		               class="text-danger fw-semibold"
		               onclick="return confirm('정말 삭제하시겠습니까?');"
		               style="cursor:pointer; text-decoration:none;">
		               삭제
		            </a>
		        </div>
		    </c:if>
		</div>
		<p class="meeting-content">${meeting.content}</p>
	
		<p class="meeting-info"><strong>장소 :</strong> ${meeting.location}</p>
		<p class="meeting-info"><strong>일시 :</strong> <fmt:formatDate value="${meeting.meetingDate}" pattern="yyyy-MM-dd HH:mm" /></p>
		<p class="meeting-info"><strong>현재 인원 :</strong> ${meeting.currentCount} / ${meeting.capacity}</p>
		<p class="meeting-info"><strong>컨텐츠명 :</strong> ${meeting.contentName}</p>
		<c:choose>
	    <%-- 로그인한 사용자가 모임 생성자일 때 --%>
	    <c:when test="${not empty loginUser and loginUser.userId == meeting.user.userId}">
		    <div class="d-flex flex-column gap-2 mt-3">
		
		        <%-- 신청현황 --%>
		        <button class="btn btn-warning"
		                onclick="location.href='/member/${meeting.meetingId}/applicants'">
		            신청 현황 보기
		        </button>
		        
		        <%-- 목록으로 --%>
		        <button class="btn btn-outline-secondary"
		                onclick="location.href='/meeting/list'">
		            모임 목록
		        </button>
		    </div>
		</c:when>
	    <%-- 로그인하지 않은 경우 --%>
	    <c:when test="${empty loginUser}">
	        <button class="btn btn-primary btn-apply"
	                onclick="alert('로그인을 해주세요!'); location.href='/login';">
	            모임 신청하기
	        </button>
	    </c:when>
	
	    <%-- 마감된 경우 --%>
	    <c:when test="${meeting.currentCount >= meeting.capacity}">
	        <div class="alert alert-warning" role="alert">
	            모집 인원이 마감되었습니다. 신청할 수 없습니다.
	        </div>
	        <button class="btn btn-secondary btn-apply" disabled>신청 불가</button>
	    </c:when>
	
	    <%-- 일반 유저(로그인 상태) --%>
	    <c:otherwise>
	        <button class="btn btn-primary btn-apply"
	                onclick="location.href='/member/${meeting.meetingId}/join'">
	            모임 신청하기
	        </button>
	    </c:otherwise>
	</c:choose>
	</div>
</div>
</body>
</html>
