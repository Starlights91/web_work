package com.example.spring08.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder //빌더 형식으로 필드에 값을 대입한 객체를 얻어낼수 있도록 해준다 (객체생성과 필드에 값 넣기를 1줄 코딩으로 할 수 있다)
@NoArgsConstructor // 빈 생성자 #m 디폴트 생성자를 자동으로 만들어 준다.
@AllArgsConstructor // 모든 필드값을 전달 받는 생성자 (Builder 를 사용하려면 필요함)  #m 필드의 모든값을 전달받아서 저장하는 생성자를 만들어 준다. (모든 인자 생성자)
@Data //setter, getter 메소드 + toString() 메소드를 자동으로 만들어준다.
public class UserDto {
	private long num;
	private String userName; 
	private String password; 
	private String email; 
	private String role; 
	private String profileImage; 
	private String createdAt; 
	private String updatedAt; 
}
