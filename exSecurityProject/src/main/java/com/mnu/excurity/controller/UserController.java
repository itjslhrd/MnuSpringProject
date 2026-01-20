package com.mnu.excurity.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnu.excurity.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	//주입
	private final UserService userService;
	private final PasswordEncoder encoder;//비번 암호화 빈
	//회원가입폼
	@GetMapping("Join/user_insert")
	public String userInsert() {
		return "Join/user_insert";
	}
	//아이디 중복검사
	//ID 중복 검사
	@ResponseBody
	@RequestMapping("Join/user_idCheck")
	public String userIdCheck(@RequestParam("userid") String userid) {
		int row = userService.userIdCheck1(userid);
		return String.valueOf(row);
	}
	
	//본인확인
	
	//회원가입처리
	
	//로그인 폼
	@GetMapping("Join/user_login")
	public String userLogin() {
		return "Join/user_login";
	}
	
	//로그인처리
	
	//마이페이지
	@GetMapping("User/user_mypage")
	public String userMyPage() {
		return "User/user_mypage";
	}

	//로그아웃처리
	@GetMapping("User/user_logout")
	public String userLogout() {
		
		return "redirect:/";
	}

}
