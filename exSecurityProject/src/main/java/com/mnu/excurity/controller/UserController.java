package com.mnu.excurity.controller;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mnu.excurity.dto.UserRequestDTO;
import com.mnu.excurity.dto.UserResponseDTO;
import com.mnu.excurity.service.CustomUserDetails;
import com.mnu.excurity.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor//자동주입
public class UserController {
	//주입
	private final CustomUserDetailsService userService;
/*
	//회원가입폼
	@GetMapping("Join/user_insert")
	public String userInsert(Model model) {
		model.addAttribute("userDTO", new UserRequestDTO());
		return "Join/user_insert";
	}
*/	
	//회원가입폼
	@GetMapping("Join/user_insert")
	public String userInsert() {
		
		return "Join/user_insert";
	}
	//아이디 중복검사
	@ResponseBody
	@RequestMapping("Join/user_idCheck")
	public String userIdCheck(@RequestParam("userid") String userid) {
		int row = userService.userIdCheck1(userid);
		return String.valueOf(row);
	}
	
	//본인확인(SMS)
	
	//회원가입처리
	@PostMapping("Join/user_insert")
	public String userInsertPro(UserRequestDTO userRequestDTO, Model model) {
		userService.userInsertPro(userRequestDTO);
		return "Join/user_login";//로그인 폼으로 이동
	}
	
	//로그인 폼
	@GetMapping("Join/user_login")
	public String userLogin() {
		return "Join/user_login";
	}
	//로그인처리는 시큐리티가 알아서 처리하므로 생략
	
	//마이페이지
	@GetMapping("User/user_mypage")
	public String userMyPage() {
		return "User/user_mypage";
	}

	//수정폼(마이페이지)
	@PreAuthorize("isAuthenticated()")//로그인이 필요한 메소드임을 의미
	@GetMapping("User/user_modify")
	//Principal : 스프링 시큐리티에서 현재 인증된 사용자를 나타내는 객체
	public String userModify(Principal principal,Model model) {
    	System.out.println("수정용 아이디 : " + principal.getName());
    	UserResponseDTO dto = userService.userSearch(principal.getName());
    	model.addAttribute("dto", dto);
		return "User/user_modify";
	}

	//수정처리
	@PostMapping("User/user_modify")
	public String userModifyPro(@ModelAttribute UserRequestDTO dto,
	        			Authentication authentication, RedirectAttributes ra) {
		CustomUserDetails user =
		        (CustomUserDetails)authentication.getPrincipal();
		System.out.println("수정처리 아이디(컽트로러) : " + user.getUserId());
		userService.updateUser(user.getUserId(), dto);
		ra.addFlashAttribute("msg", "회원정보가 수정되었습니다.");
		return "redirect:/";
	}
	//로그아웃처리
	@GetMapping("User/user_logout")
	public String userLogout() {
		
		return "User/user_logout";
	}

}
