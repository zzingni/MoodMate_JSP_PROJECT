package com.example.demo.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;

// Configuration 어노테이션 -> 이 클래스는 설정용 클래스이고, 내부에 있는 메서드들은 스프링이 관리해야 할 Bean이라는 것을 알려줌.
@Configuration 
public class UserInitConfig {
	
	@Bean
	public CommandLineRunner initUser (UserRepository userRepository) { // CommandLineRunner -> 스프링 서버 켜질 때 딱 한 번 실행되는 코드
		return args -> {
			// 이미 존재하면 넣지 않기
			if (userRepository.findByLoginId("test") == null) {
				
				User user = new User();
				user.setLoginId("test");
				user.setPassword("1234");
				user.setName("테스트유저");
				user.setNickname("징니");
				user.setGender("여");
				user.setAge(26);
				user.setFavorite("영화");
				
				userRepository.save(user);
			}
		};
	}

}
