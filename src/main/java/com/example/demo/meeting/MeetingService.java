package com.example.demo.meeting;

import org.springframework.stereotype.Service;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MeetingService {
	private final MeetingRepository meetingRepository;
	private final UserRepository userRepository;
	
	public void insert(Meeting meeting) {
		meetingRepository.save(meeting);
	}
	
	public void joinMeeting(int meetingId, int userId) {
		Meeting meeting = meetingRepository.findById(meetingId).orElseThrow();
		User user = userRepository.findById(userId).orElseThrow();
	}
	
}
