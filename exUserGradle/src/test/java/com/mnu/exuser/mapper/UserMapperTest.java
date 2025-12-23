package com.mnu.exuser.mapper;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
	//로그 출력용
	private static Logger log = 
			LoggerFactory.getLogger(UserMapperTest.class);

	//Mapper 주입
	@Autowired
	private UserMapper userMapper;
	
	//id중복 테스트
	@Test
	public void userIdCheckTest() {
		String userid = "user01";// 사용자 입력
		int cnt = userMapper.userIdCheck(userid);
		if(cnt==0) {
			log.info( userid + "는 사용가능합니다");
		}else {
			log.info( userid + "사용중인 ID입니다");			
		}
	}
	
}
