package com.ssafy.happyhouse.model.service;


import com.ssafy.happyhouse.model.ReplyDto;

public interface ReplyService {

	boolean replyArticle(ReplyDto replyDto) throws Exception;
	ReplyDto getReply(int articleno) throws Exception;
	boolean updateReply(ReplyDto replyDto) throws Exception;
	boolean deleteReply(int articleno) throws Exception;
}
