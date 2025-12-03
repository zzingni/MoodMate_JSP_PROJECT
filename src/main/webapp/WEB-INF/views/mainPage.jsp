<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
	<style>
        body { background:#f5f5f5; }

        /* 메인 영역 */
		.main {position: relative; padding: 100px 0; color: #fff; overflow: hidden;}
		.main-bg {position: absolute; top: 0; left: 0; width: 100%; height: 100%; object-fit: cover; z-index: -1; filter: brightness(80%);}
        .main-inner {display: flex; align-items: center; justify-content: space-between; gap: 30px;}
        .main h1 {font-size: 48px; font-weight: 700;}
        .main .badge {display: inline-block; padding: 8px 12px;}
        
        /* 카테고리 섹션 */
        .category-row { padding:30px 0; background:#fff; }
        .category-card { border-radius:16px; padding:18px; text-align:center; box-shadow:0 6px 18px #fff; }
        .category-card img { width:52px; height:52px; margin-bottom:8px; }

        /* 메인 레이아웃 (카테고리 밑 컨텐츠 영역) */
        .page-wrap { display:flex; gap:24px; margin-top:30px; }
        .sidebar { width:260px; } /* 필요 시 사이드바 추가용 */
        .meeting-grid { flex:1; }
        .meeting-card { border-radius:14px; overflow:hidden; }
        .meeting-card .card-body h5 { font-weight:600; }
        .badge-category { background:#f1f1f1; padding:6px 10px; border-radius:999px; font-size:12px; }

        @media (max-width:768px){
            .main-inner { flex-direction:column; gap:20px; }
        }
    </style>
</head>
<body>
    <!-- 네비게이션 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-white shadow-sm">
	    <div class="container">
	    	<!-- 로고/홈 링크 -->
	        <a class="navbar-brand fw-bold" href="/">MoodMate</a>
	        <div class="ms-auto">
	
	            <!-- 로그인 안 했을 때 로그인 버튼만 노출 -->
	            <c:if test="${empty sessionScope.loginUser}">
	                <a class="btn btn-outline-primary" href="/login">로그인</a>
	            </c:if>
	
	            <!-- 로그인 했을 때 마이페이지 / 모임 만들기 / 로그아웃 노출 -->
	            <c:if test="${not empty sessionScope.loginUser}">
	                <a class="btn btn-outline-secondary me-2" href="/mypage">마이페이지</a>
	                <a class="btn btn-outline-primary me-2" href="/meeting/create">+ 모임 만들기</a>
	                <a class="btn btn-danger" href="/logout">로그아웃</a>
	            </c:if>
	
	        </div>
	    </div>
	</nav>

    <!-- 메인 히어로 -->
    <section class="main">
    	<!-- 배경 이미지 -->
		<img src="/image/moodmate.png" class="main-bg" alt="background">
		    <div class="container main-inner">
		        <div style="flex:1">
		        
		        	<!-- 메인 문구 -->
		            <!-- <div class="badge bg-dark text-white rounded-pill mb-3">파티의 순간 기획전</div> -->
		            <h1>오늘의 에너지,<br>같이 나눌 친구들을 찾아보세요</h1>
		            <p class="mt-3">서로의 취향이 만나 인연이 되는 시간</p>
		        </div>
		    </div>
	</section>

    <!-- 카테고리 아이콘 영역 -->
    <section class="category-row">
	    <div class="container">
	        <%-- 카테고리 영문/한글 매핑 설정 (한 줄로 작성) --%>
	        <c:set var="categories" value="recommend:추천,movie:영화,theater:연극,musical:뮤지컬,reading:독서" />
	        
	        <div class="row g-3 justify-content-center">
	        	<%-- 쉼표 기준으로 쪼갠 뒤 하나씩 처리 --%>
	            <c:forEach var="catEntry" items="${fn:split(categories, ',')}">
	                <%-- 앞뒤 공백 제거 --%>
	                <c:set var="catEntryTrimmed" value="${fn:trim(catEntry)}" />
	                
	                <%-- : 뒷부분 = 한글 value (화면에 찍히는 이름) --%>
	                <c:set var="key" value="${fn:substringBefore(catEntryTrimmed, ':')}" />    <%-- 영문 (파일 이름) --%>
	                <c:set var="value" value="${fn:substringAfter(catEntryTrimmed, ':')}" />   <%-- 한글 (화면 표시) --%>
	                
	                <div class="col-auto">
	                    <div class="category-card">
	                        <%-- 아이콘: 영문 파일 이름(key) 사용 --%>
	                        <img src="/image/${key}.png" alt="${value} 아이콘" style="width:52px; height:52px; margin-bottom:8px;">
	                        
	                        <%-- 하단에 한글 이름(value) 표시 --%>
	                        <div class="mt-2 small">${value}</div>
	                    </div>
	                </div>
	            </c:forEach>
	        </div>
	    </div>
	</section>

    <!-- 메인 콘텐츠, 모임 리스트 -->
    <div class="container page-wrap">
        <main class="meeting-grid">
            <h5>활동이 활발한 모임</h5>
            <div class="row mt-3 g-4">
            
            	<%-- 최신 모임 리스트 반복 표시 (latestMeetings는 컨트롤러에서) --%>
                <c:forEach items="${latestMeetings}" var="m">
                    <div class="col-6 col-lg-4">
                        <div class="card meeting-card">
                        
                        	 <!-- 모임 썸네일 (이미지 없을 때 기본 이미지) -->
                            <img src="/resources/img/meeting_default.jpg" class="card-img-top" alt="meeting">
                            <div class="card-body">
                            
                            	<!-- 카테고리 / 날짜 -->
                                <div class="d-flex justify-content-between align-items-center mb-2">
                                    <div class="badge-category">${m.category}</div>
                                    <small class="text-muted">${m.meetDate}</small>
                                </div>
                                
                                <!-- 모임 제목 -->
                                <h5><a href="/meeting/${m.id}" class="text-decoration-none text-dark">${m.title}</a></h5>
                                
                                <!-- 모임 내용 요약 (두 줄 정도로 잘리도록) -->
                                <p class="small text-truncate" style="max-height:40px">${m.content}</p>
                                
                                <!-- 장소 / 상세보기 버튼 -->
                                <div class="d-flex justify-content-between align-items-center mt-2">
                                    <small class="text-muted">장소: ${m.place}</small>
                                    <a href="/meeting/${m.id}" class="btn btn-sm btn-outline-primary">상세</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </main>
    </div>
</body>
</html>	