<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
	<style>
        .signup-box {
            width: 350px;
            margin: 80px auto 0 auto;
        }
        body {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            height: 100vh;
            background-color: #f9f9f9;
        }
        .btn-fullwidth {
            width: 100%;
            height: 38px; /* 텍스트박스랑 높이 동일하게 */
        }
        .btn-group-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr); 
            gap: 8px; /* 버튼 사이 간격 */
        }
        .btn-group-grid-multi {
            display: grid;
            grid-template-columns: 1fr 1fr; /* 2열 */
            gap: 8px;
            margin-bottom: 1rem;
        }
    </style>
</head>

<body>
<a class="btn btn-outline-secondary position-absolute top-0 start-0 m-3"
   onclick="history.back()">
    <i class="bi bi-arrow-left"></i> 뒤로가기
</a>
<div class="signup-box">
    <h2 class="text-center mb-4">회원가입</h2>

    <form method="post" action="/join">

        <label class="form-label">아이디</label>
        <input class="form-control mb-3" name="loginId" required>

        <label class="form-label">비밀번호</label>
        <input type="password" class="form-control mb-3" name="password" required>

        <label class="form-label">이름</label>
        <input class="form-control mb-3" name="name" required>

        <label class="form-label">닉네임</label>
        <input class="form-control mb-3" name="nickname" required>
        
        <label class="form-label">성별</label>
        <div class="btn-group-grid mb-3">
            <input type="radio" class="btn-check" name="gender" id="male" value="남" autocomplete="off" checked>
            <label class="btn btn-outline-primary btn-fullwidth" for="male">남</label>

            <input type="radio" class="btn-check" name="gender" id="female" value="여" autocomplete="off">
            <label class="btn btn-outline-primary btn-fullwidth" for="female">여</label>
        </div>

        <label class="form-label">나이</label>
        <input class="form-control mb-3" name="age">

        <label class="form-label">관심 분야</label>
        <div class="btn-group-grid-multi">
            <input type="checkbox" class="btn-check" name="favorite" id="movie" value="영화" autocomplete="off">
            <label class="btn btn-outline-success btn-fullwidth" for="movie">영화</label>

            <input type="checkbox" class="btn-check" name="favorite" id="play" value="연극" autocomplete="off">
            <label class="btn btn-outline-success btn-fullwidth" for="play">연극</label>

            <input type="checkbox" class="btn-check" name="favorite" id="musical" value="뮤지컬" autocomplete="off">
            <label class="btn btn-outline-success btn-fullwidth" for="musical">뮤지컬</label>

            <input type="checkbox" class="btn-check" name="favorite" id="reading" value="독서" autocomplete="off">
            <label class="btn btn-outline-success btn-fullwidth" for="reading">독서</label>
        </div>

        <button class="btn btn-primary w-100" style="height:45px">가입하기</button>
    </form>
</div>
</body>
</html>