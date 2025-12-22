package com.mnu.expds.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mnu.expds.domain.PageDTO;
import com.mnu.expds.domain.PdsDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface PdsService {
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
	
	//public int pdsWrite2(MultipartHttpServletRequest request);
	//검색(특정 idx) -->View, Modify

	//글 조회수 증가
	public void pdsHits(int idx, HttpServletRequest request, HttpServletResponse response);

	//view, modify
	public PdsDTO pdsSelect(int idx);
	
	//수정
	public int pdsModify(MultipartHttpServletRequest request);
	
	//삭제
	public int pdsDelete(PdsDTO dto);	

}
