package com.example.demo.member;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.meeting.Meeting;
import com.example.demo.meeting.MeetingRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	private final MeetingRepository meetingRepository;
	private final UserRepository userRepository;
	private final MemberRepository memberRepository;
	
	public void joinMeeting(int meetingId, int userId) {
		
		Meeting meeting = meetingRepository.findById(meetingId)
	            .orElseThrow(() -> new IllegalArgumentException("모임 없음"));

	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new IllegalArgumentException("유저 없음"));

	    // 이미 신청했는지 검사
	    if (memberRepository.existsByMeetingAndUser(meeting, user)) {
	        throw new IllegalStateException("이미 신청한 모임입니다.");
	    }

	    // 정원 초과 체크
	    if (meeting.getCurrentCount() >= meeting.getCapacity()) {
	        throw new IllegalStateException("모집 정원이 마감되었습니다.");
	    }

	    // Member 엔티티 저장 (참가 기록)
	    Member member = new Member();
	    member.setMeeting(meeting);
	    member.setUser(user);
	    member.setApplyDate(LocalDateTime.now());

	    memberRepository.save(member);

	    // 모임 현재 인원 증가
	    meeting.setCurrentCount(meeting.getCurrentCount() + 1);
	    meetingRepository.save(meeting);
	}
}
