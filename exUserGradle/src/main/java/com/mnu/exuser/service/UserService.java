package com.mnu.exuser.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mnu.exuser.domain.UserDTO;
import com.mnu.exuser.mapper.UserMapper;
import com.mnu.exuser.util.UserSHA256;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;

@Service
public class UserService {
	//coolsms
	@Value("${coolsms.apikey}")
	private String apiKey;
	
	@Value("${coolsms.apisecret}")
	private String apiSecret;

	@Value("${coolsms.fromnumber}")
	private String fromNumber;
	
	@Value("${coolsms.url}")
	private String url;
	
	
	//Mapper 주입
	@Autowired
	private UserMapper userMapper;
	
	//ID 중복검사
	public int userIdCheck(String userid) {
		return userMapper.userIdCheck(userid);
	}
	
	//핸드폰 본인인증
	public String sendSMS(String phoneNumber) {
		String tempNum = tempRandonNumer();//인증번호
		
		DefaultMessageService messageService = 
				NurigoApp.INSTANCE.initialize(apiKey, apiSecret, url);
		Message message = new Message();
		message.setFrom(fromNumber);//발신번호
		message.setTo(phoneNumber);
		message.setText("인증번호 :"+tempNum);
		
		//전송
		messageService.sendOne(new SingleMessageSendingRequest(message));
		
		
		return tempNum;
	}
	//회원등록
	public int userInsert(UserDTO userDTO) {
		//비번 암호화
		userDTO.setPasswd(UserSHA256.getSHA256(userDTO.getPasswd()));
		
		return userMapper.userInsert(userDTO);
	}
	
	//로그인(성공시 1, 실패 0)
	//public int userLogin(String userid, String passwd);
	
	//로그인(ID가 존재하면 비번 반환)
	public String userLoginPass(String userid) {
		return userMapper.userLoginPass(userid);
	}
	
	public String userLoginSearch(UserDTO userDTO) {
		return userMapper.userLoginSearch(userDTO);
	}
	
	//로그인 성공시 session저장용
	public UserDTO userLogin(UserDTO userDTO) {
		return userMapper.userLogin(userDTO);
	}
	
	//로그인 성공시 최근 로그인날 날짜 업데이트
	public void userLoginLastTimeUpdate(UserDTO userDTO) {
		userMapper.userLoginLastTimeUpdate(userDTO);
	}
	
	//회원정보수정
	public int userModify(UserDTO userDTO) {
		//비번 암호화
		userDTO.setPasswd(UserSHA256.getSHA256(userDTO.getPasswd()));
		
		return userMapper.userModify(userDTO);
	}
	
	//회원삭제
	public int userDelete(UserDTO userDTO) {
		return userMapper.userDelete(userDTO);
	}
	
	//회원전체목록
	public List<UserDTO> userList(){
		return userMapper.userList();
	}
	
	//일시비번(4 ~6) 생성
	private String tempRandonNumer() {
		Random r = new Random();
		StringBuffer numStr = new StringBuffer();
		for(int i=0; i<4; i++) {//6자리일 경우 (i<6)
			numStr.append(r.nextInt(10));
		}
		return numStr.toString();
	}
	
}
