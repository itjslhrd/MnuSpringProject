package com.mnu.jpaboard.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mnu.jpaboard.dto.NoticeRequestDTO;
import com.mnu.jpaboard.dto.NoticeResponseDTO;
import com.mnu.jpaboard.repository.NoticeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor//자동주입(@Autowired 생략)
public class NoticeService {
	private final NoticeRepository noticeRepository;
	
	//전체 글수 카운트
	public long noticeCount() {
		return noticeRepository.count();
	}
	//전체목록
	@Transactional
	public List<NoticeResponseDTO> noticeList(){
		return noticeRepository.findAll(Sort.by(Sort.Direction.DESC,"idx"))//기본키 내림차순
				.stream()
				.map(NoticeResponseDTO::new)
				.collect(Collectors.toList());
	}
	//등록처리
	@Transactional
	public int noticeWrite(NoticeRequestDTO noticeDTO) {
		return noticeRepository.save(noticeDTO.toEntity()).getIdx();
	}
	
	//view
	
	//수정폼
	
	//수정처리
	
	//삭제처리
	
	
}
