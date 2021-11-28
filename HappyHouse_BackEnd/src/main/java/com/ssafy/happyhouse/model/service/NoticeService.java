package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.notice.NoticeDto;
import com.ssafy.happyhouse.model.dto.notice.NoticeParameterDto;
import com.ssafy.util.PageNavigation;

public interface NoticeService {

	boolean registerNotice(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> listNotice(NoticeParameterDto noticeParameterDto) throws Exception;
	PageNavigation makePageNavigation(NoticeParameterDto noticeParameterDto) throws Exception;
	
	NoticeDto getNotice(int nno) throws Exception;
	boolean updateNotice(NoticeDto noticeDto) throws Exception;
	boolean deleteNotice(int nno) throws Exception;
	void updateHit(int nno) throws Exception;
}
