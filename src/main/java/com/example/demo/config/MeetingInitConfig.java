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
			User user3 = userRepository.findByLoginId("test3");
			
			// ========================================
            // 1. 독서 모임 (마감)
            // ========================================
            Meeting m1 = new Meeting();
            m1.setUser(user1);
            m1.setTitle("완전 마감된 독서 모임");
            m1.setContent("이미 정원이 가득 찼습니다!");
            m1.setLocation("서울 강남");
            m1.setCategory("독서");
            m1.setContentName("데미안");
            m1.setImageUrl("/image/meetingBook.jpg");
            m1.setCapacity(10);
            m1.setCurrentCount(10);
            m1.setMeetingDate(new Date());
            meetingRepository.save(m1);

            // ========================================
            // 2. 영화 모임
            // ========================================
            Meeting m2 = new Meeting();
            m2.setUser(user2);
            m2.setTitle("인터스텔라 감상 모임");
            m2.setContent("영화 인터스텔라 함께 보실 분~");
            m2.setLocation("서울 홍대");
            m2.setCategory("영화");
            m2.setContentName("인터스텔라");
            m2.setImageUrl("/image/meetingMovie.jpg");
            m2.setCapacity(6);
            m2.setCurrentCount(3);
            m2.setMeetingDate(new Date());
            meetingRepository.save(m2);

            // ========================================
            // 3. 연극 모임
            // ========================================
            Meeting m3 = new Meeting();
            m3.setUser(user3);
            m3.setTitle("연극 '나의 아저씨' 관람 모임");
            m3.setContent("감동적인 연극 함께 보러 가요!");
            m3.setLocation("대학로");
            m3.setCategory("연극");
            m3.setContentName("나의 아저씨");
            m3.setImageUrl("/image/meetingTheater.jpg");
            m3.setCapacity(8);
            m3.setCurrentCount(2);
            m3.setMeetingDate(new Date());
            meetingRepository.save(m3);

            // ========================================
            // 4. 뮤지컬 모임
            // ========================================
            Meeting m4 = new Meeting();
            m4.setUser(user1);
            m4.setTitle("뮤지컬 위키드 관람");
            m4.setContent("뮤지컬 좋아하시나요? 위키드 같이 봐요!");
            m4.setLocation("잠실");
            m4.setCategory("뮤지컬");
            m4.setContentName("위키드");
            m4.setImageUrl("/image/meetingMusical.jpg");
            m4.setCapacity(7);
            m4.setCurrentCount(5);
            m4.setMeetingDate(new Date());
            meetingRepository.save(m4);

            // ========================================
            // 5. 영화 모임
            // ========================================
            Meeting m5 = new Meeting();
            m5.setUser(user2);
            m5.setTitle("범죄도시4 보러 갈 사람?");
            m5.setContent("가볍게 팝콘 먹으면서 영화 봐요~");
            m5.setLocation("용산 아이맥스");
            m5.setCategory("영화");
            m5.setContentName("범죄도시4");
            m5.setImageUrl("/image/meetingMovie.jpg");
            m5.setCapacity(10);
            m5.setCurrentCount(6);
            m5.setMeetingDate(new Date());
            meetingRepository.save(m5);

            // ========================================
            // 6. 독서 소모임
            // ========================================
            Meeting m6 = new Meeting();
            m6.setUser(user3);
            m6.setTitle("아침 독서 모임");
            m6.setContent("매일 아침 20분 책 읽기!");
            m6.setLocation("온라인 ZOOM");
            m6.setCategory("독서");
            m6.setContentName("원하는 책");
            m6.setImageUrl("/image/meetingBook.jpg");
            m6.setCapacity(20);
            m6.setCurrentCount(12);
            m6.setMeetingDate(new Date());
            meetingRepository.save(m6);

            // ========================================
            // 7. 연극 토크 모임
            // ========================================
            Meeting m7 = new Meeting();
            m7.setUser(user1);
            m7.setTitle("연극 러브레터 관람 후 토크");
            m7.setContent("연극 보고 후 감상 대화하는 모임입니다.");
            m7.setLocation("대학로");
            m7.setCategory("연극");
            m7.setContentName("러브레터");
            m7.setImageUrl("/image/meetingTheater.jpg");
            m7.setCapacity(5);
            m7.setCurrentCount(1);
            m7.setMeetingDate(new Date());
            meetingRepository.save(m7);

            // ========================================
            // 8. 뮤지컬 노래 따라부르기 모임
            // ========================================
            Meeting m8 = new Meeting();
            m8.setUser(user2);
            m8.setTitle("뮤지컬 OST 함께 부르기");
            m8.setContent("노래 좋아하는 분 환영!");
            m8.setLocation("강남 연습실");
            m8.setCategory("뮤지컬");
            m8.setContentName("Wicked OST");
            m8.setImageUrl("/image/meetingMusical.jpg");
            m8.setCapacity(12);
            m8.setCurrentCount(4);
            m8.setMeetingDate(new Date());
            meetingRepository.save(m8);

            // ========================================
            // 9. 영화 리뷰 스터디
            // ========================================
            Meeting m9 = new Meeting();
            m9.setUser(user3);
            m9.setTitle("주간 영화 리뷰 스터디");
            m9.setContent("한 주에 한 편 보고 리뷰 나누기!");
            m9.setLocation("온라인 디스코드");
            m9.setCategory("영화");
            m9.setContentName("주간 선정 영화");
            m9.setImageUrl("/image/meetingMovie.jpg");
            m9.setCapacity(15);
            m9.setCurrentCount(10);
            m9.setMeetingDate(new Date());
            meetingRepository.save(m9);

            // ========================================
            // 10. 독서 북클럽
            // ========================================
            Meeting m10 = new Meeting();
            m10.setUser(user1);
            m10.setTitle("월간 북클럽 모임");
            m10.setContent("매달 책 한 권 정해서 함께 이야기 나눠요.");
            m10.setLocation("서울 시청");
            m10.setCategory("독서");
            m10.setContentName("이달의 책");
            m10.setImageUrl("/image/meetingBook.jpg");
            m10.setCapacity(8);
            m10.setCurrentCount(3);
            m10.setMeetingDate(new Date());
            meetingRepository.save(m10);
		};
    }
}
