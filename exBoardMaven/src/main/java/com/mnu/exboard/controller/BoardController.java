package com.mnu.exboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mnu.exboard.domain.BoardDTO;
import com.mnu.exboard.service.BoardService;

@Controller
@RequestMapping("Board")
public class BoardController {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService boardService;
	
	@GetMapping("board_list")
	public String boardList(Model model) {
		log.info("Controller Call : board_list");
		
		model.addAttribute("totcount", boardService.boardCount());
		model.addAttribute("bList", boardService.boardList());
		
		return "/Board/board_list";
	}
	
	@GetMapping("board_write")
	public void boardWrite() {
		log.info("Controller Call : board_write");
	}

	@PostMapping("board_write")
	public String boardWritePro(BoardDTO dto, Model model) {
		log.info("Controller Call : board_write_pro");
		
		int row = boardService.boardWrite(dto);
		model.addAttribute("row", row);
		
		return "/Board/board_write_pro";
	}
	
}
