package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.housemap.HouseInfoDto;
import com.ssafy.happyhouse.model.dto.housemap.HouseListParamDto;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;

public interface HouseMapMapper {

	List<SidoDto> getSido() throws SQLException;
	List<GugunDto> getGugunInSido(String sido) throws SQLException;
	List<DongDto> getDongInGugun(String gugun) throws SQLException;
	List<HouseInfoDto> getHouseInDong(HouseListParamDto dto) throws SQLException;
	int getTotalCount(HouseListParamDto dto) throws Exception;

}
