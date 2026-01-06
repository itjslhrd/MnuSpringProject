package com.mnu.exboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.exboard.domain.BoardDTO;
import com.mnu.exboard.domain.PageDTO;

@Mapper
public interface BoardMapper {
	//총 게시글 수(검색조건 없음)
	public int boardCount();
	
	//총 게시글 수(검색조건 포함)
	public int boardCountSearch(PageDTO pageDTO);
	
	
	//글 전체 목록(검색, 페이지처리 없음)
	public List<BoardDTO> boardList();
	
	//글 전체 목록(검색 X, 페이지처리 O)
	public List<BoardDTO> boardListPage(PageDTO pageDTO);

	//글 전체 목록(검색 O, 페이지처리 O)
	public List<BoardDTO> boardListSearchPage(PageDTO pageDTO);
	
	//글 등록
	public int boardWrite(BoardDTO boardDTO);
	
	//글 조회수 카운트
	public void boardHits(int idx);
	
	//View & Modify 사용할 글 검색
	public BoardDTO boardSelect(int idx);
	
	//수정
	public int boardModify(BoardDTO boardDTO);
	
	//삭제
	//public int boardDelete(@Param("idx") int idx, String pass);
	public int boardDelete(BoardDTO boardDTO);
}
