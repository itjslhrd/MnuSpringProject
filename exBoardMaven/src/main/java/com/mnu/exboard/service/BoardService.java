package com.mnu.exboard.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnu.exboard.controller.BoardController;
import com.mnu.exboard.domain.BoardDTO;
import com.mnu.exboard.domain.PageDTO;
import com.mnu.exboard.mapper.BoardMapper;

@Service
public class BoardService {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardService.class);

	@Autowired
	private BoardMapper boardMapper;
	
	//전체 자료 건수(검색 X)
	public int boardCount() {
		log.info("Service :  boardCount()");
		return boardMapper.boardCount();
	}
	//전체 자료 건수(검색 O)
	public int boardCountSearch(PageDTO dto) {
		log.info("Service :  boardCountSearch()" + dto);
		return boardMapper.boardCountSearch(dto);
	}

	//전체 자료 (검색 X, 페이지 X)
	public List<BoardDTO> boardList(){
		return boardMapper.boardList();		
	}
	//전체 자료 (검색 O, 페이지 X)
	public List<BoardDTO> boardListSearch(PageDTO dto){
		return boardMapper.boardListSearch(dto);
	}

	//전체 자료 (검색 X, 페이지O)
	public List<BoardDTO> boardListPage(PageDTO dto){
		return boardMapper.boardListPage(dto);
	}
		
	//전체 자료 (검색 O, 페이지 O)
	public List<BoardDTO> boardListSearchPage(PageDTO dto){
		return boardMapper.boardListSearchPage(dto);
	}
	
	//등록
	public int boardWrite(BoardDTO dto) {
		return boardMapper.boardWrite(dto);
	}

	//조회수 증가
	public void boardHits(int idx) {
		boardMapper.boardHits(idx);
	}
		
	//뷰
	public BoardDTO boardView(int idx) {
		BoardDTO board = boardMapper.boardView(idx);
		board.setContents(board.getContents().replace("\n", "<br>"));
		//return boardMapper.boardView(idx);
		return board;
	}
	//수정폼
	public BoardDTO boardSelect(int idx) {
		BoardDTO board = boardMapper.boardView(idx);
		//board.setContents(board.getContents().replace("\n", "<br>"));
		//return boardMapper.boardView(idx);
		return board;
	}
	//수정처리
	public int boardModify(BoardDTO dto) {
		return boardMapper.boardModify(dto);
	}
	//삭제
	public int boardDelete(int idx, String pass) {
		return boardMapper.boardDelete(idx, pass);
	}
	
}
