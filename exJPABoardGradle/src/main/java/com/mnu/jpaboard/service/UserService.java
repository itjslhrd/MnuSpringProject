package com.mnu.jpaboard.service;

import org.springframework.stereotype.Service;

import com.mnu.jpaboard.dto.UserRequestDTO;
import com.mnu.jpaboard.dto.UserResponseDTO;
import com.mnu.jpaboard.entity.UserEntity;
import com.mnu.jpaboard.repository.UserRepository;
import com.mnu.jpaboard.util.UserSha256;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor//자동주입(@Autowired 생략)
public class UserService {
	private final UserRepository userRepository;
	
	//아이디 중복검사(사용자 정의)
	public int userIdCheck1(String userid) {
		if(userRepository.existsByUserid(userid)) {
			//throw new IllegalStateException("이미 존재하는 아이디입니다.");
			return 1;
		}else {
			return 0;
		}
	}

	//아이디 중복검사(기본)
	public int userIdCheck(String userid) {
		if(userRepository.existsById(userid)) {
			//throw new IllegalStateException("이미 존재하는 아이디입니다.");
			return 1;
		}else {
			return 0;
		}
	}

	//회원가입(등록)처리
	public int userInsertPro(UserRequestDTO userRequestDTO) {
		//비번 암호화
		userRequestDTO.setPasswd(UserSha256.encrypt(userRequestDTO.getPasswd()));
		try {
			userRepository.save(userRequestDTO.toEntity());
			//save() 성공이 Entity반환/ 실패시 예외발생
			return 1;
		}catch(Exception e) {
			return 0;
		}
	}
	
	//로그인 처리
	public UserResponseDTO userLoginPro(UserRequestDTO userRequestDTO) {
		UserEntity userEntity = userRepository.findByUserid(userRequestDTO.getUserid())
				.orElseThrow(()->new IllegalArgumentException("ID 없음"));
		
		if(!UserSha256.encrypt(userRequestDTO.getPasswd()).equals(userEntity.getPasswd())) {
			throw new IllegalArgumentException("비번오류");
		}
		
		return new UserResponseDTO(userEntity);
	}
/*	
 * SpringSecurity 필요
  PasswordEncoder 설정(java)
  @Configuration
  public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  }

	// 로그인처리
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserLoginResponse login(UserLoginRequest request) {

        UserEntity user = userRepository.findByUserid(request.getUserid())
                .orElseThrow(() -> new IllegalArgumentException("아이디 없음"));

        // 🔐 비밀번호 비교
        if (!passwordEncoder.matches(request.getPasswd(), user.getPasswd())) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }

        // 로그인 성공
        return UserLoginResponse.from(user);
    }	
*/    
	//회원정보 수정(update) 후 세션 변경처리
	public UserResponseDTO userModify(UserRequestDTO userRequestDTO) {
		UserEntity userEntity = userRepository.findByUserid(userRequestDTO.getUserid())
				.orElseThrow(()->new IllegalArgumentException("ID 없음"));
		//비번암호화
		userRequestDTO.setPasswd(UserSha256.encrypt(userRequestDTO.getPasswd()));
		userEntity.userModifyInfo(userRequestDTO.getName(), userRequestDTO.getPasswd(), userRequestDTO.getTel());

		return new UserResponseDTO(userEntity);
	}
}
