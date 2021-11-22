package com.ssafy.happyhouse.model.mapper;

import com.ssafy.happyhouse.model.dto.data.DealDto;

public interface DataMapper {

	void addOfficeRentData(DealDto dto);
	
	void addOfficeDealData(DealDto dto);
	
	void addHomeRentData(DealDto dto);

	void addHomeDealData(DealDto dto);
	
	void addAptRentData(DealDto dto);
	
	void addAptDealData(DealDto dto);
}
