package com.ssafy.happyhouse.model.mapper;

import java.util.List;

import com.ssafy.happyhouse.model.dto.housemap.FavoriteDto;

public interface FavoriteHouseMapper {
	
	int favorite(String name, String userid);
	int addfavorite(String name, String userid, String gugunname, String dong, String jibun);
	int cancelfavorite(String name, String userid);
	List<FavoriteDto> getFavoriteInGugun(String gugunname, String name, String userid);

}