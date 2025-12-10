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
	
	// 로그인 처리
	public User login(String loginId, String password) {
		
		User user = userRepository.findByLoginId(loginId);
		
		// 사용자 존재하지 않으면 로그인 실패
		if (user == null) {
			return null;
		}
		
		// 아이디 불일치 -> 로그인 실패
		if (!user.getLoginId().equals(loginId)) {
			return null;
		}
		
		// 비밀번호 불일치 -> 로그인 실패
		if (!user.getPassword().equals(password)) {
			return null;
		}
		
		// 로그인 성공 -> User 객체 반환
		return user;
	}
	
}
