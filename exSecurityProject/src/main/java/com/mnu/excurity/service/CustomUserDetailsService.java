package com.mnu.excurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mnu.excurity.dto.UserRequestDTO;
import com.mnu.excurity.dto.UserResponseDTO;
import com.mnu.excurity.entity.UserEntity;
import com.mnu.excurity.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service   // ⭐⭐⭐ 필수 (없으면 절대 호출 안 됨)
@RequiredArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
	private final BCryptPasswordEncoder encoder;
	//private final PasswordEncoder encoder;//비번 암호화

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

	//등록처리
	public int userInsertPro(UserRequestDTO userRequestDTO) {
		//비번암호화
		userRequestDTO.setPasswd(encoder.encode(userRequestDTO.getPasswd()));
		try {
			//dto를 entity화 DB저장
			userRepository.save(userRequestDTO.toEntity());
			return 1;
		}catch(Exception e) {
			return 0;
		}
	}
	
    @Override
    public UserDetails loadUserByUsername(String userid)
            throws UsernameNotFoundException {
        System.out.println("CustomUserDetailsService 호출됨: " + userid);
        UserEntity user = userRepository.findByUserid(userid)
                .orElseThrow(() ->
                        new UsernameNotFoundException("사용자 없음: " + userid));
        return new CustomUserDetails(user);
    }
    
    //특정(idx) 검색(modify) : 수정폼에서 사용
    public UserResponseDTO userSearch(String userid){
         UserEntity userEntity = userRepository.findByUserid(userid)
        		 .orElseThrow(() -> new IllegalArgumentException("아이디 없음"));
         UserResponseDTO dto = new UserResponseDTO(userEntity);
         return dto;
    }
    
    //회원정보수정 메소드
    public void updateUser(String userid, UserRequestDTO userRequestDTO) {
		//비번암호화
    	userRequestDTO.setPasswd(encoder.encode(userRequestDTO.getPasswd()));
        UserEntity user = userRepository.findByUserid(userid)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음"));

        // 엔티티 값 변경 (Dirty Checking)
        user.update(
        		userRequestDTO.getName(),
        		userRequestDTO.getPasswd(),
        		userRequestDTO.getTel(),
        		userRequestDTO.getEmail()
        );
    }
}
