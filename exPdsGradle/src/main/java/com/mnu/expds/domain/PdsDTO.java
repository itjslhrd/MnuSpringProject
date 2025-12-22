package com.mnu.expds.domain;

import lombok.Data;

@Data
public class PdsDTO {
	private int idx;			//고유번호
	private String name;		//작성자
	private String subject;		//제목
	private String contents;	//내용
	private String pass;		//비번
	private String regdate;		//등록일자
	private int readcnt;		//조회수
	private String filename;	//첨부파일명

	
}
