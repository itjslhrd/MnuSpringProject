package com.mnu.jpaboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {
	
	//공지사항 리스트
	@GetMapping("Notice/notice_list")
	public String noticeList() {
		return "Notice/notice_list";
	}
	
	//공지사항 등록(폼)
	@GetMapping("Notice/notice_write")
	public String noticeWrite() {
		return "Notice/notice_write";
	}

	
	//공지사항 등록(처리)

	
	//공지사항 View
	@GetMapping("Notice/notice_view")
	public String noticeView() {
		return "Notice/notice_view";
	}

	//공지사항 수정(폼)
	
	//공지사항 수정(처리)
	
	//공지사항 삭제
	
}
