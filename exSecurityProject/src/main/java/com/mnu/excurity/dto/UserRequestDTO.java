package com.mnu.excurity.dto;

import com.mnu.excurity.entity.UserEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRequestDTO {
    private String userid; 
    private String name;
    private String passwd;
    private String email;
    private String tel;

    //DTO에서 필요한 부분을 entity화
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
