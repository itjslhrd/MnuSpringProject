package com.mnu.excurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	
	//공지사항 리스트
	@GetMapping("Admin/notice_list")
	public String noticeList() {
		return "Admin/notice_list";
	}

	//관리자 리스트
	@GetMapping("Admin/admin_list")
	public String adminList() {
		return "Admin/admin_list";
	}
	
}
