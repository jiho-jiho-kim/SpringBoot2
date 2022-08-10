package com.myweb.basic.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//로그인할 때 사용하는 VO
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVO {

	private String userId;
	private String userPw;
	
	
}
