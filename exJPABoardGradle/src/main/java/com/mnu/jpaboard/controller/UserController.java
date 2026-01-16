package com.mnu.jpaboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mnu.jpaboard.dto.UserRequestDTO;
import com.mnu.jpaboard.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	//회원가입폼
	@GetMapping("User/user_insert")
	public String usertInsert() {
		
		return "User/user_insert";
	}

	//ID 중복 검사
	@ResponseBody
	@RequestMapping("User/user_idCheck")
	public String userIdCheck(@RequestParam("userid") String userid) {
		int row = userService.userIdCheck1(userid);
		return String.valueOf(row);
	}
	//회원가입처리
	@PostMapping("User/user_insert")
	public String usertInsertPro(UserRequestDTO userRequestDTO, Model model) {
		model.addAttribute("user", userService.userInsertPro(userRequestDTO));
		return "User/user_insert_pro";
	}
	//로그인 폼
	@GetMapping("User/user_login")
	public String usertLogin() {
		
		return "User/user_login";
	}
/*	
	//로그인 처리 - 1
	@PostMapping("User/user_login")
	public String userLogin(UserRequestDTO userRequestDTO, Model model, HttpServletRequest request) {
		try {
			request.getSession().setAttribute("user",userService.userLoginPro(userRequestDTO));
			request.getSession().setMaxInactiveInterval(1800);//세센유효시간
			return "index";
		}catch(IllegalArgumentException e) {
			 model.addAttribute("errorMessage", e.getMessage());
	         return "User/user_login"; // 다시 로그인 페이지
		}
	}
*/
	//로그인 처리 - 2
	//Redirect + FlashAttribute (새로고침 안전)
	//새로고침 시 에러 재전송 방지
	@PostMapping("User/user_login")
	public String userLogin(UserRequestDTO userRequestDTO, RedirectAttributes ra, HttpServletRequest request) {
		try {
			request.getSession().setAttribute("user",userService.userLoginPro(userRequestDTO));
			request.getSession().setMaxInactiveInterval(1800);//세센유효시간
			return "redirect:/";
		}catch(IllegalArgumentException e) {
			 ra.addFlashAttribute("errorMessage", e.getMessage());
	         return "redirect:user_login"; // 다시 로그인 페이지
		}		
	}

	//로그아웃 처리
	@GetMapping("User/user_logout")
	public String usertLogout(HttpSession session) {
		session.invalidate();
		return "index";
	}
	
	//회원정보 수정 폼
	@GetMapping("User/user_modify")
	public String userModify() {
		
		return "User/user_modify";
	}
	
	//회원정보수청처리
	@PostMapping("User/user_modify")
	public String userModifyPro(UserRequestDTO userRequestDTO, HttpSession session) {
		session.setAttribute("user",userService.userModify(userRequestDTO));
		session.setMaxInactiveInterval(1800);//세센유효시간
		return "redirect:/";
	}
	
}
