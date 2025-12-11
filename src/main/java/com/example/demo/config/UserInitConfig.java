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
				user2.setFavorite("독서");
				
				userRepository.save(user2);
			}
		};
	}

}
