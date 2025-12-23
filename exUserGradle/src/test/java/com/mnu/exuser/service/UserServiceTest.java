package com.mnu.exuser.service;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
	//로그 출력용
	private static Logger log = 
				LoggerFactory.getLogger(UserServiceTest.class);
	//주입
	@Autowired
	private UserService userService;
	
	@Test
	public void userIdCheckTest() {
		String userid = "user01";// 사용자 입력
		int cnt = userService.userIdCheck(userid);
		if(cnt==0) {
			log.info( userid + "는 사용가능합니다");
		}else {
			log.info( userid + "사용중인 ID입니다");			
		}
	}
}
