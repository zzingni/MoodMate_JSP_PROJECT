package com.example.demo.meeting;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		switch (meeting.getCategory()) {
	        case "영화":
	            meeting.setImageUrl("/image/meetingMovie.jpg");
	            break;
	        case "연극":
	            meeting.setImageUrl("/image/meetingTheater.jpg");
	            break;
	        case "뮤지컬":
	            meeting.setImageUrl("/image/meetingMusical.jpg");
	            break;
	        case "독서":
	            meeting.setImageUrl("/image/meetingBook.jpg");
	            break;
	        default:
	            meeting.setImageUrl("/image/default.jpg"); // 기본 이미지
	    }

		Meeting saved = meetingRepository.save(meeting); // db에 저장. > repository의 save는 db에 데이터를 저장하고 키값을 반환함 > 그걸 바로 사용
		return "redirect:/meeting/detail/" + saved.getMeetingId();
	}
	
	// 모임 선택시 디테일 페이지로 이동
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") int id, Model model, HttpSession session) {
		Meeting meeting = meetingRepository.findByMeetingId(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 모임입니다. : " + id));
		
		boolean alreadyApplied = false;
		
		User loginUser = (User) session.getAttribute("loginUser");
		
		if (loginUser != null) {
		        alreadyApplied = meeting.getMembers().stream()
		                .anyMatch(m -> m.getUser().getUserId() == loginUser.getUserId());
		}
		 
		model.addAttribute("meeting", meeting);
		model.addAttribute("loginUser", loginUser); // detail 페이지에서 현재 로그인 사용자와 다른 사용자 구분 해야 함. 
		model.addAttribute("alreadyApplied", alreadyApplied); // 이미 신청했는지 확인 필요
		return "meetingDetail";
	}
	
	// 모임 리스트 페이지로 이동(db에 있는 meeting List와 같이 넘김)
	@GetMapping("/list")
	public String meetingList(@RequestParam(name = "category", required = false) String category, Model model, HttpSession session) {
		List<Meeting> meetings;
		
		// 현재 로그인한 사용자 정보
	    User loginUser = (User) session.getAttribute("loginUser");

	    if (category == null || category.equals("전체")) {
	        meetings = meetingRepository.findAll();
	    }
	    else if (category.equals("추천")) {
	        if (loginUser != null && loginUser.getFavorite() != null) {

	            // String → List 변환
	            List<String> favoriteList =
	                    Arrays.asList(loginUser.getFavorite().split(","));

	            // 여러 카테고리로 검색
	            meetings = meetingRepository.findByCategories(favoriteList);

	        } else {
	            meetings = meetingRepository.findAll();
	        }
	    }
	    else {
	        // 일반 카테고리 필터 (영화/연극/뮤지컬/독서)
	        meetings = meetingRepository.findByCategory(category);
	    }
		
	    model.addAttribute("meetings", meetings);
	    model.addAttribute("selectedCategory", category);
	    model.addAttribute("favorite", loginUser != null ? loginUser.getFavorite() : null);

	    return "meetingList";
	}
	
	// 모임 수정 페이지로 이동
	@GetMapping("/edit/{meetingId}")
	public String editMeeting(@PathVariable("meetingId") int meetingId, Model model) {
		Meeting meeting = meetingRepository.findById(meetingId)
	            .orElseThrow(() -> new IllegalArgumentException("모임을 찾을 수 없습니다."));

	    model.addAttribute("meeting", meeting);
	    model.addAttribute("meetingDateObj", meeting.getMeetingDate());
	    
	    return "meetingEdit";
	}
	
	// 모임 수정 
	@PostMapping("/edit/{meetingId}")
	public String updateMeeting(@PathVariable("meetingId") int meetingId, Meeting updatedMeeting) {
		
		Meeting meeting = meetingRepository.findById(meetingId)
	            .orElseThrow(() -> new IllegalArgumentException("모임을 찾을 수 없습니다."));

	    // 기존 meeting에 새로운 값 덮어쓰기
	    meeting.setTitle(updatedMeeting.getTitle());
	    meeting.setContent(updatedMeeting.getContent());
	    meeting.setLocation(updatedMeeting.getLocation());
	    meeting.setMeetingDate(updatedMeeting.getMeetingDate());
	    meeting.setCapacity(updatedMeeting.getCapacity());
	    meeting.setContentName(updatedMeeting.getContentName());
	    meeting.setCategory(updatedMeeting.getCategory());

	    // 카테고리 변경 시 이미지 자동 설정
	    switch (updatedMeeting.getCategory()) {
	        case "영화":
	            meeting.setImageUrl("/image/meetingMovie.jpg");
	            break;
	        case "연극":
	            meeting.setImageUrl("/image/meetingTheater.jpg");
	            break;
	        case "뮤지컬":
	            meeting.setImageUrl("/image/meetingMusical.jpg");
	            break;
	        case "독서":
	            meeting.setImageUrl("/image/meetingBook.jpg");
	            break;
	        default:
	            meeting.setImageUrl("/image/default.jpg");
	    }

	    meetingRepository.save(meeting);

	    return "redirect:/meeting/detail/" + meetingId;
	}
	
	// 모임 삭제
	@GetMapping("/delete/{meetingId}")
	public String deleteMeeting(@PathVariable("meetingId") int meetingId) {
		meetingRepository.deleteById(meetingId);

	    return "redirect:/meeting/list";
	}
}
