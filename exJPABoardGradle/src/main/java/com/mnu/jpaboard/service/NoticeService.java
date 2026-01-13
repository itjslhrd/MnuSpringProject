package com.mnu.jpaboard.service;

import org.springframework.stereotype.Service;

import com.mnu.jpaboard.repository.NoticeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor//자동주입(@Autowired 생략)
public class NoticeService {
	private final NoticeRepository noticeRepository;
	
	
}
