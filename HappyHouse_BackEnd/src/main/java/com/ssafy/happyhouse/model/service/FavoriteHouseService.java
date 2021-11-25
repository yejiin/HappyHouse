package com.ssafy.happyhouse.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.model.dto.housemap.FavoriteDto;
import com.ssafy.happyhouse.model.mapper.FavoriteHouseMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FavoriteHouseService {
	
	private final SqlSession sqlSession;
	
	public int favorite(String name, String userid) {
		return sqlSession.getMapper(FavoriteHouseMapper.class).favorite(name, userid);
	}
	
	public boolean addfavorite(String name, String userid, String gugunname, String dong, String jibun) {
		return sqlSession.getMapper(FavoriteHouseMapper.class).addfavorite(name, userid, gugunname, dong, jibun) == 1;
	}
	
	public boolean cancelfavorite(String name, String userid) {
		return sqlSession.getMapper(FavoriteHouseMapper.class).cancelfavorite(name, userid) == 1;
	}

	public List<FavoriteDto> getFavoriteInGugun(String gugunname, String name, String userid) {
		
		List<FavoriteDto> list = sqlSession.getMapper(FavoriteHouseMapper.class).getFavoriteInGugun(gugunname, name, userid);
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setId(i + 1);
			if (list.get(i).getAptCode() != null)
				list.get(i).setComparable(true);
		}
		System.out.println(list);
		
		return list;
	}
	
}
