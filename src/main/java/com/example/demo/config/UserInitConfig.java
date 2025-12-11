package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

// Configuration 어노테이션 -> 이 클래스는 설정용 클래스이고, 내부에 있는 메서드들은 스프링이 관리해야 할 Bean이라는 것을 알려줌.
@Configuration 
public class UserInitConfig {
	
	@Bean
	@Order(1)
	public CommandLineRunner initUser (UserRepository userRepository) { // CommandLineRunner -> 스프링 서버 켜질 때 딱 한 번 실행되는 코드
		return args -> {
			// 이미 존재하면 넣지 않기
			if (userRepository.findByLoginId("test") == null) {
				
				User user1 = new User();
				user1.setLoginId("test");
				user1.setPassword("1234");
				user1.setName("테스트유저");
				user1.setNickname("징니");
				user1.setGender("여");
				user1.setAge(26);
				user1.setFavorite("독서,영화");
				
				userRepository.save(user1);
				
				User user2 = new User();
				user2.setLoginId("test2");
				user2.setPassword("1234");
				user2.setName("테스트그만");
				user2.setNickname("징니징");
				user2.setGender("여");
				user2.setAge(26);
				user2.setFavorite("뮤지컬");
				
				userRepository.save(user2);
				
				User user3 = new User();
                user3.setLoginId("test3");
                user3.setPassword("1234");
                user3.setName("테스트유저3");
                user3.setNickname("티유3");
                user3.setGender("여");
                user3.setAge(24);
                user3.setFavorite("연극,영화");
                userRepository.save(user3);
                
                User user4 = new User();
                user4.setLoginId("test4");
                user4.setPassword("1234");
                user4.setName("테스트유저4");
                user4.setNickname("티유4");
                user4.setGender("남");
                user4.setAge(28);
                user4.setFavorite("영화");
                userRepository.save(user4);

                User user5 = new User();
                user5.setLoginId("test5");
                user5.setPassword("1234");
                user5.setName("테스트유저5");
                user5.setNickname("티유5");
                user5.setGender("여");
                user5.setAge(22);
                user5.setFavorite("독서,뮤지컬");
                userRepository.save(user5);

                User user6 = new User();
                user6.setLoginId("test6");
                user6.setPassword("1234");
                user6.setName("테스트유저6");
                user6.setNickname("티유6");
                user6.setGender("남");
                user6.setAge(30);
                user6.setFavorite("연극");
                userRepository.save(user6);

                User user7 = new User();
                user7.setLoginId("test7");
                user7.setPassword("1234");
                user7.setName("테스트유저7");
                user7.setNickname("티유7");
                user7.setGender("여");
                user7.setAge(25);
                user7.setFavorite("영화,뮤지컬");
                userRepository.save(user7);

                User user8 = new User();
                user8.setLoginId("test8");
                user8.setPassword("1234");
                user8.setName("테스트유저8");
                user8.setNickname("티유8");
                user8.setGender("남");
                user8.setAge(35);
                user8.setFavorite("독서");
                userRepository.save(user8);
			}
		};
	}

}
