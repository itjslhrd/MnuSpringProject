package com.mnu.jpaboard.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mnu.jpaboard.dto.BoardRequestDTO;
import com.mnu.jpaboard.dto.BoardResponseDTO;
import com.mnu.jpaboard.entity.BoardEntity;
import com.mnu.jpaboard.repository.BoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor//자동주입(@Autowired 생략)
public class BoardService {
	private final BoardRepository boardRepository;
	
	//전체 글수 카운트
	public long boardCount() {
		return boardRepository.count();
	}
	
/*	//전체목록 검색
	public List<BoardEntity> boardList(){
		//return boardRepository.findAll();//기본키 오름차순
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC,"idx"));//기본키 내림차순
	}
*/
	//전체목록 검색
	@Transactional
	public List<BoardResponseDTO> boardList(){
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC,"idx"))//기본키 내림차순
				.stream()
				.map(BoardResponseDTO::new)
				.collect(Collectors.toList());
	}
	
	//등록처리
	@Transactional
	public int boardWrite(BoardRequestDTO board) {
		return boardRepository.save(board.toEntity()).getIdx();
		//등록후 idx 반환
	}
	
	//idx에 해당하는 글 검색(view)
	public BoardResponseDTO boardView(int idx) {
		//조회수 증가	
		boardRepository.boardHits(idx);

		BoardEntity boardEntity = boardRepository.findById(idx)
				.orElseThrow(()->new IllegalArgumentException("idx없음"));
		
		BoardResponseDTO board = new BoardResponseDTO(boardEntity);
		return board;
	}
	
}
