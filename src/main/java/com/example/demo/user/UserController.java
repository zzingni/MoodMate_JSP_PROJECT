package com.example.demo.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {
	
	private final UserService userService;
	
	// 로그인 페이지
	@GetMapping("/login")
	public String loginForm() {
		return "loginForm";
	}
	
	// 회원가입 페이지 / GET join
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
	
	// 로그인 후 처리
	@PostMapping("/auth/login")
	public String login(
			@RequestParam("loginId") String loginId,
			@RequestParam("password") String password, HttpSession session) {
		
		User user = userService.login(loginId, password);
		
		if (user == null) {
			// 로그인 실패 시 
			return "redirect:/login?error=1";
		}
		
		// 로그인 성공 -> 세선 저장
		session.setAttribute("loginUser" , user);
		
		// 메인페이지로 이동
		return "redirect:/";
	}
	
	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 전체 삭제
		return "redirect:/";
	} 
}
