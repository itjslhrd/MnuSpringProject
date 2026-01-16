package com.mnu.jpaboard.dto;

import com.mnu.jpaboard.entity.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDTO {
	private String userid;
	private String name;
	private String passwd;
	private String email;
	private String tel;	
	
    //DTO에서 필요한 부분을 entity화 시킴
	public UserEntity toEntity() {
		return UserEntity.builder()
				.userid(userid)
				.name(name)
				.passwd(passwd)
				.email(email)
				.tel(tel)
				.build();
	}
}
