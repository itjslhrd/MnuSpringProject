package com.mnu.exboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mnu.exboard.domain.BoardDTO;
import com.mnu.exboard.mapper.BoardMapper;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	//전체 자료 건수(검색 X)
	public int boardCount() {
		return boardMapper.boardCount();
	}

	//전체 자료 (검색 X, 페이지 X)
	public List<BoardDTO> boardList(){
		return boardMapper.boardList();
		
	}
	
	//등록
	public int boardWrite(BoardDTO dto) {
		return boardMapper.boardWrite(dto);
	}

	//뷰
	public BoardDTO boardView(int idx) {
		BoardDTO board = boardMapper.boardView(idx);
		board.setContents(board.getContents().replace("\n", "<br>"));
		//return boardMapper.boardView(idx);
		return board;
	}
	
}
