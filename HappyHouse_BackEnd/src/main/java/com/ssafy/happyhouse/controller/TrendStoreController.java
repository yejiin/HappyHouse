package com.ssafy.happyhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.TrendStoreDto;
import com.ssafy.happyhouse.model.service.UserService;


@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/trendstore")
public class TrendStoreController {

	private static final Logger logger = LoggerFactory.getLogger(TrendStoreController.class);
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{ageGroup}")
	public ResponseEntity<TrendStoreDto> getStoreData(@PathVariable("ageGroup") int ageGroup) throws Exception {
		logger.debug("getStoreData - 호출");
		return new ResponseEntity<TrendStoreDto>(userService.getStoreData(ageGroup), HttpStatus.OK);
	}
	
//	@PutMapping("")
//	public ResponseEntity<String> updateCount(@RequestBody UserDto userDto) throws Exception {
//		logger.debug("updateCount - 호출");
//		userService.updateCount(userDto);
//		return new ResponseEntity<String>(HttpStatus.OK);
//	}
	
}
