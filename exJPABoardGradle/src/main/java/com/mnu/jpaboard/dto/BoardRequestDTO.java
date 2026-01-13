package com.mnu.jpaboard.dto;

import com.mnu.jpaboard.entity.BoardEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor // 기본 생성자 자동 추가
@Getter
@Setter 
public class BoardRequestDTO {
	private String name;//작성자
	private String email;//이메일
	private String pass;//비번
	private String subject;//제목
	private String contents;//내용

    //DTO에서 필요한 부분을 entity화 시킴
	public BoardEntity toEntity() {
		return BoardEntity.builder()
				.name(name)
				.email(email)
				.pass(pass)
				.subject(subject)
				.contents(contents)
				.build();
	}
	
}
