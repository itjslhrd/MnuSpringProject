package com.mnu.exboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mnu.exboard.domain.BoardDTO;
import com.mnu.exboard.service.BoardService;

@Controller
@RequestMapping("Board")
public class BoardController {
	//로그출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardController.class);
	
	//서비스 주입
	@Autowired
	private BoardService boardService;
	
	//전체 목록
	@RequestMapping("board_list")
	public String boardList(Model model) {
		log.info("Call : board_list");
		
		model.addAttribute("totcount", boardService.boardCount());
		model.addAttribute("bList", boardService.boardList());
		
		return "/Board/board_list";
	}
	
	//글 쓰기 폼
	@GetMapping("board_write")
	public void boardWrite() {
		log.info("Call : board_write(form)");
	}
	
	//글 등록처리 -1 
	@PostMapping("board_write")
	public String boardWritePro(BoardDTO boardDTO) {
		log.info("Call : board_write(등록처리)");
		//log.info("boardDTO : " + boardDTO);
		int row = boardService.boardWrite(boardDTO);
		
		return "redirect:board_list";// 성공유무 관계없이 처리
	}

	//글 등록처리 - 2
	@PostMapping("board_write2")
	public String boardWritePro2(BoardDTO boardDTO, Model model) {
		
		model.addAttribute("row", 1);
		return "board_write_pro";// 성공유무 확인창
	}
	
	//view
	@GetMapping("board_view")
	public String boardView(@RequestParam("idx") int idx, @RequestParam("page") int page, Model model) {
		log.info("Call : board_view");
		BoardDTO boardDTO = boardService.boardSelect(idx);
		boardDTO.setContents(boardDTO.getContents().replace("\n", "<br>"));
		
		model.addAttribute("board", boardDTO);
		
		//model.addAttribute("board", boardService.boardSelect(idx));
		return "/Board/board_view";//html 이동
	}
	
	//수정 폼
	@GetMapping("board_modify")
	public String boardModify(@RequestParam("idx") int idx, @RequestParam("page") int page, Model model) {
		log.info("Call : board_modify(form)");
		BoardDTO boardDTO = boardService.boardSelect(idx);
		model.addAttribute("board", boardDTO);
		
		return "/Board/board_modify";
	}
	
	//수정처리
	@PostMapping("board_modify")
	public String boardModofyPro(BoardDTO boardDTO, @RequestParam("page") int page, Model model) {
		log.info("Call : board_modify(pro)");
		
		int row = boardService.boardModify(boardDTO);
		model.addAttribute("row", row);
		return "/Board/board_modify_pro";//
	}
	
	//삭제 폼
	@GetMapping("board_delete")
	public String boardDelete(@RequestParam("idx") int idx, @RequestParam("page") int page, Model model) {
		log.info("Call : board_delete(form)");
		
		model.addAttribute("idx", idx);
		
		return "/Board/board_delete";
	}
	
	//삭제 처리
	@PostMapping("board_delete")
	public String boardDeletePro(BoardDTO boardDTO,	@RequestParam("page") int page, Model model) {
		log.info("Call : board_delete(pro)");
				
		model.addAttribute("row", boardService.boardDelete(boardDTO));
		
		return "/Board/board_delete_pro";
	}
}
