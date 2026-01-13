package com.mnu.jpaboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mnu.jpaboard.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
	//count() //카운트
	//findAll() //전체목록
	//save(entity)// 등록
	//findByIdx()// 기본키를 이용한 검색
	//delete()//삭제
	
	
	
}
