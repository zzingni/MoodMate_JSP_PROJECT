package com.example.demo.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	// 로그인
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	// 회원가입 / GET join
	@GetMapping("/join")
	public String joinForm() {
		return "joinForm";
	}
	
	// 회원가입 후 처리 / POST join
	@PostMapping("/join")
	public void join(User user, HttpServletResponse response) throws IOException {
		userService.insert(user); // db에 저장
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>alert('회원가입이 완료되었습니다.'); location.href='/login';</script>");
		out.flush();
	}

}
