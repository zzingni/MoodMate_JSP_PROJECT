package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.meeting.Meeting;
import com.example.demo.meeting.MeetingRepository;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.user.User;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MeetingRepository meetingRepository;
	private final MemberRepository memberRepository;
	
	@RequestMapping("/")
	public String main(Model model) {
		List<Meeting> meetings = meetingRepository.findAll();
		model.addAttribute("meetings", meetings);
		
		return "mainPage";
	}
	
	@GetMapping("/mypage")
	public String mypage(Model model, HttpSession session) {
		
		// 로그인 사용자 정보
	    User loginUser = (User) session.getAttribute("loginUser");

	    if (loginUser == null) {
	        return "redirect:/login";   // 로그인 안 했으면 로그인 페이지로
	    }
	    
	    // 내가 생성한 모임 리스트 조회
	    List<Meeting> myMeetings = meetingRepository.findByUser(loginUser);

	    model.addAttribute("myMeetings", myMeetings);
	    model.addAttribute("loginUser", loginUser);
	    
	    // 내가 참가한 모임 리스트 조회
	    List<Member> joinedMeetings = memberRepository.findByUser(loginUser);
	    model.addAttribute("joinedMeetings", joinedMeetings);
		return "mypage";
	}
}
