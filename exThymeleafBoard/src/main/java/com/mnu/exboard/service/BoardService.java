package com.mnu.exboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnu.exboard.domain.BoardDTO;
import com.mnu.exboard.domain.PageDTO;
import com.mnu.exboard.mapper.BoardMapper;

@Service
public class BoardService {
	//Mapper 주입
	@Autowired
	private BoardMapper boardMapper;
	
	//총 게시글 수(검색조건 없음)
	public int boardCount() {
		return boardMapper.boardCount();
	}
	
	//총 게시글 수(검색조건 포함)
	public int boardCountSearch(PageDTO pageDTO) {
		return boardMapper.boardCountSearch(pageDTO);
	}
	
	//글 전체 목록(검색, 페이지처리 없음)
	public List<BoardDTO> boardList(){
		return boardMapper.boardList();
	}
	
	//글 전체 목록(검색 X, 페이지처리 O)
	public List<BoardDTO> boardListPage(PageDTO pageDTO){
		return boardMapper.boardListPage(pageDTO);
	}
	
	//글 전체 목록(검색 O, 페이지처리 O)
	public List<BoardDTO> boardListSearchPage(PageDTO pageDTO){
		return boardMapper.boardListSearchPage(pageDTO);	
	}
	
	//글 등록
	public int boardWrite(BoardDTO boardDTO) {
		return boardMapper.boardWrite(boardDTO);
	}
	
	//글 보기
	public BoardDTO boardSelect(int idx) {
		boardMapper.boardHits(idx);//조회수 증가
		return boardMapper.boardSelect(idx);
	}
	
	//수정
	public int boardModify(BoardDTO boardDTO) {
		return boardMapper.boardModify(boardDTO);
	}
	
	//삭제
//	public int boardDelete(int idx, String pass) {
//		return boardMapper.boardDelete(idx, pass);
//	}
	public int boardDelete(BoardDTO boardDTO) {
		return boardMapper.boardDelete(boardDTO);
	}

}
