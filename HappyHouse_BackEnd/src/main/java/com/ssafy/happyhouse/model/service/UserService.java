package com.ssafy.happyhouse.model.service;

import java.util.Map;
import com.ssafy.happyhouse.model.UserDto;

public interface UserService {
	
	UserDto login(Map<String, String> map) throws Exception;
	
	int idCheck(String checkId) throws Exception;
	void registerUser(UserDto userDto) throws Exception;
	
//	UserDto login(String userId, String password) throws Exception;
	UserDto getUser(String userId) throws Exception;
	void updateUser(UserDto userDto) throws Exception;
	void deleteUser(String userId) throws Exception;
}
