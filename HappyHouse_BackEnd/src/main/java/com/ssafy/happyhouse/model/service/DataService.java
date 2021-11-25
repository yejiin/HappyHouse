package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.data.AptBasicDto;
import com.ssafy.happyhouse.model.dto.data.AptDetailDto;
import com.ssafy.happyhouse.model.dto.data.AptDto;
import com.ssafy.happyhouse.model.dto.data.DealDto;

public interface DataService {
	void addOfficeRentData(DealDto dto) throws Exception;

	void addOfficeDealData(DealDto dto) throws Exception;

	void addHomeRentData(DealDto dto) throws Exception;

	void addHomeDealData(DealDto dto) throws Exception;

	void addAptRentData(DealDto dto) throws Exception;

	void addAptDealData(DealDto dto) throws Exception;

	void addAptListData(AptDto dto) throws Exception;

	List<String> getAptCode() throws Exception;

	void addAptBasicData(AptBasicDto aptdto) throws Exception;

	void addAptDetailData(AptDetailDto aptdto) throws Exception;
}
