package com.example.demo.meeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.user.User;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {
	
	private final MeetingRepository meetingRepository;
	
	// meeting 생성 페이지로
	@GetMapping("/create")
	public String meetingCreate() {
		return "createMeeting";
	}
	
	// meeting 저장 후 /meeting/detail로 이동
	@PostMapping("/create")
	public String createMeeting(Meeting meeting, HttpSession session) {
		
		User loginUser = (User) session.getAttribute("loginUser"); // 현재 세션에 저장되어 있는 유저 가져오기
		
		meeting.setUser(loginUser); // meeting에 user 세팅
		Meeting saved = meetingRepository.save(meeting); // db에 저장. > repository의 save는 db에 데이터를 저장하고 키값을 반환함 > 그걸 바로 사용
		return "redirect:/meeting/detail/" + saved.getMeetingId();
	}
	
	// 모임 선택시 디테일 페이지로 이동
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int id, Model model, HttpSession session) {
		Meeting meeting = meetingRepository.findByMeetingId(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 모임입니다. : " + id));
		
		User loginUser = (User) session.getAttribute("loginUser");
		 
		model.addAttribute("meeting", meeting);
		model.addAttribute("loginUser", loginUser); // detail 페이지에서 현재 로그인 사용자와 다른 사용자 구분 해야 함. 
		return "meetingDetail";
	}
}
