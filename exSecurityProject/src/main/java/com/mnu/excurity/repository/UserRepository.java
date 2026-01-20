package com.mnu.excurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.mnu.excurity.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, String> {
	
	//아이디 존재유무(사용자 정의)
	boolean existsByUserid(String userid);
	//count()보다 exists()가 불필요한 데이터 조회를 줄여 더 빠릅니다.
	//existsBy... 메소드는 DB 인덱스를 활용하여 중복검사를 수행합니다.

	//회원정보등록 : save()
	
	//userid에 해당하는 Entity 검색(로그인)
	Optional<UserEntity> findByUserid(String userid);

	//수정처리(이름, 비번, 전화번호)
    @Modifying
    @Query("update UserEntity user set user.name = :name, "
    		+ " user.passwd = :passwd, user.tel = :tel "
    		+ " where  user.userid = :userid")
    int userModify(String name, String passwd, String tel, String userid);
	
}
