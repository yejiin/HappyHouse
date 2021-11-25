package com.ssafy.happyhouse.model.dto.QnA;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDto {

	private int ano;
	private int qno;
	private String subject;
	private String content;
	private String regtime;

}
