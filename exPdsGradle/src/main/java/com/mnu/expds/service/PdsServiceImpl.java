package com.mnu.expds.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
