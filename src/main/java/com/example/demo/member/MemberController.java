package com.example.demo.member;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.example.demo.meeting.Meeting;
import com.example.demo.meeting.MeetingRepository;
import com.example.demo.meeting.MeetingService;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequestMapping("/member")
@Controller
@RequiredArgsConstructor
public class MemberController {
	
    private final MemberService memberService;
    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;
	
	// 모임에 참가신청 
	@GetMapping("/{meetingId}/join")
	public String joinMeeting(@PathVariable("meetingId") int meetingId,
								@SessionAttribute("loginUser") User loginUser) {
		
		memberService.joinMeeting(meetingId, loginUser.getUserId());
		 
		return "redirect:/meeting/detail/" + meetingId;
	}
	
	// 신청 현황 
	@GetMapping("/{meetingId}/applicants")
	public String members(@PathVariable("meetingId") int meetingId, Model model) {

		// 1) 모임 정보 (meetingRepository)
	    Meeting meeting = meetingRepository.findById(meetingId)
	            .orElseThrow(() -> new RuntimeException("meeting not found"));
	    
		// 신청자 목록
		List<Member> members = memberRepository.findByMeeting_MeetingId(meetingId);
		
		model.addAttribute("meeting", meeting);
		model.addAttribute("members", members);
		
		return "meetingMemberList";
	}
	
	// 멤버 모임 참가 승인
	@GetMapping("/{meetingId}/approve/{memberId}")
	public String approveMember(@PathVariable("meetingId") int meetingId, @PathVariable("memberId") int memberId) {
	    memberService.updateStatus(memberId, "승인");
	    return "redirect:/member/" + meetingId + "/applicants";
	}

	
	// 멤버 모임 참가 거절
	@GetMapping("/{meetingId}/reject/{memberId}")
	public String rejectMember(@PathVariable("meetingId") int meetingId, @PathVariable("memberId") int memberId) {
	    memberService.updateStatus(memberId, "거절");
	    return "redirect:/member/" + meetingId + "/applicants";
	}
}
