package com.mnu.exboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.exboard.domain.BoardDTO;
import com.mnu.exboard.domain.PageDTO;

@Mapper
public interface BoardMapper {
	//전체 자료 건수(검색 X)
	public int boardCount();
	
	//전체 자료 건수(검색 O)
	public int boardCountSearch(PageDTO dto);

	//전체 자료 (검색 X, 페이지 X)
	public List<BoardDTO> boardList();
	
	//전체 자료 (검색 O, 페이지 X)
	public List<BoardDTO> boardListSearch(PageDTO dto);
	
	//전체 자료 (검색 X, 페이지O)
	public List<BoardDTO> boardListPage(PageDTO dto);

	//전체 자료 (검색 O, 페이지 O)
	public List<BoardDTO> boardListSearchPage(PageDTO dto);

	//등록
	public int boardWrite(BoardDTO dto);

	//조회수 증가
	public void boardHits(int idx);

	//뷰 & modify
	public BoardDTO boardView(int idx);

	//수정 처리(update)
	public int boardModify(BoardDTO dto);

	//삭제
	public int boardDelete(int idx, String pass);

}
