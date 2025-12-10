<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>모임 생성</title>
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
	    transition: border-color 0.3s ease, box-shadow 0.3s ease;
	}
	
	.form-control:focus, .form-select:focus {
	    border-color: #6f42c1;
	    box-shadow: 0 0 0 0.2rem rgba(111,66,193,0.25);
	}
	
	.btn-primary {
	    border-radius: 12px;
	    background-color: #0d6efd; /* Bootstrap 기본 btn-primary */
	    border: none;
	    padding: 12px 0;
	    font-weight: 600;
	    transition: background-color 0.3s ease, transform 0.2s ease;
	}
	
	.btn-primary:hover {
	    background-color: #0b5ed7;
	    transform: translateY(-2px);
	}
	
	@media (max-width: 576px) {
	    .meeting-card {
	        padding: 30px 20px;
	    }
	
	    .meeting-card h3 {
	        font-size: 24px;
	    }
	}
</style>
</head>
<body>
    <div class="meeting-card">
        <h3>새 모임 생성</h3>
        <form method="post" action="/meeting/create">
            <input class="form-control mb-3" name="title" placeholder="제목" required="required">
            <textarea class="form-control mb-3" name="content" placeholder="내용" rows="4" required="required"></textarea>
            <input type="datetime-local" class="form-control mb-3" name="meetDate" id="meetDate" required="required">
            <input class="form-control mb-3" name="place" placeholder="장소" required="required">
            <input type="number" class="form-control mb-3" name="maxPeople" placeholder="인원수" required="required">
            <select class="form-select mb-3" name="category" required="required">
                <option>영화</option>
                <option>연극</option>
                <option>뮤지컬</option>
                <option>독서</option>
            </select>
            <input class="form-control mb-4" name="contentName" placeholder="컨텐츠명 (예 : 인터스텔라)" required="required">
            <button class="btn btn-primary w-100">등록하기</button>
        </form>
    </div>

    <script>
        // 현재 날짜와 시간을 datetime-local 기본값으로 설정
        const dateInput = document.getElementById('meetDate');
        const now = new Date();
        const year = now.getFullYear();
        const month = String(now.getMonth() + 1).padStart(2, '0');
        const day = String(now.getDate()).padStart(2, '0');
        const hours = String(now.getHours()).padStart(2, '0');
        const minutes = String(now.getMinutes()).padStart(2, '0');
        dateInput.value = `${year}-${month}-${day}T${hours}:${minutes}`;
    </script>
</body>
</html>