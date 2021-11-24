package com.ssafy.happyhouse.model.service;

import java.util.List;

import com.ssafy.happyhouse.model.dto.housemap.AptInfoDto;
import com.ssafy.happyhouse.model.dto.housemap.AptListRequest;
import com.ssafy.happyhouse.model.dto.housemap.CompareRequest;
import com.ssafy.happyhouse.model.dto.housemap.CompareResponse;
import com.ssafy.happyhouse.model.dto.housemap.DealResponse;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;

public interface HouseMapService {

	List<SidoDto> getSido() throws Exception;
	List<GugunDto> getGugunInSido(String sido) throws Exception;
	List<DongDto> getDongInGugun(String gugun) throws Exception;
	List<AptInfoDto> getAptInDong(AptListRequest req) throws Exception;
	DealResponse getAptDeal(String dong, String jibun) throws Exception;
	List<AptInfoDto> getFavoriteApt(String userid) throws Exception;
	CompareResponse getCompareInfo(CompareRequest req);
}
