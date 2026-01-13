package com.mnu.jpaboard.dto;

import com.mnu.jpaboard.entity.BoardEntity;
import com.mnu.jpaboard.entity.NoticeEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // 기본 생성자 자동 추가
@Getter
@Setter 
public class NoticeRequestDTO {
	private String adid;//작성자
	private String subject;//제목
	private String contents;//내용

    //DTO에서 필요한 부분을 entity화 시킴
	public NoticeEntity toEntity() {
		return NoticeEntity.builder()
				.adid(adid)
				.subject(subject)
				.contents(contents)
				.build();
	}
	
}
