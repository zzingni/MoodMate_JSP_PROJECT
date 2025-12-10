package com.example.demo.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.meeting.MeetingRepository;
import com.example.demo.meeting.MeetingService;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/meeting")
@Controller
@RequiredArgsConstructor
public class MemberController {
	
    private final MemberService memberService;
	
	// 모임에 참가신청 
	@GetMapping("/{meetingId}/join")
	public String joinMeeting(@PathVariable("meetingId") int meetingId,
								@SessionAttribute("loginUser") User loginUser) {
		
		memberService.joinMeeting(meetingId, loginUser.getUserId());
		 
		return "redirect:/meeting/detail/" + meetingId;
	}
}
