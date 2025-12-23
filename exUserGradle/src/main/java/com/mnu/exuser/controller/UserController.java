package com.mnu.exuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("User")
public class UserController {
	//로그 출력용
	private Logger log = 
			LoggerFactory.getLogger(UserController.class);
	
	//로그인 폼
	@RequestMapping("user_login")
	public void userLogin() {
		log.info("Call : user_login");
	}
	//로그인 처리
	
	//회원 가입폼
	@RequestMapping("user_insert")
	public void userInsert() {
		log.info("Call : user_insert");
	}
	
	//ID 중복 검사
	
	//본인인증(email 또는 SMS)
	
	//회원 가입처리
	
	//회원 정보수정폼
	
	//회원정보 수정처리
	
	//ID 찾기 또는 비번찾기 폼
	
	// ID 찾기 또는 비번찾기 처리
	
	//회원 탈퇴(삭제)
	

}
