package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.data.DealDto;
import com.ssafy.happyhouse.model.dto.housemap.AptInfoDto;
import com.ssafy.happyhouse.model.dto.housemap.DealRangeDto;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;

public interface HouseMapMapper {

	List<SidoDto> getSido() throws SQLException;
	List<GugunDto> getGugunInSido(String sido) throws SQLException;
	List<DongDto> getDongInGugun(String gugun) throws SQLException;
	List<AptInfoDto> getAptInDong(String gugunCode, String dong) throws SQLException;
	List<DealDto> getAptDeal(String dong, String jibun) throws SQLException;
	DealRangeDto getDealRange(String dong, String jibun) throws SQLException;
}
