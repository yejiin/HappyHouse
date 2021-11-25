package com.ssafy.happyhouse.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "UserDto : 회원정보", description = "회원의 상세 정보를 나타낸다.")
public class UserDto {

	@ApiModelProperty(value = "회원 아이디")
	private String userid;
	@ApiModelProperty(value = "회원 비밀번호")
	private String password;
	@ApiModelProperty(value = "회원 이름")
	private String username;
	@ApiModelProperty(value = "회원 이메일")
	private String email;
	@ApiModelProperty(value = "회원 연락처")
	private String phone;
	@ApiModelProperty(value = "회원 연령대")
	private int ageGroup;
	@ApiModelProperty(value = "회원 선호상권")
	private String favStore;
	@ApiModelProperty(value = "회원 관심지역")
	private String favArea;
	
	public UserDto(int ageGroup, String favStore) {
		this.ageGroup = ageGroup;
		this.favStore = favStore;
	}

}
