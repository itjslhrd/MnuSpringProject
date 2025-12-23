package com.mnu.exuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnu.exuser.domain.UserDTO;
import com.mnu.exuser.service.UserService;

@Controller
@RequestMapping("User")
public class UserController {
	//로그 출력용
	private Logger log = 
			LoggerFactory.getLogger(UserController.class);
	
	//서비스 주입
	@Autowired
	private UserService userService;
	
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
	@ResponseBody
	@RequestMapping("user_idCheck")
	public String userIdCheck(@RequestParam("userid") String userid) {
		log.info("Call : user_idCheck");
		int row = userService.userIdCheck(userid);
		return String.valueOf(row);
	}
	
	//본인인증(email 또는 SMS)
	
	//회원 가입처리
	@PostMapping("user_insert")
	public String userInsert(UserDTO userDTO, Model model) {
		log.info("Call : user_insert(post)");
		//log.info("name : " + userDTO.getName());
		//log.info("userid : " + userDTO.getUserid());
		//log.info("passwd : " + userDTO.getPasswd());
		//log.info("tel : " + userDTO.getTel());
		model.addAttribute("row", userService.userInsert(userDTO));
		return "/User/user_insert_pro";
	}
	//회원 정보수정폼
	
	//회원정보 수정처리
	
	//ID 찾기 또는 비번찾기 폼
	
	// ID 찾기 또는 비번찾기 처리
	
	//회원 탈퇴(삭제)
	

}
