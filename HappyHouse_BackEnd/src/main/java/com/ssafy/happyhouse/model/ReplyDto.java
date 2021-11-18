package com.ssafy.happyhouse.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDto {
	
	private int replyno;
	private int articleno;
	private String userId;
	private String subject;
	private String content;
	private String replyTime;
	
}
