package com.example.demo.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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

    private final MeetingService meetingService;
	private final UserRepository userRepository;
	private final MeetingRepository meetingRepository;
	
	// 모임에 참가신청 
	@GetMapping("/{id}/join")
	public String joinMeeting(@PathVariable("meetingId") int meetingId,
							HttpSession session) {
		 User user = (User) session.getAttribute("loginUser");
		 
		 if (user == null) {
		        return "redirect:/login";
		    }
		 
		 meetingService.joinMeeting(meetingId, user.getUserId());
		 
		 return "redirect:/meeting/" + meetingId;
	}
}
