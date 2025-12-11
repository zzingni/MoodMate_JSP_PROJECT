package com.example.demo.meeting;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.user.User;

public interface MeetingRepository extends JpaRepository<Meeting, Integer> {
	Optional<Meeting> findByMeetingId(int meetingId);
	List<Meeting> findByUser(User user);
	List<Meeting> findByCategory(String Category);
	
	@Query("SELECT m FROM Meeting m WHERE m.category IN :categories")
	List<Meeting> findByCategories(@Param("categories") List<String> categories); // 여러 카테고리용
}
