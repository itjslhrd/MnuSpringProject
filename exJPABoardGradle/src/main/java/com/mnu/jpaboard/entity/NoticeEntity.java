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
@Table(name="tbl_notice")
@Getter
public class NoticeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//oracle일 경우
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = 
    //          "tbl_board_seq_idx_GENERATOR") 
	//@SequenceGenerator(name="tbl_board_seq_idx_GENERATOR", 
	//sequenceName = "tbl_board_seq_idx", initialValue = 1, allocationSize = 1)
	private int idx;
	private String adid;//관리자 ID
	private String subject;//제목
	private String contents;//내용
	@Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String regdate; //등록일
	//private String updategdate;//수정일
	private int readcnt;//조회수

    //Builder 패턴 : 객체를 생성하는 패턴
    @Builder
    public NoticeEntity(String adid, String subject, String contents) {
    	this.adid=adid;
    	this.subject=subject;
    	this.contents=contents;
    }
}
