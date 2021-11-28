package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.NoticeDto;
import com.ssafy.happyhouse.model.NoticeParameterDto;

public interface NoticeMapper {

	int registerNotice(NoticeDto noticeDto) throws Exception;
	int getTotalCount(NoticeParameterDto noticeParameterDto) throws Exception;
	List<NoticeDto> listNotice(NoticeParameterDto noticeParameterDto) throws Exception;
	
	NoticeDto getNotice(int nno) throws Exception;
	int updateNotice(NoticeDto noticeDto) throws Exception;
	int deleteNotice(int nno) throws Exception;
	void updateHit(int nno) throws SQLException;
}
