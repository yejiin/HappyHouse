package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.AnswerDto;
import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.QuestionParameterDto;
import com.ssafy.happyhouse.model.mapper.QnAMapper;
import com.ssafy.util.PageNavigation;

@Service
public class QnAServiceImpl implements QnAService {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public boolean registerQuestion(QuestionDto questionDto) throws Exception {
		if(questionDto.getSubject() == null || questionDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnAMapper.class).registerQuestion(questionDto) == 1;
	}

	@Override
	public List<QuestionDto> listQuestion(QuestionParameterDto questionParameterDto) throws Exception {
		int start = questionParameterDto.getPg() == 0 ? 0 : (questionParameterDto.getPg() - 1) * questionParameterDto.getSpp();
		questionParameterDto.setStart(start);
		return sqlSession.getMapper(QnAMapper.class).listQuestion(questionParameterDto);
	}

	@Override
	public PageNavigation makePageNavigation(QuestionParameterDto questionParameterDto) throws Exception {
		int naviSize = 5;
		PageNavigation pageNavigation = new PageNavigation();
		pageNavigation.setCurrentPage(questionParameterDto.getPg());
		pageNavigation.setNaviSize(naviSize);
		int totalCount = sqlSession.getMapper(QnAMapper.class).getTotalCount(questionParameterDto);//총글갯수  269
		pageNavigation.setTotalCount(totalCount);  
		int totalPageCount = (totalCount - 1) / questionParameterDto.getSpp() + 1;//27
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = questionParameterDto.getPg() <= naviSize;
		pageNavigation.setStartRange(startRange);
		boolean endRange = (totalPageCount - 1) / naviSize * naviSize < questionParameterDto.getPg();
		pageNavigation.setEndRange(endRange);
		pageNavigation.makeNavigator();
		return pageNavigation;
	}
	
	@Override
	public QuestionDto getQuestion(int qno) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).getQuestion(qno);
	}

	@Override
	@Transactional
	public boolean updateQuestion(QuestionDto questionDto) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).updateQuestion(questionDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteQuestion(int qno) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).deleteQuestion(qno) == 1;
	}

	@Override
	public boolean registerAnswer(AnswerDto answerDto) throws Exception {
		if(answerDto.getSubject() == null || answerDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnAMapper.class).registerAnswer(answerDto) == 1;
	}

	@Override
	public List<AnswerDto> listAnswer(int qno) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).listAnswer(qno);
	}

	@Override
	public AnswerDto getAnswer(int qno) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).getAnswer(qno);
	}

	@Override
	@Transactional
	public boolean updateAnswer(AnswerDto answerDto) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).updateAnswer(answerDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteAnswer(int qno) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).deleteAnswer(qno) == 1;
	}

	@Override
	public boolean updateIsreply(int qno) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).updateIsreply(qno) == 1;
	}

}
