package com.example.demo.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.meeting.Meeting;
import com.example.demo.user.User;

public interface MemberRepository extends JpaRepository<Member, Integer> {
	boolean existsByMeetingAndUser(Meeting meeting, User user); // 이미 신청한 모임인지 확인하기 위해\
	List<Member> findByUser(User user);
	List<Member> findByMeeting_MeetingId(int meetingId);
}
