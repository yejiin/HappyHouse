package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.qna.AnswerDto;
import com.ssafy.happyhouse.model.dto.qna.QuestionDto;
import com.ssafy.happyhouse.model.dto.qna.QuestionParameterDto;
import com.ssafy.util.PageNavigation;

public interface QnAService {

	boolean registerQuestion(QuestionDto questionDto) throws Exception;
	List<QuestionDto> listQuestion(QuestionParameterDto questionParameterDto) throws Exception;
	PageNavigation makePageNavigation(QuestionParameterDto questionParameterDto) throws Exception;
	QuestionDto getQuestion(int qno) throws Exception;
	boolean updateQuestion(QuestionDto questionDto) throws Exception;
	boolean deleteQuestion(int qno) throws Exception;

	boolean registerAnswer(AnswerDto answerDto) throws Exception;
	List<AnswerDto> listAnswer(int qno) throws Exception;
	AnswerDto getAnswer(int qno) throws Exception;
	boolean updateAnswer(AnswerDto answerDto) throws Exception;
	boolean deleteAnswer(int qno) throws Exception;
}
