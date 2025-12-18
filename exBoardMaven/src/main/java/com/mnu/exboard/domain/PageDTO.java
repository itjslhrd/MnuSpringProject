package com.mnu.exboard.domain;

import lombok.Data;

@Data
public class PageDTO {
	private String search;
	private String key;
	
	private int startpage;
	private int endpage;//oralce 사용시
	private int maxlist;//mysql 사용시
	
}
