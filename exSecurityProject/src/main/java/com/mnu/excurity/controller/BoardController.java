package com.mnu.excurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
	
	@GetMapping("Board/board_list")
	public String boardList() {
		return "Board/board_list";
	}
	
}
