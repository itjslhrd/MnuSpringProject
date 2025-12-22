package com.mnu.expds.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String pdsWritePro() {
		
		return "";
	}
}
