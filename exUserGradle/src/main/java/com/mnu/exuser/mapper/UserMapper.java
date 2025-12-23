package com.mnu.exuser.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.exuser.domain.UserDTO;

@Mapper
public interface UserMapper {
	//ID 중복검사
	public int userIdCheck(String userid);
	
	//회원등록
	public int userInsert(UserDTO userDTO);
	
	
	
}
