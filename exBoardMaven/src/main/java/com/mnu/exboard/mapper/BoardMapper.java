package com.mnu.exboard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.exboard.domain.BoardDTO;

@Mapper
public interface BoardMapper {
	//전체 자료 건수(검색 X)
	public int boardCount();

	//전체 자료 (검색 X, 페이지 X)
	public List<BoardDTO> boardList();
	
	//등록
	public int boardWrite(BoardDTO dto);

	//뷰
	public BoardDTO boardView(int idx);

	//수정
	public int boardModify(BoardDTO dto);

	//삭제
	public int boardDelete(int idx, String pass);

}
