<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모임 수정</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
<style>
	body {
	    background: linear-gradient(135deg, #f0f2f5, #d9e2ec);
	    min-height: 100vh;
	    display: flex;
	    justify-content: center;
	    align-items: center;
	    font-family: 'Noto Sans KR', sans-serif;
	}
	
	.meeting-card {
	    width: 100%;
	    max-width: 600px;
	    padding: 40px;
	    background-color: #fff;
	    border-radius: 20px;
	    box-shadow: 0 12px 30px rgba(0,0,0,0.12);
	    transition: transform 0.3s ease, box-shadow 0.3s ease;
	}
	
	.meeting-card:hover {
	    transform: translateY(-5px);
	    box-shadow: 0 20px 40px rgba(0,0,0,0.15);
	}
	
	.meeting-card h3 {
	    text-align: center;
	    margin-bottom: 30px;
	    color: #4b4b4b;
	    font-weight: 700;
	    font-size: 28px;
	}
	
	.form-control, .form-select {
	    border-radius: 12px;
	    padding: 12px 15px;
	    border: 1px solid #ced4da;
	}
	
	.btn-primary {
	    border-radius: 12px;
	    background-color: #0d6efd;
	    border: none;
	    padding: 12px 0;
	    font-weight: 600;
	}
	
	.btn-primary:hover {
	    background-color: #0b5ed7;
	}
</style>
</head>
<body>
<a class="btn btn-outline-secondary position-absolute top-0 start-0 m-3"
   onclick="history.back()">
    뒤로가기
</a>
<div class="meeting-card">

    <h3>모임 수정</h3>

    <form method="post" action="/meeting/edit/${meeting.meetingId}">
        
        <!-- 제목 -->
        <input class="form-control mb-3"
               name="title"
               value="${meeting.title}"
               required>

        <!-- 내용 -->
        <textarea class="form-control mb-3"
                  name="content"
                  rows="4"
                  required>${meeting.content}</textarea>

        <!-- 날짜/시간 -->
        <input type="datetime-local"
		       id="meetingDate"
		       name="meetingDate"
		       class="form-control mb-3" required="required">
		       
		<script>
		    const dateObj = new Date("${meeting.meetingDate.time}");
		
		    const year = dateObj.getFullYear();
		    const month = String(dateObj.getMonth() + 1).padStart(2, '0');
		    const day = String(dateObj.getDate()).padStart(2, '0');
		    const hour = String(dateObj.getHours()).padStart(2, '0');
		    const minute = String(dateObj.getMinutes()).padStart(2, '0');
		
		    document.getElementById("meetingDate").value =
		        `${year}-${month}-${day}T${hour}:${minute}`;
		</script>

        <!-- 장소 -->
        <input class="form-control mb-3"
               name="location"
               value="${meeting.location}"
               required>

        <!-- 인원수 -->
        <input type="number"
               class="form-control mb-3"
               name="capacity"
               value="${meeting.capacity}"
               required>

        <!-- 카테고리 -->
        <select class="form-select mb-3" name="category" required>
            <option ${meeting.category == '영화' ? 'selected' : ''}>영화</option>
            <option ${meeting.category == '연극' ? 'selected' : ''}>연극</option>
            <option ${meeting.category == '뮤지컬' ? 'selected' : ''}>뮤지컬</option>
            <option ${meeting.category == '독서' ? 'selected' : ''}>독서</option>
        </select>

        <!-- 컨텐츠명 -->
        <input class="form-control mb-4"
               name="contentName"
               value="${meeting.contentName}"
               required>

        <!-- 수정 버튼 -->
        <button class="btn btn-primary w-100">
            수정 완료
        </button>
    </form>

</div>
</body>
</html>