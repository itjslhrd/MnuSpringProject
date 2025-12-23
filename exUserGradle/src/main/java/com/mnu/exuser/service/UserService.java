package com.mnu.exuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnu.exuser.domain.UserDTO;
import com.mnu.exuser.mapper.UserMapper;

@Service
public class UserService {
	//Mapper 주입
	@Autowired
	private UserMapper userMapper;
	
	//ID 중복검사
	public int userIdCheck(String userid) {
		return userMapper.userIdCheck(userid);
	}
	
	//회원등록
	public int userInsert(UserDTO userDTO) {
		return userMapper.userInsert(userDTO);
	}

}
