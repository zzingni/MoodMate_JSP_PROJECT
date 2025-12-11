package com.example.demo.config;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.example.demo.meeting.Meeting;
import com.example.demo.meeting.MeetingRepository;
import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MemberInitConfig {
	
	private final UserRepository userRepository;
    private final MeetingRepository meetingRepository;
    private final MemberRepository memberRepository;

    private static final List<String> STATUS = Arrays.asList("대기", "승인", "거절");
    private final Random random = new Random();

    @Bean
    @Order(3)
    public CommandLineRunner initMembers() {
        return args -> {

        	// 모든 유저 가져오기
            List<User> users = userRepository.findAll();
            if (users.isEmpty()) {
                System.out.println("사용자 데이터가 없어 MemberInitConfig 실행 불가");
                return;
            }

            // 모든 모임 가져오기
            List<Meeting> meetings = meetingRepository.findAll();
            if (meetings.isEmpty()) {
                System.out.println("MeetingInitConfig 가 먼저 실행되어야 합니다.");
                return;
            }

            // 자동 신청에서 제외할 유저 목록
            List<String> excludeUsers = Arrays.asList("test", "test2", "test3");

            for (Meeting meeting : meetings) {

                int applicantCount = 2 + random.nextInt(3); // 2~4명 랜덤 신청
                int countAdded = 0;

                for (int i = 0; i < applicantCount; i++) {

                    User randomUser = users.get(random.nextInt(users.size()));

                    // 테스트 계정 제외
                    if (excludeUsers.contains(randomUser.getLoginId())) {
                        continue;
                    }

                    // 이미 신청한 경우 패스
                    boolean alreadyJoined =
                            memberRepository.existsByUserAndMeeting(randomUser, meeting);
                    if (alreadyJoined) continue;

                    Member member = new Member();
                    member.setUser(randomUser);
                    member.setMeeting(meeting);
                    member.setConfirmStatus(
                            STATUS.get(random.nextInt(STATUS.size()))
                    );

                    memberRepository.save(member);
                    countAdded++;
                }

                // currentCount 업데이트
                meeting.setCurrentCount(meeting.getCurrentCount() + countAdded);
                meetingRepository.save(meeting);
            }
        };
    }
}
