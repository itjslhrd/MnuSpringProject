package com.mnu.jpaboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mnu.jpaboard.dto.BoardRequestDTO;
import com.mnu.jpaboard.dto.BoardResponseDTO;
import com.mnu.jpaboard.service.BoardService;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@GetMapping("Board/board_list")
	public String boardList(Model model) {
		model.addAttribute("totcount", boardService.boardCount());
		model.addAttribute("bList", boardService.boardList());
		return "Board/board_list";
	}
	
	//등록폼
	@GetMapping("Board/board_write")
	public String boardWrite(Model model) {
		model.addAttribute("board", new BoardRequestDTO());
		return "Board/board_write";
	}

/*	
	//등록폼
	@GetMapping("Board/board_write")
	public String boardWrite(@ModelAttribute("page") int page) {
		return "Board/board_write2";
	}
*/	
/*	
	//등록처리(성공/실패 메시지 출력용)
	@PostMapping("Board/board_write")
	public String boardWritePro(BoardRequestDTO board, Model model) {
		//서비스 호출
		int row = boardService.boardWrite(board);;
		model.addAttribute("row", row);
		return "Board/board_write_pro";
	}
*/
	//등록처리후 리스트로 이동
	@PostMapping("Board/board_write")
	public String boardWritePro(BoardRequestDTO board) {
		int idx = boardService.boardWrite(board);
		System.out.println("idx=" + idx);
		return "redirect:board_list";//컨트롤로 호출
	}
	
	//idx에 해당하는 글 검색(view)
	@GetMapping("Board/board_view")
	public String boardView(@RequestParam("idx") int idx, @ModelAttribute("page") int page, Model model) {
		BoardResponseDTO board = boardService.boardView(idx);
		
		model.addAttribute("board", board);
		model.addAttribute("newLineChar", "\n");
		
		return "Board/board_view";
	}
	
	//수정폼
	@GetMapping("Board/board_modify")
	public String boardModify(@RequestParam("idx") int idx, 
					@ModelAttribute("page") int page, Model model) {
		model.addAttribute("board", boardService.boardModify(idx));
		return "Board/board_modify";
	}
	//수정처리
	@PostMapping("Board/board_modify")
	public String boardModifyPro(@RequestParam("idx") int idx, 
					@ModelAttribute("page") int page, BoardRequestDTO board, Model model) {
		model.addAttribute("row", boardService.boardModifyPro(idx, board));
		return "Board/board_modify_pro";
	}
	
}
