package com.mnu.exboard.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mnu.exboard.domain.BoardDTO;
import com.mnu.exboard.domain.PageDTO;
import com.mnu.exboard.service.BoardService;
import com.mnu.exboard.util.PageIndex;

@Controller
@RequestMapping("Board")
public class BoardController {
	//로그출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardController.class);
	
	//서비스 주입
	@Autowired
	private BoardService boardService;
/*	
	//전체 목록(검색 X, 페이지 X)
	@RequestMapping("board_list")
	public String boardList(Model model) {
		log.info("Call : board_list");
		
		model.addAttribute("totcount", boardService.boardCount());
		model.addAttribute("bList", boardService.boardList());
		
		return "/Board/board_list";
	}
*/
	//전체 목록(검색 O, 페이지 O)
	@RequestMapping("board_list")
	public String boardList(@RequestParam("page") int page, PageDTO pageDTO, Model model) {
		log.info("Call : board_list");
		
		String url="board_list"; //기본 URL
		int nowpage=page;			//현재 페이지
		int maxlist=10;			//페이지당 글수
		int totpage=1;			//총 페이지
		
		int totcount=0;//총 글수 카운트
		if(pageDTO.getKey()==null) {
			totcount = boardService.boardCount();//검색없이
		}else {
			//검색된 총글수
			totcount = boardService.boardCountSearch(pageDTO);
		}
		//페이지 수 계산
		if(totcount % maxlist == 0) {
			totpage = totcount / maxlist;
		}else {
			totpage = totcount / maxlist + 1;
		}
		//totpage가 0이면 1로 고정
		if(totpage==0) {
			totpage=1;
		}
/*		//oracle용	
		//시작 페이지 ,마지막(oracle용) 페이지 계산
		int startpage = (nowpage-1) * maxlist + 1;
		int endpage = nowpage * maxlist;
		int listcount = totcount-((nowpage-1)*maxlist);//일련번호 출력용
*/		
		int startpage = (nowpage-1)*maxlist;
		int listcount = totcount-startpage;//일렵번호 출력용
		
		//전달
		model.addAttribute("page", nowpage);//현재페이지
		model.addAttribute("totcount", totcount);//총글수
		model.addAttribute("totpage", totpage);//총페이지
		model.addAttribute("listcount", listcount);
		
		pageDTO.setStartpage(startpage);
		pageDTO.setMaxlist(maxlist);
		
		if(pageDTO.getKey()==null) {
			model.addAttribute("bList", boardService.boardListPage(pageDTO));
		}else {
			model.addAttribute("bList", boardService.boardListSearchPage(pageDTO));
		}
		
		if(pageDTO.getKey()==null) {
			model.addAttribute("pageList", PageIndex.pageList(nowpage, totpage, url, maxlist));
		}else {
			model.addAttribute("pageList", PageIndex.pageListHan(nowpage, totpage, url, maxlist, pageDTO.getSearch(),pageDTO.getKey()));			
		}
		
		return "/Board/board_list";
	}
	
	//글 쓰기 폼
	@GetMapping("board_write")
	public void boardWrite(@ModelAttribute("page") int page) {
		log.info("Call : board_write(form)");
	}
	
	//글 등록처리 -1 
	@PostMapping("board_write")
	public String boardWritePro(BoardDTO boardDTO, @ModelAttribute("page") int page) {
		log.info("Call : board_write(등록처리)");
		//log.info("boardDTO : " + boardDTO);
		int row = boardService.boardWrite(boardDTO);
		
		return "redirect:board_list?page="+ page;// 성공유무 관계없이 처리
	}

	//글 등록처리 - 2
	@PostMapping("board_write2")
	public String boardWritePro2(BoardDTO boardDTO, Model model) {
		
		model.addAttribute("row", 1);
		return "board_write_pro";// 성공유무 확인창
	}
	
	//view
	@GetMapping("board_view")
	public String boardView(@RequestParam("idx") int idx, @ModelAttribute("page") int page, Model model) {
		log.info("Call : board_view");
		BoardDTO boardDTO = boardService.boardSelect(idx);
		boardDTO.setContents(boardDTO.getContents().replace("\n", "<br>"));
		
		model.addAttribute("board", boardDTO);
		
		//model.addAttribute("board", boardService.boardSelect(idx));
		return "/Board/board_view";//html 이동
	}
	
	//수정 폼
	@GetMapping("board_modify")
	public String boardModify(@RequestParam("idx") int idx, @ModelAttribute("page") int page, Model model) {
		log.info("Call : board_modify(form)");
		BoardDTO boardDTO = boardService.boardSelect(idx);
		model.addAttribute("board", boardDTO);
		
		return "/Board/board_modify";
	}
	
	//수정처리
	@PostMapping("board_modify")
	public String boardModofyPro(BoardDTO boardDTO, @ModelAttribute("page") int page, Model model) {
		log.info("Call : board_modify(pro)");
		
		int row = boardService.boardModify(boardDTO);
		model.addAttribute("row", row);
		return "/Board/board_modify_pro";//
	}
	
	//삭제 폼
	@GetMapping("board_delete")
	public String boardDelete(@RequestParam("idx") int idx, @ModelAttribute("page") int page, Model model) {
		log.info("Call : board_delete(form)");
		
		model.addAttribute("idx", idx);
		
		return "/Board/board_delete";
	}
	
	//삭제 처리
	@PostMapping("board_delete")
	public String boardDeletePro(BoardDTO boardDTO,	@ModelAttribute("page") int page, Model model) {
		log.info("Call : board_delete(pro)");
				
		model.addAttribute("row", boardService.boardDelete(boardDTO));
		
		return "/Board/board_delete_pro";
	}
}
