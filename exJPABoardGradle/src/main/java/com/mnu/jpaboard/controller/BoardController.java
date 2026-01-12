package com.mnu.jpaboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("Board/board_list")
	public String boardList() {
		return "Board/board_list";
	}
	
	//등록폼
	@GetMapping("Board/board_write")
	public String boardWrite() {
		return "Board/board_write";
	}
	
}
