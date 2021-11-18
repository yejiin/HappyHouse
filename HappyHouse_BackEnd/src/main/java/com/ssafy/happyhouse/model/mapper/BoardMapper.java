package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.BoardDto;
import com.ssafy.happyhouse.model.BoardParameterDto;

public interface BoardMapper {

	int registerArticle(BoardDto boardDto) throws Exception;
//	int replyArticle(BoardDto boardDto) throws Exception;
	int getTotalCount(BoardParameterDto boardParameterDto) throws Exception;
	List<BoardDto> listArticle(BoardParameterDto boardParameterDto) throws Exception;
	BoardDto getArticle(int articleno) throws Exception;
	int updateArticle(BoardDto boardDto) throws Exception;
	int deleteArticle(int articleno) throws Exception;
	void updateHit(int articleno) throws SQLException;
}
