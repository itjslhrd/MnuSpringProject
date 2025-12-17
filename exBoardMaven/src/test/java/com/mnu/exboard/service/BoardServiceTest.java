package com.mnu.exboard.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardServiceTest {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardServiceTest.class);
	@Autowired
	private BoardService boardService;
	
	@Test
	public void boardCountTest() {
		log.info("자료건수 : " + boardService.boardCount());
	}
	
	@Test
	public void boardListTest() {
		boardService.boardList().forEach(dto -> log.info(dto.toString()));
	}

}
