package com.mnu.jpaboard.dto;

import com.mnu.jpaboard.entity.UserEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserResponseDTO {
	private String userid;
	private String name;
	private String passwd;
	private String email;
	private String tel;	
	private String first_time;	
	private String last_time;	
	
	//Entity => DTO화 시킴
	public UserResponseDTO(UserEntity entity) {
		this.userid = entity.getUserid();
		this.name = entity.getName();
		this.passwd = entity.getPasswd();
		this.email = entity.getEmail();
		this.tel = entity.getTel();
		this.first_time = entity.getFirst_time();
		this.last_time = entity.getLast_time();
	}
}
