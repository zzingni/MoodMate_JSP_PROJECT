<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>모임 목록</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<style>
	body { background-color: #f8f9fa; }

	/* 카테고리 메뉴 */
	.category-menu {
	    background: linear-gradient(135deg, #ffffff 0%, #f3f6ff 100%);
	    border-radius: 18px;
	    padding: 20px 18px;
	    box-shadow: 0 4px 12px rgba(0,0,0,0.12);
	    position: sticky;
	    top: 20px;
	}
	
	.category-menu h6 {
	    font-size: 1rem;
	    font-weight: 700;
	    margin-bottom: 18px;
	    color: #2c3e50;
	    border-left: 4px solid #0d6efd;
	    padding-left: 8px;
	}
	
	/* 버튼 스타일 */
	.category-menu a {
		text-decoration: none;
	    display: block;
	    margin-bottom: 10px;
	    padding: 10px 12px;
	    background: #fff;
	    border-radius: 10px;
	    border: 1px solid #e0e5ff;
	    color: #3a3f52;
	    font-size: 0.95rem;
	    font-weight: 600;
	    transition: 0.25s;
	}
	
	.category-menu a:hover {
	    background: #0d6efd;
	    color: #fff;
	    transform: translateX(6px);
	    box-shadow: 0 4px 10px rgba(13,110,253,0.3);
	}
	
	/* 선택된 카테고리(선택시 class="active" 줄 경우) */
	.category-menu a.active {
	    background: #0d6efd;
	    color: #fff;
	    box-shadow: 0 4px 10px rgba(13,110,253,0.3);
	    transform: translateX(6px);
	}

	/* 카드 스타일 */
	.card {
	    border-radius: 16px;
	    overflow: hidden;
	    box-shadow: 0 4px 10px rgba(0,0,0,0.1);
	    transition: transform 0.2s, box-shadow 0.2s;
	}
	.card:hover {
	    transform: translateY(-4px);
	    box-shadow: 0 8px 20px rgba(0,0,0,0.15);
	}
	.card img {
	    height: 160px;
	    object-fit: cover;
	}
	.card h5 {
	    font-weight: 600;
	}
	.card p {
	    font-size: 0.9rem;
	}
</style>
</head>
<body>
	<!-- 상단 공통 header include -->
	<%@ include file="/WEB-INF/views/header.jsp" %>
	
    <div class="container mt-4">
        <h3 class="mb-4">모임 목록</h3>

        <div class="row">
            <!-- 좌측 카테고리 메뉴 -->
            <div class="col-md-3 mb-4">
                <div class="category-menu">
                    <h6>카테고리</h6>
                    <c:set var="categories" value="전체,추천,영화,연극,뮤지컬,독서"/>
					<c:forEach var="cat" items="${fn:split(categories, ',')}">
					    <a href="/meeting/list?category=${cat}"
					       class="${cat == selectedCategory ? 'active' : ''}">
					        ${cat}
					    </a>
					</c:forEach>
                </div>
            </div>

             <!-- 모임 카드 -->
            <div class="col-md-9">
                <div class="row g-4">
                    <c:forEach var="m" items="${meetings}">
					    <div class="col-12 col-md-6 col-lg-4">
					        <div class="card">
					            <img src="${m.imageUrl}" class="w-100">
					            <div class="p-3">
					
					                <div class="small text-muted mb-1">
					                    ${m.category} • <fmt:formatDate value="${m.meetingDate}" pattern="yyyy/MM/dd(E) HH:mm" />
					                </div>
					
					                <h5>${m.title}</h5>
					                <p class="text-truncate" style="max-height:48px">${m.content}</p>
					
					                <div class="d-flex justify-content-between align-items-center mt-2">
					                    <small>장소: ${m.location}</small>
					                    <a href="/meeting/detail/${m.meetingId}" class="btn btn-sm btn-primary">상세</a>
					                </div>
					            </div>
					        </div>
					    </div>
					</c:forEach>
                </div>
            </div>

        </div>
    </div>
</body>
</html>