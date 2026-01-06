package com.mnu.exboard.domain;

import lombok.Data;

@Data
public class PageDTO {
	private int startpage;
	private int endpage;//oracle 용
	private int maxlist;//mysql 용
	
	//검색
	private String search;
	private String key;
	
}
