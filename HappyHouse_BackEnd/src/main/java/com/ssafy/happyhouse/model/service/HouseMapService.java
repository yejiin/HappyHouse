package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.housemap.HouseListRequest;
import com.ssafy.happyhouse.model.dto.housemap.HouseListResponse;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;

public interface HouseMapService {

	List<SidoDto> getSido() throws Exception;
	List<GugunDto> getGugunInSido(String sido) throws Exception;
	List<DongDto> getDongInGugun(String gugun) throws Exception;
	HouseListResponse getHouseInDong(HouseListRequest req) throws Exception;
}
