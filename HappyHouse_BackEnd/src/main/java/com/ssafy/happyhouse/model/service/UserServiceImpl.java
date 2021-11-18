package com.ssafy.happyhouse.model.service;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SqlSession sqlSession;


	@Override
	public int idCheck(String checkId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).idCheck(checkId);
	}

	@Override
	public void registerUser(UserDto userDto) throws Exception {
		sqlSession.getMapper(UserMapper.class).registerUser(userDto);
	}

	@Override
	public UserDto login(Map<String, String> map) throws Exception {
		return sqlSession.getMapper(UserMapper.class).login(map);
	}
	
	/*
	 * @Override public UserDto login(String userId, String password) throws
	 * Exception { return userDao.login(userId, password); }
	 */

	@Override
	public UserDto getUser(String userId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getUser(userId);
	}

	@Override
	public void updateUser(UserDto userDto) throws Exception {
		sqlSession.getMapper(UserMapper.class).updateUser(userDto);
	}

	@Override
	public void deleteUser(String userId) throws Exception {
		sqlSession.getMapper(UserMapper.class).deleteUser(userId);
	}

}
