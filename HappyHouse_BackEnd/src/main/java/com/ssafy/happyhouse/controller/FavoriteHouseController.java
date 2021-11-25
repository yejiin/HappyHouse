package com.ssafy.happyhouse.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.service.FavoriteHouseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/favorite")
public class FavoriteHouseController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final FavoriteHouseService favoriteService;

	@GetMapping("/{name}/{userid}")
	public int favorite(@PathVariable("name") String name, @PathVariable("userid") String userid) {
		log.info("관심 여부 조회 : " + name + ", " + userid + "," +favoriteService.favorite(name, userid));
		return favoriteService.favorite(name, userid);
	}
	
	@PostMapping("/{name}/{userid}")
	public ResponseEntity<String> addfavorite(@PathVariable("name") String name, @PathVariable("userid") String userid) {
		if(favoriteService.addfavorite(name, userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@DeleteMapping("/{name}/{userid}")
	public ResponseEntity<String> cancelFavorite(@PathVariable("name") String name, @PathVariable("userid") String userid) {
		if (favoriteService.cancelfavorite(name, userid)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
