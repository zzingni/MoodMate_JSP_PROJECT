package com.example.demo.config;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.example.demo.meeting.Meeting;
import com.example.demo.meeting.MeetingRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MeetingInitConfig {
	
	private final UserRepository userRepository;
	private final MeetingRepository meetingRepository;

	@Bean
	@Order(2)
	public CommandLineRunner initMeeting() {
		return args -> {
	        // 사용자 확보
			User user1 = userRepository.findByLoginId("test");
			User user2 = userRepository.findByLoginId("test2");
			
			if (user1 == null || user2 == null) {
                System.out.println("⚠ UserInitConfig보다 먼저 실행되면 user가 null입니다.");
                return;
            }
	
	        // === 모임 1: 마감된 모임 (10 / 10) ===
	        Meeting m1 = new Meeting();
	        m1.setUser(user1);
	        m1.setTitle("마감된 독서 모임");
	        m1.setContent("현재 인원이 가득 찬 상태입니다.");
	        m1.setLocation("서울 강남");
	        m1.setCategory("독서");
	        m1.setContentName("데미안");
	        m1.setImageUrl("/image/meetingBook.jpg");
	        m1.setCapacity(10);
	        m1.setCurrentCount(10);
	        m1.setMeetingDate(new Date());  // 오늘로 설정
	        meetingRepository.save(m1);
	
	        // === 모임 2: 자리 남아있는 모임 (4 / 5) ===
	        Meeting m2 = new Meeting();
	        m2.setUser(user2);
	        m2.setTitle("영화 감상 모임");
	        m2.setContent("인터스텔라 보러 갈 사람 모집합니다.");
	        m2.setLocation("서울 홍대");
	        m2.setCategory("영화");
	        m2.setContentName("인터스텔라");
	        m2.setImageUrl("/image/meetingMovie.jpg");
	        m2.setCapacity(5);
	        m2.setCurrentCount(4);
	        m2.setMeetingDate(new Date());  // 오늘로 설정
	        meetingRepository.save(m2);
		};
    }
}
