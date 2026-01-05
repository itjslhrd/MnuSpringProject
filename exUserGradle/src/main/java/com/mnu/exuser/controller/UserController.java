package com.mnu.exuser.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mnu.exuser.domain.UserDTO;
import com.mnu.exuser.service.UserService;
import com.mnu.exuser.util.UserSHA256;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

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
	@GetMapping("user_login")
	public String userLoginForm(HttpSession session) {
		log.info("Call : user_login(form)");
		if(session.getAttribute("user") == null) {
			return "/User/user_login";
		}else {
			return "User/user_list";//로그인 되어 있는 경우 이동 경로 지정
		}
	}
/*
	//로그인 처리
	@PostMapping("user_login")
	public String userLogin(@RequestParam("userid") String userid, 
								@RequestParam("passwd") String passwd) {
								
		String passwd = userService.userLoginSearch(userid, passwd);
	}
*/	
	//로그인 처리
	@PostMapping("user_login")
	public String userLogin(UserDTO userDTO, Model model, HttpServletRequest request) {
		log.info("Call : user_login");
		//id에 해당하는 비번 체크
		String passwd = userService.userLoginSearch(userDTO);
		//row=1(로그인성공), 0(비번오류), -1(id없음)
		if(passwd==null) {//비번이 없음
			//id 없음
			model.addAttribute("row", -1);
			return "/User/user_login_pro";
		}else {//비번이 있는 경우
			if(passwd.equals(UserSHA256.getSHA256(userDTO.getPasswd()))) {
				//로그인 성공(세션설정)
				request.getSession().setAttribute("user", userService.userLogin(userDTO));
				request.getSession().setMaxInactiveInterval(1800);//세센유효시간
				//최근 로그인날자 업데이트
				userService.userLoginLastTimeUpdate(userDTO);
				model.addAttribute("row", 1);
				return "/User/user_login_pro";
			}else {
				//비번오류
				model.addAttribute("row", 0);
				return "/User/user_login_pro";
			}
		}
	}

	//회원 가입폼
	@RequestMapping("user_insert")
	public String userInsert(HttpSession session) {
		log.info("Call : user_insert");
		if(session.getAttribute("user") == null) {
			return "/User/user_insert";
		}else {
			return "User/user_list";//로그인 되어 있는 경우 이동 경로 지정
		}
		
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
	@ResponseBody
	@PostMapping("user_sms")
	public String smsSend(@RequestParam("tel") String tel) {
		
		String tempNum = userService.sendSMS(tel);
		
		log.info("인증코드 : " + tempNum);
		return tempNum;
	}
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
	@GetMapping("user_modify")
	public void userModify() {
		log.info("Call : user_modify(form)");
	}
	
	//회원정보 수정처리
	@PostMapping("user_modify")
	public String userModifyPro(UserDTO userDTO) {
		log.info("Call : user_modify(pro)");
		
		int row = userService.userModify(userDTO);
		//수정처리 경고 없음
		return "redirect:user_login";
	}
	
	//로그아웃 처리
	@GetMapping("user_logout")
	public String userLogout(HttpSession session) {
		log.info("user_Logout..............");
		session.invalidate();
		return "/index";
	}
	//ID 찾기 또는 비번찾기 폼
	
	// ID 찾기 또는 비번찾기 처리
	
	//회원 탈퇴(삭제)
	

}
