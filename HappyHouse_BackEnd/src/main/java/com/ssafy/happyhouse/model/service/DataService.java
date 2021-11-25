package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.data.AptBasicDto;
import com.ssafy.happyhouse.model.dto.data.AptDetailDto;
import com.ssafy.happyhouse.model.dto.data.AptDto;
import com.ssafy.happyhouse.model.dto.data.DealDto;

public interface DataService {
	void addOfficeRentData(DealDto dto) throws SQLException;

	void addOfficeDealData(DealDto dto) throws SQLException;

	void addHomeRentData(DealDto dto) throws SQLException;

	void addHomeDealData(DealDto dto) throws SQLException;

	void addAptRentData(DealDto dto) throws SQLException;

	void addAptDealData(DealDto dto) throws SQLException;

	void addAptListData(AptDto dto) throws SQLException;

	List<String> getAptCode() throws SQLException;

	void addAptBasicData(AptBasicDto aptdto) throws SQLException;

	void addAptDetailData(AptDetailDto aptdto) throws SQLException;
}
