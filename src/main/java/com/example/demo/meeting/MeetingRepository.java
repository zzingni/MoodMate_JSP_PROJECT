package com.example.demo.meeting;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.user.User;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
	Optional<Meeting> findByMeetingId(int meetingId);
	List<Meeting> findByUser(User user);
	List<Meeting> findByCategory(String Category);
}
