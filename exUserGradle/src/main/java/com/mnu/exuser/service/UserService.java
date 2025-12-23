package com.mnu.exuser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnu.exuser.domain.UserDTO;
import com.mnu.exuser.mapper.UserMapper;
import com.mnu.exuser.util.UserSHA256;

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
		//비번 암호화
		userDTO.setPasswd(UserSHA256.getSHA256(userDTO.getPasswd()));
		
		return userMapper.userInsert(userDTO);
	}

}
