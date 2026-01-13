package com.mnu.jpaboard.dto;

import com.mnu.jpaboard.entity.NoticeEntity;

import lombok.Getter;

@Getter
public class NoticeResponseDTO {
	private int idx;
	private String adid;
	private String subject;
	private String contents;
	private String regdate;
	private int readcnt;

	//Entity => DTO화 시킴
	public NoticeResponseDTO(NoticeEntity entity) {
		this.idx = entity.getIdx();
		this.adid = entity.getAdid();
		this.subject = entity.getSubject();
		this.contents = entity.getContents();
		this.regdate = entity.getRegdate();
		this.readcnt = entity.getReadcnt();
	}
}
