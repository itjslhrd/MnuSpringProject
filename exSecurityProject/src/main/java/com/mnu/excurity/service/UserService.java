package com.mnu.excurity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnu.excurity.dto.UserRequestDTO;
import com.mnu.excurity.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //자동주입요청
@Transactional
public class UserService {
	//주입
	private final UserRepository userRepository;
	
	//아이디 중복검사(사용자 정의)
	public int userIdCheck1(String userid) {
		if(userRepository.existsByUserid(userid)) {
			//throw new IllegalStateException("이미 존재하는 아이디입니다.");
			return 1;
		}else {
			return 0;
		}
	}

	//아이디 중복검사(기본)
	public int userIdCheck(String userid) {
		if(userRepository.existsById(userid)) {
			//throw new IllegalStateException("이미 존재하는 아이디입니다.");
			return 1;
		}else {
			return 0;
		}
	}

	//등록처리
	public int userInsertPro(UserRequestDTO userRequestDTO) {
		//비번암호화
		try {
			//dto를 entity화 DB저장
			userRepository.save(userRequestDTO.toEntity());
			return 1;
		}catch(Exception e) {
			return 0;
		}
	}
	
}
