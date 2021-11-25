package com.ssafy.happyhouse.model.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.model.dto.user.UserDto;

@Mapper
public interface UserMapper {
	
	UserDto login(UserDto userDto) throws Exception;
	
	int idCheck(String checkId) throws Exception;
	int registerUser(UserDto userDto) throws Exception;
	
	UserDto userInfo(String userid) throws Exception;
	int updateUser(UserDto userDto) throws Exception;
	int deleteUser(String userid) throws Exception;
}
