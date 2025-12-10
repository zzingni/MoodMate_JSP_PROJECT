package com.example.demo.user;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	// 테이블 처리 전담
	private final UserRepository userRepository;
	
	// 데이터 삽입
	public void insert(User user) {
		userRepository.save(user);
	}
	
	
}
