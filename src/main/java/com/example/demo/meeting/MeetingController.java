package com.example.demo.meeting;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/meeting")
public class MeetingController {
	@GetMapping("/create")
	public String meetingCreate() {
		return "createMeeting";
	}
}
