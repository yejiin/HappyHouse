package com.ssafy.happyhouse.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDto {

	private int qno;
	private String userid;
	private String subject;
	private String content;
	private String regtime;
	private int isreply;
}
