package com.ssafy.happyhouse.model.mapper;

public interface FavoriteHouseMapper {
	
	int favorite(String name, String userid);
	int addfavorite(String name, String userid);
	int cancelfavorite(String name, String userid);

}
