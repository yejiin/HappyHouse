package com.ssafy.happyhouse.model.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.data.AptBasicDto;
import com.ssafy.happyhouse.model.dto.data.AptDetailDto;
import com.ssafy.happyhouse.model.dto.data.AptDto;
import com.ssafy.happyhouse.model.dto.data.DealDto;
import com.ssafy.happyhouse.model.mapper.DataMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DataServiceImpl implements DataService {
	
	private final SqlSession sqlSession;
	
	@Override
	public void addOfficeRentData(DealDto dto) throws Exception {
		sqlSession.getMapper(DataMapper.class).addOfficeRentData(dto);
	}
	@Override
	public void addOfficeDealData(DealDto dto) throws Exception {
		sqlSession.getMapper(DataMapper.class).addOfficeDealData(dto);
	}
	@Override
	public void addHomeRentData(DealDto dto)throws Exception {
		sqlSession.getMapper(DataMapper.class).addHomeRentData(dto);
	}
	@Override
	public void addHomeDealData(DealDto dto) throws Exception {
		sqlSession.getMapper(DataMapper.class).addHomeDealData(dto);
	}
	@Override
	public void addAptRentData(DealDto dto) throws Exception {
		sqlSession.getMapper(DataMapper.class).addAptRentData(dto);
	}
	@Override
	public void addAptDealData(DealDto dto) throws Exception {
		sqlSession.getMapper(DataMapper.class).addAptDealData(dto);
	}
	@Override
	public void addAptListData(AptDto dto) throws Exception {
		sqlSession.getMapper(DataMapper.class).addAptListData(dto);
	}
	@Override
	public List<String> getAptCode() throws Exception {
		return sqlSession.getMapper(DataMapper.class).getAptCode();
	}
	@Override
	public void addAptBasicData(AptBasicDto aptdto)throws Exception {
		sqlSession.getMapper(DataMapper.class).addAptBasicData(aptdto);
	}
	@Override
	public void addAptDetailData(AptDetailDto aptdto) throws Exception {
		sqlSession.getMapper(DataMapper.class).addAptDetailData(aptdto);
	}
}
