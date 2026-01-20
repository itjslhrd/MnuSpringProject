package com.mnu.excurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {
	
	@GetMapping("Notice/notice_list")
	public String noticeList() {
		return "Notice/notice_list";
	}
	
}
