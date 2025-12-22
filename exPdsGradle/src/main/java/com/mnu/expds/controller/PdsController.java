package com.mnu.expds.controller;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mnu.expds.domain.PdsDTO;
import com.mnu.expds.service.PdsService;

@Controller
@RequestMapping("Pds")
public class PdsController {
	//로그 출력용
	private Logger log = 
			LoggerFactory.getLogger(PdsController.class);
	// 서비스 주입	
	@Autowired
	private PdsService pdsService;
	
	//게시판 전체목록(pds_list)
	@RequestMapping("pds_list")
	public String pdsList(Model model) {
		
		model.addAttribute("totcount", pdsService.pdsCount());
		model.addAttribute("pdsList", pdsService.pdsList());
		
		return "Pds/pds_list";
	}
	
	//등록폼
	@GetMapping("pds_write")
	public void pdsWrite() {
		
	}
/*	
 	//등록폼
	@GetMapping("pds_write")
	public String pdsWrite() {
		
		return "/Pds/pds_write";
	}
*/
	//등록처리
	@PostMapping("pds_write")
	public String pdsWritePro(MultipartHttpServletRequest request, Model model) {
		
		PdsDTO pdsDTO = new PdsDTO();
		pdsDTO.setName(request.getParameter("name"));
		pdsDTO.setSubject(request.getParameter("subject"));
		pdsDTO.setContents(request.getParameter("contents"));
		pdsDTO.setPass(request.getParameter("pass"));
		
		//파일 저장경로 설정
		String path = request.getServletContext().getRealPath("/upload/");
		MultipartFile mf = request.getFile("filename");
		String fileName = mf.getOriginalFilename();
		File file = new File(path+fileName);
		try {
			mf.transferTo(file);//파일저장
		}catch(Exception e) {
			e.printStackTrace();
		}
		pdsDTO.setFilename(fileName);
		int row = pdsService.pdsWrite(pdsDTO);
		return "redirect:pds_list";// 경고없이 리스트로 이동(controller)
/*
		model.addAttribute("row", row);
		return "/Pds/pds_write_pro";
*/		
	}
	
	//view
	@GetMapping("pds_view")
	public String pdsView(@RequestParam("idx") int idx, Model model) {
		log.info("idx=" + idx);
		PdsDTO pdsDTO = pdsService.pdsSelect(idx);
		pdsDTO.setContents(pdsDTO.getContents().replace("\n", "<br>"));
		model.addAttribute("pdsDTO", pdsDTO);
		return "/Pds/pds_view";
	}
	
	//수정폼
	@GetMapping("pds_modify")
	public String pdsModify(@RequestParam("idx") int idx, Model model) {
		PdsDTO pdsDTO = pdsService.pdsSelect(idx);
		model.addAttribute("pdsDTO", pdsDTO);
		return "/Pds/pds_modify";
	}
	
	//수정처리
	@PostMapping("pds_modify")
	public String pdsModifyPro(MultipartHttpServletRequest request, Model model) {
/*
		PdsDTO pdsDTO = new PdsDTO();
		pdsDTO.setIdx(Integer.parseInt(request.getParameter("idx")));
		pdsDTO.setName(request.getParameter("name"));
		pdsDTO.setSubject(request.getParameter("subject"));
		pdsDTO.setContents(request.getParameter("contents"));
		pdsDTO.setPass(request.getParameter("pass"));
		String oldfilename = request.getParameter("oldfilename");
		
		//파일 저장경로 설정
		String path = request.getServletContext().getRealPath("/upload/");
		MultipartFile mf = request.getFile("filename");
		
		String fileName = mf.getOriginalFilename();
		if(fileName.equals("")) {//파일을 변경하지 않을 경우
			pdsDTO.setFilename(oldfilename);
		}else {//첨부파일 변경시
			File newFile = new File(path+fileName);
			File oldFile = new File(path+oldfilename);
			try {
				if(oldFile.exists()) {
					oldFile.delete();//구 파일 삭제
				}
				mf.transferTo(newFile);//파일저장
			}catch(Exception e) {
				e.printStackTrace();
			}
			pdsDTO.setFilename(fileName);
		}

		int row = pdsService.pdsModify(pdsDTO);
*/
		int row = pdsService.pdsModify(request);
//		return "redirect:pds_list";// 경고없이 리스트로 이동(controller)

		model.addAttribute("row", row);//0 비번오류, 1 성공
		return "/Pds/pds_modify_pro";
	
	}
	
}
