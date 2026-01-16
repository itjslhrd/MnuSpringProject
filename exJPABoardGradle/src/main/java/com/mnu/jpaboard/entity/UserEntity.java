package com.mnu.jpaboard.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name="tbl_user")
@Getter
public class UserEntity {
	@Id
	private String userid;	//유저 ID
	private String name;	//유저 이름
	private String passwd;	//비번
	private String tel;		//전화번호
	private String email;	//이메일
	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String first_time; //가입일자
	private String last_time;	//최근로그인일자
	private String role;		//권한

    //Builder 패턴 : 객체를 생성하는 패턴
    @Builder
    public UserEntity(String userid, String name, String passwd, String email, String tel) {
    	this.userid=userid;
    	this.name=name;
    	this.email=email;
    	this.passwd=passwd;
    	this.tel=tel;
    }
    
    //회원정보수정 메소드(repository update() 사용시) 
    public void userModifyInfo(String name, String passwd, String tel) {
    	this.name=name;
    	this.passwd=passwd;
    	this.tel=tel;
    }
}
