package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;
import com.ssafy.util.PageNavigation;

public interface BoardService {

	boolean registerArticle(BoardDto boardDto) throws Exception;
//	boolean replyArticle(BoardDto boardDto) throws Exception;
	List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	PageNavigation makePageNavigation(BoardParameterDto boardParameterDto) throws Exception;
	
	BoardDto getArticle(int articleno) throws Exception;
	boolean updateArticle(BoardDto boardDto) throws Exception;
	boolean deleteArticle(int articleno) throws Exception;
	void updateHit(int articleno) throws Exception;
}
