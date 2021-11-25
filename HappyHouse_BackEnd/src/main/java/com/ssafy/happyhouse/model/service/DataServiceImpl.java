package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.data.AptBasicDto;
import com.ssafy.happyhouse.model.dto.data.AptDetailDto;
import com.ssafy.happyhouse.model.dto.data.AptDto;
import com.ssafy.happyhouse.model.dto.data.DealDto;
import com.ssafy.happyhouse.model.mapper.DataMapper;

@Service
public class DataServiceImpl implements DataService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	public void addOfficeRentData(DealDto dto) {
		sqlSession.getMapper(DataMapper.class).addOfficeRentData(dto);
	}
	@Autowired
	public void addOfficeDealData(DealDto dto) {
		sqlSession.getMapper(DataMapper.class).addOfficeDealData(dto);
	}
	@Autowired
	public void addHomeRentData(DealDto dto) {
		sqlSession.getMapper(DataMapper.class).addHomeRentData(dto);
	}
	@Autowired
	public void addHomeDealData(DealDto dto) {
		sqlSession.getMapper(DataMapper.class).addHomeDealData(dto);
	}
	@Autowired
	public void addAptRentData(DealDto dto) {
		sqlSession.getMapper(DataMapper.class).addAptRentData(dto);
	}
	@Autowired
	public void addAptDealData(DealDto dto) {
		sqlSession.getMapper(DataMapper.class).addAptDealData(dto);
	}
	@Autowired
	public void addAptListData(AptDto dto) {
		sqlSession.getMapper(DataMapper.class).addAptListData(dto);
	}
	@Autowired
	public List<String> getAptCode() {
		return sqlSession.getMapper(DataMapper.class).getAptCode();
	}
	@Autowired
	public void addAptBasicData(AptBasicDto aptdto) {
		sqlSession.getMapper(DataMapper.class).addAptBasicData(aptdto);
	}
	@Autowired
	public void addAptDetailData(AptDetailDto aptdto) {
		sqlSession.getMapper(DataMapper.class).addAptDetailData(aptdto);
	}
}
