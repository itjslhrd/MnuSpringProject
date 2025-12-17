package com.mnu.exboard.mapper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardMapperTest {
	//로그 출력용
	private static final Logger log =
			LoggerFactory.getLogger(BoardMapperTest.class);
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void boardCountTest() {
		log.info("자료건수 : " + boardMapper.boardCount());
	}
	
	@Test
	public void boardListTest() {
		boardMapper.boardList().forEach(dto -> log.info(dto.toString()));
	}
	
	
}
