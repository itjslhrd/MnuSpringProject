package com.mnu.jpaboard.dto;

import com.mnu.jpaboard.entity.BoardEntity;

import lombok.Getter;

@Getter
public class BoardResponseDTO {
	private int idx;
	private String name;
	private String email;
	private String subject;
	private String contents;
	private String pass;
	private String regdate;
	private int readcnt;

	//Entity => DTO화 시킴
	public BoardResponseDTO(BoardEntity entity) {
		this.idx = entity.getIdx();
		this.name = entity.getName();
		this.email = entity.getEmail();
		this.subject = entity.getSubject();
		this.contents = entity.getContents();
		this.pass = entity.getPass();
		this.regdate = entity.getRegdate();
		this.readcnt = entity.getReadcnt();
	}
}
