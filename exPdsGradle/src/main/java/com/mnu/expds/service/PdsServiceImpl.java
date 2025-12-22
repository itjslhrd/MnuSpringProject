package com.mnu.expds.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mnu.expds.domain.PdsDTO;
import com.mnu.expds.mapper.PdsMapper;

@Service
public class PdsServiceImpl implements PdsService {
	//Mapper 주입
	@Autowired
	private PdsMapper pdsMapper;
	
	
	@Override
	public int pdsCount() {
		
		return pdsMapper.pdsCount();
	}

	@Override
	public List<PdsDTO> pdsList() {
		
		return pdsMapper.pdsList();
	}

	@Override
	public int pdsWrite(PdsDTO pdsDTO) {
		
		return pdsMapper.pdsWrite(pdsDTO);
	}

	
	@Override
	public PdsDTO pdsSelect(int idx) {
		return pdsMapper.pdsSelect(idx);
	}
	
	@Override
	public int pdsModify(MultipartHttpServletRequest request) {
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

		return pdsMapper.pdsModify(pdsDTO);
	}
}
