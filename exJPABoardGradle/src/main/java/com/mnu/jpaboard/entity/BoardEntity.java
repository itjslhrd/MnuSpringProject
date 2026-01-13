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
@Table(name="tbl_board")
@Getter
public class BoardEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//oracle일 경우
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = 
    //          "tbl_board_seq_idx_GENERATOR") 
	//@SequenceGenerator(name="tbl_board_seq_idx_GENERATOR", 
	//sequenceName = "tbl_board_seq_idx", initialValue = 1, allocationSize = 1)
	private int idx;
	private String name;//작성자
	private String email;//이메일
	private String pass;//비번
	private String subject;//제목
	private String contents;//내용
	//private LocalDateTime regdate =  
	//                 LocalDateTime.now() ;//등록일
	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String regdate; //등록일
	//private String updategdate;//수정일
	private int readcnt;//조회수

    //Builder 패턴 : 객체를 생성하는 패턴
    @Builder
    public BoardEntity(String name, String email, String pass, String subject, String contents) {
    	this.name=name;
    	this.email=email;
    	this.pass=pass;
    	this.subject=subject;
    	this.contents=contents;
    }
}
