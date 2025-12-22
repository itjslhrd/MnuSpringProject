package com.mnu.expds.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mnu.expds.domain.PdsDTO;

@Mapper
public interface PdsMapper {
	//전체 카운트
	public int pdsCount();
	
	//전체카운트(검색조건포함)
	
	//전체목록(검색 X, 페이지 X)
	public List<PdsDTO> pdsList();
	
	//전체목록(검색 O, 페이지 O)
	
	//등록
	public int pdsWrite(PdsDTO pdsDTO);
	
	//검색(특정 idx) -->View, Modify
	
	//수정
	
	//삭제
	
	
}
