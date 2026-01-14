package com.mnu.jpaboard.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public long boardCountSearch(String search, String key) {
    	switch(search) {
    	case "name":
    		return boardRepository.countByNameContaining(key);
    	case "subject":
    		return boardRepository.countBySubjectContaining(key);
    	case "contents":
    		return boardRepository.countByContentsContaining(key);
    	default:
    		return 0;
    	}
	}
	
/*	//전체목록 검색
	public List<BoardEntity> boardList(){
		//return boardRepository.findAll();//기본키 오름차순
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC,"idx"));//기본키 내림차순
	}
*/
/*	
	//전체목록 검색
	@Transactional
	public List<BoardResponseDTO> boardList(){
		return boardRepository.findAll(Sort.by(Sort.Direction.DESC,"idx"))//기본키 내림차순
				.stream()
				.map(BoardResponseDTO::new)
				.collect(Collectors.toList());
	}
*/
	//전체목록 검색 + page
	@Transactional
	public Page<BoardResponseDTO> boardList(Pageable pageable){
		Page<BoardEntity> page;
		page = boardRepository.findAll(pageable);
		
		return page.map(BoardResponseDTO::new);
	}
	
    //조건(이름,제목, 내용)에 맞는 글 검색
    @Transactional
    public List<BoardResponseDTO> boardListSearch(String search, String key){
    	switch(search) {
    	case "name":
    		return boardRepository.findByNameContainingOrderByIdxDesc(key)
    				.stream()
    				.map(BoardResponseDTO::new)
    				.collect(Collectors.toList());
    	case "subject":
    		return boardRepository.findBySubjectContainingOrderByIdxDesc(key)
    				.stream()
    				.map(BoardResponseDTO::new)
    				.collect(Collectors.toList());
    	case "contents":
    		return boardRepository.findByContentsContainingOrderByIdxDesc(key)
    				.stream()
    				.map(BoardResponseDTO::new)
    				.collect(Collectors.toList());
    	default:
    		return null;
    	}
    }

    //조건(이름,제목, 내용)에 맞는 글 검색 + Page
    @Transactional
    public Page<BoardResponseDTO> boardListSearchPage(String search, String key, Pageable pageable){
    	Page<BoardEntity> page;
    	switch(search) {
    	case "name":
    		page = boardRepository.findByNameContainingOrderByIdxDesc(key, pageable);
    		return page.map(BoardResponseDTO::new);
    	case "subject":
    		page = boardRepository.findBySubjectContainingOrderByIdxDesc(key, pageable);
    	    return page.map(BoardResponseDTO::new);
    	case "contents":
    		page = boardRepository.findByContentsContainingOrderByIdxDesc(key, pageable);
    		return page.map(BoardResponseDTO::new);
    	default:
    		return null;
    	}
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

	//idx에 해당하는 글 검색(modify)
	public BoardResponseDTO boardModify(int idx) {
		BoardEntity boardEntity = boardRepository.findById(idx)
				.orElseThrow(()->new IllegalArgumentException("idx없음"));
		
		BoardResponseDTO board = new BoardResponseDTO(boardEntity);
		return board;
	}
	
	//수정처리
	public int boardModifyPro(int idx, BoardRequestDTO board) {
		int row = boardRepository.boardModify(idx, board.getSubject(), board.getContents(), board.getPass());
		return row;
	}
	
	//삭제처리
	public int boardDelete(int idx, String pass) {
		return boardRepository.boardDelete(idx, pass);
	}
}
