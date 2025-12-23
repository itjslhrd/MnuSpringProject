package com.mnu.exuser.domain;

import lombok.Data;

@Data
public class UserDTO {
	private String userid;		//사용자 ID
	private String name;		//사용자 이름
	private String passwd;		//비밀번호
	private String tel;			//전화번호
	private String email;		//이메일	
	private String first_time;	//가입일자
	private String last_time;	//최근 로그인 날자
	private int rate;// 회원등급(0,1,2,3,4,5)
}
