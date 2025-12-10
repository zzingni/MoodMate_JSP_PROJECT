package com.example.demo.meeting;

import org.springframework.stereotype.Service;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeetingService {
	private final MeetingRepository meetingRepository;
	
	public void insert(Meeting meeting) {
		meetingRepository.save(meeting);
	}
		
}
