<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
	<style>
        .login-box {
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
            height: 38px; /* 텍스트박스랑 높이 맞추기 */
        }
    </style>
</head>

<body>
    <div class="login-box">
        <h2 class="text-center mb-4">로그인</h2>

        <form method="post" action="/auth/login">
            
            <label class="form-label">아이디</label>
            <input class="form-control mb-3" name="loginId" required>

            <label class="form-label">비밀번호</label>
            <input type="password" class="form-control mb-4" name="password" required>

            <button class="btn btn-primary btn-fullwidth">로그인</button>
        </form>
    </div>
</body>
</html>