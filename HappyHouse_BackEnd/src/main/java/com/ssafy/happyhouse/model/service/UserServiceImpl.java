package com.ssafy.happyhouse.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.model.TrendStoreDto;
import com.ssafy.happyhouse.model.UserDto;
import com.ssafy.happyhouse.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public UserDto login(UserDto userDto) throws Exception {
		if(userDto.getUserid() == null || userDto.getPassword() == null)
			return null;
		return sqlSession.getMapper(UserMapper.class).login(userDto);
	}


	@Override
	public int idCheck(String checkId) throws Exception {
		return sqlSession.getMapper(UserMapper.class).idCheck(checkId);
	}

	@Override
	public boolean registerUser(UserDto userDto) throws Exception {
		 sqlSession.getMapper(UserMapper.class).registerUser(userDto);
		return sqlSession.getMapper(UserMapper.class).updateCount(userDto) == 1; 
		
	}

	@Override
	public UserDto userInfo(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).userInfo(userid);
	}

	@Override
	public boolean updateUser(UserDto userDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).updateUser(userDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteUser(String userid) throws Exception {
		return sqlSession.getMapper(UserMapper.class).deleteUser(userid) == 1;
	}
	
//	TrendStore

	@Override
	public TrendStoreDto getStoreData(int ageGroup) throws Exception {
		return sqlSession.getMapper(UserMapper.class).getStoreData(ageGroup);
	}
	
	public boolean updateCount(UserDto userDto) throws Exception {
		return sqlSession.getMapper(UserMapper.class).updateCount(userDto) == 1; 
	}
	
}
