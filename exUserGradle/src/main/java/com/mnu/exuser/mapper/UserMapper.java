package com.mnu.exuser.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.exuser.domain.UserDTO;

@Mapper
public interface UserMapper {
	//ID 중복검사
	public int userIdCheck(String userid);
	
	//회원등록
	public int userInsert(UserDTO userDTO);
	
	//로그인(성공시 1, 실패 0)
	//public int userLogin(String userid, String passwd);
	
	//로그인(ID가 존재하면 비번 반환)
	public String userLoginPass(String userid);
	
	public String userLoginSearch(UserDTO userDTO);
	
	//로그인 성공시 session저장용
	public UserDTO userLogin(UserDTO userDTO);
	
	//로그인 성공시 최근 로그인날 날짜 업데이트
	public void userLoginLastTimeUpdate(UserDTO userDTO);
	
	//회원정보수정
	public int userModify(UserDTO userDTO);
	
	//회원삭제
	public int userDelete(UserDTO userDTO);
	
	//회원전체목록
	public List<UserDTO> userList();
	
}
