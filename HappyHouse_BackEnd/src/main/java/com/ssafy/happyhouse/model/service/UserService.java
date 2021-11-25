package com.ssafy.happyhouse.model.service;

import com.ssafy.happyhouse.model.TrendStoreDto;
import com.ssafy.happyhouse.model.UserDto;

public interface UserService {
	
	UserDto login(UserDto userDto) throws Exception;
	
	int idCheck(String checkId) throws Exception;
	
	boolean registerUser(UserDto userDto) throws Exception;
	UserDto userInfo(String userid) throws Exception;
	boolean updateUser(UserDto userDto) throws Exception;
	boolean deleteUser(String userid) throws Exception;
	
	TrendStoreDto getStoreData(int ageGroup) throws Exception;
	boolean updateCount(UserDto userDto) throws Exception;
}
