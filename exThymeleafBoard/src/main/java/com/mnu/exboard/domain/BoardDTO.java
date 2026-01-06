package com.mnu.exboard.domain;

import lombok.Data;

@Data
public class BoardDTO {
	private int idx;			//글번호 기본키
	private String name;		//작성자
	private String subject;		//글 제목
	private String contents;	//글 내용
	private String pass;		//비번
	private String regdate;		//등록일자
	private int readcnt;		//조회수
	
}
