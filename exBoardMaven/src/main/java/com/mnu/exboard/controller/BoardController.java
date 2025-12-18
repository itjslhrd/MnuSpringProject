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
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService boardService;
/*	
	@GetMapping("board_list") //검색(X, 페이지 X)
	public String boardList(Model model) {
		log.info("Controller Call : board_list");
		
		model.addAttribute("totcount", boardService.boardCount());
		model.addAttribute("bList", boardService.boardList());
		
		return "/Board/board_list";
	}
*/
/*	
	@RequestMapping("board_list")//검색(O), 페이지(X)
	public String boardList(PageDTO dto, Model model) {
		log.info("Controller Call : board_list");
		log.info("Controller Call : search : " + dto.getSearch());
		log.info("Controller Call : key : " + dto.getKey());
		
		if(dto.getKey()==null) {
			model.addAttribute("totcount", boardService.boardCount());
			model.addAttribute("bList", boardService.boardList());
		}else {
			model.addAttribute("totcount", boardService.boardCountSearch(dto));
			model.addAttribute("bList", boardService.boardListSearch(dto));			
		}
		
		return "/Board/board_list";
	}
*/
	@RequestMapping("board_list")//검색(O), 페이지(O)
	public String boardList(@RequestParam("page") int page, PageDTO dto, Model model) {
		log.info("Controller Call : board_list");
		
		String url ="board_list";
		
		int nowpage = 1;//현재 페이지
		int maxlist = 10;//페이지당 글수
		int totpage = 1;//총페이지
		
		int totcount = 0;// 총 게시글수
		if(dto.getKey()==null) {// 검색에 따른 총글수 카운트
			totcount = boardService.boardCount();
		}else {
			totcount = boardService.boardCountSearch(dto);
		}
		
		//총 페이지 수 계산
		if(totcount % maxlist == 0) {
			totpage = totcount / maxlist;
		}else {
			totpage = totcount / maxlist + 1;
		}
		if(totpage==0) {
			totpage=1;
		}
		
		//페이지가 넘어온경우
		if(page != 0) {
			nowpage=page;
		}
		
		//페이지 시작번호
		int startpage = (nowpage-1)*maxlist;
		int listcount = totcount - startpage;//리스트에 일괄적으로 번호 부여시 사용
		
		// 전달
		model.addAttribute("page",nowpage);//현재페이지
		model.addAttribute("totpage",totpage);//총페이지
		model.addAttribute("totcount",totcount);//총 글수
		model.addAttribute("listcount",listcount);//리스트 번호
		//search, key는 pageDTO를 통해서 자동 전달
				
		dto.setStartpage(startpage);
		dto.setMaxlist(maxlist);
		
		if(dto.getKey()==null) {
			//페이지(O)
			model.addAttribute("bList",boardService.boardListPage(dto));
		}else {
			//검색 + 페이지
			model.addAttribute("bList",boardService.boardListSearchPage(dto));
		}
		
		//페이지 인덱싱
		if(dto.getKey()==null) {
			model.addAttribute("pageList", PageIndex.pageList(nowpage, totpage, url, maxlist));
		}else {
			model.addAttribute("pageList", PageIndex.pageListHan(nowpage, totpage, url, maxlist, dto.getSearch(), dto.getKey()));
		}
		
		return "/Board/board_list";
	}
	
	
	
	@GetMapping("board_write")
	public void boardWrite(@ModelAttribute("page") int page) {
		log.info("Controller Call : board_write");
	}

	@PostMapping("board_write")
	public String boardWritePro(BoardDTO dto, @ModelAttribute("page") int page, Model model) {
		log.info("Controller Call : board_write_pro");
		
		int row = boardService.boardWrite(dto);
		model.addAttribute("row", row);
		
		return "/Board/board_write_pro";
	}
	
	//뷰
	@GetMapping("board_view")
	public String boardView(@ModelAttribute("page") int page,  int idx, Model model) {
		log.info("Controller Call : board_view");
		//조회수ㅡ증가
		boardService.boardHits(idx);
		
		//model.addAttribute("page", page);
		model.addAttribute("board", boardService.boardView(idx));
		
		return "/Board/board_view";
	}
	
	//수정 폼(Get)
	@GetMapping("board_modify")
	public void boardModify(int idx, @ModelAttribute("page") int page, Model model) {
		log.info("Controller Call : board_modify");
		BoardDTO boardDTO = boardService.boardSelect(idx);
		
		model.addAttribute("boardDTO", boardDTO);
	}
	
	//수정처리(Post)
	@PostMapping("board_modify")
	public String boardModifyPro(BoardDTO boardDTO, @ModelAttribute("page") int page, Model model) {
		log.info("Controller Call : board_modify_pro");
		
		//int row = boardService.boardModify(boardDTO);
		//model.addAttribute("row", row);
		model.addAttribute("row", boardService.boardModify(boardDTO));
		
		return "/Board/board_modify_pro";// 경고창
	}
	//삭제 폼(Get)
	@GetMapping("board_delete")
	public void boardDelete(int idx, @ModelAttribute("page") int page, Model model) {
		log.info("Controller Call : board_delete");
		
		model.addAttribute("idx", idx);
	}
	//삭제 처리(Post)
	@PostMapping("board_delete")
	public String boardDeletePro(int idx, String pass, @ModelAttribute("page") int page, Model model) {
		log.info("Controller Call : board_delete_pro");
		
		model.addAttribute("row", boardService.boardDelete(idx, pass));
		return "/Board/board_delete_pro";
	}
}
