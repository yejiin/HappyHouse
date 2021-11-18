package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.ReplyDto;

public interface ReplyMapper {

	int replyArticle(ReplyDto replyDto) throws Exception;
	ReplyDto getReply(int articleno) throws Exception;
	int updateReply(ReplyDto replyDto) throws Exception;
	int deleteReply(int articleno) throws Exception;
}
