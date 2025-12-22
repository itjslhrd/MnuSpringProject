package com.mnu.expds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.expds.domain.PageDTO;
import com.mnu.expds.domain.PdsDTO;

@Mapper
public interface PdsMapper {
	//전체 카운트
	public int pdsCount();
	
	//전체카운트(검색조건포함)
	//전체 자료 건수(검색 O)
	public int pdsCountSearch(PageDTO dto);
	
	//전체목록(검색 X, 페이지 X)
	public List<PdsDTO> pdsList();
	
	//전체목록(검색 O, 페이지 O)
	public List<PdsDTO> pdsListSearchPage(PageDTO dto);
	
	//등록
	public int pdsWrite(PdsDTO pdsDTO);

	//특정글 조회수 증가(View)
	public void pdsHits(int idx);
	
	//검색(특정 idx) -->View, Modify
	public PdsDTO pdsSelect(int idx);
	
	//수정
	public int pdsModify(PdsDTO pdsDTO);
	
	//삭제
	public int pdsDelete(PdsDTO dto);
	
}
