package com.example.spring05;

import lombok.Data;

@Data // 자동으로 setter&getter 만들어지도록
public class UserDto {
	private String UserName;
	private String hobby;
	private String gender;
	private String comment;

}
