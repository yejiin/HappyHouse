package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.AnswerDto;
import com.ssafy.happyhouse.model.QuestionDto;
import com.ssafy.happyhouse.model.QuestionParameterDto;
import com.ssafy.util.PageNavigation;

public interface QnAMapper {

	int registerQuestion(QuestionDto questionDto) throws Exception;
	int getTotalCount(QuestionParameterDto questionParameterDto) throws Exception;
	List<QuestionDto> listQuestion(QuestionParameterDto questionParameterDto) throws Exception;
	PageNavigation makePageNavigation(QuestionParameterDto questionParameterDto) throws Exception;
	QuestionDto getQuestion(int qno) throws Exception;
	int updateQuestion(QuestionDto questionDto) throws Exception;
	int deleteQuestion(int qno) throws Exception;

	int registerAnswer(AnswerDto answerDto) throws Exception;
	List<AnswerDto> listAnswer(int qno) throws Exception;
	AnswerDto getAnswer(int qno) throws Exception;
	int updateAnswer(AnswerDto answerDto) throws Exception;
	int deleteAnswer(int qno) throws Exception;
}
