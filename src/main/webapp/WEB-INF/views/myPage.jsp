<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<style>
	body {
	    background: #f5f5f5;
	}
	
	.hero {
	    background: #3d8dd9;
	    padding: 60px 0;
	    color: #fff;
	}
	
	.hero h1 {
	    font-size: 28px;
	    font-weight: 700;
	}
	
	.hero p {
	    margin-top: 10px;
	}
	
	.page-wrap {
	    display: flex;
	    gap: 24px;
	    margin-top: 30px;
	}
	
	/* 왼쪽 사이드바 너비 고정 */
	.sidebar {
	    width: 260px;
	}
	
	/* 오른쪽 메인 콘텐츠 영역 - 남은 공간 차지 */
	.content {
	    flex: 1;
	}
	
	/* 섹션 간 하단 여백 */
	.card-section {
	    margin-bottom: 40px;
	}
	
	.card-section h5 {
	    font-weight: 600;
	    margin-bottom: 20px;
	}
	
	/* 모임 카드 공통 스타일 */
	.meeting-card {
	    border-radius: 14px;
	    overflow: hidden;
	    transition: transform .2s;
	}
	
	/* 카드 hover 시 약간 떠오르는 효과 */
	.meeting-card:hover {
	    transform: translateY(-3px);
	}
	
	
	/* 모임 카테고리 뱃지 스타일 */
	.badge-category {
	    background: #f1f1f1;
	    padding: 6px 10px;
	    border-radius: 999px;
	    font-size: 12px;
	}
	
	/* 신청 상태 - 승인일 때 색상 */
	.status-approved {
	    color: #198754;
	    font-weight: 600;
	}
	
	/* 신청 상태 - 거절일 때 색상 */
	.status-rejected {
	    color: #dc3545;
	    font-weight: 600;
	}
</style>
</head>
<body>
<!-- header 파일 inlcude -->
<%@ include file="/WEB-INF/views/header.jsp" %>

<section class="hero text-center">
    <div class="container">
        <h1>마이페이지</h1>
        <p>내가 만든 모임과 신청 현황을 확인할 수 있습니다.</p>
    </div>
</section>

<div class="container page-wrap">
    <!-- 사이드바 -->
    <aside class="sidebar">
        <div class="list-group shadow-sm rounded-3">
            <a href="#myCreatedMeetings" class="list-group-item list-group-item-action active">내가 만든 모임</a>
            <a href="#myAppliedMeetings" class="list-group-item list-group-item-action">신청한 모임</a>
        </div>
    </aside>

    <div class="container page-wrap">
    <!-- 메인 콘텐츠 -->
    <main class="content w-100">
        <!-- 내가 만든 모임 -->
        <section id="myCreatedMeetings" class="card-section">
            <div class="row g-4">
                <c:forEach items="${createdMeetings}" var="m">
                    <div class="col-12 col-md-6 col-lg-4">
                        <div class="card meeting-card shadow-sm">
                            <img src="/image/movie.jpg" class="card-img-top" alt="meeting">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-center mb-2">
                                    <div class="badge-category">${m.category}</div>
                                    <small class="text-muted">${m.meetDate}</small>
                                </div>
                                <h5><a href="/meeting/${m.id}" class="text-decoration-none text-dark">${m.title}</a></h5>
                                <p class="small text-truncate" style="max-height:40px">${m.content}</p>
                                <div class="d-flex justify-content-between align-items-center mt-2">
                                    <small class="text-muted">장소: ${m.place}</small>
                                    <a href="/meeting/${m.id}" class="btn btn-sm btn-outline-primary">상세</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>

        <!-- 내가 신청한 모임 -->
        <section id="myAppliedMeetings" class="card-section">
            <div class="row g-4">
            
            	<%-- createdMeetings 리스트를 순회하며 카드 하나씩 출력 --%>
                <c:forEach items="${appliedMeetings}" var="m">
                    <div class="col-12 col-md-6 col-lg-4">
                        <div class="card meeting-card shadow-sm">
                            <img src="/image/movie.jpg" class="card-img-top" alt="meeting">
                            <div class="card-body">
                            
                            	<!-- 카테고리 / 모임 날짜 -->
                                <div class="d-flex justify-content-between align-items-center mb-2">
                                    <div class="badge-category">${m.category}</div>
                                    <small class="text-muted">${m.meetDate}</small>
                                </div>
                                
                                <!-- 모임 제목 (상세 페이지 링크) -->
                                <h5><a href="/meeting/${m.id}" class="text-decoration-none text-dark">${m.title}</a></h5>
                                
                                <!-- 모임 내용 요약 -->
                                <p class="small text-truncate" style="max-height:40px">${m.content}</p>
                                
                                <!-- 장소 정보 + 상세 버튼 -->
                                <div class="d-flex justify-content-between align-items-center mt-2">
                                    <span class="${m.status == 'approved' ? 'status-approved' : 'status-rejected'}">
                                        ${m.status == 'approved' ? '승인' : '거절'}
                                    </span>
                                    <a href="/meeting/${m.id}" class="btn btn-sm btn-outline-primary">상세</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
    </main>
</div>
</body>
</html>