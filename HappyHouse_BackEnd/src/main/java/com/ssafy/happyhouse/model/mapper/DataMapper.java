package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.data.AptBasicDto;
import com.ssafy.happyhouse.model.dto.data.AptDetailDto;
import com.ssafy.happyhouse.model.dto.data.AptDto;
import com.ssafy.happyhouse.model.dto.data.DealDto;

public interface DataMapper {

	void addOfficeRentData(DealDto dto);
	
	void addOfficeDealData(DealDto dto);
	
	void addHomeRentData(DealDto dto);

	void addHomeDealData(DealDto dto);
	
	void addAptRentData(DealDto dto);
	
	void addAptDealData(DealDto dto);

	void addAptListData(AptDto dto);

	List<String> getAptCode();

	void addAptBasicData(AptBasicDto dto);

	void addAptDetailData(AptDetailDto aptdto);
}
