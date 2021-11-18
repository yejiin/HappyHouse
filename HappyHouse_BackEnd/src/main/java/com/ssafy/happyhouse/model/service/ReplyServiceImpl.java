package com.ssafy.happyhouse.model.service;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.ReplyDto;
import com.ssafy.happyhouse.model.mapper.ReplyMapper;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean replyArticle(ReplyDto replyDto) throws Exception {
		if(replyDto.getSubject() == null || replyDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(ReplyMapper.class).replyArticle(replyDto) == 1;
	}
	
	@Override
	public ReplyDto getReply(int articleno) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).getReply(articleno);
	}

	@Override
	@Transactional
	public boolean updateReply(ReplyDto replyDto) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).updateReply(replyDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteReply(int articleno) throws Exception {
		return sqlSession.getMapper(ReplyMapper.class).deleteReply(articleno) == 1;
	}


}
