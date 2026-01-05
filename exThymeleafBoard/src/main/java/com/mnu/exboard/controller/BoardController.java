package com.mnu.exboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("Board")
public class BoardController {
	//로그출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardController.class);
	
	//서비스 주입
	
	//전체 목록
	@RequestMapping("board_list")
	public String boardList() {
		log.info("Call : board_list");
		return "/Board/board_list";
	}
}
