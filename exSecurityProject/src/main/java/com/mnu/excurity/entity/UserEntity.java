package com.mnu.excurity.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    private String userid; 
    private String name;
    private String passwd;
    private String email;
    private String tel;
    @CreationTimestamp // INSERT 시 자동으로 값을 채워줌
    private LocalDateTime first_time = LocalDateTime.now() ;//등록일
//	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//	private String first_time; //가입일자

    @UpdateTimestamp // UPDATE시 자동으로 값을 채워줌
    private LocalDateTime last_time = LocalDateTime.now();

    @Enumerated(EnumType.STRING)//권한설정
    private Role role; //User, Admin, Manager

    @Builder
    //Builder 패턴 : 객체를 생성하는 패턴
    public UserEntity(String userid, String name, String passwd, String email, String tel) {
         this.userid=userid;
         this.name=name;
         this.passwd=passwd;
         this.email=email;
         this.tel=tel;
         this.role=Role.USER;//기본값으로 User 권한 부여
    }

    //회원정보수정용
    public void update(String name, String passwd, String tel, String email) {
        this.name = name;
        this.passwd = passwd;
        this.tel = tel;
        this.email = email;
    }
}
