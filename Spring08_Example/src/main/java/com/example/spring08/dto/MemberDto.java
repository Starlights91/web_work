package com.example.spring08.dto;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//MyBatis 에서 제공하는 @Alias 어노테이션을 이용해서 MemberDto type 별칭 부여할 수 있다.
//부여된 별칭을 Mapper.xml 문서에서 parameterType resultType 으로 활용할 수 있다. 
@Alias("memberDto")
@Data  // setter, getter 메소드를 만들어주고 toString() 메소드를 오버라이딩해서 필드에 들어있는 정보를 확인할 수 있다. 
@Builder
@AllArgsConstructor 	// 모든 필드값을 전달 받는 생성자 (Builder 를 사용하려면 필수)
@NoArgsConstructor 		// 빈 생성자
public class MemberDto {
	private int num;
	private String name;
	private String addr;
	
	
	
}
