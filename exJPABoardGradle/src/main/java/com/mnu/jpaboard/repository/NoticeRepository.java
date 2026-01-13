package com.mnu.jpaboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mnu.jpaboard.entity.NoticeEntity;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {

	
}
