package com.mnu.excurity.dto;

import com.mnu.excurity.entity.UserEntity;

import lombok.Getter;

@Getter
public class UserResponseDTO {
    private String userid; 
    private String name;
    private String passwd;
    private String email;
    private String tel;

    public UserResponseDTO(UserEntity entity) {
    	this.userid=userid;
    	this.name=name;
    	this.passwd=passwd;
    	this.tel=tel;
    	this.email=email;
    }
}
