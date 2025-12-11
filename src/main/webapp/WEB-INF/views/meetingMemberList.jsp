<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신청자 목록</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB"
	crossorigin="anonymous">
<style>
/* 외부 박스 (모달 스타일) */
.wrapper-box {
	max-width: 800px;
	margin: 40px auto;
	background: #fff;
	border-radius: 12px;
	box-shadow: 0 6px 20px rgba(0, 0, 0, 0.15);
	overflow: hidden;
}

/* 상단 타이틀 바 */
.header-bar {
	background: #0d6efd;
	padding: 15px 20px;
	color: #fff;
	font-weight: 700;
	font-size: 18px;
}

/* 승인/거절 뱃지 */
.badge-approved {
	background: #198754;
	color: #fff;
	padding: 6px 10px;
	border-radius: 8px;
	font-size: 13px;
	font-weight: 600;
}

.badge-rejected {
	background: #dc3545;
	color: #fff;
	padding: 6px 10px;
	border-radius: 8px;
	font-size: 13px;
	font-weight: 600;
}

.table>:not(caption)>*>* {
	padding: 12px 16px;
}

.close-area {
	padding: 15px;
	border-top: 1px solid #e5e5e5;
	text-align: right;
}
</style>

</head>
<body>

	<!-- 공통 헤더 include -->
	<%@ include file="/WEB-INF/views/header.jsp"%>

	<div class="wrapper-box">
		<!-- 상단 제목 -->
		<div class="header-bar">${meeting.title}-지원자 목록</div>

		<!-- 테이블 영역 -->
		<div class="p-3">
			<table class="table align-middle">
				<thead>
					<tr>
						<th>#</th>
						<th>지원자 이름</th>
						<th>닉네임</th>
						<th>성별</th>
						<th>나이</th>
						<th>신청일</th>
						<th>상태</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="m" items="${members}" varStatus="i">
						<tr>
							<td>${i.index + 1}</td>
							<td>${m.user.name}</td>
							<td>${m.user.nickname}</td>
							<td>${m.user.gender}</td>
							<td>${m.user.age}</td>
							<td>${fn:substring(m.applyDate, 0, 10)}</td>
							<td>
								<!-- 모임 생성한 사람일 때만 승인/거절 버튼 표시 --> <c:if
									test="${sessionScope.loginUser.userId == meeting.user.userId}">

									<c:choose>
										<%-- 대기 상태일 때 버튼 --%>
										<c:when test="${m.confirmStatus == '대기'}">
											<a href="/member/${meeting.meetingId}/approve/${m.memberId}"
												class="btn btn-sm btn-success me-1">승인</a>

											<a href="/member/${meeting.meetingId}/reject/${m.memberId}"
												class="btn btn-sm btn-danger">거절</a>
										</c:when>

										<%-- 승인됨 --%>
										<c:when test="${m.confirmStatus == '승인'}">
											<span class="badge bg-success">승인됨</span>
										</c:when>

										<%-- 거절됨 --%>
										<c:when test="${m.confirmStatus == '거절'}">
											<span class="badge bg-danger">거절됨</span>
										</c:when>
									</c:choose>

								</c:if> <!-- 일반 유저(생성한 사람이 아닌 경우)는 상태만 보임 --> <c:if
									test="${sessionScope.loginUser.userId != meeting.user.userId}">
									<span
										class="badge 
					            ${m.confirmStatus == '승인' ? 'bg-success' : 
					               (m.confirmStatus == '거절' ? 'bg-danger' : 'bg-secondary')}">
										${m.confirmStatus} </span>
								</c:if>

							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<!-- 닫기 버튼 -->
		<div class="close-area">
			<button class="btn btn-secondary" onclick="history.back()">닫기</button>
		</div>
	</div>

</body>
</html>