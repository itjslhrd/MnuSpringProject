package com.mnu.jpaboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mnu.jpaboard.dto.NoticeRequestDTO;
import com.mnu.jpaboard.service.NoticeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor//자동주입(@Autowired 생략)
public class NoticeController {
	private final NoticeService noticeService;
	
	//공지사항 리스트
	@GetMapping("Notice/notice_list")
	public String noticeList(Model model) {
		model.addAttribute("totcount", noticeService.noticeCount());
		model.addAttribute("noticeList", noticeService.noticeList());
		return "Notice/notice_list";
	}
	
	//공지사항 등록(폼)
	@GetMapping("Notice/notice_write")
	public String noticeWrite() {
		return "Notice/notice_write";
	}

	//공지사항 등록(처리)
	@PostMapping("Notice/notice_write")
	public String noticeWritePro(NoticeRequestDTO noticeDTO) {
		noticeService.noticeWrite(noticeDTO);	
		return "Notice/notice_write_pro";
	}
	
	//공지사항 View
	@GetMapping("Notice/notice_view")
	public String noticeView() {
		return "Notice/notice_view";
	}

	//공지사항 수정(폼)
	
	//공지사항 수정(처리)
	
	//공지사항 삭제
	
}
