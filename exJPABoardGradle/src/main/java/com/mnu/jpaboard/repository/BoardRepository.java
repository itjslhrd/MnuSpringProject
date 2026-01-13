package com.mnu.jpaboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mnu.jpaboard.entity.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
	//count() //카운트
	//findAll() //전체목록
	//save(entity)// 등록
	//findById()// 기본키를 이용한 검색
	//delete()//삭제
	
	//조회수 증가
	@Transactional
	@Modifying
	@Query("update BoardEntity board set board.readcnt=board.readcnt+1 where board.idx = :idx")
	void boardHits(@Param("idx") int idx);
	
	//수정
	@Transactional
	@Modifying
	@Query("update BoardEntity board set board.subject= :subject, board.contents= :contents where board.idx= :idx and board.pass= :pass")
	int boardModify(@Param("idx") int idx, @Param("subject") String subject, @Param("contents") String contents, @Param("pass") String pass);
	
	//삭제(idx, pass)
	@Transactional
	@Modifying
	@Query("delete from BoardEntity board where board.idx= :idx and board.pass= :pass")
	int boardDelete(@Param("idx") int idx, @Param("pass") String pass);
	
}
