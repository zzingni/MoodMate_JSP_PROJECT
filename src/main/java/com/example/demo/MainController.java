package com.example.demo;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.meeting.Meeting;
import com.example.demo.meeting.MeetingRepository;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MainController {
	
	private final MeetingRepository meetingRepository;
	
	@RequestMapping("/")
	public String main(Model model) {
		List<Meeting> meetings = meetingRepository.findAll();
		model.addAttribute("meetings", meetings);
		
		return "mainPage";
	}
}
