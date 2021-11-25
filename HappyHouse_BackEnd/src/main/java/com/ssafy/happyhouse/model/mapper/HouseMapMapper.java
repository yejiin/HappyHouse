package com.ssafy.happyhouse.model.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.model.dto.data.AptDetailDto;
import com.ssafy.happyhouse.model.dto.data.DealDto;
import com.ssafy.happyhouse.model.dto.housemap.AptCodeDto;
import com.ssafy.happyhouse.model.dto.housemap.AptInfoDto;
import com.ssafy.happyhouse.model.dto.housemap.ChartDto;
import com.ssafy.happyhouse.model.dto.housemap.DealRangeDto;
import com.ssafy.happyhouse.model.dto.housemap.SidoDto;
import com.ssafy.happyhouse.model.dto.housemap.DongDto;
import com.ssafy.happyhouse.model.dto.housemap.FavoriteDto;
import com.ssafy.happyhouse.model.dto.housemap.GugunDto;

public interface HouseMapMapper {

	List<SidoDto> getSido() throws SQLException;
	List<GugunDto> getGugunInSido(String sido) throws SQLException;
	List<DongDto> getDongInGugun(String gugun) throws SQLException;
	List<AptInfoDto> getAptInDong(String gugunCode, String dong) throws SQLException;
	List<DealDto> getAptDeal(String dong, String jibun) throws SQLException;
	DealRangeDto getDealRange(String dong, String jibun) throws SQLException;
	AptCodeDto getAptCode(String name, String dong) throws SQLException;
	AptDetailDto getAptDetail(String aptCode) throws SQLException;
	List<DealDto> getDealLatest(String dong, String name) throws SQLException;
	
	List<AptInfoDto> getFavoriteApt(String userid) throws SQLException;
	int favorite(String name, String userid) throws SQLException;
	int addfavorite(String name, String userid, String gugunname, String dong, String jibun) throws SQLException;
	int cancelfavorite(String name, String userid) throws SQLException;
	List<FavoriteDto> getFavoriteInGugun(String gugunname, String name, String userid) throws SQLException;
	
	ChartDto getChartData(String name) throws SQLException;
	int addChartData(ChartDto dto) throws SQLException;
	ChartDto getMaxData() throws SQLException;
	ChartDto getAvgData() throws SQLException;
}
