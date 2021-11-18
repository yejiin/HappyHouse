package com.ssafy.happyhouse.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {

	private int articleno;
	private String userId;
	private String subject;
	private String content;
	private int hit; // 조회수
	private String regTime;

}
