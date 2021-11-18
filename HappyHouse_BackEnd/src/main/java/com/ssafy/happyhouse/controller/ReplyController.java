package com.ssafy.happyhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.ReplyDto;
import com.ssafy.happyhouse.model.service.ReplyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/reply")
@Api("게시판 답글 컨트롤러  API")
public class ReplyController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ReplyService replyService;
	
	@ApiOperation(value = "게시판 답글작성", notes = "새로운 답글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> replyArticle(@RequestBody @ApiParam(value = "게시글 답글 정보.", required = true) ReplyDto replyDto) throws Exception {
		logger.info("replyArticle - 호출");
		if (replyService.replyArticle(replyDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "게시판 답변보기", notes = "글번호에 해당하는 게시글의 답변을 반환한다.", response = ReplyDto.class)
	@GetMapping("/{articleno}")
	public ResponseEntity<ReplyDto> getReply(@PathVariable("articleno") @ApiParam(value = "얻어올 답변의 글번호.", required = true) int articleno) throws Exception {
		logger.info("getReply - 호출 : " + articleno);
		return new ResponseEntity<ReplyDto>(replyService.getReply(articleno), HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 답변수정", notes = "새로운 게시글 답변을 입력한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateReply(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) ReplyDto replyDto) throws Exception {
		logger.info("updateReply - 호출");
		
		if (replyService.updateReply(replyDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 답변삭제", notes = "글번호에 해당하는 게시글의 답변을 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{articleno}")
	public ResponseEntity<String> deleteReply(@PathVariable("articleno") @ApiParam(value = "삭제할 글의 글번호.", required = true) int articleno) throws Exception {
		logger.info("deleteReply - 호출");
		if (replyService.deleteReply(articleno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}
